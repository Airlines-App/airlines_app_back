package co.edu.usbcali.airlinesapp.services.implementation;

import co.edu.usbcali.airlinesapp.domain.Reserva;
import co.edu.usbcali.airlinesapp.dtos.ReservaDTO;
import co.edu.usbcali.airlinesapp.mappers.AsientoMapper;
import co.edu.usbcali.airlinesapp.mappers.ReservaMapper;
import co.edu.usbcali.airlinesapp.mappers.UsuarioMapper;
import co.edu.usbcali.airlinesapp.mappers.VueloMapper;
import co.edu.usbcali.airlinesapp.repository.ReservaRepository;
import co.edu.usbcali.airlinesapp.services.interfaces.AsientoService;
import co.edu.usbcali.airlinesapp.services.interfaces.ReservaService;

import co.edu.usbcali.airlinesapp.services.interfaces.UsuarioService;
import co.edu.usbcali.airlinesapp.services.interfaces.VueloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ReservaServiceImpl implements ReservaService {
    private final ReservaRepository reservaRepository;
    private final VueloService vueloService;
    private final AsientoService asientoService;
    private final UsuarioService usuarioService;

    public ReservaServiceImpl(ReservaRepository reservaRepository, VueloService vueloService, AsientoService asientoService, UsuarioService usuarioService) {
        this.reservaRepository = reservaRepository;
        this.vueloService = vueloService;
        this.asientoService = asientoService;
        this.usuarioService = usuarioService;
    }

    @Override
    public ReservaDTO guardarReserva(ReservaDTO reservaDTO) throws Exception {
        Reserva reserva = ReservaMapper.dtoToDomain(reservaDTO);

        if (reserva == null) {
            throw new Exception("La reserva no puede ser nula");
        } if (reservaDTO.getIdVuelo() == null || reservaDTO.getIdVuelo() <= 0) {
            throw new Exception("El id del vuelo no puede ser nulo o menor o igual a cero");
        } if (reservaDTO.getIdAsiento() == null || reservaDTO.getIdAsiento() <= 0) {
            throw new Exception("El id del asiento no puede ser nulo o menor o igual a cero");
        } if (reservaDTO.getIdUsuario() == null || reservaDTO.getIdUsuario() <= 0) {
            throw new Exception("El id del usuario no puede ser nulo o menor o igual a cero");
        } if (reserva.getPrecioTotal() < 0) {
            throw new Exception("El precio total de la reserva no puede ser menor a cero");
        } if (reserva.getEstadoPago() == null || reserva.getEstadoPago().isBlank() || reserva.getEstadoPago().trim().isEmpty()) {
            throw new Exception("El estado de pago de la reserva no puede ser nulo o vacío");
        } if (reserva.getFecha() == null) {
            throw new Exception("La fecha de la reserva no puede ser nula");
        } if (reserva.getEstado() == null || reserva.getEstado().isBlank() || reserva.getEstado().trim().isEmpty()) {
            throw new Exception("El estado de la reserva no puede ser nulo o vacío");
        }

        reserva.setVuelo(VueloMapper.dtoToDomain(vueloService.obtenerVueloPorId(reservaDTO.getIdVuelo())));
        reserva.setAsiento(AsientoMapper.dtoToDomain(asientoService.obtenerAsientoPorId(reservaDTO.getIdAsiento())));
        reserva.setUsuario(UsuarioMapper.dtoToDomain(usuarioService.obtenerUsuarioPorId(reservaDTO.getIdUsuario())));

        return ReservaMapper.domainToDTO(reservaRepository.save(reserva));
    }

    @Override
    public List<ReservaDTO> obtenerReservas() {
        return ReservaMapper.domainToDTOList(reservaRepository.findAll());
    }

    @Override
    public ReservaDTO obtenerReservaPorId(Integer id) throws Exception {
        if (!reservaRepository.existsById(id)) {
            throw new Exception("La reserva con id " + id + " no existe");
        }

        return ReservaMapper.domainToDTO(reservaRepository.findById(id).get());
    }

    @Override
    public ReservaDTO actualizarReserva(ReservaDTO reservaDTO) throws Exception {
        ReservaDTO reservaSavedDTO = obtenerReservaPorId(reservaDTO.getIdReserva());

        if (reservaSavedDTO == null) {
            throw new Exception("La reserva no existe");
        }

        reservaSavedDTO.setPrecioTotal(reservaDTO.getPrecioTotal());
        reservaSavedDTO.setEstadoPago(reservaDTO.getEstadoPago());
        reservaSavedDTO.setFecha(reservaDTO.getFecha());
        reservaSavedDTO.setEstado(reservaDTO.getEstado());

        return guardarReserva(reservaSavedDTO);
    }

    @Override
    public ReservaDTO eliminarReserva(Integer id) throws Exception {
        ReservaDTO reservaSavedDTO = obtenerReservaPorId(id);

        if (reservaSavedDTO == null) {
            throw new Exception("La reserva no existe");
        }

        reservaSavedDTO.setEstado("I");

        return guardarReserva(reservaSavedDTO);
    }
}
