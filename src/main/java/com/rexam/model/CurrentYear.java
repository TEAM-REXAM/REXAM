package com.rexam.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CurrentYear {

	@Id
	private int year;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
