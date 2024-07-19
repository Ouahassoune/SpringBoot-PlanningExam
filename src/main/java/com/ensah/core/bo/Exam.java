package com.ensah.core.bo;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.Date;
import java.util.Set;


@Entity
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExam;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Temporal(TemporalType.TIME)
    private Time heureDebut;

    @Temporal(TemporalType.TIME)
    private Time finPreuve;

    @Temporal(TemporalType.TIME)
    private Time finReelle;

    private String epreuve;

    private String pv;

    @Column(columnDefinition = "TEXT")
    private String rapport;
    @ManyToOne
    @JoinColumn(name = "element_pedagogique_id")
    private ElementPedagogique elementPedagogique;

    @ManyToOne
    @JoinColumn(name = "type_exam_id")
    private TypeExam typeExamen;

    @ManyToOne
    @JoinColumn(name = "semestre_id")
    private Semestre semestre;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;


    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Surveillance> surveillants;



    public Long getIdExam() {
        return idExam;
    }

    public void setIdExam(Long idExam) {
        this.idExam = idExam;
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

    public Time getFinPreuve() {
        return finPreuve;
    }

    public void setFinPreuve(Time finPreuve) {
        this.finPreuve = finPreuve;
    }

    public Time getFinReelle() {
        return finReelle;
    }

    public void setFinReelle(Time finReelle) {
        this.finReelle = finReelle;
    }

    public String getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(String epreuve) {
        this.epreuve = epreuve;
    }

    public String getPv() {
        return pv;
    }

    public void setPv(String pv) {
        this.pv = pv;
    }

    public String getRapport() {
        return rapport;
    }

    public void setRapport(String rapport) {
        this.rapport = rapport;
    }
    public TypeExam getTypeExamen() {
        return typeExamen;
    }

    public void setTypeExamen(TypeExam typeExamen) {
        this.typeExamen = typeExamen;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public ElementPedagogique getElementPedagogique() {
        return elementPedagogique;
    }

    public void setElementPedagogique(ElementPedagogique elementPedagogique) {
        this.elementPedagogique = elementPedagogique;
    }

    public Set<Surveillance> getSurveillants() {
        return surveillants;
    }

    public void setSurveillants(Set<Surveillance> surveillants) {
        this.surveillants = surveillants;
    }
}
