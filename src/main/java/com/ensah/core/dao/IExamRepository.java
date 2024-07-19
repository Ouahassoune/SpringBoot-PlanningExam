package com.ensah.core.dao;

import com.ensah.core.bo.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface IExamRepository extends JpaRepository<Exam, Long> {
    Exam findByDate(Date date);
    public Exam findByIdExam(Long idExam);


}



