package org.training.bankapplication.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.training.bankapplication.dto.ResponseDto;
import org.training.bankapplication.entity.Customer;
import org.training.bankapplication.service.CustomerService;

@RestController
public class RegistrationController {
	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	
	@PostMapping("/addCustomer")
	public   ResponseEntity<ResponseDto> addCustomer(@RequestBody @Valid Customer customer) {

		return new ResponseEntity<ResponseDto>(customerService.addCustomer(customer),HttpStatus.CREATED);
	}
	
	
	
}
