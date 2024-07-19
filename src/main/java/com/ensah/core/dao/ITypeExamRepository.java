package com.ensah.core.dao;

import com.ensah.core.bo.TypeExam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITypeExamRepository extends JpaRepository<TypeExam, Long> {
    TypeExam findByIdTypeExam(Long idTypeExam);
}
