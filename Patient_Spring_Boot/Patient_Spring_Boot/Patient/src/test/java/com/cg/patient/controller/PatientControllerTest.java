package com.cg.patient.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.validation.BindingResult;

import com.cg.patient.domain.Hospital;
import com.cg.patient.domain.Patient;
import com.cg.patient.exception.PatientException;
import com.cg.patient.domain.Doctor;
import com.cg.patient.service.MapValidationErrorService;
import com.cg.patient.service.PatientService;
import com.cg.patient.web.PatientController;
import com.fasterxml.jackson.databind.ObjectMapper;
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PatientController.class)

class PatientControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	PatientService patientService;

	@MockBean
	MapValidationErrorService mapValidationErrorService;
	
	@Test
	void test1_getPatientById() throws Exception
	{
		BDDMockito.given(patientService.findPatienttByPatientIdentifier("PA06")).willReturn(new Patient("PA06","abc",22,87307L,"xyz"));
		mockMvc.perform(get("/api/patients/PA06"))
		.andExpect(status().isOk())
		//.andExpect(jsonPath("$").isMap())
		.andExpect(jsonPath("patientName").value("abc"))
		.andExpect(jsonPath("patientIdentifier").value("PA06"))
		.andExpect(jsonPath("patientAge").value(22))
		.andExpect(jsonPath("phoneNumber").value(87307L));
	}

	@Test
	void test2_getPatientById() throws Exception
	{
		BDDMockito.given(patientService.findPatienttByPatientIdentifier("PA06")).willThrow(new PatientException());
		mockMvc.perform(get("/api/patients/PA06"))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	void test3_searchDoctorBySpecialization() throws Exception
	{
		when(patientService.searchDoctorBySpecialization("Orthopaedics")).thenReturn(true);
		mockMvc.perform(get("/api/patients/searchDoctorBySpecialization"))
		.andExpect(status().isOk());
	}
	
	@Test
	void test4_searchDoctorBySpecialization() throws Exception
	{
		when(patientService.searchDoctorBySpecialization("Heart")).thenReturn(false);
		mockMvc.perform(post("/api/patients/searchDoctorBySpecialization"))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	void test5_bookDoctorAppointment() throws Exception
	{
		when(patientService.bookDoctorAppointment("Arun","Orthopaedics")).thenReturn(true);
		mockMvc.perform(get("/api/patients/bookDoctorAppointment"))
		.andExpect(status().isOk());
	}
	
	@Test
	void test6_bookDoctorAppointment() throws Exception
	{
		when(patientService.bookDoctorAppointment("Arya","Ortho")).thenReturn(false);
		mockMvc.perform(post("/api/patients/bookDoctorAppointment"))
		.andExpect(status().isBadRequest());
	}
    
	
}
	