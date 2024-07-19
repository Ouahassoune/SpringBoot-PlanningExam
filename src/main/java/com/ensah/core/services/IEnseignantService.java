package com.ensah.core.services;
import java.util.List;

import com.ensah.core.bo.Enseignant;
import com.ensah.core.utils.ExcelExporter;


public interface IEnseignantService {
    // Méthode pour récupérer tous les enseignants
    List<Enseignant> getAllEnseignants();

    // Méthode pour récupérer un enseignant par son identifiant
    Enseignant getEnseignantById(Long id);

    // Méthode pour enregistrer un nouvel enseignant
    void saveEnseignant(Enseignant enseignant);

    // Méthode pour mettre à jour un enseignant existant
    //void updateEnseignant(Enseignant enseignant);

    // Méthode pour supprimer un enseignant
  //  void deleteEnseignant(Long id);
}
