package com.meditrack.doctor_service.mapper;

import com.meditrack.doctor_service.dto.DoctorAvailabilityRequest;
import com.meditrack.doctor_service.dto.DoctorAvailabilityResponse;
import com.meditrack.doctor_service.dto.DoctorRequest;
import com.meditrack.doctor_service.dto.DoctorResponse;
import com.meditrack.doctor_service.entity.DoctorAvailabilityEntity;
import com.meditrack.doctor_service.entity.DoctorEntity;
import com.meditrack.doctor_service.model.Doctor;
import com.meditrack.doctor_service.model.DoctorAvailability;

public class AvailabilityMapper {


    //DTO to Model
    public static DoctorAvailability toModel(DoctorAvailabilityRequest doctorAvailabilityRequest) {
        return DoctorAvailability.builder()
                .dayOfWeek(doctorAvailabilityRequest.dayOfWeek())
                .startTime(doctorAvailabilityRequest.startTime())
                .endTime(doctorAvailabilityRequest.endTime())
                .isAvailable(doctorAvailabilityRequest.isAvailable())
                //.doctor() this will be handled in the business layer
                .build();
    }

    //Model to Entity
    public static DoctorAvailabilityEntity toEntity(DoctorAvailability doctorAvailability) {
        return DoctorAvailabilityEntity.builder()
                .dayOfWeek(doctorAvailability.getDayOfWeek())
                .startTime(doctorAvailability.getStartTime())
                .endTime(doctorAvailability.getEndTime())
                .isAvailable(doctorAvailability.isAvailable())
                //.doctor() this will be handled in the business layer
                .build();
    }

    //Entity to Model
    public static DoctorAvailability toModel(DoctorAvailabilityEntity doctorAvailabilityEntity){
        return DoctorAvailability.builder()
                .id(doctorAvailabilityEntity.getId())
                .dayOfWeek(doctorAvailabilityEntity.getDayOfWeek())
                .startTime(doctorAvailabilityEntity.getStartTime())
                .endTime(doctorAvailabilityEntity.getEndTime())
                .isAvailable(doctorAvailabilityEntity.isAvailable())
                .doctor(DoctorMapper.toModel(doctorAvailabilityEntity.getDoctor()))
                .build();
    }

    //Model to DTO
    public static DoctorAvailabilityResponse toResponse(DoctorAvailability doctorAvailability){
        return new DoctorAvailabilityResponse(
                doctorAvailability.getId(),
                doctorAvailability.getDoctor().getId(),
               doctorAvailability.getDayOfWeek(),
                doctorAvailability.getStartTime(),
                doctorAvailability.getEndTime(),
                doctorAvailability.isAvailable()

        );
    }
}
