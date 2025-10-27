package com.rv_auto_seller.dto.response;

import com.rv_auto_seller.model.Appointment;
import com.rv_auto_seller.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AppointmentResponse {
    private Long id;
    private String location;
    private LocalDateTime date;
    private String details;
    private Long client;
    private Long seller;

    public AppointmentResponse(Appointment appointment) {
        this.id = appointment.getId();
        this.location = appointment.getLocation();
        this.date = appointment.getDate();
        this.details = appointment.getDetails();
        this.client = appointment.getClient().getId();
        this.seller = appointment.getSeller().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Long getClient() {
        return client;
    }

    public void setClient(Long client) {
        this.client = client;
    }

    public Long getSeller() {
        return seller;
    }

    public void setSeller(Long seller) {
        this.seller = seller;
    }
}
