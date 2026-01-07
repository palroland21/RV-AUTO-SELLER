package com.rv_auto_seller.controller;

import com.rv_auto_seller.dto.request.AdminRoleUpdateRequest;
import com.rv_auto_seller.dto.request.AdminUserUpdateRequest;
import com.rv_auto_seller.dto.response.AdminStatsResponse;
import com.rv_auto_seller.dto.response.AdminUserResponse;
import com.rv_auto_seller.dto.response.AppointmentResponse;
import com.rv_auto_seller.dto.response.FeedbackResponse;
import com.rv_auto_seller.dto.response.ListingResponse;
import com.rv_auto_seller.model.Appointment;
import com.rv_auto_seller.model.Feedback;
import com.rv_auto_seller.model.Listing;
import com.rv_auto_seller.model.User;
import com.rv_auto_seller.security.AdminAuthService;
import com.rv_auto_seller.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/admin")
@RestController
public class AdminController {

    private final AdminAuthService adminAuthService;
    private final AdminService adminService;

    public AdminController(AdminAuthService adminAuthService, AdminService adminService) {
        this.adminAuthService = adminAuthService;
        this.adminService = adminService;
    }

    // -------- USERS --------

    @GetMapping("/users")
    public ResponseEntity<List<AdminUserResponse>> getAllUsers(@RequestHeader("Authorization") String auth) {
        adminAuthService.requireAdmin(auth);
        List<AdminUserResponse> res = adminService.getAllUsers()
                .stream().map(AdminUserResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok(res);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<AdminUserResponse> getUser(@RequestHeader("Authorization") String auth,
                                                     @PathVariable Long id) {
        adminAuthService.requireAdmin(auth);
        User u = adminService.getUserById(id);
        return ResponseEntity.ok(new AdminUserResponse(u));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<AdminUserResponse> updateUser(@RequestHeader("Authorization") String auth,
                                                        @PathVariable Long id,
                                                        @RequestBody AdminUserUpdateRequest req) {
        adminAuthService.requireAdmin(auth);
        User u = adminService.updateUser(id, req);
        return ResponseEntity.ok(new AdminUserResponse(u));
    }

    @PatchMapping("/users/{id}/role")
    public ResponseEntity<AdminUserResponse> updateUserRole(@RequestHeader("Authorization") String auth,
                                                            @PathVariable Long id,
                                                            @RequestBody AdminRoleUpdateRequest req) {
        adminAuthService.requireAdmin(auth);
        User u = adminService.updateUserRole(id, req);
        return ResponseEntity.ok(new AdminUserResponse(u));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@RequestHeader("Authorization") String auth,
                                           @PathVariable Long id) {
        adminAuthService.requireAdmin(auth);
        adminService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // -------- LISTINGS --------

    @GetMapping("/listings")
    public ResponseEntity<List<ListingResponse>> getAllListings(@RequestHeader("Authorization") String auth) {
        adminAuthService.requireAdmin(auth);
        List<ListingResponse> res = adminService.getAllListings()
                .stream().map(ListingResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok(res);
    }

    @GetMapping("/listings/{id}")
    public ResponseEntity<ListingResponse> getListing(@RequestHeader("Authorization") String auth,
                                                      @PathVariable Long id) {
        adminAuthService.requireAdmin(auth);
        Listing l = adminService.getListingById(id);
        return ResponseEntity.ok(new ListingResponse(l));
    }

    @PutMapping("/listings/{id}")
    public ResponseEntity<ListingResponse> updateListing(@RequestHeader("Authorization") String auth,
                                                         @PathVariable Long id,
                                                         @RequestBody Listing listing) {
        adminAuthService.requireAdmin(auth);
        Listing updated = adminService.updateListing(id, listing);
        return ResponseEntity.ok(new ListingResponse(updated));
    }

    // mark sold (daca ai adaugat campul sold)
    @PatchMapping("/listings/{id}/sold")
    public ResponseEntity<ListingResponse> markSold(@RequestHeader("Authorization") String auth,
                                                    @PathVariable Long id,
                                                    @RequestParam boolean sold) {
        adminAuthService.requireAdmin(auth);
        Listing updated = adminService.markListingSold(id, sold);
        return ResponseEntity.ok(new ListingResponse(updated));
    }

    @DeleteMapping("/listings/{id}")
    public ResponseEntity<Void> deleteListing(@RequestHeader("Authorization") String auth,
                                              @PathVariable Long id) {
        adminAuthService.requireAdmin(auth);
        adminService.deleteListing(id);
        return ResponseEntity.noContent().build();
    }

    // -------- APPOINTMENTS --------

    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentResponse>> getAllAppointments(@RequestHeader("Authorization") String auth) {
        adminAuthService.requireAdmin(auth);
        List<AppointmentResponse> res = adminService.getAllAppointments()
                .stream().map(AppointmentResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<Void> deleteAppointment(@RequestHeader("Authorization") String auth,
                                                  @PathVariable Long id) {
        adminAuthService.requireAdmin(auth);
        adminService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

    // -------- FEEDBACK --------

    @GetMapping("/feedbacks")
    public ResponseEntity<List<FeedbackResponse>> getAllFeedbacks(@RequestHeader("Authorization") String auth) {
        adminAuthService.requireAdmin(auth);
        List<FeedbackResponse> res = adminService.getAllFeedbacks()
                .stream().map(FeedbackResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/feedbacks/{id}")
    public ResponseEntity<Void> deleteFeedback(@RequestHeader("Authorization") String auth,
                                               @PathVariable Long id) {
        adminAuthService.requireAdmin(auth);
        adminService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }

    // -------- STATS --------

    @GetMapping("/stats")
    public ResponseEntity<AdminStatsResponse> stats(@RequestHeader("Authorization") String auth) {
        adminAuthService.requireAdmin(auth);
        return ResponseEntity.ok(adminService.getStats());
    }
}
