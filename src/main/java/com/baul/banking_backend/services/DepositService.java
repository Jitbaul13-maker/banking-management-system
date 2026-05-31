package com.baul.banking_backend.services;

import com.baul.banking_backend.DTOs.CreateDepositDTO;
import com.baul.banking_backend.exception.ResourceNotfoundException;
import com.baul.banking_backend.models.User;
import com.baul.banking_backend.models.DepositDetails;
import com.baul.banking_backend.repos.CustomerRepo;
import com.baul.banking_backend.repos.DepositDetailsRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DepositService {

    private final DepositDetailsRepo detailsRepo;
    private final CustomerRepo customerRepo;

    public DepositService(DepositDetailsRepo detailsRepo, CustomerRepo customerRepo) {
        this.detailsRepo = detailsRepo;
        this.customerRepo = customerRepo;
    }
    
    public int getUserId(){
        MyUserPrinciple user = (MyUserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUserId();
    }

    public DepositDetails getDepositById(int depositId) {
        int userId = getUserId();
        return detailsRepo
                .findByDepositIdAndCustomerCustId(depositId, userId)
                .orElseThrow(() -> new ResourceNotfoundException("No account found!"));
    }

    public List<DepositDetails> getAllDeposit() {
        int userId = getUserId();
        return detailsRepo.findByCustomerCustId(userId);
    }

    public DepositDetails createDeposit(CreateDepositDTO deposit) {

        int userId = getUserId();
        User customer = customerRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotfoundException("Customer not found"));

        DepositDetails depositDetails = new DepositDetails();

        depositDetails.setCustomer(customer);
        depositDetails.setMaturityDate(deposit.getIssueDate().plusMonths(deposit.getTenureMonths()));
        depositDetails.setDepositType(deposit.getDepositType());
        depositDetails.setDepositValue(deposit.getDepositValue());
        depositDetails.setInterest(deposit.getInterest());
        depositDetails.setIssueDate(deposit.getIssueDate());

        depositDetails.setActive(true);

        return detailsRepo.save(depositDetails);
    }

    public void activateDeposit(int custId, int depositId) {
        DepositDetails deposit = detailsRepo.findByDepositIdAndCustomerCustId(depositId, custId)
                .orElseThrow(() -> new ResourceNotfoundException("No valid deposit found!"));

        deposit.setActive(true);
    }

    public void deactivateDeposit(int custId, int depositId) {
        DepositDetails deposit = detailsRepo.findByDepositIdAndCustomerCustId(depositId, custId)
                .orElseThrow(() -> new ResourceNotfoundException("No valid deposit found!"));

        deposit.setActive(true);
    }
}
