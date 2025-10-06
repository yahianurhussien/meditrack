package com.meditrack.patient_service.controller;

import com.meditrack.patient_service.dto.PatientRequest;
import com.meditrack.patient_service.dto.PatientResponse;
import com.meditrack.patient_service.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/patients")
public class PatientController{
  @Autowired
  private PatientService patientService;


    @Operation(
            summary = "Add a new patient",
            description = "Creates and saves a new patient record in the system"
    )
    @ApiResponse(responseCode = "201", description = "Patient created successfully")
    @PostMapping
  public ResponseEntity<PatientResponse> createPatient(@RequestBody PatientRequest patientRequest){
      PatientResponse response = patientService.createPatient(patientRequest);
      return ResponseEntity.status(HttpStatus.CREATED).body(response);

  }


    @Operation(
            summary = "Get patient by ID",
            description = "Retrieves a specific patient’s details using their UUID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Patient found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientResponse.class))
    )
    @ApiResponse(responseCode = "404", description = "Patient not found")
    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> getPatientById(
            @Parameter(description = "Unique Id of the patient", example = "12")
            @PathVariable("id") Long id){
      PatientResponse response = patientService.getPatientById(id);
      return ResponseEntity.ok(response);

  }
    @Operation(
            summary = "Retrieve all patients",
            description = "Fetches a list of all registered patients from the system"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved the list of patients"
    )
  @GetMapping
  public ResponseEntity<List<PatientResponse>> getAllPatients(){
      List<PatientResponse> patientResponses = patientService.getAllPatients();
      return ResponseEntity.ok(patientResponses);
  }

    @Operation(
            summary = "Update an existing patient",
            description = "Modify an existing patient’s details by providing their ID and updated information."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Patient updated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PatientResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "404", description = "Patient not found")
    })
  @PutMapping("/{id}")
  public ResponseEntity<PatientResponse> updatePatient(
            @Parameter(description = "Unique Id of the patient", example = "12")
            @PathVariable("id") Long id,
            @RequestBody PatientRequest patientRequest){
      PatientResponse updatedPatient =  patientService.updatePatient(id, patientRequest);
      return ResponseEntity.ok(updatedPatient);
  }

    @Operation(
            summary = "Delete patient by ID",
            description = "Deletes a patient record permanently by their UUID"
    )

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Patient deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Patient not found")
    })
   @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePatient(
            @Parameter(description = "Unique Id of the patient", example = "12")
            @PathVariable("id") Long id){
      patientService.deletePatient(id);
      return ResponseEntity.noContent().build();
  }

}
