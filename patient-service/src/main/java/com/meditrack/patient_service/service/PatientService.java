package com.meditrack.patient_service.service;

import com.meditrack.patient_service.dao.PatientRepository;
import com.meditrack.patient_service.dto.PatientRequest;
import com.meditrack.patient_service.dto.PatientResponse;
import com.meditrack.patient_service.entity.PatientEntity;
import com.meditrack.patient_service.exception.PatientNotFoundException;
import com.meditrack.patient_service.mapper.PatientMapper;
import com.meditrack.patient_service.model.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class PatientService {
    @Autowired
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

    public PatientResponse getPatientById(Long id) {
       PatientEntity patientEntity = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException(id));
       Patient patient = PatientMapper.toModel(patientEntity);
       return PatientMapper.toResponse(patient);
    }

    public List<PatientResponse> getAllPatients(){
        List<Patient> patients = patientRepository.findAll().stream().map(PatientMapper::toModel).toList();
        return patients.stream().map(PatientMapper::toResponse).toList();
    }

    public PatientResponse updatePatient(Long id, PatientRequest request){

        PatientEntity existingEntity = patientRepository.findById(id)
                .orElseThrow(()->new PatientNotFoundException(id));

        existingEntity.setFirstName(request.firstName());
        existingEntity.setLastName(request.lastName());
        existingEntity.setPhoneNumber(request.phoneNumber());
        existingEntity.setEmail(request.email());
        existingEntity.setDateOfBirth(request.dateOfBirth());
        existingEntity.setCheckupNotes(request.checkupNotes());

        PatientEntity updatedEntity = patientRepository.save(existingEntity);
        Patient patient = PatientMapper.toModel(updatedEntity);

        return PatientMapper.toResponse(patient);

    }

    public void deletePatient(Long id){
        if(!patientRepository.existsById(id)){
            throw new PatientNotFoundException(id);
        }
        patientRepository.deleteById(id);
    }
}
