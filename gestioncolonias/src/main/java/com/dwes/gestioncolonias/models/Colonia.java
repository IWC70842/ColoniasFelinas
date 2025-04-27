package com.dwes.gestioncolonias.models;

/**
 * Clase Entidad de Colonia
 * 
 * @author José Antonio Pozo González IWC70842@educastur.es
 *         Módulo de Desarrollo Web en Entorno Servidor 24/25
 */

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "colonias")
public class Colonia {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;

  @Column
  private String nombre;

  @Column 
  private String imagenColonia; 

  @Column
  private String descripcion;

  @Column
  private int telefono;

  @Column
  private int movil;

  @Column
  private String ubicacion;

  @Column
  private String tamano;

  @OneToMany(mappedBy = "colonia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  //Evitamos JSON anidados
  @JsonIgnore
  private List<Gato> gatos = new ArrayList<>();

  // Getters y Setters de la entidad Colonia

  public List<Gato> getGatos() {
    return gatos;
  }

  public void setGatos(List<Gato> gatos) {
    this.gatos = gatos;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  
  public String getImagenColonia() {
    return imagenColonia;
  }

  public void setImagenColonia(String imagenColonia) {
    this.imagenColonia = imagenColonia;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public int getTelefono() {
    return telefono;
  }

  public void setTelefono(int telefono) {
    this.telefono = telefono;
  }

  public int getMovil() {
    return movil;
  }

  public void setMovil(int movil) {
    this.movil = movil;
  }

  public String getUbicacion() {
    return ubicacion;
  }

  public void setUbicacion(String ubicacion) {
    this.ubicacion = ubicacion;
  }

  public String getTamano() {
    return tamano;
  }

  public void setTamano(String tamano) {
    this.tamano = tamano;
  }

}
