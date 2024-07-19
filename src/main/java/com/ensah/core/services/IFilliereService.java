package com.ensah.core.services;
import com.ensah.core.bo.*;

import java.util.List;

public interface IFilliereService {
    Filliere getFilliereById(Long id);
    void saveFilliere(Filliere filliere);
    void updateFilliere(Filliere filliere);
    void deleteFilliere(Long id);

    List<Filliere> getAllFilieres();
}
