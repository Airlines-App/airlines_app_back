package co.edu.usbcali.airlinesapp.services;

import co.edu.usbcali.airlinesapp.dtos.FacturaDTO;
import co.edu.usbcali.airlinesapp.repository.FacturaRepository;
import co.edu.usbcali.airlinesapp.repository.ReservaRepository;
import co.edu.usbcali.airlinesapp.services.implementation.FacturaServiceImpl;

import co.edu.usbcali.airlinesapp.utility.FacturaUtilityTest;
import co.edu.usbcali.airlinesapp.utility.ReservaUtilityTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class FacturaServiceImplTest {
    @InjectMocks
    private FacturaServiceImpl facturaServiceImpl;

    @Mock
    private FacturaRepository facturaRepository;

    @Mock
    private ReservaRepository reservaRepository;

    @Test
    public void guardarFacturaOk() throws Exception {
        given(reservaRepository.existsById(ReservaUtilityTest.RESERVA_UNO.getIdReserva())).willReturn(true);
        given(reservaRepository.getReferenceById(ReservaUtilityTest.RESERVA_UNO.getIdReserva())).willReturn(ReservaUtilityTest.RESERVA_UNO);
        given(facturaRepository.existsById(FacturaUtilityTest.FACTURA_UNO.getIdFactura())).willReturn(false);
        given(facturaRepository.save(FacturaUtilityTest.FACTURA_UNO)).willReturn(FacturaUtilityTest.FACTURA_UNO);

        FacturaDTO facturaSavedDTO = facturaServiceImpl.guardarFactura(FacturaUtilityTest.FACTURADTO);

        assertEquals(FacturaUtilityTest.FACTURA_UNO.getIdFactura(), facturaSavedDTO.getIdFactura());
    }

    @Test
    public void guardarFacturaNotOk() {
        given(facturaRepository.existsById(FacturaUtilityTest.FACTURA_UNO.getIdFactura())).willReturn(true);

        assertThrows(java.lang.Exception.class, () -> facturaServiceImpl.guardarFactura(FacturaUtilityTest.FACTURADTO));
    }

    @Test
    public void obtenerFacturasOk() {
        given(facturaRepository.findAll()).willReturn(FacturaUtilityTest.FACTURAS);

        List<FacturaDTO> facturasSavedDTO = facturaServiceImpl.obtenerFacturas();

        assertEquals(2, facturasSavedDTO.size());
        assertEquals(1, facturasSavedDTO.get(0).getIdFactura());
    }

    @Test
    public void obtenerFacturasNotOk() {
        given(facturaRepository.findAll()).willReturn(FacturaUtilityTest.FACTURAS_VACIO);

        List<FacturaDTO> facturasSavedDTO = facturaServiceImpl.obtenerFacturas();

        assertEquals(0, facturasSavedDTO.size());
    }

    @Test
    public void obtenerFacturasActivasOk() {
        given(facturaRepository.findAllByEstado("A")).willReturn(FacturaUtilityTest.FACTURAS);

        List<FacturaDTO> facturasSavedTO = facturaServiceImpl.obtenerFacturasActivas();

        assertEquals(2, facturasSavedTO.size());
        assertEquals(1, facturasSavedTO.get(0).getIdFactura());
    }

    @Test
    public void obtenerFacturasActivasNotOk() {
        given(facturaRepository.findAllByEstado("A")).willReturn(FacturaUtilityTest.FACTURAS_VACIO);

        List<FacturaDTO> facturasSavedTO = facturaServiceImpl.obtenerFacturasActivas();

        assertEquals(0, facturasSavedTO.size());
    }

    @Test
    public void obtenerFacturaPorIdOk() throws Exception {
        reservaRepository.save(ReservaUtilityTest.RESERVA_UNO);
        facturaRepository.save(FacturaUtilityTest.FACTURA_UNO);

        given(facturaRepository.existsById(FacturaUtilityTest.FACTURA_UNO.getIdFactura())).willReturn(true);
        given(facturaRepository.getReferenceById(FacturaUtilityTest.FACTURA_UNO.getIdFactura())).willReturn(FacturaUtilityTest.FACTURA_UNO);

        FacturaDTO facturaSavedDTO = facturaServiceImpl.obtenerFacturaPorId(FacturaUtilityTest.FACTURA_UNO.getIdFactura());

        assertEquals(FacturaUtilityTest.FACTURA_UNO.getIdFactura(), facturaSavedDTO.getIdFactura());
    }

    @Test
    public void obtenerFacturaPorIdNotOk() {
        given(facturaRepository.existsById(FacturaUtilityTest.FACTURA_UNO.getIdFactura())).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> facturaServiceImpl.obtenerFacturaPorId(FacturaUtilityTest.FACTURA_UNO.getIdFactura()));
    }

    @Test
    public void actualizarFacturaOk() throws Exception {
        given(reservaRepository.existsById(ReservaUtilityTest.RESERVA_UNO.getIdReserva())).willReturn(true);
        given(reservaRepository.getReferenceById(ReservaUtilityTest.RESERVA_UNO.getIdReserva())).willReturn(ReservaUtilityTest.RESERVA_UNO);
        given(facturaRepository.existsById(FacturaUtilityTest.FACTURA_UNO.getIdFactura())).willReturn(true);
        given(facturaRepository.save(FacturaUtilityTest.FACTURA_UNO)).willReturn(FacturaUtilityTest.FACTURA_UNO);

        FacturaDTO facturaSavedDTO = facturaServiceImpl.actualizarFactura(FacturaUtilityTest.FACTURADTO);

        assertEquals(FacturaUtilityTest.FACTURA_UNO.getIdFactura(), facturaSavedDTO.getIdFactura());
    }

    @Test
    public void actualizarFacturaNotOk() {
        given(facturaRepository.existsById(FacturaUtilityTest.FACTURA_UNO.getIdFactura())).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> facturaServiceImpl.actualizarFactura(FacturaUtilityTest.FACTURADTO));
    }
}
