package com.ensah.core.dao;

import com.ensah.core.bo.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISessionRepository extends JpaRepository<Session, Long>  {

Session findByIdSession(Long idSession);
}
