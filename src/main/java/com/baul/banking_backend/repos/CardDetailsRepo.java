package com.baul.banking_backend.repos;

import com.baul.banking_backend.models.CardDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardDetailsRepo extends JpaRepository<CardDetails, Integer> {
}
