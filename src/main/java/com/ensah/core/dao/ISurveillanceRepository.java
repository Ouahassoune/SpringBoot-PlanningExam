package com.ensah.core.dao;

import com.ensah.core.bo.Admin;
import com.ensah.core.bo.Enseignant;
import com.ensah.core.bo.Surveillance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface ISurveillanceRepository extends JpaRepository<Surveillance, Long> {

	@Query("SELECT s FROM Surveillance s JOIN s.enseignants e WHERE e = :enseignant")
	List<Surveillance> findByEnseignantsContains(@Param("enseignant") Enseignant enseignant);

	@Query("SELECT s FROM Surveillance s JOIN s.enseignants e WHERE e = :enseignant AND s.exam.date = :examDate")
	List<Surveillance> findByEnseignantsContainsAndExamDate(@Param("enseignant") Enseignant enseignant, @Param("examDate") Date examDate);

	@Query("SELECT s.admin FROM Surveillance s WHERE s.exam.date = :date AND s.exam.heureDebut <= :startTime AND s.exam.finReelle >= :endTime")
	List<Admin> findAdminsByExamDateAndTime(@Param("date") Date date, @Param("startTime") Time startTime, @Param("endTime") Time endTime);

}


