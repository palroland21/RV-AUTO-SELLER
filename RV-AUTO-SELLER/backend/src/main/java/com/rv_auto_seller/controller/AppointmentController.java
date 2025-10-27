package com.rv_auto_seller.controller;

import com.rv_auto_seller.dto.response.AppointmentResponse;
import com.rv_auto_seller.model.Appointment;
import com.rv_auto_seller.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RequestMapping(("/appointment"))
@RestController
public class AppointmentController {

    private final AppointmentService appointmentService;
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentResponse>> getAllAppointments() {
        List<AppointmentResponse> responses = appointmentService.getAllAppointments()
                .stream()
                .map(AppointmentResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponse> getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id)
                .map(appointment -> ResponseEntity.ok(new AppointmentResponse(appointment)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/new_appointment")
    public ResponseEntity<AppointmentResponse> createAppointment(@RequestBody Appointment appointment) {
        Appointment saved = appointmentService.createAppointment(appointment);
        return ResponseEntity.ok(new AppointmentResponse(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponse> updateAppointment(@PathVariable Long id,@RequestBody Appointment appointment) {
        Appointment updated = appointmentService.updateAppointment(id, appointment);
        return ResponseEntity.ok(new AppointmentResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

}
