package com.ensah.core.dao;
import org.springframework.web.bind.annotation.*;
import com.ensah.core.bo.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ensah.core.bo.Salle;
import java.sql.Time;

public interface ISalleRepository extends JpaRepository<Salle, Long> {

    public  Salle findByIdSalle(Long idSalle);



  /*  @Query("SELECT s FROM Salle s WHERE s.idSalle NOT IN " +
            "(SELECT sur.salles.idSalle FROM Surveillance sur JOIN sur.exam ex WHERE ex.date = :date)")
    List<Salle> findAvailableSalles(@Param("date") Date date);

    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Exam e WHERE e.date = :date")
    boolean existsByDate(@Param("date") Date date);*/






    @Query("SELECT s FROM Salle s WHERE s.idSalle NOT IN " +
            "(SELECT sur.salles.idSalle FROM Surveillance sur JOIN sur.exam ex " +
            "WHERE ex.date = :date AND ex.heureDebut <= :heureFin AND ex.finPreuve >= :heureDebut)")
    List<Salle> findAvailableSalles(@Param("date") Date date,
                                    @Param("heureDebut") Time heureDebut,
                                    @Param("heureFin") Time heureFin);

    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END " +
            "FROM Exam e WHERE e.date = :date AND (:heureDebut BETWEEN e.heureDebut AND e.finPreuve OR :heureFin BETWEEN e.heureDebut AND e.finPreuve)")
    boolean existsByDate(@Param("date") Date date,
                         @Param("heureDebut") Time heureDebut,
                         @Param("heureFin") Time heureFin);


}


