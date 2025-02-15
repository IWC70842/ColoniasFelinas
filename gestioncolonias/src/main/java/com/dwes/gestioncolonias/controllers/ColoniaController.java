package com.dwes.gestioncolonias.controllers;

/**
 * Clase Controller de la gestión de las colonias proporcionando los endpoints de la API
 * 
 * @author José Antonio Pozo González IWC70842@educastur.es
 *         Módulo de Desarrollo Wen en Entorno Servidor 24/25
 */

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

  /**
   * Método para obtener el listado de las colonias
   * 
   * @return
   */
  @GetMapping
  public List<Colonia> getColonias() {
    return coloniaService.getColonias();
  }

  /**
   * Metodo para publicar una nueva colonia facilitando sus campos
   * 
   * @param colonia
   * @return
   */
  @PostMapping
  public Colonia colonia(@RequestBody Colonia colonia) {
    return coloniaService.saveColonia(colonia);
  }

  /**
   * Método para obtener un listado con todos los gatos que pertenecen en una
   * colonia a partir de la id de la colonia
   * 
   * @param id Id de la colonia
   * @return
   */
  @GetMapping("/{id}/gatos")
  public List<Gato> getGatosByColonia(@PathVariable Long id) {
    Optional<Colonia> colonia = coloniaService.getColoniaById(id);
    if (colonia.isPresent()) {
      return colonia.get().getGatos();
    } else {
      return new ArrayList<>();
    }
  }

  /**
   * Metodo para obtener los datos de una colonia en concreto a partir de su id
   * 
   * @param id Id de la colonia a obtener datos
   * @return
   */
  @GetMapping("/{id}")
  public Optional<Colonia> getColoniaById(@PathVariable("id") Long id) {
    return coloniaService.getColoniaById(id);
  }

  /**
   * Método para actualizar los datos de una colonia facilitando los campos y su
   * id
   * 
   * @param request
   * @param id      Id de la colonia a modificar
   * @return
   */
  @PutMapping("/{id}")
  public Colonia updateColonoiaById(@RequestBody Colonia request, @PathVariable("id") Long id) {
    return coloniaService.updateColoniaById(request, id);
  }

  /**
   * Método para eliminar una colonia facilitando la id de la colonia a eliminar
   * 
   * @param id Id de la colonia a eliminar
   * @return
   */
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
