package com.ensah.core.web.controllers;

import com.ensah.core.dao.DisponibiliteRepository;
import com.ensah.core.dao.GroupeRepository;
import com.ensah.core.dao.IEnseignantRepository;
import com.ensah.core.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.ensah.core.bo.*;
import com.ensah.core.utils.ExcelExporter;
import com.ensah.core.web.models.PersonModel;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.time.LocalDate;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

/**
 * Ce controleur gère les personnes de type Etudiant, Enseignant et Cadre Admin
 *
 * @author Boudaa
 *
 */

@Controller
@RequestMapping("/admin")
public class PersonneMngController {

	@Autowired
	private IPersonneService personService;

	@Autowired
	private HttpSession httpSession;


	/** Utilisé pour la journalisation */
	private Logger LOGGER = LoggerFactory.getLogger(getClass());


	public PersonneMngController() {




	}
	@Autowired
	private IElementPedagogiqueService elementPedagogiqueService;
	@Autowired
	private IEnseignantService enseignantService;
	@Autowired
	private INiveau niveauService;
	@Autowired
	private ITypeElementService typeElementServiceervice;
	@Autowired
	private IPersonneService personnelService;

	@Autowired
	private IFilliereService filliereService;

	@Autowired
	private IDepartementService departementService;
	@Autowired
	private AdminService adminService;

	@Autowired
	private IGroupeService groupeService;


	@GetMapping(value = "/elements")
	public String getAllElements(Model model) {
		List<ElementPedagogique> elements = elementPedagogiqueService.getAllElements();
		model.addAttribute("elements", elements);
		return "admin/subjects";
	}

	@GetMapping(value = "/elementsadd")
	public String afficherFormulaireAjout(Model model) {
		// Supposons que vous ayez un service pour obtenir tous les enseignants
		List<Enseignant> enseignants = enseignantService.getAllEnseignants();
		List<Niveau> niveau = niveauService.getAllniveau();
		List<TypeElement> typeelement = typeElementServiceervice.getAllTypeElement();
		model.addAttribute("enseignants", enseignants);
		model.addAttribute("niveau", niveau);
		model.addAttribute("typeelement", typeelement);
		return "admin/add-subject";
	}
	@PostMapping(value = "/elementssave")
	public String saveElement(
			@RequestParam("titre") String titre,
			@RequestParam("niveauid") Long niveauid,
			@RequestParam("coordinateurId") Long coordinateurId,
			@RequestParam("enseignantid") Long enseignantid,
			@RequestParam("typeId") Long typeId,
			Model model) {

		LOGGER.info("Saving Element: titre={}, niveauid={}, coordinateurId={}, enseignantid={}, typeId={}", titre, niveauid, coordinateurId, enseignantid, typeId);

		// Créez un nouvel objet ElementPedagogique
		ElementPedagogique element = new ElementPedagogique();
		element.setTitre(titre);

		// Recherchez le niveau sélectionné à partir de son identifiant
		Niveau niveau = niveauService.getNiveauById(niveauid);
		if (niveau == null) {
			LOGGER.error("Niveau not found with id: {}", niveauid);
			return "redirect:/admin/elementsadd";
		}
		element.setNiveau(niveau);

		// Recherchez le type sélectionné à partir de son identifiant
		TypeElement type = typeElementServiceervice.getTypeById(typeId);
		if (type == null) {
			LOGGER.error("Type not found with id: {}", typeId);
			return "redirect:/admin/elementsadd";
		}
		element.setType(type);

		// Recherchez le coordinateur sélectionné
		Enseignant coordinateur = enseignantService.getEnseignantById(coordinateurId);
		if (coordinateur == null) {
			LOGGER.error("Coordinateur not found with id: {}", coordinateurId);
			return "redirect:/admin/elementsadd";
		}
		element.setCoordinateur(coordinateur);

		// Recherchez l'enseignant sélectionné
		Enseignant enseignant = enseignantService.getEnseignantById(enseignantid);
		if (enseignant == null) {
			LOGGER.error("Enseignant not found with id: {}", enseignantid);
			return "redirect:/admin/elementsadd";
		}
		element.setEnseignant(enseignant);

		// Sauvegardez l'élément pédagogique
		elementPedagogiqueService.ajouterElement(element);

		LOGGER.info("Element saved successfully");

		// Redirigez vers une vue de confirmation ou de résultat
		return "redirect:/admin/elements"; // nom de la vue de résultat
	}

	@GetMapping("/elementedit/{id}")
	public String editElement(@PathVariable("id") Long id, Model model) {
		// Récupérer l'élément pédagogique par ID
		ElementPedagogique element = elementPedagogiqueService.getElementById(id);

		// Récupérer les autres données nécessaires pour le formulaire
		List<Enseignant> enseignants = enseignantService.getAllEnseignants();
		List<Niveau> niveaux = niveauService.getAllniveau();
		List<TypeElement> types = typeElementServiceervice.getAllTypeElement();

		// Ajouter les données au modèle
		model.addAttribute("element", element);
		model.addAttribute("enseignants", enseignants);
		model.addAttribute("niveaux", niveaux);
		model.addAttribute("types", types);

		return "admin/edit-subject";
	}

	@PostMapping("/elementsupdate")
	public String updateElement(@ModelAttribute("element") ElementPedagogique element,
								@RequestParam("niveauid") Long niveauId,
								@RequestParam("typeId") Long typeId,
								@RequestParam("enseignantid") Long enseignantId,
								@RequestParam("coordinateurId") Long coordinateurId) {
		// Mettre à jour les autres champs de l'objet ElementPedagogique avec les valeurs reçues
		element.setNiveau(niveauService.getNiveauById(niveauId)); // Remplacez niveauService.findById(niveauId) avec votre méthode pour obtenir le niveau par ID
		element.setType(typeElementServiceervice.getTypeById(typeId)); // Remplacez typeService.findById(typeId) avec votre méthode pour obtenir le type par ID
		element.setEnseignant(enseignantService.getEnseignantById(enseignantId)); // Remplacez enseignantService.findById(enseignantId) avec votre méthode pour obtenir l'enseignant par ID
		element.setCoordinateur(enseignantService.getEnseignantById(coordinateurId)); // Remplacez enseignantService.findById(coordinateurId) avec votre méthode pour obtenir le coordinateur par ID

		elementPedagogiqueService.updateElement(element);
		return "redirect:/admin/elements";
	}

	@GetMapping("/deleteElement/{id}")
	public String deleteElement(@PathVariable("id") Long id) {
		elementPedagogiqueService.supprimerElement(id);
		return "redirect:/admin/elements";
	}

	@GetMapping("/personnel")
	public String getAllPersonnel(Model model) {
		// Récupérer la liste de personnel depuis le service
		List<Personnel> personnelList = personnelService.getAllPersonnes();

		// Ajouter la liste de personnel au modèle pour l'affichage dans la vue
		model.addAttribute("personnelList", personnelList);

		// Retourner le nom de la vue (template Thymeleaf) à afficher
		return "admin/teachers";
	}

	@GetMapping("/add")
	public String showAddForm(Model model, @RequestParam("type") String type) {
		model.addAttribute("action", "add");
		if (type.equals("enseignant")) {
			model.addAttribute("personnel", new Enseignant());
		} else if (type.equals("admin")) {
			model.addAttribute("personnel", new Admin());
		} else {
			model.addAttribute("personnel", new Personnel());
		}
		model.addAttribute("type", type);
		model.addAttribute("filieres", filliereService.getAllFilieres());
		model.addAttribute("departements", departementService.getAllDepartements());
		return "admin/add-teacher";
	}
	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable("id") Long id, Model model) {
		Personnel personnel = personnelService.getPersonneById(id);
		if (personnel == null) {
			// Handle the case where personnel is not found
			return "redirect:/admin/personnel";
		}
		model.addAttribute("personnel", personnel);
		model.addAttribute("type", personnel.getClass().getSimpleName().toLowerCase());
		model.addAttribute("filieres", filliereService.getAllFilieres());
		model.addAttribute("departements", departementService.getAllDepartements());
		model.addAttribute("action", "edit");
		return "admin/add-teacher";
	}




	@PostMapping(value = {"/save", "/update"})
	public String saveOrUpdatePersonnel(
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam("nom") String nom,
			@RequestParam("prenom") String prenom,
			@RequestParam("type") String type, // "enseignant" ou "admin"
			@RequestParam(value = "filliereId", required = false) Long filliereId,
			@RequestParam(value = "departementId", required = false) Long departementId) {

		LOGGER.info("Saving or Updating Personnel: id={}, nom={}, prenom={}, type={}, filliereId={}, departementId={}", id, nom, prenom, type, filliereId, departementId);

		Personnel personnel;
		if (id != null) {
			personnel = personnelService.getPersonneById(id);
			if (personnel == null) {
				LOGGER.error("Personnel not found with id: {}", id);
				return "redirect:/admin/personnel";
			}
		} else {
			if (type.equals("enseignant")) {
				personnel = new Enseignant();
			} else if (type.equals("admin")) {
				personnel = new Admin();
			} else {
				LOGGER.error("Unknown personnel type: {}", type);
				return "redirect:/admin/personnel";
			}
		}

		personnel.setNom(nom);
		personnel.setPrenom(prenom);

		if (type.equals("enseignant") && personnel instanceof Enseignant) {
			Enseignant enseignant = (Enseignant) personnel;
			if (filliereId != null) {
				Filliere filiere = filliereService.getFilliereById(filliereId);
				enseignant.setFilliere(filiere);
			}
			if (departementId != null ) {
				Departement departement = departementService.getDepartementById(departementId);
				enseignant.setDepartement(departement);
			}
		}

		// Save or update the personnel
		personnelService.saveOrUpdatePersonnel(personnel);

		LOGGER.info("Personnel saved or updated successfully");

		// Redirect to a confirmation or result view
		return "redirect:/admin/personnel"; // Redirect to the personnel list view
	}


//
	@GetMapping("/deletee/{id}")
	public String deletePersonnel(@PathVariable("id") Long id) {
		personnelService.deletePersonne(id);
		return "redirect:/admin/personnel";
	}



//	Partie Groupe

	@GetMapping("/grp")
	public String getAllGroupes(Model model) {
		model.addAttribute("groupeList", groupeService.getAllGroupes());
		return "admin/departments";
	}
	@GetMapping("/showgrpform")
	public String showAddForm(Model model) {
		model.addAttribute("groupe", new Groupe());
		model.addAttribute("departements", departementService.getAllDepartements());
		model.addAttribute("filieres", filliereService.getAllFilieres());
		model.addAttribute("enseignants", enseignantService.getAllEnseignants());
		return "admin/add-department";
	}
	@Autowired
	private GroupeRepository groupeRepository;

	@Autowired
	private IEnseignantRepository enseignantRepository;
	@PostMapping("/savegrp")
	public String saveGroupe(
			@ModelAttribute Groupe groupe,
			@RequestParam(value = "enseignantIds", required = false) List<Long> enseignantIds,
			@RequestParam(value = "critere", required = false) String critere,
			@RequestParam(value = "departementId", required = false) Long departementId,
			@RequestParam(value = "filiereId", required = false) Long filiereId) {

		List<Enseignant> enseignants = null;

		switch (critere) {
			case "departement":
				enseignants = enseignantRepository.findByDepartementId(departementId);
				break;
			case "filiere":
				enseignants = enseignantRepository.findByFilliereId(filiereId);
				break;
			case "arbitraire":
				if (enseignantIds != null) {
					enseignants = enseignantRepository.findAllById(enseignantIds);
				}
				break;
			default:
				// No criteria, handle accordingly
				break;
		}

		if (enseignants != null) {
			for (Enseignant enseignant : enseignants) {
				enseignant.setGroupe(groupe);

			}
			groupe.setEnseignants(enseignants);
		}

		groupeRepository.save(groupe);
		return "redirect:/admin/grp";
	}
	@GetMapping("/view-group/{id}")
	public String viewGroup(@PathVariable("id") Long id, Model model) {
		Optional<Groupe> groupeOptional = groupeRepository.findById(id);
		if (groupeOptional.isPresent()) {
			Groupe groupe = groupeOptional.get();
			model.addAttribute("groupe", groupe);
			return "admin/view-group";
		} else {
			return "redirect:/admin/grp"; // rediriger si le groupe n'est pas trouvée
		}
	}
	@GetMapping("/delete-group/{id}")
	public String deletegrp(@PathVariable("id") Long id) {
		groupeRepository.deleteById(id);
		return "redirect:/admin/grp";
	}


	@Autowired
	private DisponibiliteRepository disponibiliteRepository;

	@GetMapping("/dispo/{id}")
	public String showDisponibiliteForm(@PathVariable("id") Long enseignantId, Model model) {
		Optional<Enseignant> enseignantOpt = enseignantRepository.findById(enseignantId);
		if (enseignantOpt.isPresent()) {
			Enseignant enseignant = enseignantOpt.get();
			Disponibilite disponibilite = new Disponibilite();
			disponibilite.setEnseignant(enseignant);
			model.addAttribute("disponibilite", disponibilite);
			model.addAttribute("enseignant", enseignant);
			return "admin/add-disponibilite";
		} else {
			return "redirect:/admin/enseignants";
		}
	}


	@PostMapping("/savedispo")
	public String saveDisponibilite(@ModelAttribute Disponibilite disponibilite, @RequestParam("heuresDebut") String heuresDebut, @RequestParam("heuresFin") String heuresFin,@RequestParam("eventDate") String eventDateStr) throws ParseException {
		// Diviser les chaînes de valeurs en listes
		List<String> heuresDebutList = Arrays.asList(heuresDebut.split(","));
		List<String> heuresFinList = Arrays.asList(heuresFin.split(","));

		// Définir le format des heures
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Date eventDate = dateFormat.parse(eventDateStr);

		// Traiter chaque paire d'heures de début et de fin sélectionnées
		for (int i = 0; i < heuresDebutList.size(); i++) {
			String heureDebut = heuresDebutList.get(i);
			String heureFin = heuresFinList.get(i);

			// Afficher la valeur de l'heure de début dans le log
			System.out.println("Heure de début soumise depuis le formulaire : " + heureDebut);
			System.out.println("Heure de fin soumise depuis le formulaire : " + heureFin);

			try {
				// Analyser les chaînes de caractères des heures de début et de fin pour obtenir les heures exactes
				Time heureDebutParsed = new Time(sdf.parse(heureDebut).getTime());
				Time heureFinParsed = new Time(sdf.parse(heureFin).getTime());

				// Créer un nouvel objet Disponibilite pour chaque créneau
				Disponibilite newDisponibilite = new Disponibilite();
				newDisponibilite.setEnseignant(disponibilite.getEnseignant());
				newDisponibilite.setDate(eventDate);
				newDisponibilite.setHeureDebut(heureDebutParsed);
				newDisponibilite.setHeureFin(heureFinParsed);

				// Enregistrer l'objet Disponibilite dans la base de données
				disponibiliteRepository.save(newDisponibilite);
			} catch (ParseException e) {
				e.printStackTrace();
				// Gérer l'erreur de parsing ici si nécessaire
			}
		}

		return "redirect:/admin/personnel"; // Redirection pour éviter la soumission multiple du formulaire
	}



}

