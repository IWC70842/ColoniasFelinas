package com.dwes.gestioncolonias.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dwes.gestioncolonias.models.Colonia;
import com.dwes.gestioncolonias.repositories.ColoniaRepository;

@Service
public class ColoniaService {

  @Autowired
  ColoniaRepository coloniaRepository;

  public List<Colonia> getColonias(){
    return (List<Colonia>) coloniaRepository.findAll();
  }

  public Colonia saveColonia(Colonia colonia){
    return coloniaRepository.save(colonia);
  }

  public Optional<Colonia> getColoniaById(Long id){
    return coloniaRepository.findById(id);
  }
  
  public Colonia updateColoniaById(Colonia request, Long id){
    Colonia colonia = coloniaRepository.findById(id).get();

    colonia.setDescripcion(request.getDescripcion());
    colonia.setNombre(request.getNombre());
    colonia.setTelefono(request.getTelefono());
    colonia.setMovil(request.getMovil());
    coloniaRepository.save(colonia);

    return colonia;
  }

  public Boolean deleteColoniaById(Long id){
    try {
      coloniaRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }



}
