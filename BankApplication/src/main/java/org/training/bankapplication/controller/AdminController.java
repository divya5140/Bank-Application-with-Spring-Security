package org.training.bankapplication.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.training.bankapplication.dto.ResponseDto;
import org.training.bankapplication.entity.Customer;
import org.training.bankapplication.entity.Transaction;
import org.training.bankapplication.service.CustomerService;



@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	CustomerService customerService;
	
	


    @GetMapping("/findbyid")
	public ResponseEntity<Customer> getCustomer(@RequestParam long customerId)
	{
	return new ResponseEntity<Customer>(customerService.getById(customerId),HttpStatus.CREATED);

	}
    
    
    @PutMapping("/updateCustomer")
   	public ResponseEntity<ResponseDto> updateCustomer(@RequestBody Customer customer)
   	{
    	
    	
   	return new ResponseEntity<ResponseDto>(customerService.updateById(customer),HttpStatus.CREATED);

   	}

    @DeleteMapping("/deletebyid")
	public ResponseEntity<ResponseDto> DeleteCustomer(@RequestParam long customerId)
	{
	return new ResponseEntity<ResponseDto>(customerService.deleteById(customerId),HttpStatus.CREATED);

	}
    
    @GetMapping("/{date}")
    public List<Transaction> getTransactionsByDate(@RequestParam("YYYY-MM-DD") String date) {
        return customerService.getTransactionsByDate(date);
    }








}
