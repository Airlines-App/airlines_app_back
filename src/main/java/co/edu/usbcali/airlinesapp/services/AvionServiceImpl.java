package co.edu.usbcali.airlinesapp.services;

import co.edu.usbcali.airlinesapp.dtos.AvionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AvionServiceImpl implements AvionService {
    @Override
    public List<AvionDTO> obtenerAviones() {
        return null;
    }

    @Override
    public AvionDTO guardarAvion(AvionDTO avionDTO) throws Exception {
        return null;
    }
}
