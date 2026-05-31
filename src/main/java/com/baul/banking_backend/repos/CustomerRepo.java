package com.baul.banking_backend.repos;

import com.baul.banking_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<User, Integer> {
   Optional<User> findByCustName(String username);
}
