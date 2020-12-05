package com.cg.patient.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

//@Entity
public class Doctor {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private long id; 
	private String doctorName;
	//private String doctorIdentifier;
	private String specialization;
    private long doctorPhNo;
    
    public Doctor() {
		super();
		}
    /**
	 * Create an instance of patient with given parameter
	 * @param doctorName
	 * @param specialization
	 * @param doctorPhNO
	 */
    public Doctor(String doctorName, String specialization, long doctorPhNo) {
		super();
		this.doctorName = doctorName;
		this.specialization = specialization;
		this.doctorPhNo = doctorPhNo;
	}
//	public long getId() {
//		return id;
//	}
//    public void setId(long id) {
//		this.id = id;
//	}

	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	
//    public String getDoctorIdentifier() {
//		return doctorIdentifier;
//	}
//
//	public void setDoctorIdentifier(String doctorIdentifier) {
//		this.doctorIdentifier = doctorIdentifier;
//	}
	
	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public long getDoctorPhNo() {
		return doctorPhNo;
	}

	public void setDoctorPhNo(long doctorPhNo) {
		this.doctorPhNo = doctorPhNo;
	}
}
