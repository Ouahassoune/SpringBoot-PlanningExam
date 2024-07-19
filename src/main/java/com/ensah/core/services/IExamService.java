package com.ensah.core.services;

import com.ensah.core.bo.*;
import com.ensah.core.utils.ExcelExporter;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface IExamService {
    public void addExam(Exam exam);

    public void updateExam(Exam pPerson);

    public List<Exam> getAllExams();
    public void deleteExam(Long idExam);
    public ExcelExporter prepareExamExport(List<Exam> persons);
    public Exam getExamByDate(Date dat);

    public List<Session> getAllSessions();
    public List<Semestre> getAllSemestres();
    public List<Salle> getAllSalles();

    public List<ElementPedagogique> getAllElementPedagogiques();
    public List<TypeExam> getAllTypeExams();

    public TypeExam findByIdTypeExam(Long idTypeExam);

    public Exam findByIdExam(Long idExam);

    public     ElementPedagogique getElementPedagogiqueById(Long idEP);


    public  Semestre findByIdSemestre(Long id);

    public Session findByIdSession(Long id);


/**********************Salles******************/
    public Salle findByIdSalle(Long idSalle);
    public List<Salle> getAvailableSalles(Date date, Time heureDebut, Time heureFin);

}


