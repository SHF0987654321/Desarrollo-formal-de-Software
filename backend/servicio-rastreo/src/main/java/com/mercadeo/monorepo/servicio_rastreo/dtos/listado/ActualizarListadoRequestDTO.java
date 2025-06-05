package com.mercadeo.monorepo.servicio_rastreo.dtos.listado;

import lombok.Data;
import javax.validation.constraints.Pattern;

@Data
public class ActualizarListadoRequestDTO {
    @Pattern(regexp = "^(http|https)://[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}(/\\S*)?$", message = "Formato de URL inválido.")
    private String url;

    @Pattern(regexp = "AMAZON|ALIEXPRESS|MERCADOLIBRE|EBAY|OTRO", message = "Plataforma no válida.")
    private String plataforma;

    private String idListadoPlataforma;
    private String nombreVendedor;
    private Boolean esPrincipal;
    private String estado; // ACTIVO, PAUSADO, ERROR
}