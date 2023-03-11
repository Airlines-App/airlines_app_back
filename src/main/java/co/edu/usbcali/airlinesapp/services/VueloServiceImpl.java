package co.edu.usbcali.airlinesapp.services;

import co.edu.usbcali.airlinesapp.dtos.VueloDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class VueloServiceImpl implements VueloService {
    @Override
    public List<VueloDTO> obtenerVuelos() {
        return null;
    }

    @Override
    public VueloDTO guardarVuelo(VueloDTO vueloDTO) throws Exception {
        return null;
    }
}
