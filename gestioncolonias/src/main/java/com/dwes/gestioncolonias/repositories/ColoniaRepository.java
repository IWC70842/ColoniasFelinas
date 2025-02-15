package com.dwes.gestioncolonias.repositories;

/**
 * Interface de repositorio JPA para la entidad Colonia 
 * 
 * @author José Antonio Pozo González IWC70842@educastur.es
 *         Módulo de Desarrollo Wen en Entorno Servidor 24/25
 */

import org.springframework.data.jpa.repository.JpaRepository;

import com.dwes.gestioncolonias.models.Colonia;

public interface ColoniaRepository extends JpaRepository<Colonia, Long>{

}