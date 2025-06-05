package com.mercadeo.monorepo.servicio_rastreo.service;

import com.mercadeo.monorepo.servicio_rastreo.dtos.historialprecio.HistorialPrecioResponseDTO;
import com.mercadeo.monorepo.servicio_rastreo.enums.MetodoExtraccion;
import com.mercadeo.monorepo.servicio_rastreo.exception.BadRequestException;
import com.mercadeo.monorepo.servicio_rastreo.exception.ResourceNotFoundException;
import com.mercadeo.monorepo.servicio_rastreo.models.HistorialPrecio;
import com.mercadeo.monorepo.servicio_rastreo.models.Listado;
import com.mercadeo.monorepo.servicio_rastreo.repository.HistorialPrecioRepository;
import com.mercadeo.monorepo.servicio_rastreo.repository.ListadoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HistorialPrecioService {

    private final HistorialPrecioRepository historialPrecioRepository;
    private final ListadoRepository listadoRepository;

    public HistorialPrecioService(HistorialPrecioRepository historialPrecioRepository, ListadoRepository listadoRepository) {
        this.historialPrecioRepository = historialPrecioRepository;
        this.listadoRepository = listadoRepository;
    }

    // Este método sería llamado internamente por el servicio de rastreadores
    @Transactional
    public HistorialPrecioResponseDTO registrarPrecio(UUID idListado, BigDecimal precioBase, BigDecimal precioDescuento,
                                                      BigDecimal porcentajeDescuento, BigDecimal costoEnvioEstimado,
                                                      BigDecimal impuestosEstimados, BigDecimal precioTotalEstimado,
                                                      String moneda, Boolean disponible, Integer stockCantidad,
                                                      MetodoExtraccion metodoExtraccion, BigDecimal confiabilidadExtraccion,
                                                      String detallesAdicionalesJson) {
        Listado listado = listadoRepository.findById(idListado)
                .orElseThrow(() -> new ResourceNotFoundException("Listado", "id", idListado));

        HistorialPrecio historialPrecio = new HistorialPrecio();
        historialPrecio.setListado(listado);
        historialPrecio.setPrecioBase(precioBase);
        historialPrecio.setPrecioDescuento(precioDescuento);
        historialPrecio.setPorcentajeDescuento(porcentajeDescuento);
        historialPrecio.setCostoEnvioEstimado(costoEnvioEstimado);
        historialPrecio.setImpuestosEstimados(impuestosEstimados);
        historialPrecio.setPrecioTotalEstimado(precioTotalEstimado);
        historialPrecio.setMoneda(moneda);
        historialPrecio.setDisponible(disponible);
        historialPrecio.setStockCantidad(stockCantidad);
        historialPrecio.setFechaRegistro(LocalDateTime.now()); // La fecha de registro es el momento actual
        historialPrecio.setMetodoExtraccion(metodoExtraccion);
        historialPrecio.setConfiabilidadExtraccion(confiabilidadExtraccion);
        historialPrecio.setDetallesAdicionalesJson(detallesAdicionalesJson);

        HistorialPrecio nuevoHistorial = historialPrecioRepository.save(historialPrecio);

        // Opcional: Actualizar ultimo_precio_revisado, precio_historico_minimo, precio_historico_maximo en el Listado
        listado.setUltimoPrecioRevisado(precioTotalEstimado);
        listado.setUltimaRevision(LocalDateTime.now());
        if (listado.getPrecioHistoricoMinimo() == null || precioTotalEstimado.compareTo(listado.getPrecioHistoricoMinimo()) < 0) {
            listado.setPrecioHistoricoMinimo(precioTotalEstimado);
        }
        if (listado.getPrecioHistoricoMaximo() == null || precioTotalEstimado.compareTo(listado.getPrecioHistoricoMaximo()) > 0) {
            listado.setPrecioHistoricoMaximo(precioTotalEstimado);
        }
        listadoRepository.save(listado); // Guardar los cambios en el listado

        return mapToHistorialPrecioResponseDTO(nuevoHistorial);
    }

    @Transactional(readOnly = true)
    public List<HistorialPrecioResponseDTO> obtenerHistorialPorListado(UUID idListado) {
        Listado listado = listadoRepository.findById(idListado)
                .orElseThrow(() -> new ResourceNotFoundException("Listado", "id", idListado));

        return historialPrecioRepository.findByListadoIdOrderByFechaRegistroDesc(idListado)
                .stream()
                .map(this::mapToHistorialPrecioResponseDTO)
                .collect(Collectors.toList());
    }

    // Método para obtener historial en un rango de fechas
    @Transactional(readOnly = true)
    public List<HistorialPrecioResponseDTO> obtenerHistorialPorListadoYRangoFechas(UUID idListado, LocalDateTime start, LocalDateTime end) {
        Listado listado = listadoRepository.findById(idListado)
                .orElseThrow(() -> new ResourceNotFoundException("Listado", "id", idListado));

        return historialPrecioRepository.findByListadoIdAndFechaRegistroBetween(idListado, start, end)
                .stream()
                .map(this::mapToHistorialPrecioResponseDTO)
                .collect(Collectors.toList());
    }

    private HistorialPrecioResponseDTO mapToHistorialPrecioResponseDTO(HistorialPrecio historialPrecio) {
        HistorialPrecioResponseDTO dto = new HistorialPrecioResponseDTO();
        dto.setId(historialPrecio.getId());
        dto.setIdListado(historialPrecio.getListado().getId());
        dto.setUrlListado(historialPrecio.getListado().getUrl());
        dto.setPrecioBase(historialPrecio.getPrecioBase());
        dto.setPrecioDescuento(historialPrecio.getPrecioDescuento());
        dto.setPorcentajeDescuento(historialPrecio.getPorcentajeDescuento());
        dto.setCostoEnvioEstimado(historialPrecio.getCostoEnvioEstimado());
        dto.setImpuestosEstimados(historialPrecio.getImpuestosEstimados());
        dto.setPrecioTotalEstimado(historialPrecio.getPrecioTotalEstimado());
        dto.setMoneda(historialPrecio.getMoneda());
        dto.setDisponible(historialPrecio.getDisponible());
        dto.setStockCantidad(historialPrecio.getStockCantidad());
        dto.setFechaRegistro(historialPrecio.getFechaRegistro());
        dto.setMetodoExtraccion(historialPrecio.getMetodoExtraccion().name());
        dto.setConfiabilidadExtraccion(historialPrecio.getConfiabilidadExtraccion());
        dto.setDetallesAdicionalesJson(historialPrecio.getDetallesAdicionalesJson());
        dto.setFechaCreacion(historialPrecio.getFechaCreacion());
        return dto;
    }
}