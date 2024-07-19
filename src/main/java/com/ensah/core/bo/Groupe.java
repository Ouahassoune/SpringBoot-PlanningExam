package com.ensah.core.bo;

import jakarta.persistence.*;

import java.util.*;


@Entity
public class Groupe {
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long idgroup;
   
   private String nomgroup;
   
   
   @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, targetEntity = Enseignant.class, orphanRemoval = true)//Cela signifie que dans la classe cible (Enseignant), il doit y avoir un attribut qui référence la classe actuelle (group dans ce cas).
   private List<Enseignant> enseignants;
   
   public List<Enseignant> getComptes() {
		return enseignants;
	}

	public void setComptes(List<Enseignant> ens) {
		this.enseignants = ens;
	}
   public Long getIdgroup() {
       return idgroup;
   }

   public void setIdgroup(Long idgroup) {
       this.idgroup = idgroup;
   }

   public String getNomgroup() {
       return nomgroup;
   }

    public List<Enseignant> getEnseignants() {
        return enseignants;
    }

    public void setEnseignants(List<Enseignant> enseignants) {
        this.enseignants = enseignants;
    }

    public Groupe(Long idgroup, String nomgroup, List<Enseignant> enseignants) {
        this.idgroup = idgroup;
        this.nomgroup = nomgroup;
        this.enseignants = enseignants;
    }
    public Groupe() {

    }

    public void setNomgroup(String nomgroup) {
       this.nomgroup = nomgroup;
   }
   @Override
   public String toString() {
       return "Groupe [idgroup=" + idgroup + ", nomgroup=" + nomgroup + "]";
   }
}
