package com.ensah.core.bo;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.time.LocalDate;
import java.sql.Time;
import java.util.Date;


@Entity
public class Disponibilite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDisponibilite;

    @ManyToOne
    @JoinColumn(name = "enseignant_id", nullable = false)
    private Enseignant enseignant;

    @Temporal(TemporalType.DATE)
    private Date date;

//    private LocalTime heureDebut;
//
//    private LocalTime heureFin;
    private Time heureDebut;

    private Time heureFin;



    public Disponibilite() {

    }


    public Disponibilite(Long idDisponibilite, Enseignant enseignant, Date date, Time heureDebut, Time heureFin) {
        this.idDisponibilite = idDisponibilite;
        this.enseignant = enseignant;
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

    // Getters and Setters
    public Long getIdDisponibilite() {
        return idDisponibilite;
    }

    public void setIdDisponibilite(Long idDisponibilite) {
        this.idDisponibilite = idDisponibilite;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Time heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Time getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Time heureFin) {
        this.heureFin = heureFin;
    }
}