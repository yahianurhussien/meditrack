package com.meditrack.doctor_service.model;

import lombok.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorAvailability {
    private Long id;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isAvailable; // false for holidays, time off, etc.
    private Doctor doctor;
}
