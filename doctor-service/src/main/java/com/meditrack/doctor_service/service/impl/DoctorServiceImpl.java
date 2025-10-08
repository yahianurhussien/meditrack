package com.meditrack.doctor_service.service;

import com.meditrack.doctor_service.dto.DoctorResponse;
import com.meditrack.doctor_service.dao.DoctorRepository;
import com.meditrack.doctor_service.dto.DoctorRequest;
import com.meditrack.doctor_service.entity.DoctorEntity;
import com.meditrack.doctor_service.exception.DoctorNotFoundException;
import com.meditrack.doctor_service.mapper.DoctorMapper;
import com.meditrack.doctor_service.model.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class DoctorServiceImpl{
    @Autowired
    private DoctorRepository doctorRepository;

    public DoctorResponse createDoctor(DoctorRequest doctorRequest){
        //request to model
        Doctor doctor= DoctorMapper.toModel(doctorRequest);

        //apply business rule
        //doctor.validate();

        //model to entity
        DoctorEntity doctorEntity = DoctorMapper.toEntity(doctor);

        //save
        DoctorEntity savedEntity = doctorRepository.save(doctorEntity);

        //saved entity to model
        Doctor savedModel = DoctorMapper.toModel(savedEntity);

        return DoctorMapper.toResponse(savedModel);


    }

    public DoctorResponse getDoctorById(Long id) {
       DoctorEntity doctorEntity = doctorRepository.findById(id).orElseThrow(() -> new DoctorNotFoundException(id));
       Doctor doctor = DoctorMapper.toModel(doctorEntity);
       return DoctorMapper.toResponse(doctor);
    }

    public List<DoctorResponse> getAllDoctors(){
        List<Doctor> doctors = doctorRepository.findAll().stream().map(DoctorMapper::toModel).toList();
        return doctors.stream().map(DoctorMapper::toResponse).toList();
    }

    public DoctorResponse updateDoctor(Long id, DoctorRequest request){

        DoctorEntity existingEntity = doctorRepository.findById(id)
                .orElseThrow(()->new DoctorNotFoundException(id));

        existingEntity.setFirstName(request.firstName());
        existingEntity.setLastName(request.lastName());
        existingEntity.setPhoneNumber(request.phoneNumber());
        existingEntity.setEmail(request.email());
        existingEntity.setDateOfBirth(request.dateOfBirth());
        existingEntity.setSpecialization(request.specialization());
        existingEntity.setQualifications(request.qualifications());
        existingEntity.setConsultationFee(request.consultationFee());
        existingEntity.setBio(request.bio());
        DoctorEntity updatedEntity = doctorRepository.save(existingEntity);
        Doctor doctor = DoctorMapper.toModel(updatedEntity);

        return DoctorMapper.toResponse(doctor);

    }

    public void deleteDoctor(Long id){
        if(!doctorRepository.existsById(id)){
            throw new DoctorNotFoundException(id);
        }
        doctorRepository.deleteById(id);
    }
}
