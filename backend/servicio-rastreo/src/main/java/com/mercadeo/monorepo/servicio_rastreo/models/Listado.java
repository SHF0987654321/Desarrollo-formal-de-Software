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

import com.mercadeo.monorepo.servicio_rastreo.enums.EstadoListado;
import com.mercadeo.monorepo.servicio_rastreo.enums.Plataforma;

@Entity
@Table(name = "listados")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Listado {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "id_organizacion", nullable = false)
    private UUID idOrganizacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @Column(name = "url", nullable = false, columnDefinition = "TEXT")
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(name = "plataforma", nullable = false)
    private Plataforma plataforma;

    @Column(name = "id_listado_plataforma")
    private String idListadoPlataforma;

    @Column(name = "nombre_vendedor")
    private String nombreVendedor;

    @Column(name = "es_principal", nullable = false)
    private Boolean esPrincipal = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoListado estado = EstadoListado.ACTIVO;

    @Column(name = "ultimo_precio_revisado", precision = 10, scale = 2)
    private BigDecimal ultimoPrecioRevisado;

    @Column(name = "ultima_revision")
    private LocalDateTime ultimaRevision;

    @Column(name = "precio_historico_minimo", precision = 10, scale = 2)
    private BigDecimal precioHistoricoMinimo;

    @Column(name = "precio_historico_maximo", precision = 10, scale = 2)
    private BigDecimal precioHistoricoMaximo;

    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;

    // Relaciones
    @OneToMany(mappedBy = "listado", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HistorialPrecio> historialPrecios;

    @OneToMany(mappedBy = "listado", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Rastreador> rastreadores;
}