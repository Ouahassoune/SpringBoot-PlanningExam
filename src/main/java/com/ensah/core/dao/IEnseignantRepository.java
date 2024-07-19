package com.ensah.core.dao;
import com.ensah.core.bo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IEnseignantRepository extends JpaRepository<Enseignant, Long> {
    // Trouver les enseignants par département
    List<Enseignant> findByDepartementId(Long departementId);

    // Trouver les enseignants par filière
    List<Enseignant> findByFilliereId(Long filiereId);

    // Trouver les enseignants par leurs IDs
    List<Enseignant> findAllById(Iterable<Long> ids);
}