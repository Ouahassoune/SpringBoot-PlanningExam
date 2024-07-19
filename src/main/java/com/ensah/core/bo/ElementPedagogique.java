package com.ensah.core.bo;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class ElementPedagogique {
	  @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Long idEP;
	   
	   private String titre;

	public TypeElement getType() {
		return type;
	}


	public void setType(TypeElement type) {
		this.type = type;
	}

	@ManyToOne
	    @JoinColumn(name = "niveau_id")
	    private Niveau niveau;

	    @ManyToOne
	    @JoinColumn(name = "type_element_id")
	    private TypeElement type;
	    
	    @ManyToOne
	    @JoinColumn(name = "coordinateur_id")
	    private Enseignant coordinateur;

	    @ManyToOne
	    @JoinColumn(name = "enseignant_id")
	    private Enseignant enseignant;
	   
	    @OneToMany(mappedBy = "elementPedagogique")
	    private Set<Exam> exams;

	// Constructeur par défaut
	public ElementPedagogique() {}

	// Constructeur avec tous les champs
	// Constructeur avec titre, enseignant, coordinateur, type et niveau
	public ElementPedagogique(String titre, Enseignant enseignant, Enseignant coordinateur, TypeElement type, Niveau niveau) {
		this.titre = titre;
		this.enseignant = enseignant;
		this.coordinateur = coordinateur;
		this.type = type;
		this.niveau = niveau;
	}
	    
	    public Niveau getNiveau() {
	        return niveau;
	    }

	    public void setNiveau(Niveau niveau) {
	        this.niveau = niveau;
	    }

	    public TypeElement getTypeElement() {
	        return type;
	    }
	    
	    public void setTypeElement(TypeElement typeElement) {
	        this.type = typeElement;
	    }

	    public Enseignant getCoordinateur() {
	        return coordinateur;
	    }

	    public void setCoordinateur(Enseignant coordinateur) {
	        this.coordinateur = coordinateur;
	    }
	    
	    public Enseignant getEnseignant() {
	        return enseignant;
	    }

	    public void setEnseignant(Enseignant enseignant) {
	        this.enseignant = enseignant;
	    }
	   
	   public Long getIdEP() {
	       return idEP;
	   }

	   public void setIdEP(Long idEP) {
	       this.idEP = idEP;
	   }

	   public String getTitre() {
	       return titre;
	   }

	   public void setTitre(String titre) {
	       this.titre = titre;
	   }
	   public Set<Exam> getExams() {
	        return exams;
	    }

	    public void setExams(Set<Exam> exams) {
	        this.exams = exams;
	    }
	
	
}
