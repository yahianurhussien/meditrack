package com.meditrack.doctor_service.exception;

public class DoctorNotFoundException extends RuntimeException{
    public DoctorNotFoundException(Long id){
        super("Doctor not found with Id: "+id);
    }
}
