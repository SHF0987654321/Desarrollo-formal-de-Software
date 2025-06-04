package com.mercadeo.servicio.usuarios.dtos.organization;

import lombok.Data;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;

@Data
public class ActualizarOrganizacionRequestDTO {
    @Size(max = 200, message = "El nombre no puede exceder los 200 caracteres.")
    private String nombre;

    private String direccionFacturacion;

    @Email(message = "El formato del correo de contacto no es válido.")
    private String correoContacto;

    private String telefonoContacto;
}