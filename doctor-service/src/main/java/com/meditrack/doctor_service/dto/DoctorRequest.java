package com.meditrack.doctor_service.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DoctorRequest(
        @NotBlank(message = "First name is required")
        String firstName,
        @NotBlank(message = "Last name is required")
        String lastName,
        @NotBlank(message = "Phone number is required")
        String phoneNumber,
        @Email(message = "Valid email is required")
        @NotBlank(message = "Email is required")
        String email,
        @NotNull(message = "Date of birth is required")
        @Past(message = "Date of birth must be in the past")
        LocalDate dateOfBirth,
        @NotBlank(message = "Specialization is required")
        String specialization,

        String qualifications,

        @Min(value = 0, message = "Years of experience cannot be negative")
        Integer yearsOfExperience,

       @NotNull(message = "Consultation fee is required")
       @DecimalMin(value = "0.0", inclusive = false, message = "Consultation fee must be greater than 0")
       BigDecimal consultationFee,
       Boolean isAvailable,
       String bio

){}
