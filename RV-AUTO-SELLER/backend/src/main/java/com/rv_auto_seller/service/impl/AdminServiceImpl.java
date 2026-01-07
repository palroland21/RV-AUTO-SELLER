package com.rv_auto_seller.service.impl;

import com.rv_auto_seller.dto.request.AdminRoleUpdateRequest;
import com.rv_auto_seller.dto.request.AdminUserUpdateRequest;
import com.rv_auto_seller.dto.response.AdminStatsResponse;
import com.rv_auto_seller.model.Appointment;
import com.rv_auto_seller.model.Feedback;
import com.rv_auto_seller.model.Listing;
import com.rv_auto_seller.model.User;
import com.rv_auto_seller.repository.AppointmentRepository;
import com.rv_auto_seller.repository.FeedbackRepository;
import com.rv_auto_seller.repository.ListingRepository;
import com.rv_auto_seller.repository.UserRepository;
import com.rv_auto_seller.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final ListingRepository listingRepository;
    private final AppointmentRepository appointmentRepository;
    private final FeedbackRepository feedbackRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminServiceImpl(
            UserRepository userRepository,
            ListingRepository listingRepository,
            AppointmentRepository appointmentRepository,
            FeedbackRepository feedbackRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.listingRepository = listingRepository;
        this.appointmentRepository = appointmentRepository;
        this.feedbackRepository = feedbackRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // USERS
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @Override
    public User updateUser(Long id, AdminUserUpdateRequest req) {
        User u = getUserById(id);

        if (req.getFirstName() != null) u.setFirstName(req.getFirstName());
        if (req.getLastName() != null) u.setLastName(req.getLastName());
        if (req.getUsername() != null) u.setUsername(req.getUsername().toLowerCase().trim());
        if (req.getTelephone() != null) u.setTelephone(req.getTelephone());
        if (req.getEmail() != null) u.setEmail(req.getEmail().toLowerCase().trim());

        if (req.getNewPassword() != null && !req.getNewPassword().isBlank()) {
            u.setPassword(passwordEncoder.encode(req.getNewPassword()));
        }

        if (req.getRole() != null) {
            u.setRole(req.getRole());
        }

        return userRepository.save(u);
    }

    @Override
    public User updateUserRole(Long id, AdminRoleUpdateRequest req) {
        if (req == null || req.getRole() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "role is required");
        }
        User u = getUserById(id);
        u.setRole(req.getRole());
        return userRepository.save(u);
    }

    @Override
    public void deleteUser(Long id) {
        User u = getUserById(id);
        userRepository.delete(u);
    }

    // LISTINGS
    @Override
    public List<Listing> getAllListings() {
        return listingRepository.findAll();
    }

    @Override
    public Listing getListingById(Long id) {
        return listingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Listing not found"));
    }

    @Override
    public Listing updateListing(Long id, Listing updated) {
        Listing existing = getListingById(id);

        existing.setTitle(updated.getTitle());
        existing.setPrice(updated.getPrice());
        existing.setBrand(updated.getBrand());
        existing.setModel(updated.getModel());
        existing.setType(updated.getType());
        existing.setYearOfManufacture(updated.getYearOfManufacture());
        existing.setFuelType(updated.getFuelType());
        existing.setHorsePower(updated.getHorsePower());
        existing.setLocation(updated.getLocation());
        existing.setVIN(updated.getVIN());
        existing.setTransmissionType(updated.getTransmissionType());
        existing.setDescription(updated.getDescription());

        // optional: sold (daca ai adaugat campul)
        try {
            existing.setSold(updated.isSold());
        } catch (Exception ignored) {}

        // imagini: pastreaza logica ta (clear + add)
        existing.getImages().clear();
        if (updated.getImages() != null) {
            updated.getImages().forEach(img -> {
                img.setListing(existing);
                existing.getImages().add(img);
            });
        }

        return listingRepository.save(existing);
    }

    @Override
    public Listing markListingSold(Long id, boolean sold) {
        Listing existing = getListingById(id);
        try {
            existing.setSold(sold);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Listing model has no 'sold' field. Add it in Listing.java first.");
        }
        return listingRepository.save(existing);
    }

    @Override
    public void deleteListing(Long id) {
        listingRepository.deleteById(id);
    }

    // APPOINTMENTS
    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    // FEEDBACK
    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }

    // STATS
    @Override
    public AdminStatsResponse getStats() {
        AdminStatsResponse r = new AdminStatsResponse();
        r.setTotalUsers(userRepository.count());
        r.setTotalListings(listingRepository.count());
        r.setTotalAppointments(appointmentRepository.count());
        r.setTotalFeedbacks(feedbackRepository.count());

        // breakdown simplu (din DB in memorie)
        List<Listing> listings = listingRepository.findAll();
        Map<String, Long> byBrand = listings.stream()
                .filter(l -> l.getBrand() != null)
                .collect(Collectors.groupingBy(l -> l.getBrand().name(), Collectors.counting()));
        Map<String, Long> byType = listings.stream()
                .filter(l -> l.getType() != null)
                .collect(Collectors.groupingBy(l -> l.getType().name(), Collectors.counting()));
        Map<String, Long> byFuel = listings.stream()
                .filter(l -> l.getFuelType() != null)
                .collect(Collectors.groupingBy(l -> l.getFuelType().name(), Collectors.counting()));

        r.setListingsByBrand(byBrand);
        r.setListingsByType(byType);
        r.setListingsByFuel(byFuel);

        return r;
    }
}
