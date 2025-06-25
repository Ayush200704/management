package com.pm.patientservice.mapper;

import com.pm.patientservice.dtos.PatientRequestDTO;
import com.pm.patientservice.dtos.PatientResponseDTO;
import com.pm.patientservice.model.Patient;

import java.time.LocalDate;

public class PatientMapper {
    public static PatientResponseDTO toDTO(Patient p){
        PatientResponseDTO dto = new PatientResponseDTO();
        dto.setId(p.getId().toString());
        dto.setName(p.getName());
        dto.setEmail(p.getEmail());
        dto.setAddress(p.getAddress());
        dto.setDateOfBirth(p.getDateOfBirth().toString());
        return dto;
    }

    public static Patient toEntity(PatientRequestDTO p){
        Patient patient = new Patient();
        patient.setName(p.getName());
        patient.setEmail(p.getEmail());
        patient.setAddress(p.getAddress());
        patient.setDateOfBirth(LocalDate.parse(p.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.parse(p.getRegisteredDate()));
        return patient;
    }
}
