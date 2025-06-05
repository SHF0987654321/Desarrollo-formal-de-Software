package com.mercadeo.monorepo.servicio_rastreo.dtos.listado;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Data
public class CrearListadoRequestDTO {
    @NotNull(message = "El ID de la organización no puede ser nulo.")
    private UUID idOrganizacion;

    @NotNull(message = "El ID del producto no puede ser nulo.")
    private UUID idProducto;

    @NotBlank(message = "La URL no puede estar vacía.")
    @Pattern(regexp = "^(http|https)://[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}(/\\S*)?$", message = "Formato de URL inválido.")
    private String url;

    @NotBlank(message = "La plataforma no puede estar vacía.")
    @Pattern(regexp = "AMAZON|ALIEXPRESS|MERCADOLIBRE|EBAY|OTRO", message = "Plataforma no válida.")
    private String plataforma;

    private String idListadoPlataforma;
    private String nombreVendedor;
    private Boolean esPrincipal = false;
}