package com.dwes.gestioncolonias.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dwes.gestioncolonias.models.Gato;
import com.dwes.gestioncolonias.services.GatosService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/gatos")
public class GatosController {

  @Autowired
  GatosService gatosService;

  @GetMapping
  public List<Gato> getGatos() {
    return gatosService.getGatos();
  }

  @GetMapping("/{id}")
  public Optional<Gato> getGatoById(@PathVariable("id") Long id) {
    return gatosService.getGatoById(id);
  }

  @PostMapping
  public Gato gato(@RequestBody Gato gato) {
    return gatosService.saveGato(gato);
  }

  @DeleteMapping("/{id}")
  public String deleteGatoById(@PathVariable ("id") Long id){
    boolean ok=gatosService.deleteGatoById(id);

    if (ok) {
      return "Gato con id "+id+" eliminado correctamente.";
    } else {
      return "Error, gato con id "+id+" no ha podido ser eliminado.";
    }
  } 

  @PutMapping("/{id}")
 public Gato updateGatoById(@RequestBody Gato request, @PathVariable("id") Long id){
  return gatosService.updateGatoById(request,id);
 }
}
