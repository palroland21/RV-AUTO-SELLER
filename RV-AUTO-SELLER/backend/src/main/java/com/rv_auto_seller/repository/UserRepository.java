package com.rv_auto_seller.repository;

import com.rv_auto_seller.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
