package com.rv_auto_seller.repository;

import com.rv_auto_seller.model.Appointment;
import com.rv_auto_seller.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByClientAndDate(User client, LocalDateTime date);
}
