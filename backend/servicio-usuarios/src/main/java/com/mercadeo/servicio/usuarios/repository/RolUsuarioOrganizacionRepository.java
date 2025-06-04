package com.mercadeo.servicio.usuarios.repository;

import com.mercadeo.servicio.usuarios.models.RolUsuarioOrganizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RolUsuarioOrganizacionRepository extends JpaRepository<RolUsuarioOrganizacion, UUID> {
    Optional<RolUsuarioOrganizacion> findByUsuarioIdAndOrganizacionId(UUID userId, UUID organizationId);
    List<RolUsuarioOrganizacion> findByOrganizacionId(UUID organizationId);
    List<RolUsuarioOrganizacion> findByUsuarioId(UUID userId);
}