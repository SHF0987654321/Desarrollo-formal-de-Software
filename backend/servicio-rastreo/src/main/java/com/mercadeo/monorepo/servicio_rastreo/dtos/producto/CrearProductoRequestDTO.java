package com.mercadeo.monorepo.servicio_rastreo.dtos.producto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CrearProductoRequestDTO {
    @NotNull(message = "El ID de la organización no puede ser nulo.")
    private UUID idOrganizacion;

    @NotBlank(message = "El nombre del producto no puede estar vacío.")
    @Size(max = 255, message = "El nombre del producto no puede exceder los 255 caracteres.")
    private String nombre;

    private String descripcion;
    private String categoria;
    private BigDecimal precioObjetivo;

    // El idUsuarioCreador se obtendría del contexto de seguridad, no del request
    // private UUID idUsuarioCreador;
}