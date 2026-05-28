package com.baul.banking_backend.services;

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

    @Autowired
    private AccountDetailsRepo detailsRepo;

    @Autowired
    private CustomerRepo customerRepo;

    public void createAccount(int custId, AccountDetails details) {
        Customer customer = customerRepo
                .findById(custId)
                .orElseThrow(() -> new RuntimeException("account not found"));

            details.setCustomer(customer);
            detailsRepo.save(details);
    }

    public void activateAccount(int accountId, int custId) {
        AccountDetails accountDetails = detailsRepo.
                findByAccountIdAndCustomerCustId(accountId, custId)
                .orElseThrow(() -> new RuntimeException("account not found"));

        accountDetails.setActive(true);
    }

    public void deactivateAccount(int accountId, int custId) {
        AccountDetails accountDetails = detailsRepo.
                findByAccountIdAndCustomerCustId(accountId, custId)
                .orElseThrow(() -> new RuntimeException("account not found"));

        accountDetails.setActive(false);
    }

    public AccountDetails getAccountById(int accountId, int custId) {
        return detailsRepo
                .findByAccountIdAndCustomerCustId(accountId, custId)
                .orElseThrow(()-> new RuntimeException("Account not found"));
    }

    public List<AccountDetails> getAllAccounts(int custId) {
        return detailsRepo
                .findByCustomerCustId(custId);
    }
}
