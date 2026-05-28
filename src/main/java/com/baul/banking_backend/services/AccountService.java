package com.baul.banking_backend.services;

import com.baul.banking_backend.models.AccountDetails;
import com.baul.banking_backend.models.Customer;
import com.baul.banking_backend.models.DepositDetails;
import com.baul.banking_backend.repos.AccountDetailsRepo;
import com.baul.banking_backend.repos.CustomerRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
                .orElse(null);

        if (customer != null) {
            details.setCustomer(customer);
            detailsRepo.save(details);
        }
    }

    public void deleteAccount(int custId, int accountId) {
        AccountDetails accountDetails = detailsRepo
                .findByAccountIdAndCustomerCustId(accountId, custId)
                .orElseThrow(() -> new RuntimeException("Deposit not found"));

        detailsRepo.delete(accountDetails);
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
