package co.edu.usbcali.airlinesapp.services.interfaces;

import co.edu.usbcali.airlinesapp.dtos.ReservaDTO;

import java.util.List;

public interface ReservaService {
    List<ReservaDTO> obtenerReservas();
    ReservaDTO obtenerReservaPorId(Integer id) throws Exception;
    ReservaDTO guardarReserva(ReservaDTO reservaDTO) throws Exception;
}
