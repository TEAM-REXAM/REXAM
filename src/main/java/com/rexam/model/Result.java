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
public class Result {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String dateObtened;
	
	private Double score;
	
	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private List<StudentYear> studentYear;
	
	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private List<Exam> exam;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDateObtened() {
		return dateObtened;
	}

	public void setDateObtened(String dateObtened) {
		this.dateObtened = dateObtened;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public List<StudentYear> getStudentYear() {
		return studentYear;
	}

	public void setStudentYear(List<StudentYear> studentYear) {
		this.studentYear = studentYear;
	}

	public List<Exam> getExam() {
		return exam;
	}

	public void setExam(List<Exam> exam) {
		this.exam = exam;
	}

}
