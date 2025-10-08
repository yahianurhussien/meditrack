package com.meditrack.doctor_service.service;


import com.meditrack.doctor_service.dao.DoctorAvailabilityRepository;
import com.meditrack.doctor_service.dto.DoctorAvailabilityRequest;
import com.meditrack.doctor_service.dto.DoctorAvailabilityResponse;
import com.meditrack.doctor_service.entity.DoctorAvailabilityEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorAvailabilityService {

    private final DoctorAvailabilityRepository doctorAvailabilityRepository;

    public DoctorAvailabilityResponse addAvailability(DoctorAvailabilityRequest request) {
        DoctorAvailabilityEntity entity = DoctorAvailabilityEntity.builder()
                .doctorId(request.doctorId())
                .dayOfWeek(request.dayOfWeek())
                .startTime(request.startTime())
                .endTime(request.endTime())
                .isAvailable(request.isAvailable())
                .build();
        doctorAvailabilityRepository.save(entity);
        return new DoctorAvailabilityResponse(
                entity.getId(), entity.getDoctorId(),
                entity.getDayOfWeek(), entity.getStartTime(), entity.getEndTime(), entity.isAvailable()
        );
    }

    public List<DoctorAvailabilityResponse> getAvailabilityByDoctor(Long doctorId) {
        return doctorAvailabilityRepository.findByDoctorId(doctorId).stream()
                .map(e -> new DoctorAvailabilityResponse(e.getId(), e.getDoctorId(), e.getDayOfWeek(),
                        e.getStartTime(), e.getEndTime(), e.isAvailable()))
                .toList();
    }

    public boolean isDoctorAvailable(Long doctorId, LocalDateTime appointmentTime) {
        return doctorAvailabilityRepository.findByDoctorIdAndDayOfWeek(doctorId, appointmentTime.getDayOfWeek())
                .filter(DoctorAvailabilityEntity::isAvailable)
                .filter(a -> !appointmentTime.toLocalTime().isBefore(a.getStartTime()) &&
                        !appointmentTime.toLocalTime().isAfter(a.getEndTime()))
                .isPresent();
    }
}