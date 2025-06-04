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

import com.oho.userservice.enums.EstadoInvitacion;
import com.oho.userservice.enums.RolUsuarioOrganizacion;

@Entity
@Table(name = "invitaciones_organizacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvitacionOrganizacion {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_organizacion", nullable = false)
    private Organizacion organizacion;

    @Column(name = "correo_invitado", nullable = false)
    private String correoInvitado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_invitador", nullable = false)
    private Usuario usuarioInvitador;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private RolUsuarioOrganizacion rol;

    @Column(name = "token", unique = true, nullable = false)
    private UUID token;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoInvitacion estado = EstadoInvitacion.PENDIENTE;

    @Column(name = "expira_en", nullable = false)
    private LocalDateTime expiraEn;

    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;
}