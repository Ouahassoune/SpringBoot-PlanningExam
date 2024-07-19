package com.ensah;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import com.ensah.core.dao.*;
import com.ensah.core.bo.*;
import jakarta.annotation.PostConstruct;


@Component
public class DataInitializer {

    @Autowired
    private IEnseignantRepository enseignantRepository;

    @Autowired
    private ITypeElementRepository typeElementRepository;

    @Autowired
    private INiveauRepository niveauRepository;

    @Autowired
    private IElementPedagogiqueRepository elementPedagogiqueRepository;

    @PostConstruct
    public void init() {
        Enseignant enseignant1 = new Enseignant("John", "Doe");
        Enseignant enseignant2 = new Enseignant("Jane", "Smith");

        enseignantRepository.saveAll(Arrays.asList(enseignant1, enseignant2));

        TypeElement typeElement1 = new TypeElement("Module");
        TypeElement typeElement2 = new TypeElement("Element");

        typeElementRepository.saveAll(Arrays.asList(typeElement1, typeElement2));

        Niveau niveau1 = new Niveau("CP1");
        Niveau niveau2 = new Niveau("CP2");

        niveauRepository.saveAll(Arrays.asList(niveau1, niveau2));

        ElementPedagogique ep1 = new ElementPedagogique("Mathematics", enseignant1, enseignant2, typeElement1, niveau1);
        ElementPedagogique ep2 = new ElementPedagogique("History", enseignant2, enseignant1, typeElement2, niveau2);

        elementPedagogiqueRepository.saveAll(Arrays.asList(ep1, ep2));
    }
}
