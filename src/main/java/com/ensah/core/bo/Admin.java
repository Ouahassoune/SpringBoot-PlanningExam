package com.ensah.core.bo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;

import java.util.Set;


@Entity
@PrimaryKeyJoinColumn(name = "idAdmin")
public class Admin  extends Personnel{

	  @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, targetEntity = Surveillance.class)//Cela signifie que dans la classe cible , il doit y avoir un attribut qui référence la classe actuelle .
	    private Set<Surveillance> surveillances;
		   
}
