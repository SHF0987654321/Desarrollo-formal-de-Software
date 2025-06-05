package com.mercadeo.monorepo.servicio_rastreo.dtos.historialprecio;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class HistorialPrecioResponseDTO {
    private UUID id;
    private UUID idListado;
    private String urlListado; // Para contexto
    private BigDecimal precioBase;
    private BigDecimal precioDescuento;
    private BigDecimal porcentajeDescuento;
    private BigDecimal costoEnvioEstimado;
    private BigDecimal impuestosEstimados;
    private BigDecimal precioTotalEstimado;
    private String moneda;
    private Boolean disponible;
    private Integer stockCantidad;
    private LocalDateTime fechaRegistro;
    private String metodoExtraccion;
    private BigDecimal confiabilidadExtraccion;
    private String detallesAdicionalesJson;
    private LocalDateTime fechaCreacion;
}