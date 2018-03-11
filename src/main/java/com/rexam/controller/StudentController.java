package com.rexam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rexam.dao.TeachingUnitRepository;
import com.rexam.model.TeachingUnit;

@Controller
public class StudentController {

	@Autowired
	TeachingUnitRepository tuRepository;

	@RequestMapping("/showTeachingUnits")
	public String home() {
		return "teachingUnits";
	}

	@RequestMapping("/addTeachingUnits")
	public String addTeachingUnits() {
		TeachingUnit u = new TeachingUnit();
		u.setCode("UE1");
		u.setName("nomUE");
		u.setDiscipline("Sciences");
		u.setCreditValue(3);
		tuRepository.save(u);
		return "index";
	}


	@ModelAttribute("teachingUnits")
	Iterable<TeachingUnit> teachingUnits() {
		return tuRepository.findAll();
	}
}