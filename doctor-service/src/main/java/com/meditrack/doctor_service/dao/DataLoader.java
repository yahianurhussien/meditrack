package com.meditrack.doctor_service.dao;

import com.meditrack.doctor_service.entity.DoctorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component

public class DataLoader implements CommandLineRunner {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DataLoader(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        doctorRepository.saveAll(List.of(
                new DoctorEntity(null,
                        "Alice",
                        "Johnson",
                        "123455666",
                        "alice@example.com",
                        LocalDate.of(1990,3,14),
                        "staz",
                        "ttee",
                        1,
                        BigDecimal.ONE,
                        "giejrnot",
                        LocalDateTime.now(),
                        LocalDateTime.now()
                ),
                new DoctorEntity(null, "Michael", "Brown", "234535656",  "michael@example.com",LocalDate.of(1991,3,3),"staz",
                        "ttee",
                        1,
                        BigDecimal.ONE,
                        "giejrnot", LocalDateTime.now(),LocalDateTime.now()),
                new DoctorEntity(null, "Sophia", "Lee", "123456456", "sophia@example.com",LocalDate.of(1993,3,23),"staz",
                        "ttee",
                        1,
                        BigDecimal.ONE,
                        "giejrnot", LocalDateTime.now(),LocalDateTime.now())
        ));
        System.out.println("✅ Dummy patient data loaded successfully!");
    }
}