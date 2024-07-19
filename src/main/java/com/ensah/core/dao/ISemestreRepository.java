package com.ensah.core.dao;

import com.ensah.core.bo.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISemestreRepository extends JpaRepository<Semestre, Long> {
    Semestre findByIdSemestre(Long idSemestre);
}
