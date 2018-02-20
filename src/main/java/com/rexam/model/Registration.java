package com.rexam.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Registration {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private List<TeachingUnit> teachingUnit;
	
	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private List<StudentYear> studentYear;
	
	private Double averageScore;
	private String status;
	
	public List<TeachingUnit> getTeachingUnit() {
		return teachingUnit;
	}
	public void setTeachingUnit(List<TeachingUnit> teachingUnit) {
		this.teachingUnit = teachingUnit;
	}
	public List<StudentYear> getStudentYear() {
		return studentYear;
	}
	public void setStudentYear(List<StudentYear> studentYear) {
		this.studentYear = studentYear;
	}
	public Double getAverageScore() {
		return averageScore;
	}
	public void setAverageScore(Double averageScore) {
		this.averageScore = averageScore;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
