package com.ensah.core.services.impl;
import com.ensah.core.bo.Groupe;
import com.ensah.core.dao.GroupeRepository;
import com.ensah.core.services.IGroupeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GroupeServiceImpl implements IGroupeService {

    @Autowired
    private GroupeRepository groupeRepository;

    @Override
    public List<Groupe> getAllGroupes() {
        return groupeRepository.findAll();
    }

    @Override
    public Groupe getGroupeById(Long id) {
        return groupeRepository.findById(id).orElse(null);
    }

//    @Override
//    public void saveGroupe(Groupe groupe) {
//        groupeRepository.save(groupe);
//    }

    @Override
    public void deleteGroupe(Long id) {
        groupeRepository.deleteById(id);
    }
}
