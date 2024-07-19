package com.ensah.core.bo;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Personnel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPersonnel;

	private String nom;

	private String prenom;

	public Long getIdPersonnel() {
		return idPersonnel;
	}

	public void setIdPersonnel(Long idPersonnel) {
		this.idPersonnel = idPersonnel;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Override
	public String toString() {
		return "Personnel [idPersonnel=" + idPersonnel + ", nom=" + nom + ", prenom=" + prenom + "]";
	}
}
