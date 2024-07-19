package com.ensah.core.services.impl;

import com.ensah.core.bo.*;
import com.ensah.core.dao.*;
import com.ensah.core.dao.IEnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.*;

@Service
public class SurveillanceService {
	    

    @Autowired
    private IEnseignantRepository enseignantRepository;

    @Autowired
    private IDisponibiliteRepository disponibiliteRepository;

    @Autowired
    private ISurveillanceRepository surveillanceRepository;
    
    @Autowired
    private IAdminRepository adminRepository;
    
    
    public Surveillance save(Surveillance surveillance) {
        return surveillanceRepository.save(surveillance);
    }

    public void deleteById(Long id) {
        surveillanceRepository.deleteById(id);
    }

    public List<Surveillance> findAll() {
        return surveillanceRepository.findAll();
    }

 // Méthode pour assigner des enseignants à une surveillance
    public void assignEnseignantsToSurveillance(Surveillance surveillance, boolean randomSelection, boolean sameGroup, String groupName, int numberOfEnseignants) {
        System.out.println("L'examen est 3 ");
        System.out.println("L'examen est 3 "+surveillance.getExam().getDate());

        Set<Enseignant> selectedEnseignants = new HashSet<>();
        Date examDate = surveillance.getExam().getDate();
        Time startTime = (Time) surveillance.getExam().getHeureDebut();
        Time endTime = (Time) surveillance.getExam().getFinReelle();

        List<Disponibilite> availableDisponibilites = disponibiliteRepository.findAvailableEnseignants(examDate, startTime, endTime);
        System.out.println("availableDisponibilites "+availableDisponibilites.size());

        if (sameGroup) {
            if (groupName == null || groupName.isEmpty()) {
                throw new IllegalArgumentException("Group name must be provided when sameGroup is true.");
            }

            List<Enseignant> groupEnseignants = new ArrayList<>();
            for (Disponibilite dispo : availableDisponibilites) {
                Enseignant enseignant = dispo.getEnseignant();
                if (enseignant.getGroupe().getNomgroup().equals(groupName)) {
                    groupEnseignants.add(enseignant);
                }
            }

            if (groupEnseignants.isEmpty()) {
                throw new IllegalArgumentException("No available enseignants found for the specified group.");
            }

            selectedEnseignants.addAll(groupEnseignants.subList(0, Math.min(groupEnseignants.size(), numberOfEnseignants)));
        } else {
            List<Enseignant> enseignants = new ArrayList<>();
            for (Disponibilite dispo : availableDisponibilites) {
                enseignants.add(dispo.getEnseignant());
            }

            enseignants.sort(Comparator.comparingInt(this::getTotalSurveillanceHours)); // Trier les enseignants par nombre total d'heures de surveillance
            for (Enseignant enseignant : enseignants) {
                if (!isEnseignantOverloaded(enseignant, examDate)) {
                    selectedEnseignants.add(enseignant);
                    if (selectedEnseignants.size() >= numberOfEnseignants) {
                        break;
                    }
                }
            }
        }
        Exam exam = surveillance.getExam();
        ElementPedagogique elementPedagogique = exam.getElementPedagogique();

        if (elementPedagogique != null) {
            // Récupérer le coordinateur de l'élément pédagogique
            Enseignant coordinateur = elementPedagogique.getCoordinateur();
            // Définir le coordinateur comme coordinateur de la surveillance
            surveillance.setCoordinateur(coordinateur);
        }
        
        
        
        
        // Récupérer tous les admins
        // Récupérer tous les admins
        List<Admin> allAdmins = adminRepository.findAll();

        // Récupérer les admins déjà assignés à des surveillances pour cet exam
        List<Admin> assignedAdmins = surveillanceRepository.findAdminsByExamDateAndTime(examDate, startTime, endTime);

        // Trouver un admin disponible
        Admin availableAdmin = null;
        for (Admin admin : allAdmins) {
            if (!assignedAdmins.contains(admin)) {
                availableAdmin = admin;
                break;
            }
        }

        if (availableAdmin != null) {
            surveillance.setAdmin(availableAdmin);
        }

        surveillance.setEnseignants(selectedEnseignants);
        surveillanceRepository.save(surveillance);


        for (Enseignant enseignant : selectedEnseignants) {
            List<Disponibilite> enseignantDisponibilites = disponibiliteRepository.findDisponibilitesByEnseignantAndDate(enseignant.getIdPersonnel(), examDate);
            for (Disponibilite dispo : enseignantDisponibilites) {
                if (dispo.getHeureDebut().before(endTime) && dispo.getHeureFin().after(startTime)) {
                    disponibiliteRepository.delete(dispo); // Supprimer les disponibilités affectées
                }
            }
        }
    }
    
    private int getTotalSurveillanceHours(Enseignant enseignant) {
        // Logique pour calculer le total des heures de surveillance pour un enseignant
        int totalHours = 0;
        List<Surveillance> surveillances = surveillanceRepository.findByEnseignantsContains(enseignant);
        for (Surveillance surveillance : surveillances) {
            System.out.println("L'examen est 4 "+surveillance.getExam().getDate());

            Date examDate = surveillance.getExam().getDate();
            Time startTime = (Time) surveillance.getExam().getHeureDebut();
            Time endTime = (Time) surveillance.getExam().getFinReelle();
            long diffInMillis = endTime.getTime() - startTime.getTime();
            totalHours += diffInMillis / (1000 * 60 * 60);
        }
        return totalHours;
    }

    
    private boolean isEnseignantOverloaded(Enseignant enseignant, Date date) {
        // Logique pour vérifier si un enseignant a atteint le seuil de surveillance par jour
        int maxHoursPerDay = 8; // Par exemple, limite fixée à 8 heures par jour
        int totalHoursForDate = 0;

        List<Surveillance> surveillances = surveillanceRepository.findByEnseignantsContainsAndExamDate(enseignant, date);
        for (Surveillance surveillance : surveillances) {
            Time startTime = (Time) surveillance.getExam().getHeureDebut();
            Time endTime = (Time) surveillance.getExam().getFinReelle();
            long diffInMillis = endTime.getTime() - startTime.getTime();
            totalHoursForDate += diffInMillis / (1000 * 60 * 60);
        }

        return totalHoursForDate >= maxHoursPerDay;
    }
}
