package com.ensah.core.services.impl;
import com.ensah.core.bo.Enseignant;
import com.ensah.core.dao.IFilliereRepository;
import com.ensah.core.services.IFilliereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ensah.core.bo.Filliere;

import java.util.List;
//import com.ensah.core.repositories.FilliereRepository;

@Service
public class FilliereServiceImpl implements IFilliereService {

    @Autowired
    private IFilliereRepository filliereRepository;


    @Override
    public List<Filliere> getAllFilieres() {
        return filliereRepository.findAll();
    }

    @Override
    public Filliere getFilliereById(Long id) {
        return filliereRepository.findById(id).orElse(null);
    }

    @Override
    public void saveFilliere(Filliere filliere) {
        filliereRepository.save(filliere);
    }

    @Override
    public void updateFilliere(Filliere filliere) {
        filliereRepository.save(filliere);
    }

    @Override
    public void deleteFilliere(Long id) {
        filliereRepository.deleteById(id);
    }
}
