package com.meditrack.patient_service.service;

import com.meditrack.patient_service.dao.PatientRepository;
import com.meditrack.patient_service.dto.PatientRequest;
import com.meditrack.patient_service.dto.PatientResponse;
import com.meditrack.patient_service.entity.PatientEntity;
import com.meditrack.patient_service.mapper.PatientMapper;
import com.meditrack.patient_service.model.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class PatientService {

    private PatientRepository patientRepository;

    public PatientResponse createPatient(PatientRequest patientRequest){
        //request to model
        Patient patient= PatientMapper.toModel(patientRequest);

        //apply business rule
        //patient.validate();

        //model to entity
        PatientEntity patientEntity = PatientMapper.toEntity(patient);

        //save
        PatientEntity savedEntity = patientRepository.save(patientEntity);

        //saved entity to model
        Patient savedModel = PatientMapper.toModel(savedEntity);

        return PatientMapper.toResponse(savedModel);


    }
}
