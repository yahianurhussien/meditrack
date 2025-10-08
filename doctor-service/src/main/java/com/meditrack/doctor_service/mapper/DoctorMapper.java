package com.meditrack.doctor_service.mapper;

import com.meditrack.doctor_service.dto.DoctorRequest;
import com.meditrack.doctor_service.dto.DoctorResponse;
import com.meditrack.doctor_service.entity.DoctorEntity;
import com.meditrack.doctor_service.model.Doctor;

public class DoctorMapper {

    //DTO to Model
    public static Doctor toModel(DoctorRequest doctorRequest) {
        return Doctor.builder()
                .id(null)
                .firstName(doctorRequest.firstName())
                .lastName(doctorRequest.lastName())
                .phoneNumber(doctorRequest.phoneNumber())
                .email(doctorRequest.phoneNumber())
                .dateOfBirth(doctorRequest.dateOfBirth())
                .qualifications(doctorRequest.qualifications())
                .specialization(doctorRequest.specialization())
                .consultationFee(doctorRequest.consultationFee())
                .yearsOfExperience(doctorRequest.yearsOfExperience())
                .bio(doctorRequest.bio())
                .build();
    }

    //Model to Entity
    public static DoctorEntity toEntity(Doctor doctor) {
        return DoctorEntity.builder()
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .phoneNumber(doctor.getPhoneNumber())
                .email(doctor.getEmail())
                .dateOfBirth(doctor.getDateOfBirth())
                .qualifications(doctor.getQualifications())
                .specialization(doctor.getSpecialization())
                .consultationFee(doctor.getConsultationFee())
                .yearsOfExperience(doctor.getYearsOfExperience())
                .bio(doctor.getBio())
                .build();
    }

    //Entity to Model
    public static Doctor toModel(DoctorEntity doctorEntity){
        return Doctor.builder()
                .id(doctorEntity.getId())
                .firstName(doctorEntity.getFirstName())
                .lastName(doctorEntity.getLastName())
                .phoneNumber(doctorEntity.getPhoneNumber())
                .email(doctorEntity.getEmail())
                .dateOfBirth(doctorEntity.getDateOfBirth())
                .qualifications(doctorEntity.getQualifications())
                .specialization(doctorEntity.getSpecialization())
                .consultationFee(doctorEntity.getConsultationFee())
                .yearsOfExperience(doctorEntity.getYearsOfExperience())
                .bio(doctorEntity.getBio())
                .build();
    }

    //Model to DTO
    public static DoctorResponse toResponse(Doctor doctor){
        return new DoctorResponse(
                doctor.getFirstName(),
                doctor.getLastName(),
                doctor.getPhoneNumber(),
                doctor.getEmail(),
                doctor.getSpecialization(),
                doctor.getYearsOfExperience()
        );
    }
}
