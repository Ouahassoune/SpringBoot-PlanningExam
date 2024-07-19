package com.ensah.core.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//Ce controleur affiche la page index 
@Controller
public class InitiController {
	@RequestMapping("/")
	public String index(Model model) {//ghizlane : Le paramètre Model model est utilisé pour transmettre des données du 
     	                              // contrôleur à la vue (template) qui sera affichée. 
		return "login";               //modele est vide dans ce cas je pense
	}
}
