package com.baul.banking_backend.repos;

import com.baul.banking_backend.models.CardDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardDetailsRepo extends JpaRepository<CardDetails, Integer> {
    Optional<CardDetails> findByCardIdAndCustomerCustId(int cardId, int custId);

    List<CardDetails> findByCustomerCustId(int custId);
}
