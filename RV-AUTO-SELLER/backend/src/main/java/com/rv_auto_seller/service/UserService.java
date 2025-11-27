package com.rv_auto_seller.service;

import com.rv_auto_seller.dto.request.UpdatedUserRequest;
import com.rv_auto_seller.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findByEmail(String email);
    User findByUsername(String username);
    User save(User user);
    User registerUser(User user);
    User updateUser(UpdatedUserRequest user);
}
