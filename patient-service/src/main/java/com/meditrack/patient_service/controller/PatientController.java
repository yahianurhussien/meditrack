package com.meditrack.patient_service.controller;

import com.meditrack.patient_service.dto.PatientRequest;
import com.meditrack.patient_service.dto.PatientResponse;
import com.meditrack.patient_service.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/patients")
public class PatientController{

  private PatientService patientService;

  public ResponseEntity<PatientResponse> createPatient(PatientRequest patientRequest){

      PatientResponse response = patientService.createPatient(patientRequest);

      return ResponseEntity.ok(response);

  }



}
