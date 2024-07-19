package com.ensah.core.bo;


import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Filliere {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long filliereId;

	    private String nom;

	public Filliere(Long filliereId, String nom, Set<Enseignant> enseignants) {
		this.filliereId = filliereId;
		this.nom = nom;
		this.enseignants = enseignants;
	}
	public Filliere() {

	}

	public Long getFilliereId() {
		return filliereId;
	}

	public void setFilliereId(Long filliereId) {
		this.filliereId = filliereId;
	}

	@OneToMany(mappedBy = "filliere", cascade = CascadeType.ALL, targetEntity = Enseignant.class)//Cela signifie que dans la classe cible (Enseignant), il doit y avoir un attribut qui référence la classe actuelle (Filliere dans ce cas).
	    private Set<Enseignant> enseignants;

	    public Set<Enseignant> getEnseignants() {
	        return enseignants;
	    }

	    public void setEnseignants(Set<Enseignant> enseignants) {
	        this.enseignants = enseignants;
	    }


	    public Long getId() {
	        return filliereId;
	    }

	    public void setId(Long id) {
	        this.filliereId = id;
	    }
	    public String getNom() {
	        return nom;
	    }

	    public void setNom(String nom) {
	        this.nom = nom;
	    }

	    @Override
	    public String toString() {
	        return "Filliere [id=" + filliereId + ", nom=" + nom + "]";
	    }
}
