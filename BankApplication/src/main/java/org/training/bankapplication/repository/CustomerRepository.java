package org.training.bankapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.training.bankapplication.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByUsername(String username);
}
