package org.training.bankapplication.service.implementation;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.training.bankapplication.dto.ResponseDto;
import org.training.bankapplication.entity.Customer;
import org.training.bankapplication.entity.Transaction;
import org.training.bankapplication.exception.CustomerIdNotFoundException;
import org.training.bankapplication.repository.AccountRepository;
import org.training.bankapplication.repository.CustomerRepository;
import org.training.bankapplication.repository.TransactionRepository;
import org.training.bankapplication.service.CustomerService;

@Service(value = "customerService")
public class CustomerServiceImplementation implements UserDetailsService, CustomerService {

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	TransactionRepository transactionRepository;

	@Override
	public ResponseDto addCustomer(Customer customer) {

		customer.setPassword(bcryptEncoder.encode(customer.getPassword()));

		customer.getAccount().stream().forEach(account -> account
				.setAccountNumber((long) (Math.floor(Math.random() * 9000000000000l) + 1000000000000l)));

		customerRepository.save(customer);

		return new ResponseDto("Customer Details added and Account created");

	}

	public Customer getById(long customerId) {
		return customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerIdNotFoundException("No Customer present with id=" + customerId));
	}

	public ResponseDto updateById(Customer customer) {
		Optional<Customer> customer2 = customerRepository.findById(customer.getCustomerId());
		if (customer2.isEmpty()) {
			throw new CustomerIdNotFoundException("No Customer present with id=" + customer.getCustomerId());
		}
		customerRepository.save(customer);
		return new ResponseDto("Customer Details updated successfully");

	}

	public ResponseDto deleteById(long customerId) {
		Optional<Customer> customer = customerRepository.findById(customerId);
		if (customer.isEmpty()) {
			return new ResponseDto("Customer with customerId: " + customerId + " not found");
		}
		customerRepository.deleteById(customerId);

		return new ResponseDto("Customer deleted successfully");
	}

	@Override
	public Customer findOne(String username) {
		return customerRepository.findByUsername(username);
	}

	public List<Transaction> getTransactionsByDate(String date) {
		return transactionRepository.findByTransactionDateTime(Date.valueOf(date));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer user = customerRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getAuthority(user));
	}

	public List<SimpleGrantedAuthority> getAuthority(Customer customer) {
		return Arrays.asList(new SimpleGrantedAuthority(customer.getRole().name()));
	}

}
