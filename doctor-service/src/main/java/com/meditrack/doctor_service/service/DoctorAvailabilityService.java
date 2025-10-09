package com.meditrack.doctor_service.service;


import com.meditrack.doctor_service.dao.DoctorAvailabilityRepository;
import com.meditrack.doctor_service.dao.DoctorRepository;
import com.meditrack.doctor_service.dto.DoctorAvailabilityRequest;
import com.meditrack.doctor_service.dto.DoctorAvailabilityResponse;
import com.meditrack.doctor_service.entity.DoctorAvailabilityEntity;
import com.meditrack.doctor_service.entity.DoctorEntity;
import com.meditrack.doctor_service.exception.DoctorNotFoundException;
import com.meditrack.doctor_service.mapper.AvailabilityMapper;
import com.meditrack.doctor_service.mapper.DoctorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorAvailabilityService {
    @Autowired
    private final DoctorAvailabilityRepository doctorAvailabilityRepository;
    private final DoctorRepository doctorRepository;

    public DoctorAvailabilityResponse addAvailability(DoctorAvailabilityRequest request) {

        DoctorEntity doctorEntity = doctorRepository.findById(request.doctorId())
                .orElseThrow(()->new DoctorNotFoundException(request.doctorId()));

        DoctorAvailabilityEntity entity = DoctorAvailabilityEntity.builder()
                .doctor(doctorEntity)
                .dayOfWeek(request.dayOfWeek())
                .startTime(request.startTime())
                .endTime(request.endTime())
                .isAvailable(request.isAvailable())
                .build();
        DoctorAvailabilityEntity savedAvailability = doctorAvailabilityRepository.save(entity);
        return AvailabilityMapper.toResponse(AvailabilityMapper.toModel(savedAvailability));
    }

    public List<DoctorAvailabilityResponse> getAvailabilityByDoctor(Long doctorId) {
        return doctorAvailabilityRepository.findByDoctor_Id(doctorId).stream()
                .map(AvailabilityMapper::toModel)
                .map(AvailabilityMapper::toResponse).toList();

    }

    public boolean isDoctorAvailable(Long doctorId, LocalDateTime appointmentTime) {
        return doctorAvailabilityRepository.findByDoctor_IdAndDayOfWeek(doctorId, appointmentTime.getDayOfWeek())
                .filter(DoctorAvailabilityEntity::isAvailable)
                .filter(a -> !appointmentTime.toLocalTime().isBefore(a.getStartTime()) &&
                        !appointmentTime.toLocalTime().isAfter(a.getEndTime()))
                .isPresent();
    }
}