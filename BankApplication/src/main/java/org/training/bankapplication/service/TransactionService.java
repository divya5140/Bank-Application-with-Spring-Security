package org.training.bankapplication.service;

import java.util.List;

import org.training.bankapplication.dto.ResponseDto;
import org.training.bankapplication.entity.Transaction;

public interface TransactionService{


	ResponseDto fundTransfer(long fromAccNo, long toAccNo, float amount);

	List<Transaction> getStatement(long accountNumber, String fromDate, String toDate);
	
	

}
