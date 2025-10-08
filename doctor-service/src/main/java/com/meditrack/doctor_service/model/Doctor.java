package com.meditrack.doctor_service.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private LocalDate dateOfBirth;
    private String specialization;
    private String qualifications;
    private Integer yearsOfExperience;
    private BigDecimal consultationFee;
    private Boolean isAvailable;
    private String bio;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
