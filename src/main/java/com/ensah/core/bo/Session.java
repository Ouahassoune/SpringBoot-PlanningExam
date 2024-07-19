package com.ensah.core.bo;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSession;

    private String intitule;

    @OneToMany(mappedBy = "session")
    private Set<Exam> exams;

    public Session(String intitule) {
        this.intitule = intitule;
    }

    public Session() { }

    public Long getIdSession() {
        return idSession;
    }

    public void setIdSession(Long idSession) {
        this.idSession = idSession;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Set<Exam> getExams() {
        return exams;
    }

    public void setExams(Set<Exam> exams) {
        this.exams = exams;
    }

    // Autres méthodes ou attributs
}

