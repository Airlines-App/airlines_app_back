package co.edu.usbcali.airlinesapp.controller;

import co.edu.usbcali.airlinesapp.dtos.AeropuertoDTO;
import co.edu.usbcali.airlinesapp.repository.AeropuertoRepository;
import co.edu.usbcali.airlinesapp.service.implementation.AeropuertoServiceImpl;
import co.edu.usbcali.airlinesapp.utility.AeropuertoUtilityTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class AeropuertoControllerTest {
    @Autowired
    private AeropuertoController aeropuertoController;

    @InjectMocks
    private AeropuertoServiceImpl aeropuertoServiceImpl;

    @Mock
    private AeropuertoRepository aeropuertoRepository;

    @Test
    void guardarAeropuertoOk() throws Exception {
        given(aeropuertoServiceImpl.guardarAeropuerto(any())).willReturn(AeropuertoUtilityTest.AEROPUERTODTO);

        ResponseEntity<AeropuertoDTO> response = aeropuertoController.guardarAeropuerto(AeropuertoUtilityTest.AEROPUERTODTO);

        verify(aeropuertoServiceImpl).guardarAeropuerto(eq(AeropuertoUtilityTest.AEROPUERTODTO));

        assertEquals(response.getStatusCode().value(), HttpStatus.CREATED.value());
    }
}
