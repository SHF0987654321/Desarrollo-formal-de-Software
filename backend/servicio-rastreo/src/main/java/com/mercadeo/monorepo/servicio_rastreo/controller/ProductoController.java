package com.mercadeo.monorepo.servicio_rastreo.controller;

import com.mercadeo.monorepo.servicio_rastreo.dtos.producto.ActualizarProductoRequestDTO;
import com.mercadeo.monorepo.servicio_rastreo.dtos.producto.CrearProductoRequestDTO;
import com.mercadeo.monorepo.servicio_rastreo.dtos.producto.ProductoResponseDTO;
import com.mercadeo.monorepo.servicio_rastreo.service.interfaces.ProductoServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/productos")
@Validated
public class ProductoController {

    private final ProductoServiceInterface productoService;

    public ProductoController(ProductoServiceInterface productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crearProducto(@Valid @RequestBody CrearProductoRequestDTO request) {
        // En un sistema real, el idUsuarioCreador se obtendría del contexto de seguridad
        UUID idUsuarioCreador = UUID.fromString("b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12"); // Placeholder
        ProductoResponseDTO response = productoService.crearProducto(request, idUsuarioCreador);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> obtenerProductoPorId(@PathVariable UUID id) {
        // En un sistema real, se verificaría que el usuario/organización tiene acceso a este producto
        ProductoResponseDTO response = productoService.obtenerProductoPorId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(@PathVariable UUID id, @Valid @RequestBody ActualizarProductoRequestDTO request) {
        // En un sistema real, se verificarían permisos de edición
        ProductoResponseDTO response = productoService.actualizarProducto(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable UUID id) {
        // En un sistema real, se verificarían permisos de eliminación
        productoService.eliminarProducto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/organizacion/{idOrganizacion}")
    public ResponseEntity<List<ProductoResponseDTO>> obtenerProductosPorOrganizacion(@PathVariable UUID idOrganizacion) {
        // En un sistema real, se verificaría que el usuario autenticado pertenece a esta organización
        List<ProductoResponseDTO> response = productoService.obtenerProductosPorOrganizacion(idOrganizacion);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}