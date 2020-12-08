/**
 * Creates controllers for the necessary services.
 * @author Jeya Prashanthini
 *
 */
package com.cg.patient.web;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.patient.domain.Doctor;
import com.cg.patient.domain.Patient;
import com.cg.patient.service.MapValidationErrorService;
import com.cg.patient.service.PatientService;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	/*
	 * Controller for saving or updating new patients
	 */
	@PostMapping("")
	public ResponseEntity<?> createNewPatient(@Valid @RequestBody Patient patient, BindingResult result)
	{
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null)
			return errorMap;
		Patient newPatient= patientService.saveOrUpdate(patient);
		return new ResponseEntity<Patient>(newPatient,HttpStatus.CREATED);
	}
	/*
	 * Controller for finding patients by identifier
	 */
	@GetMapping("/{patientIdentifier}")
	public ResponseEntity<?> getPatientById(@PathVariable String patientIdentifier){
		return new ResponseEntity<Patient>( patientService.findPatienttByPatientIdentifier(patientIdentifier),HttpStatus.OK);
	}
	/*
	 * Controller for finding all patients
	 */

	@GetMapping("/all")
	public Iterable<Patient> getAllPatients(){
		return patientService.findAllPatients();
	}
	/*
	 * Controller for deleting patient by identifier
	 */
	
	@DeleteMapping("/{patientIdentifier}")
	public ResponseEntity<?> deletePatient(@PathVariable String patientIdentifier){
		patientService.deletePatientByPatientIdentifier(patientIdentifier);
		return new ResponseEntity<String> ("Project with Id : "+patientIdentifier.toUpperCase()+" Deleted!",HttpStatus.OK);
	}
	/*
	 * Controller for searching doctor by specialization
	 */

	@PostMapping("/searchDoctorBySpecialization")
	private ResponseEntity<?> searchByDoctorSpecialization(@RequestBody Map<String, String> specializationMap){
		String specialization = specializationMap.get("specialization");
		boolean availability = patientService.searchDoctorBySpecialization(specialization) ;
		if(availability) {
			return new ResponseEntity<String>("Doctor with specialization in "+specialization+ " is found in the hospital",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Doctor with specialization in " +specialization+ " is not found in the hospital",HttpStatus.BAD_REQUEST);
		}
	}
	/*
	 * Controller for booking doctor appointment based on specialization
	 */
	
    @RequestMapping(value = "/bookDoctorAppointment", method = RequestMethod.POST)
	private ResponseEntity<?> bookDoctorAppointment(@RequestBody Map<String, String> doctorMap){
		String doctorName = doctorMap.get("doctorName");
		String specialization= doctorMap.get("specialization");
		boolean doctorAppointment = patientService.bookDoctorAppointment(doctorName,specialization);
		if(doctorAppointment) {
			return new ResponseEntity<String>("Your appointment for doctor "+doctorName+ " is booked.",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Your appointment for doctor "+doctorName+ " is not booked.",HttpStatus.BAD_REQUEST);
		}
		
	}
		
	}



