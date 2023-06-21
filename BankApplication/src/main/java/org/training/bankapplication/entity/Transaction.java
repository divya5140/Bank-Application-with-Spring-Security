package org.training.bankapplication.entity;



import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;






@Entity
@Getter
@Setter
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long transcationId;


	private long fromAccountNumber;

	private long toAccountNumber;

	private float amount;

	@CreationTimestamp
	private Date transactionDateTime;

}
