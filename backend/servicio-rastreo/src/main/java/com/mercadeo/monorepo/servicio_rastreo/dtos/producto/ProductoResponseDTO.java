package com.mercadeo.monorepo.servicio_rastreo.dtos.producto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ProductoResponseDTO {
    private UUID id;
    private UUID idOrganizacion;
    private String nombre;
    private String descripcion;
    private String categoria;
    private BigDecimal precioObjetivo;
    private UUID idUsuarioCreador;
    private String estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}