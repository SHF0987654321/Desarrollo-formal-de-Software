package com.mercadeo.monorepo.servicio_rastreo.repository;

import com.mercadeo.monorepo.servicio_rastreo.models.Listado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ListadoRepository extends JpaRepository<Listado, UUID> {
    List<Listado> findByIdOrganizacion(UUID idOrganizacion);
    List<Listado> findByProductoId(UUID idProducto);
    Optional<Listado> findByUrl(String url);
    Optional<Listado> findByProductoIdAndUrl(UUID idProducto, String url);
}