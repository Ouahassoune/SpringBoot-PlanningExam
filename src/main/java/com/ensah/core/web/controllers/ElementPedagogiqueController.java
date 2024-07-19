package com.ensah.core.web.controllers;
import com.ensah.core.services.IElementPedagogiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.ensah.core.bo.*;
import java.util.List;

@Controller
public class ElementPedagogiqueController {

    @Autowired
    private IElementPedagogiqueService elementPedagogiqueService;

//    @GetMapping("/elements")
//    public String getAllElements(Model model) {
////        List<ElementPedagogique> elements = elementPedagogiqueService.getAllElements();
////        model.addAttribute("elements", elements);
//        return "admin/subjects";
//    }
}
