package com.baul.banking_backend.repos;

import com.baul.banking_backend.models.DepositDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositDetailsRepo extends JpaRepository<DepositDetails, Integer> {
}
