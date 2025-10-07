package com.meditrack.doctor_service.model;

import lombok.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvailabilitySlot {
    private Long id;
    private Long doctorId;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private Boolean isRecurring;
    private Boolean isActive;
}
