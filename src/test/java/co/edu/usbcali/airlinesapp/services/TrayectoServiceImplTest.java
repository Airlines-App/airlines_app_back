package co.edu.usbcali.airlinesapp.services;

import co.edu.usbcali.airlinesapp.repository.TrayectoRepository;
import co.edu.usbcali.airlinesapp.services.interfaces.TrayectoService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class TrayectoServiceImplTest {
    @Autowired
    private TrayectoService trayectoService;

    @MockBean
    private TrayectoRepository trayectoRepository;

    @Test
    public void obtenerTrayectosOk() {
    }

    @Test
    public void obtenerTrayectosNotOk() {
    }

    @Test
    public void obtenerTrayectosPorIdOk() {
    }

    @Test
    public void obtenerTrayectosPorIdNotOk() {
    }
}
