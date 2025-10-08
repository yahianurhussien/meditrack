package com.meditrack.doctor_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record CreateAvailabilitySlotRequest (
        @NotNull(message = "Doctor ID is required")
        Long doctorId,

        @NotNull(message = "Day of week is required")
        DayOfWeek dayOfWeek,

        @NotNull(message = "Start time is required")
        LocalTime startTime,

       @NotNull(message = "End time is required")
        LocalTime endTime,

        Boolean isRecurring
){}