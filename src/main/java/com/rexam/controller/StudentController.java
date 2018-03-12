package com.rexam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rexam.dao.TeachingUnitRepository;
import com.rexam.model.TeachingUnit;

@Controller
public class StudentController {

	@Autowired
	TeachingUnitRepository tuRepository;

	@RequestMapping("/showTeachingUnits")
	public ModelAndView showDisciplines() {
		List<String> disciplines = tuRepository.findDisciplines();
		ModelAndView mav = new ModelAndView("teachingUnits");
		mav.addObject("disciplines", disciplines);
		return mav;
	}

	@RequestMapping("/addTeachingUnits")
	public String addTeachingUnits() {
		TeachingUnit u = new TeachingUnit();
		u.setCode("UE1");
		u.setName("nomUE");
		u.setDiscipline("Sciences");
		u.setCreditValue(3);
		tuRepository.save(u);
		TeachingUnit u2 = new TeachingUnit();
		u2.setCode("UE2");
		u2.setName("philo");
		u2.setDiscipline("Lettres");
		u2.setCreditValue(2);
		tuRepository.save(u2);
		return "index";
	}


	@ModelAttribute("teachingUnits")
	Iterable<TeachingUnit> teachingUnits() {
		return tuRepository.findAll();
	}
}