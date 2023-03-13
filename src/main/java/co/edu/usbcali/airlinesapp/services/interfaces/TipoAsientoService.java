package co.edu.usbcali.airlinesapp.services.interfaces;

import co.edu.usbcali.airlinesapp.dtos.TipoAsientoDTO;

import java.util.List;

public interface TipoAsientoService {
    List<TipoAsientoDTO> obtenerTipoAsientos();
    TipoAsientoDTO guardarTipoAsiento(TipoAsientoDTO tipoAsientoDTO) throws Exception;
}
