package com.rv_auto_seller.dto.request;

import com.rv_auto_seller.model.enums.Role;

public class AdminUserUpdateRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String telephone;
    private String email;

    private String newPassword;
    private Role role;

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getUsername() { return username; }
    public String getTelephone() { return telephone; }
    public String getEmail() { return email; }
    public String getNewPassword() { return newPassword; }
    public Role getRole() { return role; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setUsername(String username) { this.username = username; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public void setEmail(String email) { this.email = email; }
    public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
    public void setRole(Role role) { this.role = role; }
}
