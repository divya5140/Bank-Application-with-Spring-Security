package org.training.bankapplication.service.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.training.bankapplication.entity.Account;
import org.training.bankapplication.repository.AccountRepository;
import org.training.bankapplication.repository.TransactionRepository;

@ExtendWith(SpringExtension.class)
public class TransactionServiceImplTest {
	
	@InjectMocks
	TransactionServiceImpl transactionServiceImpl;
	
	@Mock
	AccountRepository accountRepository;

	@Mock
	TransactionRepository transactionRepository;
	
	@Test
	void testFundTransfer()
	{
		long fromAccountNumber=123456;
		long toAccountNumber=45678;
		float amount=500;
		
		Account fromAccount=new Account();
		Account toAccount=new Account();
		fromAccount.setAccountId(4);
		fromAccount.setAccountNumber(123456);
		fromAccount.setAccountType("savings");
		
		fromAccount.setBalance(5000);
	//	fromAccount.setCustomer(null);
		
		toAccount.setAccountId(2);
		toAccount.setAccountNumber(45678);
		toAccount.setAccountType("savings");
		
		toAccount.setBalance(2000);
	//	toAccount.setCustomer(null);
		
	
		Mockito.when(accountRepository.findAccountByAccountNumber(fromAccountNumber)).thenReturn(fromAccount);
		Mockito.when(accountRepository.findAccountByAccountNumber(toAccountNumber)).thenReturn(toAccount);
		
		transactionServiceImpl.fundTransfer(fromAccountNumber, toAccountNumber, amount);
		
		assertEquals(4500, fromAccount.getBalance());
		assertEquals(2500, toAccount.getBalance());
	}
}
