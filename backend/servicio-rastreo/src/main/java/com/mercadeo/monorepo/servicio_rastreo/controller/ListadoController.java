package com.mercadeo.monorepo.servicio_rastreo.controller;

import com.mercadeo.monorepo.servicio_rastreo.dtos.listado.ActualizarListadoRequestDTO;
import com.mercadeo.monorepo.servicio_rastreo.dtos.listado.CrearListadoRequestDTO;
import com.mercadeo.monorepo.servicio_rastreo.dtos.listado.ListadoResponseDTO;
import com.mercadeo.monorepo.servicio_rastreo.service.ListadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/listados")
@Validated
public class ListadoController {

    private final ListadoService listadoService;

    public ListadoController(ListadoService listadoService) {
        this.listadoService = listadoService;
    }

    @PostMapping
    public ResponseEntity<ListadoResponseDTO> crearListado(@Valid @RequestBody CrearListadoRequestDTO request) {
        // En un sistema real, se verificaría que el idOrganizacion es válido y que el usuario tiene permisos
        ListadoResponseDTO response = listadoService.crearListado(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListadoResponseDTO> obtenerListadoPorId(@PathVariable UUID id) {
        // Verificar acceso
        ListadoResponseDTO response = listadoService.obtenerListadoPorId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListadoResponseDTO> actualizarListado(@PathVariable UUID id, @Valid @RequestBody ActualizarListadoRequestDTO request) {
        // Verificar permisos
        ListadoResponseDTO response = listadoService.actualizarListado(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarListado(@PathVariable UUID id) {
        // Verificar permisos
        listadoService.eliminarListado(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<List<ListadoResponseDTO>> obtenerListadosPorProducto(@PathVariable UUID idProducto) {
        // Verificar acceso a los listados de este producto
        List<ListadoResponseDTO> response = listadoService.obtenerListadosPorProducto(idProducto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/organizacion/{idOrganizacion}")
    public ResponseEntity<List<ListadoResponseDTO>> obtenerListadosPorOrganizacion(@PathVariable UUID idOrganizacion) {
        // Verificar que el usuario autenticado pertenece a esta organización
        List<ListadoResponseDTO> response = listadoService.obtenerListadosPorOrganizacion(idOrganizacion);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}