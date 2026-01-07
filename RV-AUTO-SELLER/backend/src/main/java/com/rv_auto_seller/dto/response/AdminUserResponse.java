package com.rv_auto_seller.dto.response;

import com.rv_auto_seller.model.Listing;
import com.rv_auto_seller.model.User;
import com.rv_auto_seller.model.enums.Role;

import java.util.ArrayList;
import java.util.List;

public class AdminUserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String telephone;
    private String email;
    private Role role;
    private List<Long> listingIds = new ArrayList<>();

    public AdminUserResponse() {}

    public AdminUserResponse(User u) {
        this.id = u.getId();
        this.firstName = u.getFirstName();
        this.lastName = u.getLastName();
        this.username = u.getUsername();
        this.telephone = u.getTelephone();
        this.email = u.getEmail();
        this.role = u.getRole();
        if (u.getListings() != null) {
            this.listingIds = u.getListings().stream().map(Listing::getId).toList();
        }
    }

    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getUsername() { return username; }
    public String getTelephone() { return telephone; }
    public String getEmail() { return email; }
    public Role getRole() { return role; }
    public List<Long> getListingIds() { return listingIds; }

    public void setId(Long id) { this.id = id; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setUsername(String username) { this.username = username; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public void setEmail(String email) { this.email = email; }
    public void setRole(Role role) { this.role = role; }
    public void setListingIds(List<Long> listingIds) { this.listingIds = listingIds; }
}
