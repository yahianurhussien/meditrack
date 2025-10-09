package com.meditrack.appointment_service.controller;

import com.meditrack.appointment_service.dto.AppointmentRequest;
import com.meditrack.appointment_service.dto.AppointmentResponse;
import com.meditrack.appointment_service.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;


    @Operation(
            summary = "Add a new Appointment",
            description = "Creates and saves a new appointment record in the system"
    )
    @ApiResponse(responseCode = "201", description = "appointment created successfully")
    @PostMapping
    public ResponseEntity<AppointmentResponse> create(@RequestBody AppointmentRequest request) {
        return ResponseEntity.ok(appointmentService.createAppointment(request));
    }



    @Operation(
            summary = "Get appointment by ID",
            description = "Retrieves a specific appointment’s details using ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "appointment found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentResponse.class))
    )
    @ApiResponse(responseCode = "404", description = "appointment not found")

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponse> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }



    @Operation(
            summary = "Get appointment by doctor's ID",
            description = "Retrieves a specific appointment’s details using doctor's ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "appointment found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentResponse.class))
    )
    @ApiResponse(responseCode = "404", description = "appointment not found")
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentResponse>> getByDoctor(@PathVariable("doctorId") Long doctorId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByDoctor(doctorId));
    }




    @Operation(
            summary = "Get appointment by patient's ID",
            description = "Retrieves a specific appointment’s details using patient's ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "appointment found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentResponse.class))
    )
    @ApiResponse(responseCode = "404", description = "appointment not found")
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppointmentResponse>> getByPatient(@PathVariable("patientId") Long patientId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByPatient(patientId));
    }


    @Operation(
            summary = "Cancel appointment by ID",
            description = "Cancel an appointment by ID"
    )

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "appointment cancelled successfully"),
            @ApiResponse(responseCode = "404", description = "appointment not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancel(@PathVariable("id") Long id) {
        appointmentService.cancelAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
