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

import com.mercadeo.monorepo.servicio_rastreo.enums.EstadoProducto;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    // FK a la tabla Organizacion del microservicio de usuarios
    // Se recomienda solo almacenar el ID y no la entidad completa si son de microservicios diferentes
    @Column(name = "id_organizacion", nullable = false)
    private UUID idOrganizacion;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "precio_objetivo", precision = 10, scale = 2)
    private BigDecimal precioObjetivo;

    // FK a la tabla Usuario del microservicio de usuarios (opcional para auditoría)
    @Column(name = "id_usuario_creador")
    private UUID idUsuarioCreador;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoProducto estado = EstadoProducto.ACTIVO;

    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;

    // Relaciones
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Listado> listados;
}