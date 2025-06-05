package com.mercadeo.monorepo.servicio_rastreo.service;

import com.mercadeo.monorepo.servicio_rastreo.dtos.alerta.ActualizarAlertaRequestDTO;
import com.mercadeo.monorepo.servicio_rastreo.dtos.alerta.CrearAlertaRequestDTO;
import com.mercadeo.monorepo.servicio_rastreo.dtos.alerta.AlertaResponseDTO;
import com.mercadeo.monorepo.servicio_rastreo.enums.EstadoAlerta;
import com.mercadeo.monorepo.servicio_rastreo.enums.TipoAlerta;
import com.mercadeo.monorepo.servicio_rastreo.exception.BadRequestException;
import com.mercadeo.monorepo.servicio_rastreo.exception.ResourceNotFoundException;
import com.mercadeo.monorepo.servicio_rastreo.models.Alerta;
import com.mercadeo.monorepo.servicio_rastreo.models.Rastreador;
import com.mercadeo.monorepo.servicio_rastreo.repository.AlertaRepository;
import com.mercadeo.monorepo.servicio_rastreo.repository.RastreadorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AlertaService {

    private final AlertaRepository alertaRepository;
    private final RastreadorRepository rastreadorRepository;
    // Asumimos que hay un servicio para obtener el nombre del usuario
    // private final UserService userService;

    public AlertaService(AlertaRepository alertaRepository, RastreadorRepository rastreadorRepository /*, UserService userService*/) {
        this.alertaRepository = alertaRepository;
        this.rastreadorRepository = rastreadorRepository;
        // this.userService = userService;
    }

    @Transactional
    public AlertaResponseDTO crearAlerta(CrearAlertaRequestDTO request) {
        Rastreador rastreador = rastreadorRepository.findById(request.getIdRastreador())
                .orElseThrow(() -> new ResourceNotFoundException("Rastreador", "id", request.getIdRastreador()));

        Alerta alerta = new Alerta();
        alerta.setIdUsuarioDestinatario(request.getIdUsuarioDestinatario());
        alerta.setRastreador(rastreador);
        alerta.setTipoAlerta(TipoAlerta.valueOf(request.getTipoAlerta()));
        alerta.setCondicionValor(request.getCondicionValor());
        alerta.setMensaje(request.getMensaje());
        alerta.setMetodoEnvio(request.getMetodoEnvio());
        alerta.setEstado(EstadoAlerta.PENDIENTE); // Estado inicial

        Alerta nuevaAlerta = alertaRepository.save(alerta);
        return mapToAlertaResponseDTO(nuevaAlerta);
    }

    @Transactional(readOnly = true)
    public AlertaResponseDTO obtenerAlertaPorId(UUID id) {
        Alerta alerta = alertaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alerta", "id", id));
        return mapToAlertaResponseDTO(alerta);
    }

    @Transactional
    public AlertaResponseDTO actualizarAlerta(UUID id, ActualizarAlertaRequestDTO request) {
        Alerta alerta = alertaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alerta", "id", id));

        if (request.getCondicionValor() != null) alerta.setCondicionValor(request.getCondicionValor());
        if (request.getMensaje() != null) alerta.setMensaje(request.getMensaje());
        if (request.getEnviada() != null) {
            alerta.setEnviada(request.getEnviada());
            if (request.getEnviada() && alerta.getFechaEnvio() == null) {
                alerta.setFechaEnvio(LocalDateTime.now()); // Registrar fecha de envío si se marca como enviada
            }
        }
        if (request.getMetodoEnvio() != null) alerta.setMetodoEnvio(request.getMetodoEnvio());
        if (request.getEstado() != null) {
            try {
                alerta.setEstado(EstadoAlerta.valueOf(request.getEstado()));
            } catch (IllegalArgumentException e) {
                throw new BadRequestException("Estado de alerta inválido: " + request.getEstado());
            }
        }

        Alerta updatedAlerta = alertaRepository.save(alerta);
        return mapToAlertaResponseDTO(updatedAlerta);
    }

    @Transactional
    public void eliminarAlerta(UUID id) {
        Alerta alerta = alertaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alerta", "id", id));
        alertaRepository.delete(alerta);
    }

    @Transactional(readOnly = true)
    public List<AlertaResponseDTO> obtenerAlertasPorUsuario(UUID idUsuarioDestinatario) {
        return alertaRepository.findByIdUsuarioDestinatario(idUsuarioDestinatario)
                .stream()
                .map(this::mapToAlertaResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AlertaResponseDTO> obtenerAlertasPorRastreador(UUID idRastreador) {
        return alertaRepository.findByRastreadorId(idRastreador)
                .stream()
                .map(this::mapToAlertaResponseDTO)
                .collect(Collectors.toList());
    }

    private AlertaResponseDTO mapToAlertaResponseDTO(Alerta alerta) {
        AlertaResponseDTO dto = new AlertaResponseDTO();
        dto.setId(alerta.getId());
        dto.setIdUsuarioDestinatario(alerta.getIdUsuarioDestinatario());
        // dto.setNombreUsuarioDestinatario(userService.obtenerUsuarioPorId(alerta.getIdUsuarioDestinatario()).getNombre()); // Requiere llamada a otro MS
        dto.setIdRastreador(alerta.getRastreador().getId());
        dto.setNombreRastreador(alerta.getRastreador().getNombrePersonalizado()); // O el nombre del listado/producto
        dto.setTipoAlerta(alerta.getTipoAlerta().name());
        dto.setCondicionValor(alerta.getCondicionValor());
        dto.setMensaje(alerta.getMensaje());
        dto.setEnviada(alerta.getEnviada());
        dto.setFechaEnvio(alerta.getFechaEnvio());
        dto.setMetodoEnvio(alerta.getMetodoEnvio());
        dto.setEstado(alerta.getEstado().name());
        dto.setFechaCreacion(alerta.getFechaCreacion());
        dto.setFechaActualizacion(alerta.getFechaActualizacion());
        return dto;
    }
}