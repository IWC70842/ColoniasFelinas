package com.dwes.gestioncolonias.controllers;

/**
 * Clase Controller de la gestión de las colonias proporcionando los endpoints de la API
 * 
 * @author José Antonio Pozo González IWC70842@educastur.es
 *         Módulo de Desarrollo Web en Entorno Servidor 24/25
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
   * Obtiene la lista completa de colonias almacenadas en la base de datos.
   * 
   * @return Un lista de objetos Colonia con todos los registros almacenados.
   */
  @GetMapping
  public List<Colonia> getColonias() {
    return coloniaService.getColonias();
  }

  /**
   * Almacena una nueva colonia en la base de datos.
   * 
   * @param colonia Objeto Colonia que se desea almacenar.
   * @return El objeto Colonia almacenado con sus datos actualizados.
   */
  @PostMapping
  public Colonia colonia(@RequestBody Colonia colonia) {
    return coloniaService.saveColonia(colonia);
  }

  /**
   * Obtiene los gatos que pertenecen a una colonia con un identificador único.
   * 
   * @param id Indentificador de la colonia en la base de datos.
   * @return Una lista de objetos Gato que pertenecen a la colonia con el id
   *         proporcionado.
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
   * Obtiene una colonia específica a partir de su identificador único.
   * 
   * @param id Identificador de la colonia en la base de datos.
   * @return Un objeto Optional que contiene la colonia si se encuentra, o vacío
   *         si no existe una colonia con el Id proporcionado.
   */
  @GetMapping("/{id}")
  public Optional<Colonia> getColoniaById(@PathVariable("id") Long id) {
    return coloniaService.getColoniaById(id);
  }

  /**
   * Actualiza los datos de una colonia existente en la base de datos.
   * 
   * @param request Objeto colonia con los datos actualizados.
   * @param id      Indentificador de la colonia que se desea modificar en la base
   *                de datos.
   * @return El objeto Colonia con los datos actualizados tras la modificación.
   */
  @PutMapping("/{id}")
  public Colonia updateColonoiaById(@RequestBody Colonia request, @PathVariable("id") Long id) {
    return coloniaService.updateColoniaById(request, id);
  }

  /**
   * Elimina una colonia de la base de datos a partir de su identificador.
   * 
   * @param id Indentificador de la colonia que se desea eliminar.
   * @return Un mensaje de confirmación si la eliminación es exitosa o un mensaje
   *         de error si no se encuentra un gato con el Id proporcionado.
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
