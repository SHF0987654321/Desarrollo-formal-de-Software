package com.mercadeo.monorepo.servicio_rastreo.dtos.rastreador;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class RastreadorResponseDTO {
    private UUID id;
    private UUID idUsuarioConfigurador;
    private String nombreUsuarioConfigurador; // Para contexto
    private UUID idListado;
    private String urlListado; // Para contexto
    private String nombrePersonalizado;
    private BigDecimal precioObjetivo;
    private Integer frecuenciaRastreo;
    private String estado;
    private LocalDateTime ultimoRastreo;
    private LocalDateTime proximoRastreo;
    private Integer intentosFallidos;
    private Integer maxIntentosFallidos;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}