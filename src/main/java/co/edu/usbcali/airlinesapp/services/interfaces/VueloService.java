package co.edu.usbcali.airlinesapp.services.interfaces;

import co.edu.usbcali.airlinesapp.dtos.VueloDTO;

import java.util.List;

public interface VueloService {
    List<VueloDTO> obtenerVuelos();
    VueloDTO guardarVuelo(VueloDTO vueloDTO) throws Exception;
}
