package com.meditrack.doctor_service.controller;

import com.meditrack.doctor_service.dto.DoctorRequest;
import com.meditrack.doctor_service.dto.DoctorResponse;
import com.meditrack.doctor_service.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/doctors")
public class DoctorController{
  @Autowired
  private DoctorService doctorService;


    @Operation(
            summary = "Add a new doctor",
            description = "Creates and saves a new doctor record in the system"
    )
    @ApiResponse(responseCode = "201", description = "doctor created successfully")
    @PostMapping
  public ResponseEntity<DoctorResponse> createDoctor(@Valid @RequestBody DoctorRequest doctorRequest){
      DoctorResponse response = doctorService.createDoctor(doctorRequest);
      return ResponseEntity.status(HttpStatus.CREATED).body(response);

  }


    @Operation(
            summary = "Get doctor by ID",
            description = "Retrieves a specific doctor’s details using their UUID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "doctor found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = DoctorResponse.class))
    )
    @ApiResponse(responseCode = "404", description = "doctor not found")
    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponse> getDoctorById(
            @Parameter(description = "Unique Id of the doctor", example = "12")
            @PathVariable("id") Long id){
      DoctorResponse response = doctorService.getDoctorById(id);
      return ResponseEntity.ok(response);

  }
    @Operation(
            summary = "Retrieve all doctors",
            description = "Fetches a list of all registered doctors from the system"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved the list of doctors"
    )
  @GetMapping
  public ResponseEntity<List<DoctorResponse>> getAllDoctors(){
      List<DoctorResponse> doctorResponses = doctorService.getAllDoctors();
      return ResponseEntity.ok(doctorResponses);
  }

    @Operation(
            summary = "Update an existing doctor",
            description = "Modify an existing doctor’s details by providing their ID and updated information."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "doctor updated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DoctorResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "404", description = "doctor not found")
    })
  @PutMapping("/{id}")
  public ResponseEntity<DoctorResponse> updatedoctor(
            @Parameter(description = "Unique Id of the doctor", example = "12")
            @PathVariable("id") Long id,
            @Valid @RequestBody DoctorRequest doctorRequest){
      DoctorResponse updatedDoctor =  doctorService.updateDoctor(id, doctorRequest);
      return ResponseEntity.ok(updatedDoctor);
  }

    @Operation(
            summary = "Delete doctor by ID",
            description = "Deletes a doctor record permanently by their UUID"
    )

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "doctor deleted successfully"),
            @ApiResponse(responseCode = "404", description = "doctor not found")
    })
   @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteDoctor(
            @Parameter(description = "Unique Id of the doctor", example = "12")
            @PathVariable("id") Long id){
      doctorService.deleteDoctor(id);
      return ResponseEntity.noContent().build();
  }

}
