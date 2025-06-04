package com.mercadeo.servicio.usuarios.repository;

import com.mercadeo.servicio.usuarios.models.Organizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrganizacionRepository extends JpaRepository<Organizacion, UUID> {
    List<Organizacion> findByUsuarioPropietarioId(UUID idUsuarioPropietario);

    // Consulta para la restricción UNIQUE(id_fiscal, codigo_pais_id_fiscal) WHERE es_empresa_formal = TRUE
    // Spring Data JPA puede inferir esto, pero a veces es útil ser explícito o si la condición es compleja.
    Optional<Organizacion> findByIdFiscalAndCodigoPaisIdFiscalAndEsEmpresaFormalIsTrue(String idFiscal, String codigoPaisIdFiscal);

    // Si necesitas buscar organizaciones por un usuario que es miembro (no solo propietario)
    @Query("SELECT o FROM Organizacion o JOIN o.rolesOrganizacion r WHERE r.usuario.id = :userId")
    List<Organizacion> findByMemberUserId(@Param("userId") UUID userId);
}