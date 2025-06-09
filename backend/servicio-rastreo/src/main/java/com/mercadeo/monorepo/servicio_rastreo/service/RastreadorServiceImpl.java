package com.mercadeo.monorepo.servicio_rastreo.service;

import com.mercadeo.monorepo.servicio_rastreo.dtos.rastreador.ActualizarRastreadorRequestDTO;
import com.mercadeo.monorepo.servicio_rastreo.dtos.rastreador.CrearRastreadorRequestDTO;
import com.mercadeo.monorepo.servicio_rastreo.dtos.rastreador.RastreadorResponseDTO;
import com.mercadeo.monorepo.servicio_rastreo.enums.EstadoRastreador;
import com.mercadeo.monorepo.servicio_rastreo.exception.BadRequestException;
import com.mercadeo.monorepo.servicio_rastreo.exception.ResourceNotFoundException;
import com.mercadeo.monorepo.servicio_rastreo.models.Listado;
import com.mercadeo.monorepo.servicio_rastreo.models.Rastreador;
import com.mercadeo.monorepo.servicio_rastreo.repository.ListadoRepository;
import com.mercadeo.monorepo.servicio_rastreo.repository.RastreadorRepository;
import com.mercadeo.monorepo.servicio_rastreo.service.interfaces.RastreadorServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RastreadorServiceImpl implements RastreadorServiceInterface {

    private final RastreadorRepository rastreadorRepository;
    private final ListadoRepository listadoRepository;
    // Asumimos que hay un servicio para obtener el nombre del usuario
    // private final UserService userService;

    public RastreadorService(RastreadorRepository rastreadorRepository, ListadoRepository listadoRepository /*, UserService userService*/) {
        this.rastreadorRepository = rastreadorRepository;
        this.listadoRepository = listadoRepository;
        // this.userService = userService;
    }

    @Override
    @Transactional
    public RastreadorResponseDTO crearRastreador(CrearRastreadorRequestDTO request) {
        Listado listado = listadoRepository.findById(request.getIdListado())
                .orElseThrow(() -> new ResourceNotFoundException("Listado", "id", request.getIdListado()));

        // Validar unicidad: un usuario solo puede configurar un rastreador para un listado específico
        if (rastreadorRepository.findByIdUsuarioConfiguradorAndListadoId(request.getIdUsuarioConfigurador(), request.getIdListado()).isPresent()) {
            throw new BadRequestException("Ya existe un rastreador configurado por este usuario para este listado.");
        }

        Rastreador rastreador = new Rastreador();
        rastreador.setIdUsuarioConfigurador(request.getIdUsuarioConfigurador());
        rastreador.setListado(listado);
        rastreador.setNombrePersonalizado(request.getNombrePersonalizado());
        rastreador.setPrecioObjetivo(request.getPrecioObjetivo());
        rastreador.setFrecuenciaRastreo(request.getFrecuenciaRastreo());
        rastreador.setEstado(EstadoRastreador.valueOf(request.getEstado()));
        rastreador.setProximoRastreo(LocalDateTime.now().plusMinutes(request.getFrecuenciaRastreo())); // Primer próximo rastreo

        Rastreador nuevoRastreador = rastreadorRepository.save(rastreador);
        return mapToRastreadorResponseDTO(nuevoRastreador);
    }

    @Override
    @Transactional(readOnly = true)
    public RastreadorResponseDTO obtenerRastreadorPorId(UUID id) {
        Rastreador rastreador = rastreadorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rastreador", "id", id));
        return mapToRastreadorResponseDTO(rastreador);
    }

    @Override
    @Transactional
    public RastreadorResponseDTO actualizarRastreador(UUID id, ActualizarRastreadorRequestDTO request) {
        Rastreador rastreador = rastreadorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rastreador", "id", id));

        if (request.getNombrePersonalizado() != null) rastreador.setNombrePersonalizado(request.getNombrePersonalizado());
        if (request.getPrecioObjetivo() != null) rastreador.setPrecioObjetivo(request.getPrecioObjetivo());
        if (request.getFrecuenciaRastreo() != null) {
            rastreador.setFrecuenciaRastreo(request.getFrecuenciaRastreo());
            // Recalcular proximo_rastreo si la frecuencia cambia y el estado es activo
            if (rastreador.getEstado() == EstadoRastreador.ACTIVO) {
                rastreador.setProximoRastreo(LocalDateTime.now().plusMinutes(request.getFrecuenciaRastreo()));
            }
        }
        if (request.getEstado() != null) {
            try {
                EstadoRastreador nuevoEstado = EstadoRastreador.valueOf(request.getEstado());
                rastreador.setEstado(nuevoEstado);
                // Si se activa, recalcular proximo_rastreo
                if (nuevoEstado == EstadoRastreador.ACTIVO && rastreador.getProximoRastreo() == null) {
                    rastreador.setProximoRastreo(LocalDateTime.now().plusMinutes(rastreador.getFrecuenciaRastreo()));
                }
            } catch (IllegalArgumentException e) {
                throw new BadRequestException("Estado de rastreador inválido: " + request.getEstado());
            }
        }

        Rastreador updatedRastreador = rastreadorRepository.save(rastreador);
        return mapToRastreadorResponseDTO(updatedRastreador);
    }

    @Override
    @Transactional
    public void eliminarRastreador(UUID id) {
        Rastreador rastreador = rastreadorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rastreador", "id", id));
        rastreadorRepository.delete(rastreador);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RastreadorResponseDTO> obtenerRastreadoresPorUsuario(UUID idUsuarioConfigurador) {
        return rastreadorRepository.findByIdUsuarioConfigurador(idUsuarioConfigurador)
                .stream()
                .map(this::mapToRastreadorResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RastreadorResponseDTO> obtenerRastreadoresPorListado(UUID idListado) {
        return rastreadorRepository.findByListadoId(idListado)
                .stream()
                .map(this::mapToRastreadorResponseDTO)
                .collect(Collectors.toList());
    }

    private RastreadorResponseDTO mapToRastreadorResponseDTO(Rastreador rastreador) {
        RastreadorResponseDTO dto = new RastreadorResponseDTO();
        dto.setId(rastreador.getId());
        dto.setIdUsuarioConfigurador(rastreador.getIdUsuarioConfigurador());
        // dto.setNombreUsuarioConfigurador(userService.obtenerUsuarioPorId(rastreador.getIdUsuarioConfigurador()).getNombre()); // Requiere llamada a otro MS
        dto.setIdListado(rastreador.getListado().getId());
        dto.setUrlListado(rastreador.getListado().getUrl());
        dto.setNombrePersonalizado(rastreador.getNombrePersonalizado());
        dto.setPrecioObjetivo(rastreador.getPrecioObjetivo());
        dto.setFrecuenciaRastreo(rastreador.getFrecuenciaRastreo());
        dto.setEstado(rastreador.getEstado().name());
        dto.setUltimoRastreo(rastreador.getUltimoRastreo());
        dto.setProximoRastreo(rastreador.getProximoRastreo());
        dto.setIntentosFallidos(rastreador.getIntentosFallidos());
        dto.setMaxIntentosFallidos(rastreador.getMaxIntentosFallidos());
        dto.setFechaCreacion(rastreador.getFechaCreacion());
        dto.setFechaActualizacion(rastreador.getFechaActualizacion());
        return dto;
    }
}