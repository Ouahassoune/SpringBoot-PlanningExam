package com.ensah.core.services.impl;

import com.ensah.core.bo.TypeElement;
import com.ensah.core.dao.ITypeElementRepository;
import com.ensah.core.services.ITypeElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TypeElementService implements ITypeElementService {

    @Autowired
    private ITypeElementRepository typeElementRepositoryRepository;

    public List<TypeElement> getAllTypeElement() {
        return typeElementRepositoryRepository.findAll();
    }

    public TypeElement getTypeById(Long id) {
        // Utilisez votre repository ou autre méthode d'accès aux données pour récupérer le type correspondant à l'identifiant donné
        Optional<TypeElement> typeOptional = typeElementRepositoryRepository.findById(id);

        // Vérifiez si le type a été trouvé
        if (typeOptional.isPresent()) {
            // Retournez le type trouvé
            return typeOptional.get();
        } else {
            // Gérez le cas où le type n'est pas trouvé, par exemple, en lançant une exception ou en retournant null
            throw new RuntimeException("Type not found with id: " + id);
        }
    }

}
