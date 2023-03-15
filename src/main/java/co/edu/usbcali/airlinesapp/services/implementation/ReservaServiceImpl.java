package co.edu.usbcali.airlinesapp.services.implementation;

import co.edu.usbcali.airlinesapp.domain.Reserva;
import co.edu.usbcali.airlinesapp.dtos.ReservaDTO;
import co.edu.usbcali.airlinesapp.mappers.ReservaMapper;
import co.edu.usbcali.airlinesapp.repository.ReservaRepository;
import co.edu.usbcali.airlinesapp.services.interfaces.ReservaService;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ReservaServiceImpl implements ReservaService {
    private final ReservaRepository reservaRepository;
    private final ModelMapper modelMapper;

    public ReservaServiceImpl(ReservaRepository reservaRepository, ModelMapper modelMapper) {
        this.reservaRepository = reservaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ReservaDTO> obtenerReservas() {
        return ReservaMapper.domainToDTOList(reservaRepository.findAll());
    }

    @Override
    public ReservaDTO guardarReserva(ReservaDTO reservaDTO) throws Exception {
        Reserva reserva = ReservaMapper.dtoToDomain(reservaDTO);

        if (reserva == null) {
            throw new Exception("La reserva no puede ser nula");
        } if (reserva.getPrecioTotal() < 0) {
            throw new Exception("El precio total de la reserva no puede ser menor a cero");
        } if (reserva.getEstadoPago() == null || reserva.getEstadoPago().isBlank() || reserva.getEstadoPago().trim().isEmpty()) {
            throw new Exception("El estado de pago de la reserva no puede ser nulo o vacío");
        } if (reserva.getFecha() == null) {
            throw new Exception("La fecha de la reserva no puede ser nula");
        } if (reserva.getEstado() == null || reserva.getEstado().isBlank() || reserva.getEstado().trim().isEmpty()) {
            throw new Exception("El estado de la reserva no puede ser nulo o vacío");
        }

        return ReservaMapper.domainToDTO(reservaRepository.save(reserva));
    }
}
