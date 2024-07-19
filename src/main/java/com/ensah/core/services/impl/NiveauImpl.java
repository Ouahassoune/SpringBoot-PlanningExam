package com.ensah.core.services.impl;

import com.ensah.core.bo.TypeElement;
import com.ensah.core.dao.INiveauRepository;
import com.ensah.core.dao.ITypeElementRepository;
import com.ensah.core.services.INiveau;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ensah.core.bo.Niveau;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NiveauImpl implements INiveau {

    @Autowired
    private INiveauRepository niveauRepository;

    public List<Niveau> getAllniveau() {
        return niveauRepository.findAll();
    }
    public Niveau getNiveauById(Long id) {
        // Utilisez votre repository ou autre méthode d'accès aux données pour récupérer le niveau correspondant à l'identifiant donné
        Optional<Niveau> niveauOptional = niveauRepository.findById(id);

        // Vérifiez si le niveau a été trouvé
        if (niveauOptional.isPresent()) {
            // Retournez le niveau trouvé
            return niveauOptional.get();
        } else {
            // Gérez le cas où le niveau n'est pas trouvé, par exemple, en lançant une exception ou en retournant null
            throw new RuntimeException("Niveau not found with id: " + id);
        }
    }

}
