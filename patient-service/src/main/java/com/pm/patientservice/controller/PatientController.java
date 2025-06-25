package com.pm.patientservice.controller;

import com.pm.patientservice.dtos.PatientRequestDTO;
import com.pm.patientservice.dtos.PatientResponseDTO;
import com.pm.patientservice.dtos.validators.CreatePatientValidationGroup;
import com.pm.patientservice.service.PatientService;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@AllArgsConstructor
public class PatientController {
    private PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        return ResponseEntity.ok(patientService.getPatients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable UUID id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PostMapping
    public ResponseEntity<PatientResponseDTO> addPatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO patientResponseDTO = patientService.addPatient(patientRequestDTO);
        return ResponseEntity.ok(patientResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id, @Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO patientResponseDTO = patientService.updatePatient(id, patientRequestDTO);
        return ResponseEntity.ok(patientResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok().build();
    }
}
