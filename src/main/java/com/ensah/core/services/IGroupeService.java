package com.ensah.core.services;
import com.ensah.core.bo.*;

import com.ensah.core.bo.Groupe;
import java.util.List;

public interface IGroupeService {
    List<Groupe> getAllGroupes();
    Groupe getGroupeById(Long id);
//    void saveGroupe(Groupe groupe);
    void deleteGroupe(Long id);
}
