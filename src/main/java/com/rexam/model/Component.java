package com.rexam.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Component implements Serializable{
	
	/**
     * 
     */
    private static final long serialVersionUID = 7003966455711016986L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
	@OneToOne(cascade = {CascadeType.ALL})
	private Exam exam;
	
	@NotNull
    @Min(value = 1, message = "Le poids doit être supérieur à 0")
	private int weight;

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
