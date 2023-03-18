package co.edu.usbcali.airlinesapp.services.interfaces;

import co.edu.usbcali.airlinesapp.dtos.AvionDTO;

import java.util.List;

public interface AvionService {
    List<AvionDTO> obtenerAviones();
    AvionDTO obtenerAvionPorId(Integer id) throws Exception;
    AvionDTO guardarAvion(AvionDTO avionDTO) throws Exception;
}
