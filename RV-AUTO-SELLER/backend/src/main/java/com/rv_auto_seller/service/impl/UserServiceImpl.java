package com.rv_auto_seller.service.impl;

import com.rv_auto_seller.dto.request.UpdatedUserRequest;
import com.rv_auto_seller.model.User;
import com.rv_auto_seller.repository.UserRepository;
import com.rv_auto_seller.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User registerUser(User user){
        // TODO validations

        user.setEmail(user.getEmail().toLowerCase().trim());
        user.setUsername(user.getUsername().toLowerCase().trim());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User updateUser(UpdatedUserRequest user) {
        User savedUser = findByUsername(user.getUsername());

        System.out.println("Hello: " + savedUser.getFirstName());
        // Changed
        savedUser.setFirstName(user.getFirstName());
        savedUser.setLastName(user.getLastName());
        savedUser.setTelephone(user.getTelephone());

        System.out.println("Hello: " + savedUser.getFirstName());

        return userRepository.save(savedUser);
    }

    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElse(null);
    }

}
