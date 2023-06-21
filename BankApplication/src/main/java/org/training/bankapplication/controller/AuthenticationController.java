package org.training.bankapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.training.bankapplication.config.JwtTokenUtil;
import org.training.bankapplication.entity.ApiResponse;
import org.training.bankapplication.entity.AuthToken;
import org.training.bankapplication.entity.Customer;
import org.training.bankapplication.entity.LoginUser;
import org.training.bankapplication.service.CustomerService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/generate-token", method = RequestMethod.POST)
	public ApiResponse<AuthToken> register(@RequestBody LoginUser loginUser)
			throws AuthenticationException {
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
		final Customer user = customerService.findOne(loginUser.getUsername());
		
		String token = jwtTokenUtil.generateToken(user, customerService.getAuthority(user));
				
		return new ApiResponse<>(200, "success", new AuthToken(token, user.getUsername()));
	}
		

}
