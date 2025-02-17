package com.dwes.gestioncolonias.services;

/**
 * Clase GatosService para la conexión de los gatos con el interface GatoRepository de JPA
 * 
 * @author José Antonio Pozo González IWC70842@educastur.es
 *         Módulo de Desarrollo Web en Entorno Servidor 24/25
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
   * Obtiene la lista completa de gatos almacenados en la base de datos.
   *
   * @return Una lista de objetos Gato con todos los registros almacenados.
   */
  public List<Gato> getGatos() {
    return (List<Gato>) gatoRepository.findAll();
  }

  /**
   * Obtiene un gato específico a partir de su identificador único.
   *
   * @param id Identificador del gato en la base de datos.
   * @return Un objeto Optional que contiene el gato si se encuentra,
   *         o vacío si no existe un gato con el Id proporcionado.
   */
  public Optional<Gato> getGatoById(Long id) {
    return gatoRepository.findById(id);
  }

  /**
   * Almacena un nuevo gato en la base de datos.
   *
   * @param gato Objeto Gato que se desea almacenar.
   * @return El objeto Gato almacenado con sus datos actualizados.
   */
  public Gato saveGato(Gato gato) {
    Optional<Colonia> coloniaOptional = coloniaRepository.findById(gato.getColonia().getId());
    gato.setColonia(coloniaOptional.get());
    return gatoRepository.save(gato);
  }

  /**
   * Elimina un gato de la base de datos a partir de su identificador.
   *
   * @param id Identificador del gato que se desea eliminar.
   * @return Devuelte verdadero si la eliminación es exitosa o falso si no se
   *         encuentra un gato con el Id proporcionado.
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
   * Actualiza los datos de un gato existente en la base de datos.
   *
   * @param request Objeto Gato con los datos actualizados.
   * @param id      Identificador del gato que se desea modificar.
   * @return El objeto Gato con los datos actualizados tras la modificación.
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
