package com.cg.patient.exception;

public class DoctorException extends  RuntimeException {
	public DoctorException() {
		super();
	}
	
	public DoctorException(String errMessage) {
		super(errMessage);
	}


}
