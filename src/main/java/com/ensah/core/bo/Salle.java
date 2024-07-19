package com.ensah.core.bo;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Salle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSalle;

    private String nomSalle;

    private int capacite;
    @OneToMany(mappedBy = "salles")
    private Set<Surveillance> surveillances;


    public Set<Surveillance> getSurveillances() {
        return surveillances;
    }

    public void setSurveillances(Set<Surveillance> surveillances) {
        this.surveillances = surveillances;
    }

    public Long getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(Long idSalle) {
        this.idSalle = idSalle;
    }

    public String getNomSalle() {
        return nomSalle;
    }

    public void setNomSalle(String nomSalle) {
        this.nomSalle = nomSalle;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }


}
