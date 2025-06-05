package com.mercadeo.monorepo.servicio_rastreo.dtos.listado;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ListadoResponseDTO {
    private UUID id;
    private UUID idOrganizacion;
    private UUID idProducto; // Solo el ID del producto
    private String nombreProducto; // Nombre del producto para contexto
    private String url;
    private String plataforma;
    private String idListadoPlataforma;
    private String nombreVendedor;
    private Boolean esPrincipal;
    private String estado;
    private BigDecimal ultimoPrecioRevisado;
    private LocalDateTime ultimaRevision;
    private BigDecimal precioHistoricoMinimo;
    private BigDecimal precioHistoricoMaximo;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}