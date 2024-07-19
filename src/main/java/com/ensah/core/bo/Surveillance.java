package com.ensah.core.bo;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
public class Surveillance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSurv;
	private String nomSurv;
	@ManyToOne
	@JoinColumn(name = "salle_id")
	private Salle salles;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "enseignant_surveillance",
			joinColumns = @JoinColumn(name = "surveillance_id"),
			inverseJoinColumns = @JoinColumn(name = "enseignant_id"))
	private Set<Enseignant> enseignants=new HashSet<Enseignant>();


	@ManyToOne
	@JoinColumn(name = "admin_id")
	private Admin admin;



	@ManyToOne
	@JoinColumn(name = "coordinateur_id")
	private Enseignant coordinateur;





	@ManyToOne
	@JoinColumn(name = "exam_id")
	private Exam exam;



	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Salle getSalles() {
		return salles;
	}

	public void setSalles(Salle salles) {
		this.salles = salles;
	}

	public Enseignant getCoordinateur() {
		return coordinateur;
	}

	public void setCoordinateur(Enseignant coordinateur) {
		this.coordinateur = coordinateur;
	}
	public Set<Enseignant> getEnseignants() {
		return enseignants;
	}

	public void setEnseignants(Set<Enseignant> enseignants) {
		this.enseignants = enseignants;
	}
	public Long getIdSurv() {
		return idSurv;
	}

	public void setIdSurv(Long idSurv) {
		this.idSurv = idSurv;
	}

	public String getNomSurv() {
		return nomSurv;
	}

	public void setNomSurv(String nom) {
		this.nomSurv = nom;
	}
	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}


}
