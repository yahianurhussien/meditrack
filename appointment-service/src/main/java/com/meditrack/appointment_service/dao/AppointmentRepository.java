package com.meditrack.appointment_service.dao;

import com.meditrack.appointment_service.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
    List<AppointmentEntity> findByDoctorId(Long doctorId);
    List<AppointmentEntity> findByPatientId(Long patientId);
}
