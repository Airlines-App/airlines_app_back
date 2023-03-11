package co.edu.usbcali.airlinesapp.services;

import co.edu.usbcali.airlinesapp.dtos.ReservaDTO;

import java.util.List;

public interface ReservaService {
    List<ReservaDTO> obtenerReservas();
    ReservaDTO guardarReserva(ReservaDTO reservaDTO) throws Exception;
}
