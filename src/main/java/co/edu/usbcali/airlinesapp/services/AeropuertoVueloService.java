package co.edu.usbcali.airlinesapp.services;

import co.edu.usbcali.airlinesapp.dtos.AeropuertoVueloDTO;

import java.util.List;

public interface AeropuertoVueloService {
    List<AeropuertoVueloDTO> obtenerAeropuertoVuelos();
    AeropuertoVueloDTO guardarAeropuertoVuelo(AeropuertoVueloDTO aeropuertoVueloDTO) throws Exception;
}
