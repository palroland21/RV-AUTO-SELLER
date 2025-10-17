package com.rv_auto_seller.controller;

import com.rv_auto_seller.dto.request.LoginRequest;
import com.rv_auto_seller.dto.response.LoginResponse;
import com.rv_auto_seller.dto.response.RegisterResponse;
import com.rv_auto_seller.model.User;
import com.rv_auto_seller.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

   private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
       this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

   @PostMapping("/register")
   public ResponseEntity<RegisterResponse> registerUser(@RequestBody User user) {
      User savedUser = userService.registerUser(user);
      RegisterResponse response = new RegisterResponse(
              savedUser.getId(),
              savedUser.getFirstName(),
              savedUser.getLastName(),
              savedUser.getUsername(),
              savedUser.getEmail()
      );

      System.out.println("Registration succeful!");

      return ResponseEntity.ok(response);
  }

  @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
        User user = userService.findByUsername(request.getUsername().toLowerCase());

        if(user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            // TODO validation for jwtUtil

            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(401).body("Invalid credentials!");
  }

}
