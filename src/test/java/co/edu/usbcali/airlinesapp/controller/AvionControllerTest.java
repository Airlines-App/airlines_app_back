package co.edu.usbcali.airlinesapp.controller;

import co.edu.usbcali.airlinesapp.dtos.AvionDTO;
import co.edu.usbcali.airlinesapp.service.interfaces.AvionService;
import co.edu.usbcali.airlinesapp.utility.AvionUtilityTest;
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
public class AvionControllerTest {
    @Autowired
    private AvionController avionController;

    @MockBean
    private AvionService avionService;

    @Test
    public void guardarAvionOk() throws Exception {
        when(avionService.guardarAvion(any())).thenReturn(AvionUtilityTest.AVIONDTO_UNO);

        ResponseEntity<AvionDTO> response = avionController.guardarAvion(AvionUtilityTest.AVIONDTO_UNO);

        verify(avionService).guardarAvion(eq(AvionUtilityTest.AVIONDTO_UNO));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    public void guardarAvionNotOk() {
        try {
            avionController.guardarAvion(AvionUtilityTest.AVIONDTO_MODELO_NULL);
        } catch (Exception e) {
            assertEquals(AvionUtilityTest.MODELO_REQUIRED_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void obtenerAvionesOk() {
        when(avionService.obtenerAviones()).thenReturn(AvionUtilityTest.AVIONESDTO);

        ResponseEntity<List<AvionDTO>> response = avionController.obtenerAviones();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(AvionUtilityTest.AVIONES_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerAvionesNotOk() {
        when(avionService.obtenerAviones()).thenReturn(AvionUtilityTest.AVIONESDTO_VACIO);

        ResponseEntity<List<AvionDTO>> response = avionController.obtenerAviones();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(AvionUtilityTest.AVIONES_VACIO_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerAvionesActivosOk() {
        when(avionService.obtenerAvionesActivos()).thenReturn(AvionUtilityTest.AVIONESDTO);

        ResponseEntity<List<AvionDTO>> response = avionController.obtenerAvionesActivos();

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(AvionUtilityTest.AVIONES_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerAvionesActivosNotOk() {
        when(avionService.obtenerAvionesActivos()).thenReturn(AvionUtilityTest.AVIONESDTO_VACIO);

        ResponseEntity<List<AvionDTO>> response = avionController.obtenerAvionesActivos();

        assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(AvionUtilityTest.AVIONES_VACIO_SIZE, response.getBody().size());
    }

    @Test
    public void obtenerAvionOk() throws Exception {
        when(avionService.obtenerAvionPorId(any())).thenReturn(AvionUtilityTest.AVIONDTO_UNO);

        ResponseEntity<AvionDTO> response = avionController.obtenerAvion(AvionUtilityTest.ID_UNO);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    public void obtenerAvionNotOk() {
        try {
            avionController.obtenerAvion(AvionUtilityTest.ID_DOS);
        } catch (Exception e) {
            assertEquals(String.format(AvionUtilityTest.ID_NOT_FOUND_MESSAGE, AvionUtilityTest.ID_DOS), e.getMessage());
        }
    }

    @Test
    public void actualizarAvionOk() throws Exception {
        when(avionService.actualizarAvion(any())).thenReturn(AvionUtilityTest.AVIONDTO_UNO);

        ResponseEntity<AvionDTO> response = avionController.actualizarAvion(AvionUtilityTest.AVIONDTO_UNO);

        verify(avionService).actualizarAvion(eq(AvionUtilityTest.AVIONDTO_UNO));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    public void actualizarAvionNotOk() {
        try {
            avionController.actualizarAvion(AvionUtilityTest.AVIONDTO_MODELO_NULL);
        } catch (Exception e) {
            assertEquals(AvionUtilityTest.MODELO_REQUIRED_MESSAGE, e.getMessage());
        }
    }

    @Test
    void eliminarAvionOk() throws Exception {
        when(avionService.eliminarAvion(any())).thenReturn(AvionUtilityTest.AVIONDTO_UNO);

        ResponseEntity<AvionDTO> response = avionController.eliminarAvion(AvionUtilityTest.ID_UNO);

        verify(avionService).eliminarAvion(eq(AvionUtilityTest.ID_UNO));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    void eliminarAvionNotOk() throws Exception {
        try {
            avionController.eliminarAvion(AvionUtilityTest.ID_DOS);
        } catch (Exception e) {
            assertEquals(AvionUtilityTest.ID_NOT_FOUND_MESSAGE, e.getMessage());
        }
    }
}
