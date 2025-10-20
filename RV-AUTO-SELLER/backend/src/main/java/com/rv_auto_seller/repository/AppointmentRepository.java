package com.rv_auto_seller.repository;

import com.rv_auto_seller.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
