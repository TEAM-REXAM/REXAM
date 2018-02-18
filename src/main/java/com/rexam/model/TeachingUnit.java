package com.rexam.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TeachingUnit {

	@Id
	private String code;
	private String name;
	private int creditValue;
	private String discipline;
	
	@OneToMany(cascade = {CascadeType.ALL})
	private List<Component> components;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getDiscipline() {
		return discipline;
	}

	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}

	public List<Component> getComponents() {
		return components;
	}

	public void setComponents(List<Component> components) {
		this.components = components;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getCreditValue() {
		return creditValue;
	}

	public void setCreditValue(int creditValue) {
		this.creditValue = creditValue;
	}
}
