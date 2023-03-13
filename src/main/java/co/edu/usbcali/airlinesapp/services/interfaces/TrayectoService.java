package co.edu.usbcali.airlinesapp.services.interfaces;

import co.edu.usbcali.airlinesapp.dtos.TrayectoDTO;

import java.util.List;

public interface TrayectoService {
    List<TrayectoDTO> obtenerTrayectos();
    TrayectoDTO guardarTrayecto(TrayectoDTO trayectoDTO) throws Exception;
}
