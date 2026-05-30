package com.baul.banking_backend.services;

import com.baul.banking_backend.DTOs.CreateDepositDTO;
import com.baul.banking_backend.exception.ResourceNotfoundException;
import com.baul.banking_backend.models.User;
import com.baul.banking_backend.models.DepositDetails;
import com.baul.banking_backend.repos.CustomerRepo;
import com.baul.banking_backend.repos.DepositDetailsRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DepositService {

    @Autowired
    private DepositDetailsRepo detailsRepo;

    @Autowired
    private CustomerRepo customerRepo;

    public void deleteDeposit(int custId, int depositId) {
        DepositDetails deposit = detailsRepo
                .findByDepositIdAndCustomerCustId(depositId, custId)
                .orElseThrow(() -> new ResourceNotfoundException("Deposit not found"));

        detailsRepo.delete(deposit);
    }

    public DepositDetails getDepositById(int custId, int depositId) {
        return detailsRepo
                .findByDepositIdAndCustomerCustId(depositId, custId)
                .orElseThrow(() -> new ResourceNotfoundException("No account found!"));
    }

    public List<DepositDetails> getAllDeposit(int custId) {
        return detailsRepo.findByCustomerCustId(custId);
    }

    public DepositDetails createDeposit(int custId, CreateDepositDTO deposit) {
        User customer = customerRepo.findById(custId)
                .orElseThrow(() -> new ResourceNotfoundException("Customer not found"));

        DepositDetails depositDetails = new DepositDetails();

        depositDetails.setCustomer(customer);
        depositDetails.setMaturityDate(deposit.getIssueDate().plusMonths(deposit.getTenureMonths()));
        depositDetails.setDepositType(deposit.getDepositType());
        depositDetails.setDepositValue(deposit.getDepositValue());
        depositDetails.setInterest(deposit.getInterest());
        depositDetails.setIssueDate(deposit.getIssueDate());

        return detailsRepo.save(depositDetails);
    }
}
