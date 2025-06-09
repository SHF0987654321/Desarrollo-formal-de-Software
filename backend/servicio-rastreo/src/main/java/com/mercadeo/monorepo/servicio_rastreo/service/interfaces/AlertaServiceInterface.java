package com.mercadeo.monorepo.servicio_rastreo.service.interfaces;

import com.mercadeo.monorepo.servicio_rastreo.dtos.alerta.ActualizarAlertaRequestDTO;
import com.mercadeo.monorepo.servicio_rastreo.dtos.alerta.CrearAlertaRequestDTO;
import com.mercadeo.monorepo.servicio_rastreo.dtos.alerta.AlertaResponseDTO;

import java.util.List;
import java.util.UUID;

public interface AlertaServiceInterface {
    AlertaResponseDTO crearAlerta(CrearAlertaRequestDTO request);
    AlertaResponseDTO obtenerAlertaPorId(UUID id);
    AlertaResponseDTO actualizarAlerta(UUID id, ActualizarAlertaRequestDTO request);
    void eliminarAlerta(UUID id);
    List<AlertaResponseDTO> obtenerAlertasPorUsuario(UUID idUsuarioDestinatario);
    List<AlertaResponseDTO> obtenerAlertasPorRastreador(UUID idRastreador);
}