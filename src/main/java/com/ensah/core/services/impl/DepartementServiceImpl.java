package com.ensah.core.services.impl;

import com.ensah.core.bo.Filliere;
import com.ensah.core.dao.IDepartementRepository;
import com.ensah.core.services.IDepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ensah.core.bo.Departement;

import java.util.List;
//import com.ensah.core.repositories.DepartementRepository;

@Service
public class DepartementServiceImpl implements IDepartementService {

    @Autowired
    private IDepartementRepository departementRepository;

    @Override
    public List<Departement> getAllDepartements() {
        return departementRepository.findAll();
    }

    @Override
    public Departement getDepartementById(Long id) {
        return departementRepository.findById(id).orElse(null);
    }

    @Override
    public void saveDepartement(Departement departement) {
        departementRepository.save(departement);
    }

    @Override
    public void updateDepartement(Departement departement) {
        departementRepository.save(departement);
    }

    @Override
    public void deleteDepartement(Long id) {
        departementRepository.deleteById(id);
    }
}
