package com.ensah.core.services;
import com.ensah.core.bo.*;

import java.util.List;


public interface IDepartementService {
    Departement getDepartementById(Long id);
    void saveDepartement(Departement departement);
    void updateDepartement(Departement departement);
    void deleteDepartement(Long id);
    List<Departement> getAllDepartements();
}
