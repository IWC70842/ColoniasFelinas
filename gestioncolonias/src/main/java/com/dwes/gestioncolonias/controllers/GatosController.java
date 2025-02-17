package com.dwes.gestioncolonias.controllers;

/**
 * Clase Controller de la gestión de los gatos proporcionando los endpoints de la API
 * 
 * @author José Antonio Pozo González IWC70842@educastur.es
 *         Módulo de Desarrollo Web en Entorno Servidor 24/25
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
   * Obtiene la lista completa de gatos almacenados en la base de datos.
   *
   * @return Una lista de objetos Gato con todos los registros almacenados.
   */
  @GetMapping
  public List<Gato> getGatos() {
    return gatosService.getGatos();
  }

  /**
   * Obtiene un gato específico a partir de su identificador único.
   *
   * @param id Identificador del gato en la base de datos.
   * @return Un objeto Optional que contiene el gato si se encuentra,
   *         o vacío si no existe un gato con el Id proporcionado.
   */
  @GetMapping("/{id}")
  public Optional<Gato> getGatoById(@PathVariable("id") Long id) {
    return gatosService.getGatoById(id);
  }

  /**
   * Almacena un nuevo gato en la base de datos.
   *
   * @param gato Objeto Gato que se desea almacenar.
   * @return El objeto Gato almacenado con sus datos actualizados.
   */
  @PostMapping
  public Gato gato(@RequestBody Gato gato) {
    return gatosService.saveGato(gato);
  }

  /**
   * Elimina un gato de la base de datos a partir de su identificador.
   *
   * @param id Identificador del gato que se desea eliminar.
   * @return Un mensaje de confirmación si la eliminación es exitosa o un mensaje
   *         de error si no se encuentra un gato con el Id proporcionado.
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
   * Actualiza los datos de un gato existente en la base de datos.
   *
   * @param request Objeto Gato con los datos actualizados.
   * @param id      Identificador del gato que se desea modificar.
   * @return El objeto Gato con los datos actualizados tras la modificación.
   */
  @PutMapping("/{id}")
  public Gato updateGatoById(@RequestBody Gato request, @PathVariable("id") Long id) {
    return gatosService.updateGatoById(request, id);
  }
}
