package co.edu.usbcali.airlinesapp.services;

import co.edu.usbcali.airlinesapp.dtos.ReservaDTO;
import co.edu.usbcali.airlinesapp.repository.AsientoRepository;
import co.edu.usbcali.airlinesapp.repository.ReservaRepository;
import co.edu.usbcali.airlinesapp.repository.UsuarioRepository;
import co.edu.usbcali.airlinesapp.repository.VueloRepository;
import co.edu.usbcali.airlinesapp.services.implementation.ReservaServiceImpl;

import co.edu.usbcali.airlinesapp.utility.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class ReservaServiceImplTest {
    @InjectMocks
    private ReservaServiceImpl reservaServiceImpl;

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private VueloRepository vueloRepository;

    @Mock
    private AsientoRepository asientoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    public void guardarReservaOk() throws Exception {
        given(vueloRepository.existsById(VueloUtilityTest.VUELO_UNO.getIdVuelo())).willReturn(true);
        given(vueloRepository.getReferenceById(VueloUtilityTest.VUELO_UNO.getIdVuelo())).willReturn(VueloUtilityTest.VUELO_UNO);
        given(asientoRepository.existsById(AsientoUtilityTest.ASIENTO_UNO.getIdAsiento())).willReturn(true);
        given(asientoRepository.getReferenceById(AsientoUtilityTest.ASIENTO_UNO.getIdAsiento())).willReturn(AsientoUtilityTest.ASIENTO_UNO);
        given(usuarioRepository.existsById(UsuarioUtilityTest.USUARIO_UNO.getIdUsuario())).willReturn(true);
        given(usuarioRepository.getReferenceById(UsuarioUtilityTest.USUARIO_UNO.getIdUsuario())).willReturn(UsuarioUtilityTest.USUARIO_UNO);
        given(reservaRepository.existsById(ReservaUtilityTest.RESERVA_UNO.getIdReserva())).willReturn(false);
        given(reservaRepository.save(ReservaUtilityTest.RESERVA_UNO)).willReturn(ReservaUtilityTest.RESERVA_UNO);

        ReservaDTO reservaSavedDTO = reservaServiceImpl.guardarReserva(ReservaUtilityTest.RESERVADTO);

        assertEquals(ReservaUtilityTest.RESERVA_UNO.getIdReserva(), reservaSavedDTO.getIdReserva());
    }

    @Test
    public void guardarReservaNotOk() {
        given(reservaRepository.existsById(ReservaUtilityTest.RESERVA_UNO.getIdReserva())).willReturn(true);

        assertThrows(java.lang.Exception.class, () -> reservaServiceImpl.guardarReserva(ReservaUtilityTest.RESERVADTO));
    }

    @Test
    public void obtenerReservasOk() {
        given(reservaRepository.findAll()).willReturn(ReservaUtilityTest.RESERVAS);

        List<ReservaDTO> reservasSavedDTO = reservaServiceImpl.obtenerReservas();

        assertEquals(2, reservasSavedDTO.size());
        assertEquals(100000, reservasSavedDTO.get(0).getPrecioTotal());
    }

    @Test
    public void obtenerReservasNotOk() {
        given(reservaRepository.findAll()).willReturn(ReservaUtilityTest.RESERVAS_VACIO);

        List<ReservaDTO> reservasSavedDTO = reservaServiceImpl.obtenerReservas();

        assertEquals(0, reservasSavedDTO.size());
    }

    @Test
    public void obtenerReservasActivasOk() {
        given(reservaRepository.findAllByEstado("A")).willReturn(ReservaUtilityTest.RESERVAS);

        List<ReservaDTO> reservasSavedDTO = reservaServiceImpl.obtenerReservasActivas();

        assertEquals(2, reservasSavedDTO.size());
        assertEquals(100000, reservasSavedDTO.get(0).getPrecioTotal());
    }

    @Test
    public void obtenerReservasActivasNotOk() {
        given(reservaRepository.findAllByEstado("A")).willReturn(ReservaUtilityTest.RESERVAS_VACIO);

        List<ReservaDTO> reservasSavedDTO = reservaServiceImpl.obtenerReservasActivas();

        assertEquals(0, reservasSavedDTO.size());
    }

    @Test
    public void obtenerReservaPorIdOk() throws Exception {
        vueloRepository.save(VueloUtilityTest.VUELO_UNO);
        asientoRepository.save(AsientoUtilityTest.ASIENTO_UNO);
        usuarioRepository.save(UsuarioUtilityTest.USUARIO_UNO);
        reservaRepository.save(ReservaUtilityTest.RESERVA_UNO);

        given(reservaRepository.existsById(ReservaUtilityTest.RESERVA_UNO.getIdReserva())).willReturn(true);
        given(reservaRepository.getReferenceById(ReservaUtilityTest.RESERVA_UNO.getIdReserva())).willReturn(ReservaUtilityTest.RESERVA_UNO);

        ReservaDTO reservaSavedDTO = reservaServiceImpl.obtenerReservaPorId(ReservaUtilityTest.RESERVA_UNO.getIdReserva());

        assertEquals(ReservaUtilityTest.RESERVA_UNO.getIdReserva(), reservaSavedDTO.getIdUsuario());
    }

    @Test
    public void obtenerReservaPorIdNotOk() {
        given(reservaRepository.existsById(ReservaUtilityTest.RESERVA_UNO.getIdReserva())).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> reservaServiceImpl.obtenerReservaPorId(ReservaUtilityTest.RESERVA_UNO.getIdReserva()));
    }

    @Test
    public void actualizarReservaOk() throws Exception {
        given(vueloRepository.existsById(VueloUtilityTest.VUELO_UNO.getIdVuelo())).willReturn(true);
        given(vueloRepository.getReferenceById(VueloUtilityTest.VUELO_UNO.getIdVuelo())).willReturn(VueloUtilityTest.VUELO_UNO);
        given(asientoRepository.existsById(AsientoUtilityTest.ASIENTO_UNO.getIdAsiento())).willReturn(true);
        given(asientoRepository.getReferenceById(AsientoUtilityTest.ASIENTO_UNO.getIdAsiento())).willReturn(AsientoUtilityTest.ASIENTO_UNO);
        given(usuarioRepository.existsById(UsuarioUtilityTest.USUARIO_UNO.getIdUsuario())).willReturn(true);
        given(usuarioRepository.getReferenceById(UsuarioUtilityTest.USUARIO_UNO.getIdUsuario())).willReturn(UsuarioUtilityTest.USUARIO_UNO);
        given(reservaRepository.existsById(ReservaUtilityTest.RESERVA_UNO.getIdReserva())).willReturn(true);
        given(reservaRepository.save(ReservaUtilityTest.RESERVA_UNO)).willReturn(ReservaUtilityTest.RESERVA_UNO);

        ReservaDTO reservaSavedDTO = reservaServiceImpl.actualizarReserva(ReservaUtilityTest.RESERVADTO);

        assertEquals(ReservaUtilityTest.RESERVA_UNO.getIdReserva(), reservaSavedDTO.getIdReserva());
    }

    @Test
    public void actualizarReservaNotOk() {
        given(reservaRepository.existsById(ReservaUtilityTest.RESERVA_UNO.getIdReserva())).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> reservaServiceImpl.actualizarReserva(ReservaUtilityTest.RESERVADTO));
    }
}
