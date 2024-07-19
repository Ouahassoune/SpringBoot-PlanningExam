package com.ensah.core.bo;


import jakarta.persistence.*;

import java.util.Set;


@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRole;

	private String nomRole;

	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL , targetEntity = Compte.class)
	private Set<Compte> comptes;

	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

	public String getNomRole() {
		return nomRole;
	}

	public void setNomRole(String nomRole) {
		this.nomRole = nomRole;
	}

	public Set<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(Set<Compte> comptes) {
		this.comptes = comptes;
	}

	
	
}