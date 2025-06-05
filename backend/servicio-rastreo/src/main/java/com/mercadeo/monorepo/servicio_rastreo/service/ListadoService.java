package com.mercadeo.monorepo.servicio_rastreo.service;

import com.mercadeo.monorepo.servicio_rastreo.dtos.listado.ActualizarListadoRequestDTO;
import com.mercadeo.monorepo.servicio_rastreo.dtos.listado.CrearListadoRequestDTO;
import com.mercadeo.monorepo.servicio_rastreo.dtos.listado.ListadoResponseDTO;
import com.mercadeo.monorepo.servicio_rastreo.enums.EstadoListado;
import com.mercadeo.monorepo.servicio_rastreo.enums.Plataforma;
import com.mercadeo.monorepo.servicio_rastreo.exception.BadRequestException;
import com.mercadeo.monorepo.servicio_rastreo.exception.ResourceNotFoundException;
import com.mercadeo.monorepo.servicio_rastreo.models.Listado;
import com.mercadeo.monorepo.servicio_rastreo.models.Producto;
import com.mercadeo.monorepo.servicio_rastreo.repository.ListadoRepository;
import com.mercadeo.monorepo.servicio_rastreo.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ListadoService {

    private final ListadoRepository listadoRepository;
    private final ProductoRepository productoRepository;

    public ListadoService(ListadoRepository listadoRepository, ProductoRepository productoRepository) {
        this.listadoRepository = listadoRepository;
        this.productoRepository = productoRepository;
    }

    @Transactional
    public ListadoResponseDTO crearListado(CrearListadoRequestDTO request) {
        Producto producto = productoRepository.findById(request.getIdProducto())
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", request.getIdProducto()));

        // Validar que la URL no esté ya asociada a este producto
        if (listadoRepository.findByProductoIdAndUrl(request.getIdProducto(), request.getUrl()).isPresent()) {
            throw new BadRequestException("Ya existe un listado con esta URL para el producto especificado.");
        }

        Listado listado = new Listado();
        listado.setIdOrganizacion(request.getIdOrganizacion());
        listado.setProducto(producto);
        listado.setUrl(request.getUrl());
        listado.setPlataforma(Plataforma.valueOf(request.getPlataforma()));
        listado.setIdListadoPlataforma(request.getIdListadoPlataforma());
        listado.setNombreVendedor(request.getNombreVendedor());
        listado.setEsPrincipal(request.getEsPrincipal() != null ? request.getEsPrincipal() : false);
        listado.setEstado(EstadoListado.ACTIVO); // Estado inicial

        Listado nuevoListado = listadoRepository.save(listado);
        return mapToListadoResponseDTO(nuevoListado);
    }

    @Transactional(readOnly = true)
    public ListadoResponseDTO obtenerListadoPorId(UUID id) {
        Listado listado = listadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Listado", "id", id));
        return mapToListadoResponseDTO(listado);
    }

    @Transactional
    public ListadoResponseDTO actualizarListado(UUID id, ActualizarListadoRequestDTO request) {
        Listado listado = listadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Listado", "id", id));

        if (request.getUrl() != null) listado.setUrl(request.getUrl());
        if (request.getPlataforma() != null) {
            try {
                listado.setPlataforma(Plataforma.valueOf(request.getPlataforma()));
            } catch (IllegalArgumentException e) {
                throw new BadRequestException("Plataforma inválida: " + request.getPlataforma());
            }
        }
        if (request.getIdListadoPlataforma() != null) listado.setIdListadoPlataforma(request.getIdListadoPlataforma());
        if (request.getNombreVendedor() != null) listado.setNombreVendedor(request.getNombreVendedor());
        if (request.getEsPrincipal() != null) listado.setEsPrincipal(request.getEsPrincipal());
        if (request.getEstado() != null) {
            try {
                listado.setEstado(EstadoListado.valueOf(request.getEstado()));
            } catch (IllegalArgumentException e) {
                throw new BadRequestException("Estado de listado inválido: " + request.getEstado());
            }
        }

        Listado updatedListado = listadoRepository.save(listado);
        return mapToListadoResponseDTO(updatedListado);
    }

    @Transactional
    public void eliminarListado(UUID id) {
        Listado listado = listadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Listado", "id", id));
        listadoRepository.delete(listado);
    }

    @Transactional(readOnly = true)
    public List<ListadoResponseDTO> obtenerListadosPorProducto(UUID idProducto) {
        return listadoRepository.findByProductoId(idProducto)
                .stream()
                .map(this::mapToListadoResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ListadoResponseDTO> obtenerListadosPorOrganizacion(UUID idOrganizacion) {
        return listadoRepository.findByIdOrganizacion(idOrganizacion)
                .stream()
                .map(this::mapToListadoResponseDTO)
                .collect(Collectors.toList());
    }

    private ListadoResponseDTO mapToListadoResponseDTO(Listado listado) {
        ListadoResponseDTO dto = new ListadoResponseDTO();
        dto.setId(listado.getId());
        dto.setIdOrganizacion(listado.getIdOrganizacion());
        dto.setIdProducto(listado.getProducto().getId());
        dto.setNombreProducto(listado.getProducto().getNombre()); // Nombre del producto para contexto
        dto.setUrl(listado.getUrl());
        dto.setPlataforma(listado.getPlataforma().name());
        dto.setIdListadoPlataforma(listado.getIdListadoPlataforma());
        dto.setNombreVendedor(listado.getNombreVendedor());
        dto.setEsPrincipal(listado.getEsPrincipal());
        dto.setEstado(listado.getEstado().name());
        dto.setUltimoPrecioRevisado(listado.getUltimoPrecioRevisado());
        dto.setUltimaRevision(listado.getUltimaRevision());
        dto.setPrecioHistoricoMinimo(listado.getPrecioHistoricoMinimo());
        dto.setPrecioHistoricoMaximo(listado.getPrecioHistoricoMaximo());
        dto.setFechaCreacion(listado.getFechaCreacion());
        dto.setFechaActualizacion(listado.getFechaActualizacion());
        return dto;
    }
}