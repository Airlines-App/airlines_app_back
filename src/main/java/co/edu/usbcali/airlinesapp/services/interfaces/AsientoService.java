package co.edu.usbcali.airlinesapp.services.interfaces;

import co.edu.usbcali.airlinesapp.dtos.AsientoDTO;

import java.util.List;

public interface AsientoService {
    List<AsientoDTO> obtenerAsientos();
    AsientoDTO obtenerAsientoPorId(Integer id) throws Exception;
    AsientoDTO guardarAsiento(AsientoDTO asientoDTO) throws Exception;
}
