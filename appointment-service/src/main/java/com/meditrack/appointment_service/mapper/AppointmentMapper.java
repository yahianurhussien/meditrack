package com.meditrack.appointment_service.mapper;

import com.meditrack.appointment_service.dto.AppointmentRequest;
import com.meditrack.appointment_service.dto.AppointmentResponse;
import com.meditrack.appointment_service.entity.AppointmentEntity;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    public AppointmentEntity toEntity(AppointmentRequest request) {
        return AppointmentEntity.builder()
                .doctorId(request.doctorId())
                .patientId(request.patientId())
                .appointmentTime(request.appointmentTime())
                .status(AppointmentEntity.AppointmentStatus.SCHEDULED)
                .build();
    }

    public AppointmentResponse toResponse(AppointmentEntity entity) {
        return new AppointmentResponse(
                entity.getId(),
                entity.getPatientId(),
                entity.getDoctorId(),
                entity.getAppointmentTime(),
                entity.getStatus().name()
        );
    }
}
