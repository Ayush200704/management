package com.pm.patientservice.repository;

import com.pm.patientservice.model.Patient;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@Registered
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, UUID id);
}
