package com.rexam.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "email")
public class Student extends User{

	private String firstname;
	private String lastname;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private List<StudentYear> studentYear;
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public List<StudentYear> getStudentYear() {
		return studentYear;
	}

	public void setStudentYear(List<StudentYear> studentYear) {
		this.studentYear = studentYear;
	}
}
