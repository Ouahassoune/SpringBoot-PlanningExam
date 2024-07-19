package com.ensah.core.bo;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class TypeExam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeExam;

    private String intitule;

    @OneToMany(mappedBy = "typeExamen")
    private Set<Exam> exams;


    public Long getIdTypeExam() {
        return idTypeExam;
    }

    public void setIdTypeExam(Long idTypeExam) {
        this.idTypeExam = idTypeExam;
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
