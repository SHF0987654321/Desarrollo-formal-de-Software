package com.mercadeo.monorepo.servicio_rastreo.dtos.producto;

import lombok.Data;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class ActualizarProductoRequestDTO {
    @Size(max = 255, message = "El nombre del producto no puede exceder los 255 caracteres.")
    private String nombre;

    private String descripcion;
    private String categoria;
    private BigDecimal precioObjetivo;
    private String estado; // Para actualizar el estado del producto (ACTIVO, DESCONTINUADO, AGOTADO)
}