package org.training.bankapplication.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity

public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long accountId;
	
	@NotNull(message="Account number should not be empty")
	@Column(unique = true)
	private long accountNumber;

	@NotNull(message="Account type is required")
	@Pattern(regexp = "^(?:current|savings|other)$",message = "please enter valid account type(savings,current or other)")
	private String accountType;
	
	@NotNull(message="Minimum balance  is required")
	@Min(value = 500, message = "Minimum Initial deposit should be Rs.500")
	private float balance;

	

}
