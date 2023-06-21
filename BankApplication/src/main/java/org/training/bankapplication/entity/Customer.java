package org.training.bankapplication.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;



import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long customerId;

	@NotBlank(message = "Firstname must not be empty")
	private String firstName;

	@NotBlank(message = "Lastname must not be empty")
	private String lastName;

	@NotNull(message = "Age is required")
	@Min(value = 18, message = "Age must be greater than or equal to 18")
	private int age;

	@NotNull(message = "pan number is required")
	@Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$", message = "It should follow this pattern(ABGYH7890H)")
	private String panNumber;

	@NotNull(message = "email is required")
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "Email should follow this pattern([a-z0-9._%+-]+@[a-z0-9.-]+\\\\.[a-z]{2,3)})")
	@Column(unique = true)
	private String emailId;

	@NotNull(message = "contact number is required")
	@Pattern(regexp = "^[6-9]{1}[0-9]{9}$", message = "Contact Number  should follow this pattern(^[6-9]{1}[0-9]{9}$)")
	private String contactNumber;

	@Column
	private String username;
	
	@Column
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;


	
	@Valid
	@OneToMany(cascade = CascadeType.ALL)
	private List<Account> account;

}
