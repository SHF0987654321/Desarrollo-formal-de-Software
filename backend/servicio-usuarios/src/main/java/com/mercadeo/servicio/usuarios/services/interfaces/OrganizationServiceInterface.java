package com.mercadeo.servicio.usuarios.services.interfaces;

import com.mercadeo.servicio.usuarios.dtos.organization.ActualizarOrganizacionRequestDTO;
import com.mercadeo.servicio.usuarios.dtos.organization.CrearOrganizacionRequestDTO;
import com.mercadeo.servicio.usuarios.dtos.organization.OrganizacionResponseDTO;

import java.util.List;
import java.util.UUID;

public interface OrganizationServiceInterface {
    OrganizacionResponseDTO crearOrganizacion(CrearOrganizacionRequestDTO request, UUID idUsuarioPropietario);
    OrganizacionResponseDTO obtenerOrganizacionPorId(UUID id);
    OrganizacionResponseDTO actualizarOrganizacion(UUID idOrganizacion, ActualizarOrganizacionRequestDTO request);
    List<OrganizacionResponseDTO> obtenerOrganizacionesPorUsuario(UUID idUsuario);
}