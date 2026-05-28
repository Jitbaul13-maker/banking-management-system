package com.baul.banking_backend.repos;

import com.baul.banking_backend.models.DepositDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepositDetailsRepo extends JpaRepository<DepositDetails, Integer> {
    Optional<DepositDetails> findByDepositIdAndCustomerCustId(int depositId, int custId);

    List<DepositDetails> findByCustomerCustId(int custId);
}
