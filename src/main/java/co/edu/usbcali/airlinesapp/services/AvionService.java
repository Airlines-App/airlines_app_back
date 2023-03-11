package co.edu.usbcali.airlinesapp.services;

import co.edu.usbcali.airlinesapp.dtos.AvionDTO;

import java.util.List;

public interface AvionService {
    List<AvionDTO> obtenerAviones();
    AvionDTO guardarAvion(AvionDTO avionDTO) throws Exception;
}
