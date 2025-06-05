package com.mercadeo.monorepo.servicio_rastreo.repository;

import com.mercadeo.monorepo.servicio_rastreo.models.HistorialPrecio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface HistorialPrecioRepository extends JpaRepository<HistorialPrecio, UUID> {
    List<HistorialPrecio> findByListadoIdOrderByFechaRegistroDesc(UUID idListado);
    List<HistorialPrecio> findByListadoIdAndFechaRegistroBetween(UUID idListado, LocalDateTime start, LocalDateTime end);
}