package com.ensah.core.services;

import com.ensah.core.bo.TypeElement;

import java.util.List;




public interface ITypeElementService {
    List<TypeElement> getAllTypeElement();
    TypeElement getTypeById(Long id);
}