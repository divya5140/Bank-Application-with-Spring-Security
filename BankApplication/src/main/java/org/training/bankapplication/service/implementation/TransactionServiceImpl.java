package org.training.bankapplication.service.implementation;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.training.bankapplication.dto.ResponseDto;
import org.training.bankapplication.entity.Account;
import org.training.bankapplication.entity.Transaction;
import org.training.bankapplication.exception.AccountNotFoundException;
import org.training.bankapplication.exception.NoTransactionFoundException;
import org.training.bankapplication.repository.AccountRepository;
import org.training.bankapplication.repository.TransactionRepository;
import org.training.bankapplication.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	TransactionRepository transactionRepository;

	@Override
	public ResponseDto fundTransfer(long fromAccountNumber, long toAccountNumber, float amount) {

		Account account1 = new Account();
		Account account2 = new Account();

		account1 = accountRepository.findAccountByAccountNumber(fromAccountNumber);
		account2 = accountRepository.findAccountByAccountNumber(toAccountNumber);
		if (account1.getBalance() <= 500) {
			return new ResponseDto("Minimum Account balance should be 500");
		}

		if (account1.getBalance() - amount > 0) {
			account1.setBalance(account1.getBalance() - amount);
			account2.setBalance(account2.getBalance() + amount);

			accountRepository.save(account1);
			accountRepository.save(account2);

			Transaction transaction = new Transaction();
			transaction.setFromAccountNumber(fromAccountNumber);
			transaction.setToAccountNumber(toAccountNumber);
			transaction.setAmount(amount);

			transactionRepository.save(transaction);

			return new ResponseDto("Fund transfer was successfull");

		}
		return new ResponseDto("not transferred");

	}

	public List<Transaction> getStatement(long accountNumber, String fromDate, String toDate) {
		Account account1 = accountRepository.findAccountByAccountNumber(accountNumber);
		if (account1 == null) {
			throw new AccountNotFoundException("No such Account exists");
		}
		List<Transaction> transaction = transactionRepository
				.findAllByTransactionDateTimeBetweenAndFromAccountNumberOrToAccountNumber(Date.valueOf(fromDate),
						Date.valueOf(toDate), accountNumber, accountNumber);
		if (transaction.isEmpty()) {
			throw new NoTransactionFoundException("No Transactions found on this accountnumber:" + accountNumber);
		}
		return transactionRepository.findAllByTransactionDateTimeBetweenAndFromAccountNumberOrToAccountNumber(
				Date.valueOf(fromDate), Date.valueOf(toDate), accountNumber, accountNumber);
	}

	

}
