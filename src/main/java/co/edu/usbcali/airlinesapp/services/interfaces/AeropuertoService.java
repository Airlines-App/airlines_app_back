package co.edu.usbcali.airlinesapp.services.interfaces;

import co.edu.usbcali.airlinesapp.dtos.AeropuertoDTO;

import java.util.List;

public interface AeropuertoService {
    List<AeropuertoDTO> obtenerAeropuertos();
    AeropuertoDTO obtenerAeropuertoPorId(Integer id) throws Exception;
    AeropuertoDTO guardarAeropuerto(AeropuertoDTO aeropuertoDTO) throws Exception;
}
