package com.rv_auto_seller.repository;

import com.rv_auto_seller.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String email);
    Optional<User> findByEmail(String email);
}
