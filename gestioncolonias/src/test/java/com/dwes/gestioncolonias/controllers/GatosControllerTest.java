package com.dwes.gestioncolonias.controllers;

/**
 *  Test unitarios utilizando Mockito
 * 
 *  @author José Antonio Pozo González IWC70842@educastur.es
 *          Módulo de Desarrollo Wen en Entorno Servidor 24/25
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dwes.gestioncolonias.constants.Pelaje;
import com.dwes.gestioncolonias.constants.Sexo;
import com.dwes.gestioncolonias.constants.Tamano;
import com.dwes.gestioncolonias.models.Gato;
import com.dwes.gestioncolonias.services.GatosService;

@ExtendWith(MockitoExtension.class)
public class GatosControllerTest {

  @Mock
  private GatosService gatosService;

  @InjectMocks
  GatosController gatosController;

  private Gato gato;
  
  @BeforeEach
  void preparacion() {
    gato = new Gato();
    gato.setId(666L);
    gato.setEdadAproximada(2);
    gato.setRaza("Siamés");
    gato.setColores("Blanco y negro");
    gato.setSexo(Sexo.MACHO);
    gato.setPelaje(Pelaje.CORTO);
    gato.setTamano(Tamano.MEDIANO);
    gato.setFechaEntrada(LocalDate.of(2024, 11, 1));
    gato.setFechaSalida(LocalDate.of(2025, 2, 1));
    gato.setMotivoSalida("Adoptado");
  }
  
  @Test
  void testDeleteGatoById() {
    when(gatosService.deleteGatoById(666L)).thenReturn(true);

    String resultado = gatosController.deleteGatoById(666L);

    assertEquals("Gato con id 666 eliminado correctamente.", resultado);
    verify(gatosService, times(1)).deleteGatoById(666L);
  }

  @Test
  void testDeleteGatoByIdError() {
      when(gatosService.deleteGatoById(666L)).thenReturn(false);

      String resultado = gatosController.deleteGatoById(666L);

      assertEquals("Error, gato con id 666 no ha podido ser eliminado.", resultado);
      verify(gatosService, times(1)).deleteGatoById(666L);
  }

  @Test
  void testGato() {
    when(gatosService.saveGato(any(Gato.class))).thenReturn(gato);

    Gato resultado = gatosController.gato(gato);

    assertNotNull(resultado);
    assertEquals("Siamés", resultado.getRaza());
    verify(gatosService, times(1)).saveGato(any(Gato.class));
  }

  @Test
  void testGetGatoById() {
    when(gatosService.getGatoById(666L)).thenReturn(Optional.of(gato));

    Optional<Gato> resultado = gatosController.getGatoById(666L);

    assertTrue(resultado.isPresent());
    assertEquals("Siamés", resultado.get().getRaza());
    verify(gatosService, times(1)).getGatoById(666L);

  }

  @Test
  void testGetGatos() {
    List<Gato> listaGatos = Arrays.asList(gato);
    when(gatosService.getGatos()).thenReturn(listaGatos);

    List<Gato> resultado = gatosController.getGatos();

    assertEquals(1, resultado.size());
    assertEquals("Siamés", resultado.get(0).getRaza());
    verify(gatosService, times(1)).getGatos();

  }

  @Test
  void testUpdateGatoById() {
     when(gatosService.updateGatoById(any(Gato.class), eq(666L))).thenReturn(gato);

        Gato resultado = gatosController.updateGatoById(gato, 666L);

        assertNotNull(resultado);
        assertEquals("Siamés", resultado.getRaza());
        verify(gatosService, times(1)).updateGatoById(any(Gato.class), eq(666L));

  }
}
