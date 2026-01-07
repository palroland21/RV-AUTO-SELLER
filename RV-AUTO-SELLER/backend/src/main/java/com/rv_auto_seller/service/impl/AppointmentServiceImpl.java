package com.rv_auto_seller.service.impl;

import com.rv_auto_seller.dto.request.AppointmentRequest;
import com.rv_auto_seller.dto.response.AppointmentResponse;
import com.rv_auto_seller.model.Appointment;
import com.rv_auto_seller.model.User;
import com.rv_auto_seller.repository.AppointmentRepository;
import com.rv_auto_seller.repository.UserRepository;
import com.rv_auto_seller.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, UserRepository userRepository) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
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

    @Override
    public AppointmentResponse scheduleAppointment(AppointmentRequest request, String clientUsername) {

        User client = userRepository.findByUsername(clientUsername)
                .orElseThrow(() -> new RuntimeException("Client not found (Auth Error)"));

        User seller = userRepository.findById(request.getSellerId())
                .orElseThrow(() -> new RuntimeException("Seller not found with ID: " + request.getSellerId()));

        if (client.getId().equals(seller.getId())) {
            throw new IllegalArgumentException("Nu te poți programa la tine însuți.");
        }

        Appointment appointment = new Appointment();
        appointment.setLocation(request.getLocation());
        appointment.setDate(request.getDate());
        appointment.setDetails(request.getDetails());
        appointment.setClient(client);
        appointment.setSeller(seller);

        Appointment savedAppointment = this.createAppointment(appointment);

        return new AppointmentResponse(savedAppointment);
    }
}
