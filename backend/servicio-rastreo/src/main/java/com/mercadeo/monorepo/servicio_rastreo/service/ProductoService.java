package com.mercadeo.monorepo.servicio_rastreo.service;

import com.mercadeo.monorepo.servicio_rastreo.dtos.producto.ActualizarProductoRequestDTO;
import com.mercadeo.monorepo.servicio_rastreo.dtos.producto.CrearProductoRequestDTO;
import com.mercadeo.monorepo.servicio_rastreo.dtos.producto.ProductoResponseDTO;
import com.mercadeo.monorepo.servicio_rastreo.enums.EstadoProducto;
import com.mercadeo.monorepo.servicio_rastreo.exception.BadRequestException;
import com.mercadeo.monorepo.servicio_rastreo.exception.ResourceNotFoundException;
import com.mercadeo.monorepo.servicio_rastreo.models.Producto;
import com.mercadeo.monorepo.servicio_rastreo.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    // Asumimos que hay un servicio para validar la existencia de la organización
    // private final OrganizationService organizationService;

    public ProductoService(ProductoRepository productoRepository /*, OrganizationService organizationService*/) {
        this.productoRepository = productoRepository;
        // this.organizationService = organizationService;
    }

    @Transactional
    public ProductoResponseDTO crearProducto(CrearProductoRequestDTO request, UUID idUsuarioCreador) {
        // Validar que la organización exista (llamada a microservicio de usuarios)
        // organizationService.obtenerOrganizacionPorId(request.getIdOrganizacion());

        Producto producto = new Producto();
        producto.setIdOrganizacion(request.getIdOrganizacion());
        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setCategoria(request.getCategoria());
        producto.setPrecioObjetivo(request.getPrecioObjetivo());
        producto.setIdUsuarioCreador(idUsuarioCreador); // Usuario del contexto de seguridad
        producto.setEstado(EstadoProducto.ACTIVO); // Estado inicial

        Producto nuevoProducto = productoRepository.save(producto);
        return mapToProductoResponseDTO(nuevoProducto);
    }

    @Transactional(readOnly = true)
    public ProductoResponseDTO obtenerProductoPorId(UUID id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", id));
        return mapToProductoResponseDTO(producto);
    }

    @Transactional
    public ProductoResponseDTO actualizarProducto(UUID id, ActualizarProductoRequestDTO request) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", id));

        if (request.getNombre() != null) producto.setNombre(request.getNombre());
        if (request.getDescripcion() != null) producto.setDescripcion(request.getDescripcion());
        if (request.getCategoria() != null) producto.setCategoria(request.getCategoria());
        if (request.getPrecioObjetivo() != null) producto.setPrecioObjetivo(request.getPrecioObjetivo());
        if (request.getEstado() != null) {
            try {
                producto.setEstado(EstadoProducto.valueOf(request.getEstado()));
            } catch (IllegalArgumentException e) {
                throw new BadRequestException("Estado de producto inválido: " + request.getEstado());
            }
        }

        Producto updatedProducto = productoRepository.save(producto);
        return mapToProductoResponseDTO(updatedProducto);
    }

    @Transactional
    public void eliminarProducto(UUID id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", id));
        productoRepository.delete(producto);
    }

    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> obtenerProductosPorOrganizacion(UUID idOrganizacion) {
        return productoRepository.findByIdOrganizacion(idOrganizacion)
                .stream()
                .map(this::mapToProductoResponseDTO)
                .collect(Collectors.toList());
    }

    private ProductoResponseDTO mapToProductoResponseDTO(Producto producto) {
        ProductoResponseDTO dto = new ProductoResponseDTO();
        dto.setId(producto.getId());
        dto.setIdOrganizacion(producto.getIdOrganizacion());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setCategoria(producto.getCategoria());
        dto.setPrecioObjetivo(producto.getPrecioObjetivo());
        dto.setIdUsuarioCreador(producto.getIdUsuarioCreador());
        dto.setEstado(producto.getEstado().name());
        dto.setFechaCreacion(producto.getFechaCreacion());
        dto.setFechaActualizacion(producto.getFechaActualizacion());
        return dto;
    }
}