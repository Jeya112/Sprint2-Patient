/**
 * Creates a information of the hospital stating name,specialization,list of doctors,phone number etc
 * @author Jeya Prashanthini
 *
 */
package com.cg.patient.domain;

import java.util.Arrays;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class Hospital {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private long hospitalId;
	private String hospitalName;
	private long hospitalPhNO;
	private String[] hospitalSpecialities;
    private List<Doctor> doctorList;
    private static int noOfDoctorAppointments=3;
	/**
	 * Create an instance of hospital with given parameter
	 * @param hospitalName
	 * @param hospitalAddress
	 * @param hospitalPhNO
	 */
	public Hospital(String hospitalName, long hospitalPhNO, String[] hospitalSpecialities,List<Doctor> doctorList) {
		super();
		this.hospitalName = hospitalName;
		this.hospitalPhNO = hospitalPhNO;
		this.hospitalSpecialities = hospitalSpecialities;
		this.doctorList = doctorList;
	}
	public Hospital() {
		super();
	}
	/**
	 * return hospital specialty
	 */
	public String[] getHospitalSpecialities() {
		return hospitalSpecialities;
	}
	/**
	 * set the hospital Speciality
	 * @param hospitalSpeciality
	 */
	public void setHospitalSpecialities(String[] hospitalSpecialities) {
		this.hospitalSpecialities = hospitalSpecialities;
	}
	
	public void setHospitalSpeciality(String[] hospitalSpecialities) {
		this.hospitalSpecialities = hospitalSpecialities;
	}
	
//	/**
//	 * @return the hospital ID
//	 */
//	public long getHospitalId() {
//		return hospitalId;
//	}
//	/**
//	 * Set the hospital ID
//	 * @param hospitalId
//	 */
//	public void setHospitalId(long hospitalId) {
//		this.hospitalId = hospitalId;
//	}
	/**
	 * @return hospital name 
	 */
	public String getHospitalName() {
		return hospitalName;
	}
	/**
	 * Set the name of the hospital
	 * @param hospitalName
	 */
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	
	/**
	 * @return the hospital phone number 
	 */
	public long getHospitalPhNO() {
		return hospitalPhNO;
	}
	/**
	 * Set the PhNo of the hospital
	 * @param hospitalPhNo
	 */
	public void setHospitalPhNO(long hospitalPhNO) {
		this.hospitalPhNO = hospitalPhNO;
	}
	/**
	 * @return the doctor list 
	 */
	public List<Doctor> getDoctorList() {
		return doctorList;
	}
	/**
	 * Set the list of doctors
	 * @param doctorList
	 */
	public void setDoctorList(List<Doctor> doctorList) {
		this.doctorList = doctorList;
	}
	
	/**
	 * @return the no of Appointments
	 */
	public static int getNoOfDoctorAppointments() {
		return noOfDoctorAppointments;
	}
	/**
	 * Set the no Of Doctor Appointments
	 * @param noOfDoctorAppointments
	 */
	public static void setNoOfDoctorAppointments(int noOfDoctorAppointments) {
		Hospital.noOfDoctorAppointments = noOfDoctorAppointments;
	}
	
	@Override
	public String toString() {
		return "Hospital [hospitalName=" + hospitalName + ", hospitalPhNO=" + hospitalPhNO + ", hospitalSpecialities="
				+ Arrays.toString(hospitalSpecialities) + ", doctorList=" + doctorList + "]";
	}
	
	
	
	
}
