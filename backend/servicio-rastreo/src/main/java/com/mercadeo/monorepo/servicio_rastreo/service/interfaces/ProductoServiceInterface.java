package com.mercadeo.monorepo.servicio_rastreo.service.interfaces;

import com.mercadeo.monorepo.servicio_rastreo.dtos.producto.ActualizarProductoRequestDTO;
import com.mercadeo.monorepo.servicio_rastreo.dtos.producto.CrearProductoRequestDTO;
import com.mercadeo.monorepo.servicio_rastreo.dtos.producto.ProductoResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ProductoServiceInterface {
    ProductoResponseDTO crearProducto(CrearProductoRequestDTO request, UUID idUsuarioCreador);
    ProductoResponseDTO obtenerProductoPorId(UUID id);
    ProductoResponseDTO actualizarProducto(UUID id, ActualizarProductoRequestDTO request);
    void eliminarProducto(UUID id);
    List<ProductoResponseDTO> obtenerProductosPorOrganizacion(UUID idOrganizacion);
}