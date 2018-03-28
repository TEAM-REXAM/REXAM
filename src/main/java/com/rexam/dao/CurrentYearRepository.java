package com.rexam.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.rexam.model.CurrentYear;

public interface CurrentYearRepository extends CrudRepository<CurrentYear, Integer>{

    @Transactional
    @Modifying
    @Query(value="update CurrentYear set year = ?1")
    public void updateYear(Integer year);
}
