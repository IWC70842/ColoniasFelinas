package com.dwes.gestioncolonias.services;

/**
 * Clase de Test Unitarios para ColoniaService
 * 
 * @author José Antonio Pozo González IWC70842@educastur.es
 *         Módulo de Desarrollo Web en Entorno Servidor 24/25
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dwes.gestioncolonias.models.Colonia;
import com.dwes.gestioncolonias.repositories.ColoniaRepository;
import com.dwes.gestioncolonias.repositories.GatoRepository;

@ExtendWith(MockitoExtension.class)
public class ColoniaServiceTest {

  @Mock
  private ColoniaRepository coloniaRepository;

  @Mock
  private GatoRepository gatoRepository;

  @InjectMocks
  ColoniaService coloniaService;

  private Colonia colonia;

  /**
   * Configura un objeto Colonia de prueba antes de ejecutar cada test
   */
  @BeforeEach
  void preparacion() {
    colonia = new Colonia();
    colonia.setId(666L);
    colonia.setNombre("ColoniaMock");

  }

  /**
   * Eliminar una colonia existente con éxito.
   */
  @Test
  void testDeleteColoniaById() {
    doNothing().when(coloniaRepository).deleteById(666L);

    boolean resultado = coloniaService.deleteColoniaById(666L);

    assertTrue(resultado);
    verify(coloniaRepository, times(1)).deleteById(666L);
  }

  /**
   * Obtener una colonia por su Id con éxito.
   */
  @Test
  void testGetColoniaById() {
    when(coloniaRepository.findById(666L)).thenReturn(Optional.of(colonia));

    Optional<Colonia> resultado = coloniaService.getColoniaById(666L);

    assertTrue(resultado.isPresent());
    assertEquals("ColoniaMock", resultado.get().getNombre());
    verify(coloniaRepository, times(1)).findById(666L);
  }

  /**
   * Obtener la lista completa de colonias con éxito.
   */
  @Test
  void testGetColonias() {
    when(coloniaRepository.findAll()).thenReturn(Arrays.asList(colonia));

    List<Colonia> resultado = coloniaService.getColonias();

    assertFalse(resultado.isEmpty());
    assertEquals(1, resultado.size());
    assertEquals("ColoniaMock", colonia.getNombre());
    verify(coloniaRepository, times(1)).findAll();

  }

  /**
   * Guardar una colonia con éxito.
   */
  @Test
  void testSaveColonia() {
    when(coloniaRepository.save(any(Colonia.class))).thenReturn(colonia);

    Colonia resultado = coloniaService.saveColonia(colonia);

    assertNotNull(resultado);
    assertEquals("ColoniaMock", resultado.getNombre());
    verify(coloniaRepository, times(1)).save(any(Colonia.class));
  }

  /**
   * Actualizar una colonia con éxito
   */
  @Test
  void testUpdateColoniaById() {
    when(coloniaRepository.findById(666L)).thenReturn(Optional.of(colonia));

    Colonia nuevaColonia = new Colonia();
    nuevaColonia.setNombre("ColoniaMockNueva");
    nuevaColonia.setMovil(666555444);

    Colonia resultado = coloniaService.updateColoniaById(nuevaColonia, 666L);

    assertNotNull(resultado);
    assertEquals("ColoniaMockNueva", resultado.getNombre());
    assertEquals(666555444, resultado.getMovil());
    verify(coloniaRepository, times(1)).findById(666L);

  }
}
