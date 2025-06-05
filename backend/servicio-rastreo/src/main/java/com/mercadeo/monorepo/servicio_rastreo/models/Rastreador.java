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
import java.util.Set;

import com.mercadeo.monorepo.servicio_rastreo.enums.EstadoRastreador;

@Entity
@Table(name = "rastreadores", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_usuario_configurador", "id_listado"}, name = "uq_usuario_listado_rastreador")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rastreador {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    // FK a la tabla Usuario del microservicio de usuarios
    @Column(name = "id_usuario_configurador", nullable = false)
    private UUID idUsuarioConfigurador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_listado", nullable = false)
    private Listado listado;

    @Column(name = "nombre_personalizado")
    private String nombrePersonalizado;

    @Column(name = "precio_objetivo", precision = 10, scale = 2)
    private BigDecimal precioObjetivo;

    @Column(name = "frecuencia_rastreo", nullable = false) // en minutos
    private Integer frecuenciaRastreo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoRastreador estado = EstadoRastreador.ACTIVO;

    @Column(name = "ultimo_rastreo")
    private LocalDateTime ultimoRastreo;

    @Column(name = "proximo_rastreo")
    private LocalDateTime proximoRastreo;

    @Column(name = "intentos_fallidos", nullable = false)
    private Integer intentosFallidos = 0;

    @Column(name = "max_intentos_fallidos", nullable = false)
    private Integer maxIntentosFallidos = 3;

    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;

    // Relaciones
    @OneToMany(mappedBy = "rastreador", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Alerta> alertas;
}