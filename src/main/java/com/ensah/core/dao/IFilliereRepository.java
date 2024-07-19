package com.ensah.core.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ensah.core.bo.Filliere;

@Repository
public interface IFilliereRepository extends JpaRepository<Filliere, Long> {
    // Vous pouvez ajouter des méthodes personnalisées si nécessaire
}
