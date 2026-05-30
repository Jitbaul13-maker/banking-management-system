package com.baul.banking_backend.services;

import com.baul.banking_backend.DTOs.CreateAccountDTO;
import com.baul.banking_backend.exception.ResourceNotfoundException;
import com.baul.banking_backend.models.AccountDetails;
import com.baul.banking_backend.models.Customer;
import com.baul.banking_backend.repos.AccountDetailsRepo;
import com.baul.banking_backend.repos.CustomerRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AccountService {

    private final AccountDetailsRepo detailsRepo;
    private final CustomerRepo customerRepo;

    public AccountService(AccountDetailsRepo detailsRepo, CustomerRepo customerRepo) {
        this.detailsRepo = detailsRepo;
        this.customerRepo = customerRepo;
    }

    public void createAccount(int custId, CreateAccountDTO account) {
        Customer customer = customerRepo
                .findById(custId)
                .orElseThrow(() -> new ResourceNotfoundException("Account not found"));

        AccountDetails accountDetails = new AccountDetails();

        accountDetails.setAccountType(account.getAccountType());
        accountDetails.setCustomer(customer);
        accountDetails.setActive(true);

        detailsRepo.save(accountDetails);
    }

    public void activateAccount(int accountId, int custId) {
        AccountDetails accountDetails = detailsRepo.
                findByAccountIdAndCustomerCustId(accountId, custId)
                .orElseThrow(() -> new ResourceNotfoundException("Account not found"));

        accountDetails.setActive(true);
    }

    public void deactivateAccount(int accountId, int custId) {
        AccountDetails accountDetails = detailsRepo.
                findByAccountIdAndCustomerCustId(accountId, custId)
                .orElseThrow(() -> new ResourceNotfoundException("Account not found"));

        accountDetails.setActive(false);
    }

    public AccountDetails getAccountById(int accountId, int custId) {
        return detailsRepo
                .findByAccountIdAndCustomerCustId(accountId, custId)
                .orElseThrow(()-> new ResourceNotfoundException("Account not found"));
    }

    public List<AccountDetails> getAllAccounts(int custId) {
        return detailsRepo
                .findByCustomerCustId(custId);
    }
}
