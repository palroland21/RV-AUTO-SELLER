package com.rv_auto_seller.service.impl;

import com.rv_auto_seller.model.Appointment;
import com.rv_auto_seller.repository.AppointmentRepository;
import com.rv_auto_seller.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public Appointment createAppointment(Appointment appointment) {
        boolean exists = appointmentRepository.existsByClientAndDate(
                appointment.getClient(), appointment.getDate());

        if (exists) {
            throw new IllegalArgumentException("Client already have an appointment at that time!");
        }
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment updateAppointment(Long id,Appointment appointment) {
        Appointment existing = appointmentRepository.findById(id).get();

        existing.setLocation(appointment.getLocation());
        existing.setDate(appointment.getDate());
        existing.setDetails(appointment.getDetails());

        return appointmentRepository.save(existing);
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}
