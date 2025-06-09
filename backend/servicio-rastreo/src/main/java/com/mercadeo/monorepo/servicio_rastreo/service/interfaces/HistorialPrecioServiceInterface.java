package com.mercadeo.monorepo.servicio_rastreo.service.interfaces;

import com.mercadeo.monorepo.servicio_rastreo.dtos.historialprecio.HistorialPrecioResponseDTO;
import com.mercadeo.monorepo.servicio_rastreo.enums.MetodoExtraccion;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface HistorialPrecioServiceInterface {
    HistorialPrecioResponseDTO registrarPrecio(UUID idListado, BigDecimal precioBase, BigDecimal precioDescuento,
    BigDecimal porcentajeDescuento, BigDecimal costoEnvioEstimado,
    BigDecimal impuestosEstimados, BigDecimal precioTotalEstimado,
    String moneda, Boolean disponible, Integer stockCantidad,
    MetodoExtraccion metodoExtraccion, BigDecimal confiabilidadExtraccion,
    String detallesAdicionalesJson);
    List<HistorialPrecioResponseDTO> obtenerHistorialPorListado(UUID idListado);
    List<HistorialPrecioResponseDTO> obtenerHistorialPorListadoYRangoFechas(UUID idListado, LocalDateTime start, LocalDateTime end);
}