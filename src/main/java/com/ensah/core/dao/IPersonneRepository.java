package com.ensah.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensah.core.bo.Personnel;

public interface IPersonneRepository extends JpaRepository<Personnel, Long> {


}
