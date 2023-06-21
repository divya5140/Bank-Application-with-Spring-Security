package org.training.bankapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.training.bankapplication.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	Account findAccountByAccountNumber(long accountNumber);

}
