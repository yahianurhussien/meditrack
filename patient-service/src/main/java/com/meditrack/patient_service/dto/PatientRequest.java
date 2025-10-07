package com.meditrack.patient_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record PatientRequest(
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
        String checkupNotes){}
