package com.ensah.core.services.impl;

import com.ensah.core.bo.Enseignant;
import com.ensah.core.dao.*;
import com.ensah.core.services.IEnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EnseignantService implements IEnseignantService {

    @Autowired
    private IPersonneRepository personDao;
    @Autowired
    private IEnseignantRepository enseignantRepository;
    @Autowired
    private IFilliereRepository filliereRepository;
    @Autowired
    private IDepartementRepository departementRepository;
    @Autowired
    private IAdminRepository adminRepository;


    @Override
    public List<Enseignant> getAllEnseignants() {
        return enseignantRepository.findAll();
    }

    public Enseignant getEnseignantById(Long id) {
        return enseignantRepository.findById(id).orElse(null);
    }
    @Override
    public void saveEnseignant(Enseignant enseignant) {
        enseignant.setFilliere(filliereRepository.findById(enseignant.getFilliere().getId()).orElse(null));
        enseignant.setDepartement(departementRepository.findById(enseignant.getDepartement().getId()).orElse(null));
        enseignantRepository.save(enseignant);
    }
}
