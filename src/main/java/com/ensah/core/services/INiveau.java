package com.ensah.core.services;

import com.ensah.core.bo.Niveau;
import com.ensah.core.bo.TypeElement;

import java.util.List;

public interface INiveau {
    List<Niveau> getAllniveau();
    Niveau getNiveauById(Long id);
}

