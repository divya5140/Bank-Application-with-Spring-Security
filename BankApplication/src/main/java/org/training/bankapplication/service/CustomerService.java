package org.training.bankapplication.service;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.training.bankapplication.dto.ResponseDto;
import org.training.bankapplication.entity.Customer;
import org.training.bankapplication.entity.Transaction;

public interface CustomerService {

	ResponseDto addCustomer(Customer customer);

	Customer getById(long customerId);

	ResponseDto updateById(Customer customer);

	ResponseDto deleteById(long customerId);
	
	Customer findOne(String username);
	
	UserDetails loadUserByUsername(String username);
	
	List<Transaction> getTransactionsByDate(String date);
	
	List<SimpleGrantedAuthority> getAuthority(Customer customer);

}
