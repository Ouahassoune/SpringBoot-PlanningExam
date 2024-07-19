package com.ensah.core.bo;

import jakarta.persistence.*;

import java.util.Set;

public class ElementPedagogiqueDTO {
    private String titre;
    private Long niveauid;
    private Long coordinateurId;
    private Long enseignantid;
    private Long typeId;

    // Getters and Setters

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Long getNiveauid() {
        return niveauid;
    }

    public void setNiveauid(Long niveauid) {
        this.niveauid = niveauid;
    }

    public Long getCoordinateurId() {
        return coordinateurId;
    }

    public void setCoordinateurId(Long coordinateurId) {
        this.coordinateurId = coordinateurId;
    }

    public Long getEnseignantid() {
        return enseignantid;
    }

    public void setEnseignantid(Long enseignantid) {
        this.enseignantid = enseignantid;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
}

