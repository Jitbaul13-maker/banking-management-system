package com.baul.banking_backend.repos;

import com.baul.banking_backend.models.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountDetailsRepo extends JpaRepository<AccountDetails, Integer> {

    List<AccountDetails> findByCustomerCustId(int custId);

    Optional<AccountDetails> findByAccountIdAndCustomerCustId(int accountId, int custId);

    Optional<AccountDetails> findByCustomerCustNameAndAccountId(String userName, int accountId);

    Optional<AccountDetails> findByCustomerCustIdAndAccountId(int userId, int accountId);
}
