package com.ensah.core.web.controllers;

import com.ensah.core.bo.*;
import com.ensah.core.services.IExamService;
import com.ensah.core.services.impl.SurveillanceService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class ExamController {

    @Autowired
    private IExamService examService;

    @Autowired
    private SurveillanceService surveillanceService;

    @Autowired
    private HttpSession httpSession;

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    public ExamController() {
    }

    @GetMapping(value = "/exams")
    public String getExamList(Model model) {
        List<Exam> exams = examService.getAllExams();
        model.addAttribute("exam", exams);
        return "admin/exam";  // Retourne la vue 'examList.html' située dans 'templates/admin'
    }

    @GetMapping("/delete/{id}")
    public String deleteElement(@PathVariable("id") Long id) {
        examService.deleteExam(id);
        return "redirect:/admin/exams";
    }

    @GetMapping("/edit-exam/{id}")
    public String editExam(@PathVariable("id") Long id, Model model) {
        Exam exam = examService.findByIdExam(id);
        if (exam != null) {
            model.addAttribute("exam", exam);
            model.addAttribute("elementPedagogiques", examService.getAllElementPedagogiques());
            model.addAttribute("semestres", examService.getAllSemestres());
            model.addAttribute("typeExams", examService.getAllTypeExams());
            model.addAttribute("examSessions", examService.getAllSessions());
            model.addAttribute("salles", examService.getAllSalles());
            Set<Surveillance> surveillances = exam.getSurveillants();
            model.addAttribute("surveillances", surveillances);

            return "admin/edit-exam";
        } else {
            return "redirect:/admin/exams?error=ExamNotFound";
        }
    }



    @GetMapping("/voir-exam/{id}")
    public String voir(@PathVariable("id") Long id, Model model) {
        Exam exam = examService.findByIdExam(id);
        if (exam != null) {
            model.addAttribute("exam", exam);
            model.addAttribute("elementPedagogiques", examService.getAllElementPedagogiques());
            model.addAttribute("semestres", examService.getAllSemestres());
            model.addAttribute("typeExams", examService.getAllTypeExams());
            model.addAttribute("examSessions", examService.getAllSessions());
            model.addAttribute("salles", examService.getAllSalles());
            Set<Surveillance> surveillances = exam.getSurveillants();
            model.addAttribute("surveillances", surveillances);

            return "admin/voir-exam";
        } else {
            return "redirect:/admin/exams";
        }
    }


    @GetMapping("/availableSalles")
    @ResponseBody
    public List<Salle> getAvailableSalles(@RequestParam("date") @DateTimeFormat(pattern = "dd-MM-yyyy") Date date,
                                          @RequestParam("heureDebut") String heureDebut,
                                          @RequestParam("heureFin") String heureFin) {
        System.out.println("herrrr ");

        System.out.println("Date: " + date);
        System.out.println("Heure début: " + heureDebut);
        System.out.println("Heure fin: " + heureFin);

        // Vérifier si l'heure de fin est valide
        if (heureFin == null || heureFin.isEmpty()) {
            // Gérer le cas où l'heure de fin est vide ou nulle
            System.out.println("Heure de fin invalide");
            // Vous pouvez retourner un message d'erreur ou une valeur par défaut
            return null;
        }

        try {
            // Convert heureDebut to LocalTime object
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime localTimeDebut = LocalTime.parse(heureDebut, timeFormatter);

            // Ajouter 2 heures à l'heure de début pour obtenir l'heure de fin
            LocalTime localTimeFin = localTimeDebut.plusHours(2);

            // Convert LocalTime to SQL Time (adding seconds as 00)
            Time sqlTimeDebut = Time.valueOf(localTimeDebut.withSecond(0));
            Time sqlTimeFin = Time.valueOf(localTimeFin.withSecond(0));

            System.out.println("SQL Time début: " + sqlTimeDebut);
            System.out.println("SQL Time fin: " + sqlTimeFin);

            List<Salle> availableSalles = examService.getAvailableSalles(date, sqlTimeDebut, sqlTimeFin);
            System.out.println("availableSalles size is " + availableSalles.size());
            return availableSalles;
        } catch (DateTimeParseException e) {
            // Gérer le cas où l'heure de fin n'est pas au bon format
            System.out.println("Erreur de format pour l'heure de fin: " + heureFin);
            // Vous pouvez retourner un message d'erreur ou une valeur par défaut
            return null;
        }
    }




    @GetMapping(value = "/addexam")
    public String addExam(Model model) {
        List<Semestre> semestres = examService.getAllSemestres();
        List<Session> examSessions = examService.getAllSessions();
        List<TypeExam> typeExams = examService.getAllTypeExams();
        List<ElementPedagogique> elementPedagogiques = examService.getAllElementPedagogiques();
        List<Salle> salles = examService.getAllSalles(); // Ajouté pour récupérer les salles
        model.addAttribute("salles", salles); // Ajouté pour ajouter les salles au modèle

        model.addAttribute("semestres", semestres);
        model.addAttribute("examSessions", examSessions);
        model.addAttribute("typeExams", typeExams);
        model.addAttribute("elementPedagogiques", elementPedagogiques);

        return "admin/add-exam";  // Retourne la vue 'add-exam.html' située dans 'templates/admin'
    }










    @PostMapping(value = "/addexam")
    public String addExamPost(@RequestParam("elementPedagogique") Long elementPedagogiqueId,
                              @RequestParam("semestre") Long semestreId,
                              @RequestParam("typeExam") Long typeExamId,
                              @RequestParam("examSession") Long examSessionId,
                              @RequestParam("startTime") String startTimeStr,
                              @RequestParam("expectedDuration") int expectedDuration,
                              @RequestParam("eventDate") String eventDateStr,
                              @RequestParam("eventFile") MultipartFile eventFile,
                              @RequestParam("salles") List<String> salles,
                              @RequestParam("numberOfSurveillance") int numberOfSurveillance,
                              Model model) {

        // Convertir les chaînes de date et d'heure en objets Date et Time
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Date eventDate;
        Time startTime;
        try {
            eventDate = dateFormat.parse(eventDateStr);
            startTime = new Time(timeFormat.parse(startTimeStr).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return "redirect:/admin/addexam?error";
        }

        // Boucle pour afficher les salles sélectionnées
        for (String salleId : salles) {
            System.out.println("Salle sélectionnée : " + salleId);
        }

        // Création de l'objet Exam
        Exam exam = new Exam();
        exam.setDate(eventDate);
        exam.setHeureDebut(startTime);
        long endTimeMillis = startTime.getTime() + (expectedDuration * 60000); // Convertir la durée de minutes en millisecondes et ajouter à l'heure de début
        Time endTime = new Time(endTimeMillis); // Créer un nouvel objet Time pour l'heure de fin
        exam.setFinReelle(endTime);// Fin prévue basée sur la durée

        exam.setFinPreuve(endTime);// Fin prévue basée sur la durée

        // Récupération des objets associés
        TypeExam typeExam = examService.findByIdTypeExam(typeExamId);
        Semestre semestre = examService.findByIdSemestre(semestreId);
        Session session = examService.findByIdSession(examSessionId);
        ElementPedagogique elementPedagogique = examService.getElementPedagogiqueById(elementPedagogiqueId);

        exam.setTypeExamen(typeExam);
        exam.setSemestre(semestre);
        exam.setSession(session);
        exam.setElementPedagogique(elementPedagogique);

        // Gérer le fichier si nécessaire (par exemple, enregistrer le fichier sur le serveur)
        if (!eventFile.isEmpty()) {
            // Enregistrer le fichier et obtenir le chemin du fichier
            String filePath = saveEventFile(eventFile);
            exam.setEpreuve(filePath); // Enregistrer le chemin du fichier dans l'objet Exam
        }

        // Sauvegarder l'objet Exam dans la base de données
        examService.addExam(exam);

        processSelectedSalles(salles, exam, numberOfSurveillance, true, false, null);
        // Afficher un message dans la console
        System.out.println("L'examen a été enregistré avec succès !");

        // Rediriger l'utilisateur après l'ajout de l'examen
        return "redirect:/admin/exams";
    }






    // Ajouter les salles à notre examen
    public void processSelectedSalles(List<String> salleIds, Exam exam, int numberOfEnseignants, boolean randomSelection, boolean sameGroup, String groupName) {
        // Parcourir les identifiants des salles sélectionnées
        System.out.println("L'examen est 1"+exam.getHeureDebut());

        for (String salleId : salleIds) {
            // Récupérer la salle correspondante depuis la base de données
            Salle salle = examService.findByIdSalle(Long.parseLong(salleId));
            if (salle != null) {
                // Créer une instance de Surveillance pour chaque salle sélectionnée
                Surveillance surveillance = new Surveillance();
                surveillance.setSalles(salle);
                surveillance.setExam(exam);
                System.out.println("L'examen est 2 "+surveillance.getExam().getHeureDebut());

                // Assigner des enseignants à la surveillance
                surveillanceService.assignEnseignantsToSurveillance(surveillance, randomSelection, sameGroup, groupName, numberOfEnseignants);

                // Enregistrer la surveillance dans la base de données
               // surveillanceService.save(surveillance);
            }
        }
    }



    private String saveEventFile(MultipartFile eventFile) {
        // Implémentez la logique pour sauvegarder le fichier et retourner le chemin d'accès du fichier sauvegardé
        // Vous pouvez utiliser File I/O ou un service de stockage de fichiers pour enregistrer le fichier
        // Exemple simple :
        try {
            String filePath = "C:/Users/Pc/Desktop/3yiiit/ProjetPLanningExam (5)/ProjetPLanningExam/src/main/webapp/resources/images/" + eventFile.getOriginalFilename();
            eventFile.transferTo(new java.io.File(filePath));
            return filePath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }






    @PostMapping(value = "/update-exam")
    public String updateExamPost(@RequestParam("id") Long examId,
                                 @RequestParam("elementPedagogique") Long elementPedagogiqueId,
                                 @RequestParam("semestre") Long semestreId,
                                 @RequestParam("typeExam") Long typeExamId,
                                 @RequestParam("examSession") Long examSessionId,
                                 @RequestParam("startTime") String startTimeStr,
                                 @RequestParam("expectedDuration") int expectedDuration,
                                 @RequestParam("eventDate") String eventDateStr,
                                 @RequestParam(value = "salles", required = false) List<String> salles,
                                 @RequestParam(value = "numberOfSurveillance", required = false) Integer numberOfSurveillance,
                                 Model model) {
        System.out.println("elementPedagogiqueId: " + elementPedagogiqueId);

        // Convertir les chaînes de date et d'heure en objets Date et Time
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Date eventDate;
        Time startTime;
        try {
            eventDate = dateFormat.parse(eventDateStr);
            startTime = new Time(timeFormat.parse(startTimeStr).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return "redirect:/admin/edit-exam/{id}?error";
        }

        // Récupérer l'examen existant à partir de la base de données en utilisant son identifiant
        Exam existingExam = examService.findByIdExam(examId);

        // Mettre à jour les champs de l'examen existant avec les nouvelles valeurs
        existingExam.setDate(eventDate);
        existingExam.setHeureDebut(startTime);
        long endTimeMillis = startTime.getTime() + (expectedDuration * 60000); // Convertir la durée de minutes en millisecondes et ajouter à l'heure de début
        Time endTime = new Time(endTimeMillis); // Créer un nouvel objet Time pour l'heure de fin
        existingExam.setFinReelle(endTime);// Fin prévue basée sur la durée
        existingExam.setFinPreuve(endTime);// Fin prévue basée sur la durée


        // Récupération des objets associés
        TypeExam typeExam = examService.findByIdTypeExam(typeExamId);
        Semestre semestre = examService.findByIdSemestre(semestreId);
        Session session = examService.findByIdSession(examSessionId);
        ElementPedagogique elementPedagogique = examService.getElementPedagogiqueById(elementPedagogiqueId);

        existingExam.setTypeExamen(typeExam);
        existingExam.setSemestre(semestre);
        existingExam.setSession(session);
        existingExam.setElementPedagogique(elementPedagogique);

        // Mettre à jour l'examen dans la base de données
        examService.updateExam(existingExam);
if(salles!=null && numberOfSurveillance!=null){
    processSelectedSalles(salles, existingExam, numberOfSurveillance, true, false, null);
}
        // Mettre à jour les salles associées à l'examen

        // Afficher un message dans la console
        System.out.println("L'examen a été mis à jour avec succès !");

        // Rediriger l'utilisateur après la modification de l'examen
        return "redirect:/admin/exams";
    }


}
