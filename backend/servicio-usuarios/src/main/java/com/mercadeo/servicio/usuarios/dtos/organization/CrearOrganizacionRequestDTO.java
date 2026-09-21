package com.mercadeo.servicio.usuarios.dtos.organization;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

@Data
public class CrearOrganizacionRequestDTO {
    @NotBlank(message = "El nombre de la organización no puede estar vacío.")
    @Size(max = 200, message = "El nombre no puede exceder los 200 caracteres.")
    private String nombre;

    private Boolean esEmpresaFormal = false;

    private String idFiscal;
    private String codigoPaisIdFiscal;
    private String direccionFacturacion;

    @Email(message = "El formato del correo de contacto no es válido.")
    private String correoContacto;

    private String telefonoContacto;
    private String dominio;
}