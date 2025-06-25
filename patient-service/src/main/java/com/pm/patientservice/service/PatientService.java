package com.pm.patientservice.service;

import com.pm.patientservice.dtos.PatientRequestDTO;
import com.pm.patientservice.dtos.PatientResponseDTO;
import com.pm.patientservice.exception.EmailAlreadyExistsException;
import com.pm.patientservice.exception.PatientNotFoundException;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Data
@AllArgsConstructor
public class PatientService {
    private PatientRepository patientRepository;

     public List<PatientResponseDTO> getPatients(){
         List<Patient>  patients = patientRepository.findAll();
         return patients.stream().map(PatientMapper::toDTO).toList();
     }

     public PatientResponseDTO getPatientById(UUID patientId){
         if(patientRepository.findById(patientId).isEmpty()){
             throw new PatientNotFoundException("Patient not found with id " + patientId);
         }
         Patient patient = patientRepository.getReferenceById(patientId);
         return PatientMapper.toDTO(patient);
     }

    public PatientResponseDTO addPatient(PatientRequestDTO patientRequestDTO) {
         Patient patient = PatientMapper.toEntity(patientRequestDTO);
         if(patientRepository.existsByEmail(patientRequestDTO.getEmail())){
             throw new EmailAlreadyExistsException("Patient with this email already exists " +  patientRequestDTO.getEmail());
         }
         patientRepository.save(patient);
         return PatientMapper.toDTO(patient);
    }

    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient not found with id " + id));

        if(patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)){
            throw new EmailAlreadyExistsException("Patient with this email already exists " +  patientRequestDTO.getEmail());
        }


        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        Patient updatedPatient = patientRepository.save(patient);
        return PatientMapper.toDTO(updatedPatient);
    }

    public void deletePatient(UUID id){
         patientRepository.deleteById(id);
    }
}
