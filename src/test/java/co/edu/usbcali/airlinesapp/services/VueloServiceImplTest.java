package co.edu.usbcali.airlinesapp.services;

import co.edu.usbcali.airlinesapp.repository.VueloRepository;
import co.edu.usbcali.airlinesapp.services.interfaces.VueloService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class VueloServiceImplTest {
    @Autowired
    private VueloService vueloService;

    @MockBean
    private VueloRepository vueloRepository;

    @Test
    public void obtenerVuelosOk() {
    }

    @Test
    public void obtenerVuelosNotOk() {
    }

    @Test
    public void obtenerVueloPorIdOk() {
    }

    @Test
    public void obtenerVueloPorIdNotOk() {
    }
}
