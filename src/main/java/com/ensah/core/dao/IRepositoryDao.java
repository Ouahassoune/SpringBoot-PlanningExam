package com.ensah.core.dao;


import com.ensah.core.bo.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryDao extends JpaRepository<Role, Long> {

}
