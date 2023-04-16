package co.edu.usbcali.airlinesapp.services;

import co.edu.usbcali.airlinesapp.repository.FacturaRepository;
import co.edu.usbcali.airlinesapp.services.interfaces.FacturaService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class FacturaServiceImplTest {
    @Autowired
    private FacturaService facturaService;

    @MockBean
    private FacturaRepository facturaRepository;

    @Test
    public void obtenerFacturasOk() {
    }

    @Test
    public void obtenerFacturasNotOk() {
    }

    @Test
    public void obtenerFacturaPorIdOk() {
    }

    @Test
    public void obtenerFacturaPorIdNotOk() {
    }
}
