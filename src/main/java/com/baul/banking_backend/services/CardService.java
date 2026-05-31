package com.baul.banking_backend.services;

import com.baul.banking_backend.DTOs.CreateCardDTO;
import com.baul.banking_backend.exception.ResourceNotfoundException;
import com.baul.banking_backend.models.CardDetails;
import com.baul.banking_backend.models.User;
import com.baul.banking_backend.repos.CardDetailsRepo;
import com.baul.banking_backend.repos.CustomerRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CardService {

    private final CardDetailsRepo cardDetailsRepo;
    private final CustomerRepo customerRepo;

    public CardService(CardDetailsRepo cardDetailsRepo, CustomerRepo customerRepo) {
        this.cardDetailsRepo = cardDetailsRepo;
        this.customerRepo = customerRepo;
    }

    public int getUserId(){
        MyUserPrinciple user = (MyUserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUserId();
    }

    public CardDetails createCard(CreateCardDTO card) {

        int userId = getUserId();
        User customer = customerRepo
                .findById(userId)
                .orElseThrow(() -> new ResourceNotfoundException("No valid customer found"));

        CardDetails cardDetails = new CardDetails();

        cardDetails.setExpiryDate(card.getIssueDate().plusMonths(card.getTenure()));
        cardDetails.setCustomer(customer);
        cardDetails.setActive(true);

        return cardDetailsRepo.save(cardDetails);
    }

    public void activateCard(int custId, int cardId) {
        CardDetails cardDetails = cardDetailsRepo
                .findByCardIdAndCustomerCustId(cardId, custId)
                .orElseThrow(() -> new ResourceNotfoundException("No valid card(s) to be deleted"));

        cardDetails.setActive(Boolean.TRUE);
    }

    public void deActivateCard(int custId, int cardId) {
        CardDetails cardDetails = cardDetailsRepo
                .findByCardIdAndCustomerCustId(cardId, custId)
                .orElseThrow(() -> new ResourceNotfoundException("No valid card(s) to be deleted"));

        cardDetails.setActive(Boolean.FALSE);
    }

    public List<CardDetails> getAllCards() {
        int userId = getUserId();
        return cardDetailsRepo.findByCustomerCustId(userId);
    }

    public CardDetails getCardsById(int cardId) {
        int userId = getUserId();
        return cardDetailsRepo
                .findByCardIdAndCustomerCustId(cardId, userId)
                .orElseThrow(() -> new ResourceNotfoundException("No valid card(s) to be deleted"));
    }
}
