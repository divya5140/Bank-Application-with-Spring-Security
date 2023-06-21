package org.training.bankapplication.service.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.training.bankapplication.entity.Account;
import org.training.bankapplication.entity.Customer;
import org.training.bankapplication.repository.AccountRepository;
import org.training.bankapplication.repository.CustomerRepository;

@ExtendWith(SpringExtension.class)
public class CustomerServiceImplementationTest {
	
	@InjectMocks
	CustomerServiceImplementation customerServiceImplementation;
	
	@Mock
	AccountRepository accountRepository;
	
	@Mock
	CustomerRepository customerRepository;
	
	@Test
	void testAddCustomer()
	{
		 Account account=new Account();
		 account.setAccountId(10);
		 account.setAccountNumber(67890096);
		 account.setAccountType("savings");
		 account.setBalance(15000);
		 
		 Customer customer=new Customer();
		 customer.setAge(21);
		 customer.setContactNumber("7483060926");
		 customer.setCustomerId(10);
		 customer.setEmailId("divya@gmail.com");
		 customer.setFirstName("divya");
		 customer.setLastName("s p");
		 customer.setPanNumber("DFGYH7890H");
		 List<Account> accounts=Arrays.asList(account);
		 customer.setAccount(accounts);
		Mockito.when(customerRepository.save(customer)).thenReturn(customer);
		assertEquals("Customer Details added and Account created", customerServiceImplementation.addCustomer(customer).getMessage());

		 
	}
}
