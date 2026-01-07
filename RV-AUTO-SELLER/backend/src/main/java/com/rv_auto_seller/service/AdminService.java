package com.rv_auto_seller.service;

import com.rv_auto_seller.dto.request.AdminRoleUpdateRequest;
import com.rv_auto_seller.dto.request.AdminUserUpdateRequest;
import com.rv_auto_seller.dto.response.AdminStatsResponse;
import com.rv_auto_seller.model.Appointment;
import com.rv_auto_seller.model.Feedback;
import com.rv_auto_seller.model.Listing;
import com.rv_auto_seller.model.User;

import java.util.List;

public interface AdminService {

    // Users
    List<User> getAllUsers();
    User getUserById(Long id);
    User updateUser(Long id, AdminUserUpdateRequest req);
    User updateUserRole(Long id, AdminRoleUpdateRequest req);
    void deleteUser(Long id);

    // Listings
    List<Listing> getAllListings();
    Listing getListingById(Long id);
    Listing updateListing(Long id, Listing listing);
    Listing markListingSold(Long id, boolean sold);
    void deleteListing(Long id);

    // Appointments
    List<Appointment> getAllAppointments();
    void deleteAppointment(Long id);

    // Feedback
    List<Feedback> getAllFeedbacks();
    void deleteFeedback(Long id);

    // Stats
    AdminStatsResponse getStats();
}
