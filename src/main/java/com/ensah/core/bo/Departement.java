package com.ensah.core.bo;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Departement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long departementId;

	public Long getDepartementId() {
		return departementId;
	}

	public void setDepartementId(Long departement_id) {
		this.departementId = departement_id;
	}

	@OneToMany(mappedBy = "departement", cascade = CascadeType.ALL, targetEntity = Enseignant.class)
	private Set<Enseignant> enseignants;

	public Set<Enseignant> getEnseignants() {
		return enseignants;
	}

	public void setEnseignants(Set<Enseignant> ens) {
		this.enseignants =ens;
	}



	private String nom;

	public Long getId() {
		return departementId;
	}

	public void setId(Long id) {
		this.departementId = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Departement [departement_id=" + departementId + ", nom=" + nom + "]";
	}

}
