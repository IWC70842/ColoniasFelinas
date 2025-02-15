package com.dwes.gestioncolonias.services;

/**
 * Clase ColoniaService para el conexión de las colonias con la interface ColoniaRepository de JPA
 * 
 * @author José Antonio Pozo González IWC70842@educastur.es
 *         Módulo de Desarrollo Wen en Entorno Servidor 24/25
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
   * Método para obtener todas las colonias almacenadas en la base de datos
   * 
   * @return List<Colonia>
   */
  public List<Colonia> getColonias() {
    return (List<Colonia>) coloniaRepository.findAll();
  }

  /**
   * Métpdo para guardar una colonia en la base de datos
   * 
   * @param colonia
   * @return Colonia
   */
  public Colonia saveColonia(Colonia colonia) {
    return coloniaRepository.save(colonia);
  }

  /**
   * Método para obtener una colonia almacenada en la base de datos a partir de su
   * Id
   * 
   * @param id Id de la colonia a buscar
   * @return Optional<Colonia>
   */
  public Optional<Colonia> getColoniaById(Long id) {
    return coloniaRepository.findById(id);
  }

  /**
   * Método para actualizar una colonia facilitando los datos y su id
   * 
   * @param request Datos de la colonia a actualizar
   * @param id      Id de la colonia a actuializar
   * @return
   */
  public Colonia updateColoniaById(Colonia request, Long id) {
    Colonia colonia = coloniaRepository.findById(id).get();

    colonia.setDescripcion(request.getDescripcion());
    colonia.setNombre(request.getNombre());
    colonia.setTelefono(request.getTelefono());
    colonia.setMovil(request.getMovil());
    coloniaRepository.save(colonia);

    return colonia;
  }

  /**
   * Método para eliminar una colonia de la base de datos facilitando la id
   * 
   * @param id Id de la colonia a eliminar
   * @return
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
