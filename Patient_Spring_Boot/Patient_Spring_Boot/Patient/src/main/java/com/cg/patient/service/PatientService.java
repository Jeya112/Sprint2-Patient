package com.cg.patient.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.cg.patient.domain.Doctor;
import com.cg.patient.domain.Hospital;
import com.cg.patient.domain.Patient;
import com.cg.patient.exception.DoctorException;
import com.cg.patient.exception.PatientException;
import com.cg.patient.repository.PatientRepository;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


@Service
public class PatientService {
	
	@Autowired
	PatientRepository repository;
    Doctor doctor = new Doctor("Arun","Orthopaedics",9563124752L);
	List<Doctor> doctorList = Arrays.asList(new Doctor[] {doctor});
	
	Hospital hospital= new Hospital("Arun",8382856782L,new String[]{"Ortho","Heart"},doctorList);
	
	public Patient saveOrUpdate(Patient patient) {

		try {
			patient.setPatientIdentifier(patient.getPatientIdentifier().toUpperCase());
			return repository.save(patient);
		} catch (Exception e) {
			throw new PatientException("PatientIdentifier " + patient.getPatientIdentifier() + " already available");
		}
}
    public Patient findPatienttByPatientIdentifier(String patientIdentifier) {
		Patient patient = repository.findByPatientIdentifier(patientIdentifier.toUpperCase());
		if (patient == null) {
			throw new PatientException("PatientIdentifier " + patientIdentifier + " not available");
		}
		return patient;
		}
	public Iterable<Patient> findAllPatients(){
		return repository.findAll();
		
	}
	public void deletePatientByPatientIdentifier(String patientIdentifier) {
		Patient patient=findPatienttByPatientIdentifier(patientIdentifier.toUpperCase());
		if(patient==null) {
			throw new PatientException("PatientIdentifier " + patientIdentifier + " not available");
		}
		repository.delete(patient);
	}
	
	public boolean searchDoctorBySpecialization(String specialization) {
		List<Doctor> doctorlist= this.hospital.getDoctorList();
		for(Doctor doctor:doctorlist) {
			if(doctor.getSpecialization().equalsIgnoreCase(specialization)) {
				return true;
				}
		}
	    return false;
	}
	
      public boolean bookDoctorAppointment(String doctorName, String specialization) {
    	  try {
			if(doctor.getDoctorName().equalsIgnoreCase(doctorName)) {
				for(Doctor doctor:hospital.getDoctorList()) {
					if(doctor.getSpecialization().equalsIgnoreCase(specialization))
						if(hospital.getNoOfDoctorAppointments()!=0)
						{
							hospital.setNoOfDoctorAppointments(hospital.getNoOfDoctorAppointments()-1);
							return true;
						}
					}
				return false;
			    }
			else {
				throw new DoctorException("Doctor name is not available");
			}
    	  
      }
			catch(DoctorException e){
				throw e;
			}
		
      }
}
			

