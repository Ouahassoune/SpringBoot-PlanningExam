package com.ensah.core.services;
import java.util.List;
import com.ensah.core.bo.*;

public interface IElementPedagogiqueService {
    List<ElementPedagogique> getAllElements();
    void ajouterElement(ElementPedagogique element);
    void supprimerElement(Long id);
    void modifierElement(ElementPedagogique element);
    ElementPedagogique getElementById(Long id);
    void updateElement(ElementPedagogique element);
    void deleteElementById(Long id);
}
