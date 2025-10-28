package com.rv_auto_seller.dto.request;

import com.rv_auto_seller.model.enums.Role;
import jakarta.validation.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class RegisterRequest {
    @NotBlank
    @Size(max=30)
    private String firstName;

    @NotBlank @Size(max=30)
    private String lastName;

    @NotBlank @Size(max=20)
    private String username;

    @NotBlank @Size(min=10, max=10)
    private String telephone;

    @Email
    @NotBlank @Size(max=50)
    private String email;

    @NotBlank @Size(min=6, max=200)
    private String password;

    private Role role = Role.USER;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

