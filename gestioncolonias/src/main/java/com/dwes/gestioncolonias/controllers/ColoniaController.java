package com.dwes.gestioncolonias.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dwes.gestioncolonias.models.Colonia;
import com.dwes.gestioncolonias.models.Gato;
import com.dwes.gestioncolonias.services.ColoniaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/colonias")
public class ColoniaController {

  @Autowired
  private ColoniaService coloniaService;

  @GetMapping
  public List<Colonia> getColonias() {
    return coloniaService.getColonias();
  }

  @PostMapping
  public Colonia colonia(@RequestBody Colonia colonia) {
    return coloniaService.saveColonia(colonia);
  }

  @GetMapping("/{id}/gatos")
  public List<Gato> getGatosByColonia(@PathVariable Long id) {
    Optional<Colonia> colonia = coloniaService.getColoniaById(id);
    if (colonia.isPresent()) {
      return colonia.get().getGatos();
    } else {
      return new ArrayList<>();
    }
  }

  @GetMapping("/{id}")
  public Optional<Colonia> getColoniaById(@PathVariable("id") Long id) {
    return coloniaService.getColoniaById(id);
  }

  @PutMapping("/{id}")
  public Colonia updateColonoiaById(@RequestBody Colonia request, @PathVariable("id") Long id) {
    return coloniaService.updateColoniaById(request, id);
  }

  @DeleteMapping("/{id}")
  public String deleteColoniaById(@PathVariable("id") Long id) {
    boolean ok = coloniaService.deleteColoniaById(id);
    if (ok) {
      return "Colonia con id " + id + " eliminada correctamente.";
    } else {
      return "Error la colonia con id " + id + " no ha podido ser eliminada.";
    }
  }
}
