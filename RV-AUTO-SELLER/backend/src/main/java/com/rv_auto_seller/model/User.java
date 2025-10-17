package com.rv_auto_seller.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rv_auto_seller.model.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "FIRSTNAME", nullable = false, length = 30)
    private String firstName;

    @Column(name = "LASTNAME", nullable = false, length = 30)
    private String lastName;

    @Column(name = "USERNAME", nullable = false, length = 20, unique = true)
    private String username;

    @Column(name = "TELEPHONE", nullable = false, length = 10, unique = true)
    private String telephone;

    @Column(name = "EMAIL", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "PASSWORD", nullable = false, length = 200)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", nullable = false)
    private Role role = Role.USER;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Listing> listings;

    @OneToMany(mappedBy = "toUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Feedback> receivedFeedbacks;

    @OneToMany(mappedBy = "fromUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Feedback> sentFeedbacks;

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

    public List<Listing> getListings() {
        return listings;
    }

    public void setListings(List<Listing> listings) {
        this.listings = listings;
    }

    public List<Feedback> getReceivedFeedbacks() {
        return receivedFeedbacks;
    }

    public void setReceivedFeedbacks(List<Feedback> receivedFeedbacks) {
        this.receivedFeedbacks = receivedFeedbacks;
    }

    public List<Feedback> getSentFeedbacks() {
        return sentFeedbacks;
    }

    public void setSentFeedbacks(List<Feedback> sentFeedbacks) {
        this.sentFeedbacks = sentFeedbacks;
    }
}
