package com.ensah.core.bo;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class TypeElement {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idType;
    private String titre;

    public TypeElement() {
        // Initialize any fields or perform any necessary setup here
    }

    public TypeElement(String nom) {
        this.titre = nom;
    }
    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private List<ElementPedagogique> elements;
    public Long getIdType() {
        return idType;
    }

    public void setIdType(Long idType) {
        this.idType = idType;
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
    // Autres méthodes ou attributs
}
