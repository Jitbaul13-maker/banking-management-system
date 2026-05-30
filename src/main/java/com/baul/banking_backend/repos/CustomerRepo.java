package com.baul.banking_backend.repos;

import com.baul.banking_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<User, Integer> {
    User getCustomerByCustName(String username);
}
