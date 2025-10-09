package com.meditrack.appointment_service.service;

import com.meditrack.appointment_service.dto.AppointmentRequest;
import com.meditrack.appointment_service.dto.AppointmentResponse;

import java.util.List;

public interface AppointmentService {
    AppointmentResponse createAppointment(AppointmentRequest request);
    AppointmentResponse getAppointmentById(Long id);
    List<AppointmentResponse> getAppointmentsByDoctor(Long doctorId);
    List<AppointmentResponse> getAppointmentsByPatient(Long patientId);
    void cancelAppointment(Long id);
}
