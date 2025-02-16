package com.dwes.gestioncolonias.controllers;

/**
 * Clase de Test Unitarios para ColoniaController
 * 
 * @author José Antonio Pozo González IWC70842@educastur.es
 *         Módulo de Desarrollo Wen en Entorno Servidor 24/25
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dwes.gestioncolonias.models.Colonia;
import com.dwes.gestioncolonias.models.Gato;
import com.dwes.gestioncolonias.services.ColoniaService;
import com.dwes.gestioncolonias.services.GatosService;

@ExtendWith(MockitoExtension.class)
public class ColoniaControllerTest {

  @Mock
  private ColoniaService coloniaService;

  @Mock
  private GatosService gatosService;

  @InjectMocks
  ColoniaController coloniaController;

  @InjectMocks
  GatosController gatosController;

  private Colonia colonia;

  @BeforeEach
  void preparacion() {
    colonia = new Colonia();
    colonia.setId(666L);
    colonia.setNombre("ColoniaMock");
    colonia.setDescripcion("Colonia mockeada");
    colonia.setTelefono(987654321);
    colonia.setMovil(666555444);
    colonia.setUbicacion("Gijón");
    colonia.setTamano("Grande");
    colonia.setGatos(null);
  }

  @Test
  void testDeleteColoniaById() {
    when(coloniaService.deleteColoniaById(666L)).thenReturn(true);

    String resultado = coloniaController.deleteColoniaById(666L);

    assertEquals("Colonia con id 666 eliminada correctamente.", resultado);
    verify(coloniaService, times(1)).deleteColoniaById(666L);
  }

  @Test
  void testGetColoniaById() {
    when(coloniaService.getColoniaById(666L)).thenReturn(Optional.of(colonia));

    Optional<Colonia> resultado = coloniaController.getColoniaById(666L);

    assertTrue(resultado.isPresent());
    assertEquals("ColoniaMock", resultado.get().getNombre());
    verify(coloniaService, times(1)).getColoniaById(666L);
  }

  @Test
  void testGetColonias() {
    List<Colonia> listaColonias = Arrays.asList(colonia);
    when(coloniaService.getColonias()).thenReturn(listaColonias);

    List<Colonia> resultado = coloniaController.getColonias();

    assertEquals(1, resultado.size());
    assertEquals("ColoniaMock", resultado.get(0).getNombre());
    verify(coloniaService, times(1)).getColonias();

  }

  @Test
  void testGetGatosByColonia() {
    Gato gato1 = new Gato();
    gato1.setId(1L);
    gato1.setRaza("Cartujo");
    Gato gato2 = new Gato();
    gato2.setId(2L);
    gato2.setRaza("Azul Ruso");
    List<Gato> gatos = Arrays.asList(gato1, gato2);
    colonia.setGatos(gatos);

    when(coloniaService.getColoniaById(666L)).thenReturn(Optional.of(colonia));

    List<Gato> resultado = coloniaController.getGatosByColonia(666L);

    assertNotNull(resultado);
    assertEquals(2, resultado.size());
    assertEquals("Cartujo", resultado.get(0).getRaza());
    assertEquals("Azul Ruso", resultado.get(1).getRaza());
    verify(coloniaService, times(1)).getColoniaById(666L);

  }

  @Test
  void testUpdateColonoiaById() {

    when(coloniaService.updateColoniaById(any(Colonia.class), eq(666L))).thenReturn(colonia);

    Colonia resultado = coloniaController.updateColonoiaById(colonia, 666L);

    assertNotNull(resultado);
    assertEquals("ColoniaMock", resultado.getNombre());
    verify(coloniaService, times(1)).updateColoniaById(any(Colonia.class), eq(666L));

  }
}
