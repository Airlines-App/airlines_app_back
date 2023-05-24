package co.edu.usbcali.airlinesapp.controller;

import co.edu.usbcali.airlinesapp.dtos.ReservaDTO;
import co.edu.usbcali.airlinesapp.service.interfaces.ReservaService;
import co.edu.usbcali.airlinesapp.utility.ReservaUtilityTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ReservaControllerTest {
    @Autowired
    private ReservaController reservaController;

    @MockBean
    private ReservaService reservaService;

    @Test
    public void guardarReservaOk() throws Exception {
        when(reservaService.guardarReserva(any())).thenReturn(ReservaUtilityTest.RESERVADTO_UNO);

        ResponseEntity<ReservaDTO> response = reservaController.guardarReserva(ReservaUtilityTest.RESERVADTO_UNO);

        verify(reservaService).guardarReserva(eq(ReservaUtilityTest.RESERVADTO_UNO));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    public void guardarReservaNotOk() {
        try {
            reservaController.guardarReserva(ReservaUtilityTest.RESERVADTO_FECHA_NULL);
        } catch (Exception e) {
            assertEquals(ReservaUtilityTest.FECHA_REQUIRED_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void obtenerReservasOk() {
        when(reservaService.obtenerReservas()).thenReturn(ReservaUtilityTest.RESERVASDTO);

        ResponseEntity<List<ReservaDTO>> response = reservaController.obtenerReservas();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(ReservaUtilityTest.RESERVAS_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerReservasNotOk() {
        when(reservaService.obtenerReservas()).thenReturn(ReservaUtilityTest.RESERVASDTO_VACIO);

        ResponseEntity<List<ReservaDTO>> response = reservaController.obtenerReservas();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(ReservaUtilityTest.RESERVAS_VACIO_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerReservasActivasOk() {
        when(reservaService.obtenerReservasActivas()).thenReturn(ReservaUtilityTest.RESERVASDTO);

        ResponseEntity<List<ReservaDTO>> response = reservaController.obtenerReservasActivas();

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(ReservaUtilityTest.RESERVAS_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerReservasActivasNotOk() {
        when(reservaService.obtenerReservasActivas()).thenReturn(ReservaUtilityTest.RESERVASDTO_VACIO);

        ResponseEntity<List<ReservaDTO>> response = reservaController.obtenerReservasActivas();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(ReservaUtilityTest.RESERVAS_VACIO_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerReservaOk() throws Exception {
        when(reservaService.obtenerReservaPorId(any())).thenReturn(ReservaUtilityTest.RESERVADTO_UNO);

        ResponseEntity<ReservaDTO> response = reservaController.obtenerReserva(ReservaUtilityTest.ID_UNO);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    public void obtenerReservaNotOk() {
        try {
            reservaController.obtenerReserva(ReservaUtilityTest.ID_DOS);
        } catch (Exception e) {
            assertEquals(String.format(ReservaUtilityTest.ID_NOT_FOUND_MESSAGE, ReservaUtilityTest.ID_DOS), e.getMessage());
        }
    }

    @Test
    public void actualizarReservaOk() throws Exception {
        when(reservaService.actualizarReserva(any())).thenReturn(ReservaUtilityTest.RESERVADTO_UNO);

        ResponseEntity<ReservaDTO> response = reservaController.actualizarReserva(ReservaUtilityTest.RESERVADTO_UNO);

        verify(reservaService).actualizarReserva(eq(ReservaUtilityTest.RESERVADTO_UNO));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    public void actualizarReservaNotOk() {
        try {
            reservaController.actualizarReserva(ReservaUtilityTest.RESERVADTO_FECHA_NULL);
        } catch (Exception e) {
            assertEquals(ReservaUtilityTest.FECHA_REQUIRED_MESSAGE, e.getMessage());
        }
    }

    @Test
    void eliminarReservaOk() throws Exception {
        when(reservaService.eliminarReserva(any())).thenReturn(ReservaUtilityTest.RESERVADTO_UNO);

        ResponseEntity<ReservaDTO> response = reservaController.eliminarReserva(ReservaUtilityTest.ID_UNO);

        verify(reservaService).eliminarReserva(eq(ReservaUtilityTest.ID_UNO));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    void eliminarReservaNotOk() throws Exception {
        try {
            reservaController.eliminarReserva(ReservaUtilityTest.ID_DOS);
        } catch (Exception e) {
            assertEquals(ReservaUtilityTest.ID_NOT_FOUND_MESSAGE, e.getMessage());
        }
    }
}
