package com.baul.banking_backend.services;

import com.baul.banking_backend.DTOs.CreateCardDTO;
import com.baul.banking_backend.exception.ResourceNotfoundException;
import com.baul.banking_backend.models.CardDetails;
import com.baul.banking_backend.models.Customer;
import com.baul.banking_backend.repos.CardDetailsRepo;
import com.baul.banking_backend.repos.CustomerRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CardService {

    @Autowired
    private  CardDetailsRepo cardDetailsRepo;

    @Autowired
    private CustomerRepo customerRepo;

    public CardDetails createCard(int custId, CreateCardDTO card) {
        Customer customer = customerRepo
                .findById(custId)
                .orElseThrow(()-> new ResourceNotfoundException("No valid customer found"));

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

    public List<CardDetails> getAllCards(int custId) {
        return cardDetailsRepo.findByCustomerCustId(custId);
    }

    public CardDetails getCardsById(int custId, int cardId) {
        return cardDetailsRepo
                .findByCardIdAndCustomerCustId(cardId, custId)
                .orElseThrow(() -> new ResourceNotfoundException("No valid card(s) to be deleted"));
    }
}
