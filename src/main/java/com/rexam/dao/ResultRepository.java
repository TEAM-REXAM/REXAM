package com.rexam.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.rexam.model.Exam;
import com.rexam.model.IdResult;
import com.rexam.model.Result;
import com.rexam.model.Student;
import com.rexam.model.StudentYear;
import com.rexam.model.TeachingUnit;

public interface ResultRepository extends CrudRepository<Result, IdResult> {

    @Query(value="select res from Result res, CurrentYear cy where res.exam = ?1 and res.studentYear.id.year = cy.year")
    public List<Result> findByExam(Exam exam);

    @Transactional
    @Modifying
    @Query(value="update Registration set average_score"
            + "=(select sum(r.score*c.weight)/sum(c.weight)"
            + "from teaching_unit_components tucs, Component c,Exam e, Result r"
            + " where tucs.teaching_unit_code = ?1 and c.id = (tucs.components_id)"
            + " and c.exam_code = e.code and r.exam_code = e.code and r.student_year_id = ?2"
            + " and student_year_year = ?3) "
            + "where teaching_unit_code=?1 and student_year_id = ?2 and student_year_year = ?3",nativeQuery = true)
    public void computeAvg(String tu_code, Integer sy_id, Integer sy_y);
    
    @Transactional
    @Modifying
    @Query(value="update Registration set status"
            + "=?3 where teachingUnit=?1 and studentYear = ?2")
    public void setStatus(TeachingUnit tu, StudentYear sy, String status);

    @Query(value="select res from Result res where res.exam = ?1 and res.studentYear.student=?2")
    public Result findByExamAndStudent(Exam exam, Student student);
    
    public Result findByExamAndStudentYear(Exam exam, StudentYear studentYear);
    
}
