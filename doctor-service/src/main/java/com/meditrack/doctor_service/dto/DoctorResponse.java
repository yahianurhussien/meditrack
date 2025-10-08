package com.meditrack.doctor_service.dto;

public record DoctorResponse(
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        String specialization,
        Integer yearsOfExperience



) {
}
