package com.ensah.core.services;

import java.util.List;

import com.ensah.core.bo.Admin;
import com.ensah.core.bo.Enseignant;
import com.ensah.core.bo.Personnel;
import com.ensah.core.utils.ExcelExporter;

public interface IPersonneService {

	public void addPersonne(Personnel pPerson);

	public void updatePersonne(Personnel pPerson);

	public List<Personnel> getAllPersonnes();

	public void deletePersonne(Long id);

	public Personnel getPersonneById(Long id);

	//public Personnel getPersonneByCin(String cin);

	public ExcelExporter preparePersonneExport(List<Personnel> persons);

	void savePersonnel(Personnel personnel);
	void saveEnseignant(Enseignant enseignant)
;
	void saveAdmin(Admin admin)
;
	void saveOrUpdatePersonnel(Personnel personnel);

}
