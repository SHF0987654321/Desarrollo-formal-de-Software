package com.mercadeo.servicio.usuarios.repository;

import com.mercadeo.servicio.usuarios.models.InvitacionOrganizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InvitacionOrganizacionRepository extends JpaRepository<InvitacionOrganizacion, UUID> {
    Optional<InvitacionOrganizacion> findByToken(UUID token);
    Optional<InvitacionOrganizacion> findByCorreoInvitadoAndOrganizacionId(String correoInvitado, UUID organizacionId);
    List<InvitacionOrganizacion> findByOrganizacionId(UUID organizacionId);
    List<InvitacionOrganizacion> findByCorreoInvitadoAndEstado(String correoInvitado, String estado);
}
