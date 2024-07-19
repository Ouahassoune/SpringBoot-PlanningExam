package com.ensah.core.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ensah.core.bo.Departement;

@Repository
public interface IDepartementRepository extends JpaRepository<Departement, Long> {
    Departement findByNom(String nom);
    // Vous pouvez ajouter des méthodes personnalisées si nécessaire
}
