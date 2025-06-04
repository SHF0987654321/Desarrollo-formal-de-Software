package com.mercadeo.servicio.usuarios.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.Set;

import com.oho.userservice.enums.EstadoOrganizacion;

@Entity
@Table(name = "organizaciones", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_fiscal", "codigo_pais_id_fiscal"}, name = "uq_id_fiscal_pais", condition = "es_empresa_formal = TRUE")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Organizacion {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_propietario", nullable = false)
    private Usuario usuarioPropietario;

    @Column(name = "es_empresa_formal", nullable = false)
    private Boolean esEmpresaFormal = false;

    @Column(name = "id_fiscal")
    private String idFiscal;

    @Column(name = "codigo_pais_id_fiscal")
    private String codigoPaisIdFiscal;

    @Column(name = "direccion_facturacion", columnDefinition = "TEXT")
    private String direccionFacturacion;

    @Column(name = "correo_contacto")
    private String correoContacto;

    @Column(name = "telefono_contacto")
    private String telefonoContacto;

    @Column(name = "dominio")
    private String dominio;

    @Column(name = "id_plan_suscripcion") // FK a tabla de otro microservicio, solo se almacena el ID
    private UUID idPlanSuscripcion;

    @Column(name = "fecha_inicio_plan")
    private LocalDateTime fechaInicioPlan;

    @Column(name = "fecha_fin_plan")
    private LocalDateTime fechaFinPlan;

    @Column(name = "suscripcion_activa", nullable = false)
    private Boolean suscripcionActiva = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoOrganizacion estado = EstadoOrganizacion.ACTIVA;

    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;

    // Relaciones
    @OneToMany(mappedBy = "organizacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RolUsuarioOrganizacion> rolesOrganizacion;

    @OneToMany(mappedBy = "organizacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<InvitacionOrganizacion> invitaciones;
}