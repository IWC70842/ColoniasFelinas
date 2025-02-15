package com.dwes.gestioncolonias.services;

/**
 * Clase GatosService para el conexión de los gatos con el interface GatoRepository de JPA
 * 
 * @author José Antonio Pozo González IWC70842@educastur.es
 *         Módulo de Desarrollo Wen en Entorno Servidor 24/25
 */

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dwes.gestioncolonias.models.Colonia;
import com.dwes.gestioncolonias.models.Gato;
import com.dwes.gestioncolonias.repositories.GatoRepository;
import com.dwes.gestioncolonias.repositories.ColoniaRepository;

@Service
public class GatosService {

  @Autowired
  ColoniaRepository coloniaRepository;

  @Autowired
  GatoRepository gatoRepository;

  
  /** 
   * Método para obtener un listado completo de gatos
   * 
   * @return List<Gato>
   */
  public List<Gato> getGatos() {
    return (List<Gato>) gatoRepository.findAll();
  }

  
  /** 
   * Método para encontrar un gato en concreto facilitando su ID
   * 
   * @param id Id del gato a encontrar
   * @return Optional<Gato>
   */
  public Optional<Gato> getGatoById(Long id) {
    return gatoRepository.findById(id);
  }

  
  /** 
   * Método para almacenar un gato en la base de datos 
   * 
   * @param gato 
   * @return Gato
   */
  public Gato saveGato(Gato gato) {
    Optional<Colonia> coloniaOptional = coloniaRepository.findById(gato.getColonia().getId());
    gato.setColonia(coloniaOptional.get());
    return gatoRepository.save(gato);
  }

  
  /** 
   * Método para eliminar un gato de la base de datos a partir de su id
   * 
   * @param id Id del gato a eliminar
   * @return Boolean
   */
  public Boolean deleteGatoById(Long id) {
    try {
      gatoRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  
  /** 
   * Método para actualizar los datos de un gato facilitando los campos y la Id del mismo
   * 
   * @param request
   * @param id Id del gato a modificar
   * @return Gato
   */
  public Gato updateGatoById(Gato request, Long id) {
    Gato gato = gatoRepository.findById(id).get();

    gato.setEdadAproximada(request.getEdadAproximada());
    gato.setRaza(request.getRaza());
    gato.setColores(request.getColores());
    gato.setSexo(request.getSexo());
    gato.setPelaje(request.getPelaje());
    gato.setTamano(request.getTamano());
    gato.setFechaEntrada(request.getFechaEntrada());
    gato.setFechaSalida(request.getFechaSalida());
    gato.setMotivoEntrada(request.getMotivoEntrada());
    gato.setMotivoSalida(request.getMotivoSalida());

    return gato;
  }

}
