package com.rexam.controller;



import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rexam.dao.CurrentYearRepository;
import com.rexam.dao.ResultRepository;
import com.rexam.dao.TeachingUnitRepository;
import com.rexam.model.CurrentYear;
import com.rexam.model.Exam;
import com.rexam.service.RegistrationService;
import com.rexam.service.ResultEditionService;

@Controller
@RequestMapping("/admin")
public class ResultController {

    @Autowired
    ResultRepository rRepository;
    
    @Autowired
    private TeachingUnitRepository tuRepository;

    @Autowired
    ResultEditionService resService;
    
    @Autowired
    RegistrationService regService;
    
    @Autowired
    CurrentYearRepository yearRepository;
    
    @RequestMapping("/showExamResults")
    public ModelAndView showExamResults(@ModelAttribute(value = "results") Results examResults) {

        ModelAndView mav = new ModelAndView("examResults");
        return mav;
    }

    @RequestMapping("/editResults")
    public ModelAndView showExams(@ModelAttribute(value = "results") Results re,
            BindingResult result) {

        if (!result.hasErrors()) {
            rRepository.save(re.getExamResults());
        }

        resService.computeAvg(re.getExamResults().get(0).getExam());
        
        return new ModelAndView("index");
    }

    @RequestMapping("/initData")
    public ModelAndView addTeachingUnits(@ModelAttribute(value = "results") Results examResults) {
        try {
            regService.registration("srowlands0@vimeo.com", "ENSPHCU89");
            regService.registration("srowlands0@vimeo.com", "ENSPHCU97");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        ModelAndView mav = new ModelAndView("redirect:/showExamResults");
        return mav;
    }
    
    @ModelAttribute("results")
    Results examResutls(@RequestParam(value = "exam", required = false) Exam exam) {
        Results re = new Results();
        re.setExamResults(rRepository.findByExam(tuRepository.findOne("ENSPHCU89").getComponents().get(0).getExam()));
        return re;
    }
    
	@ModelAttribute("currentYear")
	Integer currentYear() {
		return ((Collection<CurrentYear>) yearRepository.findAll())
					.stream().findFirst().get().getYear();
	}

}