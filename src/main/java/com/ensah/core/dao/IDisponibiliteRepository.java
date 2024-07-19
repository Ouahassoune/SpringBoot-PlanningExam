
package com.ensah.core.dao;

import com.ensah.core.bo.Disponibilite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface IDisponibiliteRepository extends JpaRepository<Disponibilite, Long> {
	@Query("SELECT d FROM Disponibilite d WHERE d.date = :date AND d.heureDebut <= :startTime AND d.heureFin >= :endTime")
	List<Disponibilite> findAvailableEnseignants(@Param("date") Date date, @Param("startTime") Time startTime, @Param("endTime") Time endTime);
	
	@Query("SELECT d FROM Disponibilite d WHERE d.enseignant.id = :enseignantId AND d.date = :date")
	List<Disponibilite> findDisponibilitesByEnseignantAndDate(@Param("enseignantId") Long enseignantId, @Param("date") Date date);



}
