package com.mercadeo.monorepo.servicio_rastreo.service.interfaces;

import com.mercadeo.monorepo.servicio_rastreo.dtos.rastreador.ActualizarRastreadorRequestDTO;
import com.mercadeo.monorepo.servicio_rastreo.dtos.rastreador.CrearRastreadorRequestDTO;
import com.mercadeo.monorepo.servicio_rastreo.dtos.rastreador.RastreadorResponseDTO;

import java.util.List;
import java.util.UUID;

public interface RastreadorServiceInterface {
    RastreadorResponseDTO crearRastreador(CrearRastreadorRequestDTO request);
    RastreadorResponseDTO obtenerRastreadorPorId(UUID id);
    RastreadorResponseDTO actualizarRastreador(UUID id, ActualizarRastreadorRequestDTO request);
    void eliminarRastreador(UUID id);
    List<RastreadorResponseDTO> obtenerRastreadoresPorUsuario(UUID idUsuarioConfigurador);
    List<RastreadorResponseDTO> obtenerRastreadoresPorListado(UUID idListado);
}