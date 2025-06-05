package com.mercadeo.monorepo.servicio_rastreo.repository;

import com.mercadeo.monorepo.servicio_rastreo.models.Rastreador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RastreadorRepository extends JpaRepository<Rastreador, UUID> {
    List<Rastreador> findByIdUsuarioConfigurador(UUID idUsuarioConfigurador);
    List<Rastreador> findByListadoId(UUID idListado);
    Optional<Rastreador> findByIdUsuarioConfiguradorAndListadoId(UUID idUsuarioConfigurador, UUID idListado);
    List<Rastreador> findByEstado(String estado); // Para buscar rastreadores activos, pausados, etc.
}