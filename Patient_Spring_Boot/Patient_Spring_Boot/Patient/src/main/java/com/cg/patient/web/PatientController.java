package com.cg.patient.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.patient.domain.Doctor;
import com.cg.patient.service.MapValidationErrorService;
import com.cg.patient.service.PatientService;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;

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



