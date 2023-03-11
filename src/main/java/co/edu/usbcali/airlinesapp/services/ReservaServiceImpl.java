package co.edu.usbcali.airlinesapp.services;

import co.edu.usbcali.airlinesapp.dtos.ReservaDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ReservaServiceImpl implements ReservaService {
    @Override
    public List<ReservaDTO> obtenerReservas() {
        return null;
    }

    @Override
    public ReservaDTO guardarReserva(ReservaDTO reservaDTO) throws Exception {
        return null;
    }
}
