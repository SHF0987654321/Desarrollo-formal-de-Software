package com.mercadeo.monorepo.servicio_rastreo.repository;

import com.mercadeo.monorepo.servicio_rastreo.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, UUID> {
    List<Producto> findByIdOrganizacion(UUID idOrganizacion);
    List<Producto> findByIdOrganizacionAndIdUsuarioCreador(UUID idOrganizacion, UUID idUsuarioCreador);
}