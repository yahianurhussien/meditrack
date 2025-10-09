package com.meditrack.appointment_service.dto;


import java.time.LocalDateTime;

public record AppointmentRequest(
        Long patientId,
        Long doctorId,
        LocalDateTime appointmentTime
) {}
