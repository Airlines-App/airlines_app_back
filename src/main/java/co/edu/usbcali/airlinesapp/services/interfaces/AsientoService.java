package co.edu.usbcali.airlinesapp.services.interfaces;

import co.edu.usbcali.airlinesapp.dtos.AeropuertoDTO;
import co.edu.usbcali.airlinesapp.dtos.AsientoDTO;

import java.util.List;

public interface AsientoService {
    AsientoDTO guardarAsiento(AsientoDTO asientoDTO) throws Exception;
    List<AsientoDTO> obtenerAsientos();
    AsientoDTO obtenerAsientoPorId(Integer id) throws Exception;
    AsientoDTO actualizarAsiento(AsientoDTO asientoDTO) throws Exception;
    AsientoDTO eliminarAsiento(Integer id) throws Exception;
}
