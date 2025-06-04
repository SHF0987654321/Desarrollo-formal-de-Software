package com.mercadeo.servicio.usuarios.dtos.auth;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class OrganizacionResponseDTO {
    private UUID id;
    private String nombre;
    private UUID idUsuarioPropietario; // Solo el ID para evitar ciclos
    private Boolean esEmpresaFormal;
    private String idFiscal;
    private String codigoPaisIdFiscal;
    private String direccionFacturacion;
    private String correoContacto;
    private String telefonoContacto;
    private String dominio;
    private UUID idPlanSuscripcion;
    private LocalDateTime fechaInicioPlan;
    private LocalDateTime fechaFinPlan;
    private Boolean suscripcionActiva;
    private String estado; // Representación del ENUM
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}