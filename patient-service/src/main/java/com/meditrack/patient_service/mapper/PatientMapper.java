package com.meditrack.patient_service.mapper;

import com.meditrack.patient_service.dto.PatientRequest;
import com.meditrack.patient_service.dto.PatientResponse;
import com.meditrack.patient_service.entity.PatientEntity;
import com.meditrack.patient_service.model.Patient;

public class PatientMapper {

    //DTO to Model
    public static Patient toModel(PatientRequest patientRequest) {
        return Patient.builder()
                .id(null)
                .firstName(patientRequest.firstName())
                .lastName(patientRequest.lastName())
                .phoneNumber(patientRequest.phoneNumber())
                .email(patientRequest.phoneNumber())
                .dateOfBirth(patientRequest.dateOfBirth())
                .checkupNotes(patientRequest.checkupNotes())
                .build();
    }

    //Model to Entity
    public static PatientEntity toEntity(Patient patient) {
        return PatientEntity.builder()
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .phoneNumber(patient.getPhoneNumber())
                .email(patient.getEmail())
                .dateOfBirth(patient.getDateOfBirth())
                .checkupNotes(patient.getCheckupNotes())
                .build();
    }

    //Entity to Model
    public static Patient toModel(PatientEntity patientEntity){
        return Patient.builder()
                .id(patientEntity.getId())
                .firstName(patientEntity.getFirstName())
                .lastName(patientEntity.getLastName())
                .phoneNumber(patientEntity.getPhoneNumber())
                .email(patientEntity.getEmail())
                .dateOfBirth(patientEntity.getDateOfBirth())
                .checkupNotes(patientEntity.getCheckupNotes())
                .build();
    }

    //Model to DTO
    public static PatientResponse toResponse(Patient patient){
        return new PatientResponse(patient.getFirstName(), patient.getLastName(),patient.getPhoneNumber(),patient.getEmail());
    }
}
