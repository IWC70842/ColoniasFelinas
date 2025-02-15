package com.dwes.gestioncolonias.services;

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

  public List<Gato> getGatos() {
    return (List<Gato>) gatoRepository.findAll();
  }

  public Optional<Gato> getGatoById(Long id) {
    return gatoRepository.findById(id);
  }

  public Gato saveGato(Gato gato) {
    Optional<Colonia> coloniaOptional = coloniaRepository.findById(gato.getColonia().getId());
    gato.setColonia(coloniaOptional.get());
    return gatoRepository.save(gato);
  }

  public Boolean deleteGatoById(Long id) {
    try {
      gatoRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

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
