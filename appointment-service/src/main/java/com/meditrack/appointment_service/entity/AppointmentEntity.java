package com.meditrack.appointment_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "appointment")
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;

    private Long patientId;
    private Long doctorId;

    private LocalDateTime appointmentTime;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    public enum AppointmentStatus {
        SCHEDULED, COMPLETED, CANCELLED
    }
}
