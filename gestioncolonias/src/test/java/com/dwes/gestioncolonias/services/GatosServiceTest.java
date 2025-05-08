package com.dwes.gestioncolonias.services;

/**
 * Clase de Test Unitarios para GatosService
 * 
 * @author José Antonio Pozo González IWC70842@educastur.es
 *         Módulo de Desarrollo Web en Entorno Servidor 24/25
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
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
import com.dwes.gestioncolonias.constants.Salud;
import com.dwes.gestioncolonias.models.Colonia;
import com.dwes.gestioncolonias.models.Gato;
import com.dwes.gestioncolonias.repositories.ColoniaRepository;
import com.dwes.gestioncolonias.repositories.GatoRepository;

@ExtendWith(MockitoExtension.class)
class GatosServiceTest {

  @Mock
  private GatoRepository gatoRepository;

  @Mock
  private ColoniaRepository coloniaRepository;

  @InjectMocks
  private GatosService gatosService;

  private Colonia colonia;
  private Gato gato;

  /**
   * Configura un objeto Gato de prueba antes de ejecutar cada test
   */
  @BeforeEach
  void preparacion() {
    colonia = new Colonia();
    colonia.setId(1L);
    colonia.setNombre("Colonia Test");

    gato = new Gato();
    gato.setId(666L);
    gato.setEdadAproximada(2);
    gato.setRaza("Siamés");
    gato.setColores("Blanco y marrón");
    gato.setSexo(Sexo.MACHO);
    gato.setPelaje(Pelaje.CORTO);
    gato.setTamano(Tamano.MEDIANO);
    gato.setVacunado(true);
    gato.setCer(true);
    gato.setSalud(Salud.SANO);
    gato.setFechaEntrada(LocalDate.of(2024, 1, 1));
    gato.setFechaSalida(LocalDate.of(2025, 2, 1));
    gato.setMotivoEntrada("Rescatado");
    gato.setMotivoSalida("Adoptado");
    gato.setColonia(colonia);
  }

  /**
   * Obtener la lista completa de gatos almacenados con éxito.
   */
  @Test
  void testGetGatosHappyPath() {
    when(gatoRepository.findAll()).thenReturn(Arrays.asList(gato));

    List<Gato> resultado = gatosService.getGatos();

    assertFalse(resultado.isEmpty());
    assertEquals(1, resultado.size());
    assertEquals("Siamés", resultado.get(0).getRaza());
    verify(gatoRepository, times(1)).findAll();
  }

  /**
   * Intentar obtener la lista de gatos cuando esta no existe.
   */
  @Test
  void testGetGatosEmptyList() {
    when(gatoRepository.findAll()).thenReturn(Arrays.asList());

    List<Gato> resultado = gatosService.getGatos();

    assertTrue(resultado.isEmpty());
    verify(gatoRepository, times(1)).findAll();
  }

  /**
   * Obtener un gato por su Id con éxito.
   */
  @Test
  void testGetGatoByIdHappyPath() {
    when(gatoRepository.findById(666L)).thenReturn(Optional.of(gato));

    Optional<Gato> resultado = gatosService.getGatoById(666L);

    assertTrue(resultado.isPresent());
    assertEquals("Siamés", resultado.get().getRaza());
    verify(gatoRepository, times(1)).findById(666L);
  }

  /**
   * Intentar obtener un gato con un Id inexistente.
   */
  @Test
  void testGetGatoByIdNotFound() {
    when(gatoRepository.findById(666L)).thenReturn(Optional.empty());

    Optional<Gato> resultado = gatosService.getGatoById(666L);

    assertFalse(resultado.isPresent());
    verify(gatoRepository, times(1)).findById(666L);
  }

  /**
   * Guardar un gato con éxito en la base de datos.
   */
  @Test
  void testSaveGatoHappyPath() {
    when(coloniaRepository.findById(1L)).thenReturn(Optional.of(colonia));
    when(gatoRepository.save(any(Gato.class))).thenReturn(gato);

    Gato resultado = gatosService.saveGato(gato);

    assertNotNull(resultado);
    assertEquals("Siamés", resultado.getRaza());
    verify(gatoRepository, times(1)).save(any(Gato.class));
  }

  /**
   * Eliminar un gato existente con éxito.
   */
  @Test
  void testDeleteGatoByIdHappyPath() {
    when(gatoRepository.findById(666L)).thenReturn(Optional.of(gato));

    boolean resultado = gatosService.deleteGatoById(666L);

    assertTrue(resultado);
    assertNotNull(gato.getFechaSalida());
    verify(gatoRepository, times(1)).save(gato);
  }

  /**
   * Intentar eliminar un gato que no existe en la base de datos.
   */
  @Test
  void testDeleteGatoByIdError() {
    when(gatoRepository.findById(666L)).thenReturn(Optional.of(gato));

    doThrow(new RuntimeException("Error al eliminar"))
        .when(gatoRepository).save(gato);

    boolean resultado = gatosService.deleteGatoById(666L);

    assertFalse(resultado);
    verify(gatoRepository, times(1)).save(gato);
  }

  /**
   * Actualizar un gato existente con éxito
   */
  @Test
  void testUpdateGatoByIdHappyPath() {
    when(gatoRepository.findById(666L)).thenReturn(Optional.of(gato));

    Gato nuevoGato = new Gato();
    nuevoGato.setRaza("Persa");
    nuevoGato.setEdadAproximada(3);

    Gato resultado = gatosService.updateGatoById(nuevoGato, 666L);

    assertNotNull(resultado);
    assertEquals("Persa", resultado.getRaza());
    assertEquals(3, resultado.getEdadAproximada());
    verify(gatoRepository, times(1)).findById(666L);
  }

  /**
   * Intentar actualizar un gato con un Id inexistente
   */
  @Test
  void testUpdateGatoByIdNotFound() {
    when(gatoRepository.findById(666L)).thenReturn(Optional.empty());

    assertThrows(NoSuchElementException.class, () -> {
      gatosService.updateGatoById(new Gato(), 666L);
    });

    verify(gatoRepository, times(1)).findById(666L);
    verify(gatoRepository, never()).save(any(Gato.class));
  }

  /**
   * Intentar guardar un gato con datos vacíos.
   */
  @Test
  void testSaveGatoNull() {
    Gato gatoNulo = new Gato();
    Colonia colonia = new Colonia();
    colonia.setId(1L);
    gatoNulo.setColonia(colonia);

    when(coloniaRepository.findById(1L)).thenReturn(Optional.of(colonia));
    when(gatoRepository.save(any(Gato.class))).thenReturn(gatoNulo);

    Gato resultado = gatosService.saveGato(gatoNulo);

    assertNotNull(resultado);
    assertNull(resultado.getRaza());
    assertNull(resultado.getColores());
    assertNull(resultado.getFechaEntrada());
    verify(gatoRepository, times(1)).save(any(Gato.class));
  }

  /**
   * Intentar actualizar un gato con datos vacíos.
   */
  @Test
  void testUpdateGatoByIdNull() {
    Gato gatoExistente = new Gato();
    gatoExistente.setId(666L);
    gatoExistente.setRaza("Siamés");

    Gato gatoNulo = new Gato(); // Todo null

    when(gatoRepository.findById(666L)).thenReturn(Optional.of(gatoExistente));

    Gato resultado = gatosService.updateGatoById(gatoNulo, 666L);

    assertNotNull(resultado);
    assertNull(resultado.getEdadAproximada());
    assertNull(resultado.getRaza());
    assertNull(resultado.getColores());
    verify(gatoRepository, times(1)).findById(666L);

  }

}
