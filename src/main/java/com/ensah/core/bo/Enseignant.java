package com.ensah.core.bo;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "idEnseignant")
public class Enseignant extends Personnel {

    @ManyToOne
    @JoinColumn(name = "filliere_id")
    private Filliere filliere;

    @ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Groupe group;

    @OneToMany(mappedBy = "coordinateur", cascade = CascadeType.ALL, targetEntity = Surveillance.class)
    private Set<Surveillance> coordonSurveillances = new HashSet<>();

    @OneToMany(mappedBy = "enseignant", cascade = CascadeType.ALL, targetEntity = ElementPedagogique.class)
    private Set<ElementPedagogique> elemEnseignes = new HashSet<>();

    @OneToMany(mappedBy = "coordinateur", cascade = CascadeType.ALL, targetEntity = ElementPedagogique.class)
    private Set<ElementPedagogique> elemCoordonnes = new HashSet<>();

    @ManyToMany(mappedBy = "enseignants")
    private Set<Surveillance> surveillances = new HashSet<>();

    @OneToMany(mappedBy = "enseignant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Disponibilite> disponibilites;



    // Constructeur par défaut
    public Enseignant() {}

    // Constructeur avec nom et prénom
    public Enseignant(String nom, String prenom) {
        super.setNom(nom);
        super.setPrenom(prenom);
    }

    // Getters et Setters

    public Filliere getFilliere() {
        return filliere;
    }

    public void setFilliere(Filliere filliere) {
        this.filliere = filliere;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public Groupe getGroupe() {
        return group;
    }

    public void setGroupe(Groupe groupe) {
        this.group = groupe;
    }

    public Set<Surveillance> getCoordonSurveillances() {
        return coordonSurveillances;
    }

    public void setCoordonSurveillances(Set<Surveillance> coordonSurveillances) {
        this.coordonSurveillances = coordonSurveillances;
    }

    public Set<ElementPedagogique> getElemEnseignes() {
        return elemEnseignes;
    }

    public void setElemEnseignes(Set<ElementPedagogique> elemEnseignes) {
        this.elemEnseignes = elemEnseignes;
    }

    public Set<ElementPedagogique> getElemCoordonnes() {
        return elemCoordonnes;
    }

    public void setElemCoordonnes(Set<ElementPedagogique> elemCoordonnes) {
        this.elemCoordonnes = elemCoordonnes;
    }

    public Groupe getGroup() {
        return group;
    }

    public void setGroup(Groupe group) {
        this.group = group;
    }

    public Set<Surveillance> getSurveillances() {
        return surveillances;
    }

    public void setSurveillances(Set<Surveillance> surveillances) {
        this.surveillances = surveillances;
    }
}
