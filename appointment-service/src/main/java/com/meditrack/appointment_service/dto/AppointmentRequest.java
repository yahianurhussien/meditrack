package com.meditrack.appointment_service.dto;


import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record AppointmentRequest(
        @NotNull(message = "patientId is required")
        Long patientId,
        @NotNull(message = "doctorId is required")
        Long doctorId,
        @NotNull(message = "appointmentTime is required")
        LocalDateTime appointmentTime
) {}
