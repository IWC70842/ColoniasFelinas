package com.dwes.gestioncolonias.controllers;

/**
 * Clase Controller de la gestión de los gatos proporcionando los endpoints de la API
 * 
 * @author José Antonio Pozo González IWC70842@educastur.es
 *         Módulo de Desarrollo Wen en Entorno Servidor 24/25
 */

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

  /**
   * Método para obtener el listado completo de gatos
   * 
   * @return
   */
  @GetMapping
  public List<Gato> getGatos() {
    return gatosService.getGatos();
  }

  /**
   * Método para obtener un gato en concreto a partir de su id
   * 
   * @param id id del gato a obtener
   * @return
   */
  @GetMapping("/{id}")
  public Optional<Gato> getGatoById(@PathVariable("id") Long id) {
    return gatosService.getGatoById(id);
  }

  /**
   * Método para solicitar la creación de un nuevo gato
   * 
   * @param gato
   * @return
   */
  @PostMapping
  public Gato gato(@RequestBody Gato gato) {
    return gatosService.saveGato(gato);
  }

  /**
   * Método para eliminar un gato a partir de su id
   * 
   * @param id Id del gato a eliminar
   * @return
   */
  @DeleteMapping("/{id}")
  public String deleteGatoById(@PathVariable("id") Long id) {
    boolean ok = gatosService.deleteGatoById(id);

    if (ok) {
      return "Gato con id " + id + " eliminado correctamente.";
    } else {
      return "Error, gato con id " + id + " no ha podido ser eliminado.";
    }
  }

  /**
   * Método para actualizar los datos de un gato a partir de su id
   * 
   * @param request
   * @param id      Id del gato a actualizar
   * @return
   */
  @PutMapping("/{id}")
  public Gato updateGatoById(@RequestBody Gato request, @PathVariable("id") Long id) {
    return gatosService.updateGatoById(request, id);
  }
}
