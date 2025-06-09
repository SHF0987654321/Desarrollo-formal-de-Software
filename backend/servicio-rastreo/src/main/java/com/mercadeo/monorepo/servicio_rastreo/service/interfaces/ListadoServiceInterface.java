package com.mercadeo.monorepo.servicio_rastreo.service.interfaces;

import com.mercadeo.monorepo.servicio_rastreo.dtos.listado.ActualizarListadoRequestDTO;
import com.mercadeo.monorepo.servicio_rastreo.dtos.listado.CrearListadoRequestDTO;
import com.mercadeo.monorepo.servicio_rastreo.dtos.listado.ListadoResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ListadoServiceInterface {
    ListadoResponseDTO crearListado(CrearListadoRequestDTO request);
    ListadoResponseDTO obtenerListadoPorId(UUID id);
    ListadoResponseDTO actualizarListado(UUID id, ActualizarListadoRequestDTO request);
    void eliminarListado(UUID id);
    List<ListadoResponseDTO> obtenerListadosPorProducto(UUID idProducto);
    List<ListadoResponseDTO> obtenerListadosPorOrganizacion(UUID idOrganizacion);
}