package com.ensah.core.bo;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Semestre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSemestre;

    public Semestre() { }

    public Semestre(String intitule) {
        this.intitule = intitule;
    }

    private String intitule;

    @OneToMany(mappedBy = "semestre")
    private Set<Exam> exams;

    public Long getIdSemestre() {
        return idSemestre;
    }

    public void setIdSemestre(Long idSemestre) {
        this.idSemestre = idSemestre;
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

}

