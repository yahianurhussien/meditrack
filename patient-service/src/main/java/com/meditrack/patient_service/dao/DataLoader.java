package com.meditrack.patient_service.dao;

import com.meditrack.patient_service.entity.PatientEntity;
import com.meditrack.patient_service.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component

public class DataLoader implements CommandLineRunner {

    private final PatientRepository patientRepository;

    @Autowired
    public DataLoader(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        patientRepository.saveAll(List.of(
                new PatientEntity(null, "Alice", "Johnson", "123455666", "alice@example.com", LocalDate.of(1990,3,14),"notes", LocalDateTime.now(),LocalDateTime.now()),
                new PatientEntity(null, "Michael", "Brown", "234535656",  "michael@example.com",LocalDate.of(1991,3,3),"notes", LocalDateTime.now(),LocalDateTime.now()),
                new PatientEntity(null, "Sophia", "Lee", "123456456", "sophia@example.com",LocalDate.of(1993,3,23),"notes", LocalDateTime.now(),LocalDateTime.now())
        ));
        System.out.println("✅ Dummy patient data loaded successfully!");
    }
}