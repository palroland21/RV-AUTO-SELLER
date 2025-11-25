package com.rv_auto_seller.controller;

import com.rv_auto_seller.dto.request.LoginRequest;
import com.rv_auto_seller.dto.response.LoginResponse;
import com.rv_auto_seller.dto.response.RegisterResponse;
import com.rv_auto_seller.dto.response.UserResponse;
import com.rv_auto_seller.model.Listing;
import com.rv_auto_seller.model.User;
import com.rv_auto_seller.security.JwtUtil;
import com.rv_auto_seller.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/auth")
public class AuthController {

   private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
       this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
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

      System.out.println("Registration succesfull!");

      return ResponseEntity.ok(response);
  }

  @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
        User user = userService.findByUsername(request.getUsername().toLowerCase());

        if(user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            // TODO validation for jwtUtil
            String token = jwtUtil.generateToken(user.getUsername(), user.getPassword());
            System.out.println("User " + user.getUsername() + " logged succefully!");

            return ResponseEntity.ok(new LoginResponse(token));
        }
        return ResponseEntity.status(401).body("Invalid credentials!");
  }

  @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<User> users = userService.findAll();

        List<UserResponse> userResponseList = new ArrayList<>();

        for(User u: users){
            userResponseList.add(new UserResponse(
                    u.getId(),
                    u.getFirstName(),
                    u.getLastName(),
                    u.getUsername(),
                    u.getTelephone(),
                    u.getEmail(),
                    u.getPassword(),
                    u.getRole(),
                    u.getListings().stream()
                            .map(Listing::getId)
                            .toList()
            ));
        }

      return ResponseEntity.ok(userResponseList);
  }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Missing or invalid token");
        }

        // extragem tokenul (fara "Bearer ")
        String token = authHeader.substring(7);

        // extragem username din token
        String username = jwtUtil.extractUsername(token);

        if (username == null) {
            return ResponseEntity.status(401).body("Invalid token");
        }

        User user = userService.findByUsername(username);

        if (user == null) {
            return ResponseEntity.status(404).body("User not found");
        }

        UserResponse response = new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getTelephone(),
                user.getEmail(),
                user.getPassword(),
                user.getRole(),
                user.getListings().stream().map(Listing::getId).toList()
        );

        return ResponseEntity.ok(response);
    }


}
