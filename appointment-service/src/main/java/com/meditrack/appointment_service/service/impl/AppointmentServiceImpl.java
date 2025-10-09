package com.meditrack.appointment_service.service.impl;

import com.meditrack.appointment_service.dao.AppointmentRepository;
import com.meditrack.appointment_service.dto.AppointmentRequest;
import com.meditrack.appointment_service.dto.AppointmentResponse;
import com.meditrack.appointment_service.entity.AppointmentEntity;
import com.meditrack.appointment_service.exception.AppointmentNotFoundException;
import com.meditrack.appointment_service.mapper.AppointmentMapper;
import com.meditrack.appointment_service.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository repository;
    private final AppointmentMapper mapper;

    @Override
    public AppointmentResponse createAppointment(AppointmentRequest request) {
        AppointmentEntity entity = mapper.toEntity(request);
        repository.save(entity);
        return mapper.toResponse(entity);
    }

    @Override
    public AppointmentResponse getAppointmentById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new AppointmentNotFoundException(id));
    }

    @Override
    public List<AppointmentResponse> getAppointmentsByDoctor(Long doctorId) {
        return repository.findByDoctorId(doctorId).stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<AppointmentResponse> getAppointmentsByPatient(Long patientId) {
        return repository.findByPatientId(patientId).stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public void cancelAppointment(Long id) {
        AppointmentEntity entity = repository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException(id));
        entity.setStatus(AppointmentEntity.AppointmentStatus.CANCELLED);
        repository.save(entity);
    }
}
