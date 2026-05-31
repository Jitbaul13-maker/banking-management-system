package com.baul.banking_backend.controllers;

import com.baul.banking_backend.DTOs.CreateCardDTO;
import com.baul.banking_backend.models.CardDetails;
import com.baul.banking_backend.services.CardService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardController {

    private final CardService service;

    public CardController(CardService service) {
        this.service = service;
    }

    @PostMapping("/customers/me/cards")
    public ResponseEntity<CardDetails> createCard(@Valid @RequestBody CreateCardDTO card){
        CardDetails cardDetails = service.createCard(card);
        return ResponseEntity.status(HttpStatus.CREATED).body(cardDetails);
    }

    @PatchMapping("/admin/customers/{custId}/cards/{cardId}/activate")
    public ResponseEntity<?> activateCard(@PathVariable("custId") int custId,
                                          @PathVariable("cardId") int cardId){
        service.activateCard(custId, cardId);
        return ResponseEntity.ok("Activated Successfully");
    }

    @PatchMapping("/admin/customers/{custId}/cards/{cardId}/deactivate")
    public ResponseEntity<?> deactivateCard(@PathVariable("custId") int custId,
                                            @PathVariable("cardId") int cardId){
        service.deActivateCard(custId, cardId);
        return ResponseEntity.ok("Deactivated Successfully");
    }

    @GetMapping("customers/me/cards")
    public ResponseEntity<List<CardDetails>> getAllCards(){
        List<CardDetails> cardDetails = service.getAllCards();
        return  ResponseEntity.ok(cardDetails);
    }

    @GetMapping("/customers/me/cards/{cardId}")
    public ResponseEntity<CardDetails> getCardsById(@PathVariable("cardId") int cardId){
        CardDetails cardDetails = service.getCardsById(cardId);
        return  ResponseEntity.ok(cardDetails);
    }

}
