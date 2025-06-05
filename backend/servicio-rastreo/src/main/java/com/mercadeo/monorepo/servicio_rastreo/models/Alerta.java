package com.mercadeo.monorepo.servicio_rastreo.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.mercadeo.monorepo.servicio_rastreo.enums.TipoAlerta;
import com.mercadeo.monorepo.servicio_rastreo.enums.EstadoAlerta;

@Entity
@Table(name = "alertas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alerta {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    // FK a la tabla Usuario del microservicio de usuarios
    @Column(name = "id_usuario_destinatario", nullable = false)
    private UUID idUsuarioDestinatario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rastreador", nullable = false)
    private Rastreador rastreador;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_alerta", nullable = false)
    private TipoAlerta tipoAlerta;

    @Column(name = "condicion_valor", precision = 10, scale = 2)
    private BigDecimal condicionValor;

    @Column(name = "mensaje", columnDefinition = "TEXT")
    private String mensaje;

    @Column(name = "enviada", nullable = false)
    private Boolean enviada = false;

    @Column(name = "fecha_envio")
    private LocalDateTime fechaEnvio;

    @Column(name = "metodo_envio")
    private String metodoEnvio; // Podría ser un ENUM si los métodos son fijos: EMAIL, SMS, PUSH, WEBHOOK

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoAlerta estado = EstadoAlerta.PENDIENTE;

    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;
}