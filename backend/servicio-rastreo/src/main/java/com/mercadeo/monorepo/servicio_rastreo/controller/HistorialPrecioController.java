package com.mercadeo.monorepo.servicio_rastreo.controller;

import com.mercadeo.monorepo.servicio_rastreo.dtos.historialprecio.HistorialPrecioResponseDTO;
import com.mercadeo.monorepo.servicio_rastreo.service.HistorialPrecioService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/historial-precios")
@Validated
public class HistorialPrecioController {

    private final HistorialPrecioService historialPrecioService;

    public HistorialPrecioController(HistorialPrecioService historialPrecioService) {
        this.historialPrecioService = historialPrecioService;
    }

    @GetMapping("/listado/{idListado}")
    public ResponseEntity<List<HistorialPrecioResponseDTO>> obtenerHistorialPorListado(
            @PathVariable UUID idListado,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {

        // Verificar acceso al listado
        List<HistorialPrecioResponseDTO> response;
        if (start != null && end != null) {
            response = historialPrecioService.obtenerHistorialPorListadoYRangoFechas(idListado, start, end);
        } else {
            response = historialPrecioService.obtenerHistorialPorListado(idListado);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Nota: El registro de precios se haría principalmente de forma interna por el microservicio
    // (ej. por un job de rastreo), no directamente a través de un endpoint público.
    // Si se necesitara un endpoint para "registrar manualmente", se añadiría aquí.
}