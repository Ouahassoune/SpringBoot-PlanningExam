package com.ensah.core.bo;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Niveau {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNiveau;
    private String titre;

    // Default constructor
    public Niveau() {
        // Initialize any fields or perform any necessary setup here
    }

    public Niveau(String nom) {
        this.titre = nom;
    }

    @OneToMany(mappedBy = "niveau", cascade = CascadeType.ALL)
    private List<ElementPedagogique> elements;


    public Long getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(Long idNiveau) {
        this.idNiveau = idNiveau;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
    public List<ElementPedagogique> getElements() {
        return elements;
    }

    public void setElements(List<ElementPedagogique> elements) {
        this.elements = elements;
    }

}
