package com.mercadeo.monorepo.servicio_rastreo.controller;

import com.mercadeo.monorepo.servicio_rastreo.dtos.rastreador.ActualizarRastreadorRequestDTO;
import com.mercadeo.monorepo.servicio_rastreo.dtos.rastreador.CrearRastreadorRequestDTO;
import com.mercadeo.monorepo.servicio_rastreo.dtos.rastreador.RastreadorResponseDTO;
import com.mercadeo.monorepo.servicio_rastreo.service.interfaces.RastreadorServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/rastreadores")
@Validated
public class RastreadorController {

    private final RastreadorServiceInterface rastreadorService;

    public RastreadorController(RastreadorServiceInterface rastreadorService) {
        this.rastreadorService = rastreadorService;
    }

    @PostMapping
    public ResponseEntity<RastreadorResponseDTO> crearRastreador(@Valid @RequestBody CrearRastreadorRequestDTO request) {
        // En un sistema real, el idUsuarioConfigurador se obtendría del contexto de seguridad
        UUID idUsuarioConfigurador = UUID.fromString("c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13"); // Placeholder
        RastreadorResponseDTO response = rastreadorService.crearRastreador(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RastreadorResponseDTO> obtenerRastreadorPorId(@PathVariable UUID id) {
        // Verificar acceso
        RastreadorResponseDTO response = rastreadorService.obtenerRastreadorPorId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RastreadorResponseDTO> actualizarRastreador(@PathVariable UUID id, @Valid @RequestBody ActualizarRastreadorRequestDTO request) {
        // Verificar permisos
        RastreadorResponseDTO response = rastreadorService.actualizarRastreador(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRastreador(@PathVariable UUID id) {
        // Verificar permisos
        rastreadorService.eliminarRastreador(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/usuario/{idUsuarioConfigurador}")
    public ResponseEntity<List<RastreadorResponseDTO>> obtenerRastreadoresPorUsuario(@PathVariable UUID idUsuarioConfigurador) {
        // Verificar que el usuario autenticado es el mismo o tiene permisos de admin
        List<RastreadorResponseDTO> response = rastreadorService.obtenerRastreadoresPorUsuario(idUsuarioConfigurador);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/listado/{idListado}")
    public ResponseEntity<List<RastreadorResponseDTO>> obtenerRastreadoresPorListado(@PathVariable UUID idListado) {
        // Verificar acceso a los rastreadores de este listado
        List<RastreadorResponseDTO> response = rastreadorService.obtenerRastreadoresPorListado(idListado);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}