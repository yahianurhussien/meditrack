package com.meditrack.doctor_service.dao;

import com.meditrack.doctor_service.entity.DoctorEntity;
import com.meditrack.doctor_service.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {

    Optional<Doctor> findByEmail(String email);

    List<Doctor> findBySpecialization(String specialization);

    List<Doctor> findByIsAvailableTrue();

    List<Doctor> findBySpecializationAndIsAvailableTrue(String specialization);

    boolean existsByEmail(String email);
}
