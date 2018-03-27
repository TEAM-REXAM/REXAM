package com.rexam.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rexam.dao.CurrentYearRepository;
import com.rexam.dao.ExamRepository;
import com.rexam.dao.RegistrationRepository;
import com.rexam.dao.ResultRepository;
import com.rexam.model.CurrentYear;
import com.rexam.model.TeachingUnit;
import com.rexam.service.RegistrationService;
import com.rexam.service.ResultEditionService;

@Controller
@RequestMapping("/admin")
public class ResultController {

    @Autowired
    ResultRepository rRepository;

    @Autowired
    ResultEditionService resService;

    @Autowired
    RegistrationService regService;

    @Autowired
    CurrentYearRepository yearRepository;

    @Autowired
    RegistrationRepository regRepository;

    @Autowired
    ExamRepository exRepository;

    @RequestMapping("/showTU")
    public ModelAndView showTU() {

        List<String> disciplines = regRepository.findDisciplines();
        List<TeachingUnit> tuList = regRepository.findTeachingUnits();

        ModelAndView mav = new ModelAndView("teachingUnits");
        mav.addObject("disciplines", disciplines);
        mav.addObject("tuList", tuList);
        return mav;
    }

    @RequestMapping("/showExamResults")
    public ModelAndView showExamResults(
            @ModelAttribute(value = "results") Results re) {

        ModelAndView mav = new ModelAndView("examResults");
        mav.addObject("results", re);
        return mav;
    }

    @RequestMapping("/editResults")
    public ModelAndView processEdition(@ModelAttribute(value = "results") Results re,
            BindingResult result) {

        if (!result.hasErrors()) {
            rRepository.save(re.getExamResults());
            resService.setStatus("Calculable", re.getExamResults().get(0).getExam());
        } 

        resService.computeAvg(re.getExamResults().get(0).getExam());
        
        return new ModelAndView("redirect:/admin/showTU");
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

        ModelAndView mav = new ModelAndView("redirect:/admin/showTU");
        return mav;
    }

    @ModelAttribute("results")
    Results examResutls(@RequestParam(value = "codeExam", required = false) String exam, ModelMap model) {
        if(exam == null)
            return (Results) model.get("results");
        
        Results re = new Results();
        re.setExamResults(rRepository.findByExam(exRepository.findOne(exam)));
        return re;
    }

    @ModelAttribute("currentYear")
    Integer currentYear() {
        return ((Collection<CurrentYear>) yearRepository.findAll()).stream().findFirst().get()
                .getYear();
    }

}