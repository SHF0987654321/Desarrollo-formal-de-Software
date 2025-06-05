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

import com.mercadeo.monorepo.servicio_rastreo.enums.MetodoExtraccion;

@Entity
@Table(name = "historial_precios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistorialPrecio {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_listado", nullable = false)
    private Listado listado;

    @Column(name = "precio_base", precision = 10, scale = 2, nullable = false)
    private BigDecimal precioBase;

    @Column(name = "precio_descuento", precision = 10, scale = 2)
    private BigDecimal precioDescuento;

    @Column(name = "porcentaje_descuento", precision = 5, scale = 2)
    private BigDecimal porcentajeDescuento;

    @Column(name = "costo_envio_estimado", precision = 10, scale = 2)
    private BigDecimal costoEnvioEstimado;

    @Column(name = "impuestos_estimados", precision = 10, scale = 2)
    private BigDecimal impuestosEstimados;

    @Column(name = "precio_total_estimado", precision = 10, scale = 2, nullable = false)
    private BigDecimal precioTotalEstimado;

    @Column(name = "moneda", length = 3, nullable = false)
    private String moneda;

    @Column(name = "disponible", nullable = false)
    private Boolean disponible = true;

    @Column(name = "stock_cantidad")
    private Integer stockCantidad;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_extraccion", nullable = false)
    private MetodoExtraccion metodoExtraccion;

    @Column(name = "confiabilidad_extraccion", precision = 3, scale = 2, nullable = false)
    private BigDecimal confiabilidadExtraccion;

    @Column(name = "detalles_adicionales_json", columnDefinition = "JSON")
    private String detallesAdicionalesJson; // Almacenar como String JSON

    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;
}