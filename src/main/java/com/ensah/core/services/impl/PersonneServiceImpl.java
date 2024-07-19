package com.ensah.core.services.impl;

import java.util.List;

import com.ensah.core.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensah.core.bo.*;
import com.ensah.core.services.IPersonneService;
import com.ensah.core.utils.ExcelExporter;

@Service
@Transactional
public class PersonneServiceImpl implements IPersonneService {

	@Autowired
	private IPersonneRepository personDao;
	@Autowired
	private IEnseignantRepository enseignantRepository;
	@Autowired
	private IFilliereRepository filliereRepository;
	@Autowired
	private IDepartementRepository departementRepository;
	@Autowired
	private IAdminRepository adminRepository;


	public List<Personnel> getAllPersonnes() {

		return personDao.findAll();
	}

	public void deletePersonne(Long id) {
		personDao.deleteById(id);

	}

	public Personnel getPersonneById(Long id) {
		return personDao.findById(id).get();

	}



	public void addPersonne(Personnel pPerson) {
		personDao.save(pPerson);

	}

	public void updatePersonne(Personnel pPerson) {
		personDao.save(pPerson);

	}

	public ExcelExporter preparePersonneExport(List<Personnel> persons) {
		String[] columnNames = new String[] { "Nom", "Prénom", "CIN", "Email", "Télé" };
		String[][] data = new String[persons.size()][5];

		int i = 0;
		for (Personnel u : persons) {
			data[i][0] = u.getNom();
			data[i][1] = u.getPrenom();
			i++;
		}

		return new ExcelExporter(columnNames, data, "persons");

	}
	@Override
	public void savePersonnel(Personnel personnel) {
		if (personnel instanceof Enseignant) {
			Enseignant enseignant = (Enseignant) personnel;
			// Assurez-vous que les filières et les départements sont définis dans l'enseignant
			// Vous pouvez obtenir ces valeurs à partir des champs du formulaire et les définir dans l'objet enseignant
			enseignant.setFilliere(filliereRepository.findById(enseignant.getFilliere().getId()).orElse(null));
			enseignant.setDepartement(departementRepository.findById(enseignant.getDepartement().getId()).orElse(null));
			enseignantRepository.save(enseignant);
		} else if (personnel instanceof Admin) {
			adminRepository.save((Admin) personnel);
		} else {
			personDao.save(personnel);
		}
	}
	@Override
	public void saveEnseignant(Enseignant enseignant) {
		enseignant.setFilliere(filliereRepository.findById(enseignant.getFilliere().getId()).orElse(null));
		enseignant.setDepartement(departementRepository.findById(enseignant.getDepartement().getId()).orElse(null));
		enseignantRepository.save(enseignant);
	}

	@Override
	public void saveAdmin(Admin admin) {
		adminRepository.save(admin);
	}

	@Override
	public void saveOrUpdatePersonnel(Personnel personnel) {
		if (personnel instanceof Enseignant) {
			Enseignant enseignant = (Enseignant) personnel;
			// Assurez-vous que les filières et les départements sont définis dans l'enseignant
			// Vous pouvez obtenir ces valeurs à partir des champs du formulaire et les définir dans l'objet enseignant
			enseignant.setFilliere(filliereRepository.findById(enseignant.getFilliere().getId()).orElse(null));
			enseignant.setDepartement(departementRepository.findById(enseignant.getDepartement().getId()).orElse(null));
			enseignantRepository.save(enseignant);
		} else if (personnel instanceof Admin) {
			adminRepository.save((Admin) personnel);
		} else {
			personDao.save(personnel);
		}
	}


}
