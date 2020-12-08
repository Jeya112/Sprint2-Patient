/*
 * Creates the tests for the services provided
 * @ Jeya Prashanthini
 */
package com.cg.patient.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertTrue;



import com.cg.patient.domain.Doctor;
import com.cg.patient.domain.Patient;
import com.cg.patient.exception.DoctorException;
import com.cg.patient.exception.PatientException;
import com.cg.patient.domain.Hospital;
import com.cg.patient.repository.PatientRepository;

class PatientServiceTest {
	
	@Mock
	PatientRepository patientRepository;
	
	@InjectMocks
	PatientService patientService;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	void test1_saveOrUpdate()
	{
		Patient patient = new Patient("PA06","sam", 22,97401348L,"xyz");
		BDDMockito.given(patientRepository.save(patient)).willReturn(patient);
		Patient p = patientService.saveOrUpdate(patient);
		assertNotNull(p);
		assertEquals("sam",p.getPatientName());
		assertEquals("PA06",p.getPatientIdentifier());
		assertEquals(22,p.getPatientAge());
		assertEquals(97401348L,p.getPhoneNumber());
		assertEquals("xyz",p.getPatientAddress());

	}
	
	@Test
	void test2_saveOrUpdate_ThrowPatientException()
	{
		Patient patient = new Patient("PA06","sam", 22,97401348L,"xyz");
		BDDMockito.given(patientRepository.save(patient)).willThrow(new PatientException());
		assertThrows(PatientException.class, ()->patientService.saveOrUpdate(patient));
	}
	
	@Test
	void test_findPatientByPatientIdentifier() {
		BDDMockito.given(patientRepository.findByPatientIdentifier("PA01")).willReturn((new Patient("PA01","abc",22,97401348L,"xyz")));
		Patient patient= patientService.findPatienttByPatientIdentifier("PA01");
		assertNotNull(patient);
		assertEquals("abc", patient.getPatientName());
		assertEquals("PA01",patient.getPatientIdentifier());
		assertEquals(22, patient.getPatientAge());
		assertEquals(97401348L,patient.getPhoneNumber());
		assertEquals("xyz",patient.getPatientAddress());
	}
	
	@Test
	void test2_findPatientByPatientIDentifier__ThrowPatientException()
	{
		BDDMockito.given(patientRepository.findByPatientIdentifier("PA10")).willThrow(new PatientException());
		assertThrows(PatientException.class, ()->patientService.findPatienttByPatientIdentifier("PA10"));
	}
	
	
	@Test
	void test_searchDoctorBySpecialization() {
		Doctor doctor = new Doctor("Arun","Orthopaedics",9563124752L);
		List<Doctor> doctorList = Arrays.asList(new Doctor[] {doctor});
        assertNotNull(doctorList);
        assertEquals("Arun",  patientService.doctor.getDoctorName());
	    assertEquals(9563124752L,  patientService.doctor.getDoctorPhNo());
	    assertEquals("Orthopaedics", patientService.doctor.getSpecialization());
	}
	
	@Test
	void test_bookDoctorAppointment() {
		Doctor doctor = new Doctor("Arun","Orthopaedics",9563124752L);
		List<Doctor> doctorList = Arrays.asList(new Doctor[] {doctor});
        assertNotNull(doctorList);
        if(patientService.doctor.getDoctorName().equalsIgnoreCase("Arun"))
        {
        	assertEquals("Orthopaedics", patientService.doctor.getSpecialization());
        }
       }
	
	
	
}
