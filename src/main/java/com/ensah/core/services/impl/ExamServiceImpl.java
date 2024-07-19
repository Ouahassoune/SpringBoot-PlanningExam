package com.ensah.core.services.impl;

import com.ensah.core.bo.*;
import com.ensah.core.dao.IElementPedagogiqueRepository;
import com.ensah.core.dao.IExamRepository;
import com.ensah.core.dao.ISalleRepository;
import com.ensah.core.dao.*;
import com.ensah.core.services.IExamService;
import com.ensah.core.utils.ExcelExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExamServiceImpl implements IExamService {
    @Autowired
    private IExamRepository examRepository;

    @Autowired
    private IElementPedagogiqueRepository elementPedagogiqueRepository;

    @Autowired
    private ITypeExamRepository typeExamRepository;

    @Autowired
    private ISessionRepository sessionRepository;

    @Autowired
    private ISemestreRepository semestreRepository;

    @Autowired
    private ISurveillanceRepository surveillanceRepository;

    @Autowired
    private ISalleRepository salleRepository;

    @Autowired
    private IDisponibiliteRepository disponibiliteRepository;


    @Override
    public Exam findByIdExam(Long idExam){
      return  examRepository.findByIdExam(idExam);

    }


    public void addExam(Exam exam) {
       if (exam.getSurveillants() != null) {
            exam.getSurveillants().forEach(surveillance -> surveillance.setExam(exam));
        }
        examRepository.save(exam);
    }

    @Override
    public void updateExam(Exam exam) {
        if (exam.getSurveillants() != null) {
            exam.getSurveillants().forEach(surveillance -> surveillance.setExam(exam));
        }
        examRepository.save(exam);
    }

    @Override
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    @Override
    public void deleteExam(Long id) {
        Optional<Exam> optionalExam = examRepository.findById(id);
        if (optionalExam.isPresent()) {
            examRepository.deleteById(id);
        } else {
            throw new RuntimeException("Exam not found with id: " + id);
        }
    }

    @Override
    public ExcelExporter prepareExamExport(List<Exam> exams) {
        String[] columnNames = {
                "ID", "Date", "Heure de début", "Fin prévue", "Fin réelle", "Epreuve",
                "PV", "Rapport", "Type d'examen", "Semestre", "Session", "Element pédagogique"
        };
        String sheetName = "Exams";
        String[][] data = new String[exams.size()][columnNames.length];
        for (int i = 0; i < exams.size(); i++) {
            Exam exam = exams.get(i);
            data[i][0] = exam.getIdExam().toString();
            data[i][1] = exam.getDate().toString();
            data[i][2] = exam.getHeureDebut().toString();
            data[i][3] = exam.getFinPreuve().toString();
            data[i][4] = exam.getFinReelle().toString();
            data[i][5] = exam.getEpreuve();
            data[i][6] = exam.getPv();
            data[i][7] = exam.getRapport();
            data[i][8] = exam.getTypeExamen().getIntitule();
            data[i][9] = exam.getSemestre().getIntitule();
            data[i][10] = exam.getSession().getIntitule();
            data[i][11] = exam.getElementPedagogique().getTitre();
        }
        return new ExcelExporter(columnNames, data, sheetName);
    }


    @Override
    public Exam getExamByDate(Date date) {
        return examRepository.findByDate(date);
    }



    @Override
    public List<Semestre> getAllSemestres() {
        return semestreRepository.findAll();
    }

    @Override
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();

    }


    @Override
    public List<TypeExam> getAllTypeExams() {
        return typeExamRepository.findAll();
    }
    @Override
    public TypeExam findByIdTypeExam(Long idTypeExam) {
        Optional<TypeExam> typeExam = typeExamRepository.findById(idTypeExam);
        return typeExam.orElse(null); // ou gérer l'absence de l'objet autrement, par exemple en lançant une exception
    }



    /*******Salles******/
    public List<Salle> getAvailableSalles(Date date, Time heureDebut, Time heureFin) {
        boolean examsExist = salleRepository.existsByDate(date, heureDebut, heureFin);
        if (examsExist) {
            return salleRepository.findAvailableSalles(date, heureDebut, heureFin);
        } else {
            return salleRepository.findAll();
        }
    }
    @Override
    public List<Salle> getAllSalles() {
        return salleRepository.findAll();
    }

    @Override
    public Salle findByIdSalle(Long idSalle) {
        // Utilisation de la méthode findByIdSalle du référentiel de salle
        return salleRepository.findByIdSalle(idSalle);
    }



    /*******getElementPedagogique******/
    @Override
    public ElementPedagogique getElementPedagogiqueById(Long idEP) {
        Optional<ElementPedagogique> typeExam = elementPedagogiqueRepository.findById(idEP);
        return typeExam.orElse(null); // ou gérer l'absence de l'objet autrement, par exemple en lançant une exception
    }
    @Override
    public List<ElementPedagogique> getAllElementPedagogiques() {
        return elementPedagogiqueRepository.findAll();
    }

    @Override
    public Semestre findByIdSemestre(Long idSemestre) {
        Optional<Semestre> semestreOptional = semestreRepository.findById(idSemestre);
        return semestreOptional.orElse(null);
    }

    @Override
    public Session findByIdSession(Long idSession) {
        Optional<Session> sessionOptional = sessionRepository.findById(idSession);
        return sessionOptional.orElse(null);
    }



}
