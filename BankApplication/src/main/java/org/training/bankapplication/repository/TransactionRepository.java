package org.training.bankapplication.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.training.bankapplication.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	List<Transaction> findAllByTransactionDateTimeBetweenAndFromAccountNumberOrToAccountNumber(Date startDate,Date endDate, long fromAccountNumber, long toAccountNumber);

	List<Transaction> findByTransactionDateTime(Date transactionDateTime);

	

}
