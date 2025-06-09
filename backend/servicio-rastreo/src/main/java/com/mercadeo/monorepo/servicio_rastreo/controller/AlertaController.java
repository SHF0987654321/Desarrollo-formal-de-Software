package com.mercadeo.monorepo.servicio_rastreo.controller;

import com.mercadeo.monorepo.servicio_rastreo.dtos.alerta.ActualizarAlertaRequestDTO;
import com.mercadeo.monorepo.servicio_rastreo.dtos.alerta.CrearAlertaRequestDTO;
import com.mercadeo.monorepo.servicio_rastreo.dtos.alerta.AlertaResponseDTO;
import com.mercadeo.monorepo.servicio_rastreo.service.interfaces.AlertaServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/alertas")
@Validated
public class AlertaController {

    private final AlertaServiceInterface alertaService;

    public AlertaController(AlertaServiceInterface alertaService) {
        this.alertaService = alertaService;
    }

    @PostMapping
    public ResponseEntity<AlertaResponseDTO> crearAlerta(@Valid @RequestBody CrearAlertaRequestDTO request) {
        // En un sistema real, el idUsuarioDestinatario se podría obtener del contexto de seguridad
        AlertaResponseDTO response = alertaService.crearAlerta(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertaResponseDTO> obtenerAlertaPorId(@PathVariable UUID id) {
        // Verificar acceso
        AlertaResponseDTO response = alertaService.obtenerAlertaPorId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlertaResponseDTO> actualizarAlerta(@PathVariable UUID id, @Valid @RequestBody ActualizarAlertaRequestDTO request) {
        // Verificar permisos
        AlertaResponseDTO response = alertaService.actualizarAlerta(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAlerta(@PathVariable UUID id) {
        // Verificar permisos
        alertaService.eliminarAlerta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/usuario/{idUsuarioDestinatario}")
    public ResponseEntity<List<AlertaResponseDTO>> obtenerAlertasPorUsuario(@PathVariable UUID idUsuarioDestinatario) {
        // Verificar que el usuario autenticado es el mismo o tiene permisos de admin
        List<AlertaResponseDTO> response = alertaService.obtenerAlertasPorUsuario(idUsuarioDestinatario);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/rastreador/{idRastreador}")
    public ResponseEntity<List<AlertaResponseDTO>> obtenerAlertasPorRastreador(@PathVariable UUID idRastreador) {
        // Verificar acceso a las alertas de este rastreador
        List<AlertaResponseDTO> response = alertaService.obtenerAlertasPorRastreador(idRastreador);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
