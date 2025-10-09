package com.meditrack.doctor_service.dao;

import com.meditrack.doctor_service.entity.DoctorAvailabilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorAvailabilityRepository extends JpaRepository<DoctorAvailabilityEntity, Long> {
    List<DoctorAvailabilityEntity> findByDoctor_Id(Long doctorId);
    Optional<DoctorAvailabilityEntity> findByDoctor_IdAndDayOfWeek(Long doctorId, DayOfWeek dayOfWeek);
}