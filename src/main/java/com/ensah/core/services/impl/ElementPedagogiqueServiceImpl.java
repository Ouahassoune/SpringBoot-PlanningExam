package com.ensah.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.ensah.core.bo.*;
import com.ensah.core.dao.IElementPedagogiqueRepository;
import com.ensah.core.services.IElementPedagogiqueService;
import com.ensah.core.utils.ExcelExporter;


@Service
public class ElementPedagogiqueServiceImpl implements IElementPedagogiqueService {

    @Autowired
    private IElementPedagogiqueRepository elementPedagogiqueRepository;

    @Override
    public List<ElementPedagogique> getAllElements() {
        return elementPedagogiqueRepository.findAll();
    }

    @Override
    public void ajouterElement(ElementPedagogique element) {
        elementPedagogiqueRepository.save(element);
    }

    @Override
    public void supprimerElement(Long id) {
        elementPedagogiqueRepository.deleteById(id);
    }

    @Override
    public void modifierElement(ElementPedagogique element) {
        elementPedagogiqueRepository.save(element);
    }


    @Override
    public ElementPedagogique getElementById(Long id) {
        Optional<ElementPedagogique> optionalElement = elementPedagogiqueRepository.findById(id);
        if (optionalElement.isPresent()) {
            return optionalElement.get();
        } else {
            throw new RuntimeException("Element not found for id :: " + id);
        }
    }

    @Override
    public void updateElement(ElementPedagogique element) {
        elementPedagogiqueRepository.save(element); // Since save() updates an existing entity if it already exists
    }

    @Override
    public void deleteElementById(Long id) {
        // Vérifie si l'élément existe
        Optional<ElementPedagogique> optionalElement = elementPedagogiqueRepository.findById(id);
        if (optionalElement.isPresent()) {
            ElementPedagogique element = optionalElement.get();
            // Supprime l'élément
            elementPedagogiqueRepository.delete(element);
        } else {
            throw new NoSuchElementException("L'élément avec l'ID " + id + " n'existe pas");
        }
    }
}
