package co.edu.usbcali.airlinesapp.services.interfaces;

import co.edu.usbcali.airlinesapp.dtos.FacturaDTO;

import java.util.List;

public interface FacturaService {
    List<FacturaDTO> obtenerFacturas();
    FacturaDTO obtenerFacturaPorId(Integer id) throws Exception;
    FacturaDTO guardarFactura(FacturaDTO facturaDTO) throws Exception;
}
