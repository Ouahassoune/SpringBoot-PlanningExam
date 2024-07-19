package com.ensah.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ensah.core.bo.Disponibilite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface DisponibiliteRepository extends JpaRepository<Disponibilite, Long> {
}
