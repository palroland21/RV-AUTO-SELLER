package com.rv_auto_seller.service;

import com.rv_auto_seller.dto.request.AppointmentRequest;
import com.rv_auto_seller.dto.response.AppointmentResponse;
import com.rv_auto_seller.model.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    List<Appointment>  getAllAppointments();
    Optional<Appointment> getAppointmentById(Long id);
    Appointment createAppointment(Appointment appointment);
    Appointment updateAppointment(Long id,Appointment appointment);
    void deleteAppointment(Long id);
    AppointmentResponse scheduleAppointment(AppointmentRequest request, String clientUsername);
}
