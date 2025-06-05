package com.mercadeo.monorepo.servicio_rastreo.repository;

import com.mercadeo.monorepo.servicio_rastreo.models.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, UUID> {
    List<Alerta> findByIdUsuarioDestinatario(UUID idUsuarioDestinatario);
    List<Alerta> findByRastreadorId(UUID idRastreador);
    List<Alerta> findByIdUsuarioDestinatarioAndEstado(UUID idUsuarioDestinatario, String estado);
}