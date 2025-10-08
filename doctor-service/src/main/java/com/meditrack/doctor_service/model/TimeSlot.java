package com.meditrack.doctor_service.model;

import lombok.*;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeSlot {
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date; // Optional: specific date for the slot
    private DayOfWeek dayOfWeek; // Optional: day of week for recurring slots

    // Helper method to check if a time falls within this slot
    public boolean contains(LocalTime time) {
        return !time.isBefore(startTime) && !time.isAfter(endTime);
    }

    // Helper method to get duration in minutes
    public long getDurationMinutes() {
        return Duration.between(startTime, endTime).toMinutes();
    }

    // Helper method to check if this slot overlaps with another
    public boolean overlapsWith(TimeSlot other) {
        return !startTime.isAfter(other.endTime) && !endTime.isBefore(other.startTime);
    }
}
