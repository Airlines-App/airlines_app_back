package co.edu.usbcali.airlinesapp.services;

import co.edu.usbcali.airlinesapp.repository.ReservaRepository;
import co.edu.usbcali.airlinesapp.services.interfaces.ReservaService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ReservaServiceImplTest {
    @Autowired
    private ReservaService reservaService;

    @MockBean
    private ReservaRepository reservaRepository;

    @Test
    public void obtenerReservasOk() {
    }

    @Test
    public void obtenerReservasNotOk() {
    }

    @Test
    public void obtenerReservaPorIdOk() {
    }

    @Test
    public void obtenerReservaPorIdNotOk() {
    }
}
