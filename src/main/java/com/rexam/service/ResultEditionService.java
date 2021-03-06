package com.rexam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rexam.dao.ComponentRepository;
import com.rexam.dao.RegistrationRepository;
import com.rexam.dao.ResultRepository;
import com.rexam.dao.TeachingUnitRepository;
import com.rexam.model.Component;
import com.rexam.model.Exam;
import com.rexam.model.Registration;
import com.rexam.model.Result;
import com.rexam.model.TeachingUnit;

@Service
public class ResultEditionService {

    @Autowired
    ResultRepository rRepository;

    @Autowired
    RegistrationRepository regRepository;

    @Autowired
    TeachingUnitRepository tuRepository;

    @Autowired
    ComponentRepository cRepository;

    public void computeAvg(Exam exam) {
        for (Component c : cRepository.findByExam(exam)) {
            for (TeachingUnit tu : tuRepository.findByComponent(c.getId())) {
                for (Registration reg : regRepository.findByIdCodeTeachingUnit(tu.getCode())) {
                    double avg = 0.0;
                    double weightSum = 0.0;
                    for (Component comp : reg.getTeachingUnit().getComponents()) {
                        avg += comp.getWeight()
                                * (rRepository.findByExamAndStudentYear(comp.getExam(),
                                        reg.getStudentYear())).getScore();
                        weightSum += comp.getWeight();
                    }

                    // rRepository.computeAvg(tu.getCode(),
                    // reg.getStudentYear().getId().getId(),
                    // reg.getStudentYear().getId().getYear());
                    if (weightSum > 0.0) {
                        reg.setAverageScore(avg / weightSum);
                        regRepository.save(reg);
                    }
                }
            }
        }
    }

    public void updateStatus(Exam exam) {
        for (Component c : cRepository.findByExam(exam)) {
            for (TeachingUnit tu : tuRepository.findByComponent(c.getId())) {
                for (Registration reg : regRepository.findByIdCodeTeachingUnit(tu.getCode())) {
                    boolean flag = true;
                    for (Component comp : reg.getTeachingUnit().getComponents()) {

                        Result res = rRepository.findByExamAndStudentYear(comp.getExam(),
                                reg.getStudentYear());

                        if (res.getDateObtened() == null || res.getDateObtened().equals("")) {
                            reg.setStatus("Partiellement calculable");
                            regRepository.save(reg);
                            // rRepository.setStatus(tu, reg.getStudentYear(),
                            // "Partiellement calculable");
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        reg.setStatus("Complétement calculable");
                        regRepository.save(reg);
                        // rRepository.setStatus(tu, reg.getStudentYear(),
                        // "Entièrement calculable");
                    }
                }
            }
        }
    }
}
