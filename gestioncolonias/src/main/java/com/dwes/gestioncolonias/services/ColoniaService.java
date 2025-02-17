package com.dwes.gestioncolonias.services;

/**
 * Clase ColoniaService para la conexión de las colonias con la interface ColoniaRepository de JPA
 * 
 * @author José Antonio Pozo González IWC70842@educastur.es
 *         Módulo de Desarrollo Web en Entorno Servidor 24/25
 */

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

  /**
   * Obtiene la lista completa de colonias almacenadas en la base de datos.
   * 
   * @return Un lista de objetos Colonia con todos los registros almacenados.
   */
  public List<Colonia> getColonias() {
    return (List<Colonia>) coloniaRepository.findAll();
  }

  /**
   * Almacena una nueva colonia en la base de datos.
   * 
   * @param colonia Objeto Colonia que se desea almacenar.
   * @return El objeto Colonia almacenado con sus datos actualizados.
   */
  public Colonia saveColonia(Colonia colonia) {
    return coloniaRepository.save(colonia);
  }

  /**
   * Obtiene una colonia específica a partir de su identificador único.
   * 
   * @param id Identificador de la colonia en la base de datos.
   * @return Un objeto Optional que contiene la colonia si se encuentra, o vacío
   *         si no existe una colonia con el Id proporcionado.
   */
  public Optional<Colonia> getColoniaById(Long id) {
    return coloniaRepository.findById(id);
  }

  /**
   * Actualiza los datos de una colonia existente en la base de datos.
   * 
   * @param request Objeto colonia con los datos actualizados.
   * @param id      Indentificador de la colonia que se desea modificar en la base
   *                de datos.
   * @return El objeto Colonia con los datos actualizados tras la modificación.
   */
  public Colonia updateColoniaById(Colonia request, Long id) {
    Colonia colonia = coloniaRepository.findById(id).get();

    colonia.setNombre(request.getNombre());
    colonia.setDescripcion(request.getDescripcion());
    colonia.setTelefono(request.getTelefono());
    colonia.setMovil(request.getMovil());
    colonia.setUbicacion(request.getUbicacion());
    colonia.setTamano(request.getTamano());
    coloniaRepository.save(colonia);

    return colonia;
  }

  /**
   * Elimina una colonia de la base de datos a partir de su identificador.
   * 
   * @param id Indentificador de la colonia que se desea eliminar.
   * @return Devuelve verdadero si la eliminación es exitosa o falso si no se
   *         encuentra un gato con el Id proporcionado.
   */
  public Boolean deleteColoniaById(Long id) {
    try {
      coloniaRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

}
