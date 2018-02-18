package com.rexam.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Exam {
	
	@Id
	private String code;
	
	private String typeExam;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTypeExam() {
		return typeExam;
	}

	public void setTypeExam(String typeExam) {
		this.typeExam = typeExam;
	}
}
