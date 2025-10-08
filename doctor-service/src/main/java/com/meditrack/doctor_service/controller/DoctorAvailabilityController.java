package com.meditrack.doctor_service.controller;

import com.meditrack.doctor_service.dto.DoctorAvailabilityRequest;
import com.meditrack.doctor_service.dto.DoctorAvailabilityResponse;
import com.meditrack.doctor_service.service.DoctorAvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors/availability")
@RequiredArgsConstructor
public class DoctorAvailabilityController {

    private final DoctorAvailabilityService doctorAvailabilityService;

    @PostMapping
    public ResponseEntity<DoctorAvailabilityResponse> addAvailability(@RequestBody DoctorAvailabilityRequest request) {
        return ResponseEntity.ok(doctorAvailabilityService.addAvailability(request));
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<List<DoctorAvailabilityResponse>> getAvailability(@PathVariable Long doctorId) {
        return ResponseEntity.ok(doctorAvailabilityService.getAvailabilityByDoctor(doctorId));
    }
}