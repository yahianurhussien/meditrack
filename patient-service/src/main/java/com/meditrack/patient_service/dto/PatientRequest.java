package com.meditrack.patient_service.dto;

import java.time.LocalDate;

public record PatientRequest(String firstName, String lastName,String phoneNumber, String email, LocalDate dateOfBirth){}
