package com.rv_auto_seller.dto.response;

import com.rv_auto_seller.model.enums.Role;

import java.util.List;

public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String telephone;
    private String email;
    private String password;
    private Role role;
    private List<Long> listingIds;

    public UserResponse(Long id, String firstName, String lastName, String username, String telephone, String email, String password, Role role, List<Long> listingIds) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        this.role = role;
        this.listingIds = listingIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public List<Long> getListingIds() {
        return listingIds;
    }

    public void setListingIds(List<Long> listingIds) {
        this.listingIds = listingIds;
    }
}
