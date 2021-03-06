package com.cg.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.patient.domain.Doctor;
import com.cg.patient.domain.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> { 
	
               Patient findByPatientIdentifier(String patientIdentifier);
		
			
             

            }
