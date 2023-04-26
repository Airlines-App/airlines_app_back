package co.edu.usbcali.airlinesapp.services;

import co.edu.usbcali.airlinesapp.dtos.AsientoDTO;
import co.edu.usbcali.airlinesapp.repository.AsientoRepository;
import co.edu.usbcali.airlinesapp.repository.AvionRepository;
import co.edu.usbcali.airlinesapp.repository.TipoAsientoRepository;
import co.edu.usbcali.airlinesapp.services.implementation.AsientoServiceImpl;

import co.edu.usbcali.airlinesapp.utility.AsientoUtilityTest;
import co.edu.usbcali.airlinesapp.utility.AvionUtilityTest;
import co.edu.usbcali.airlinesapp.utility.TipoAsientoUtilityTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class AsientoServiceImplTest {
    @InjectMocks
    private AsientoServiceImpl asientoServiceImpl;

    @Mock
    private AsientoRepository asientoRepository;

    @Mock
    private TipoAsientoRepository tipoAsientoRepository;

    @Mock
    private AvionRepository avionRepository;

    @Test
    public void guardarAsientoOk() throws Exception {
        given(tipoAsientoRepository.existsById(TipoAsientoUtilityTest.TIPOASIENTO_UNO.getIdTipoAsiento())).willReturn(true);
        given(tipoAsientoRepository.getReferenceById(TipoAsientoUtilityTest.TIPOASIENTO_UNO.getIdTipoAsiento())).willReturn(TipoAsientoUtilityTest.TIPOASIENTO_UNO);
        given(asientoRepository.existsById(AsientoUtilityTest.ASIENTO_UNO.getIdAsiento())).willReturn(false);
        given(asientoRepository.save(AsientoUtilityTest.ASIENTO_UNO)).willReturn(AsientoUtilityTest.ASIENTO_UNO);
        given(avionRepository.existsById(AvionUtilityTest.AVION_UNO.getIdAvion())).willReturn(true);
        given(avionRepository.getReferenceById(AvionUtilityTest.AVION_UNO.getIdAvion())).willReturn(AvionUtilityTest.AVION_UNO);

        AsientoDTO asientoSavedDTO = asientoServiceImpl.guardarAsiento(AsientoUtilityTest.ASIENTODTO);

        assertEquals(AsientoUtilityTest.ASIENTO_UNO.getIdAsiento(), asientoSavedDTO.getIdAsiento());
    }

    @Test
    public void guardarAsientoNotOk() {
        given(asientoRepository.existsById(AsientoUtilityTest.ASIENTO_UNO.getIdAsiento())).willReturn(true);

        assertThrows(java.lang.Exception.class, () -> asientoServiceImpl.guardarAsiento(AsientoUtilityTest.ASIENTODTO));
    }

    @Test
    public void obtenerAsientosOk() {
        given(asientoRepository.findAll()).willReturn(AsientoUtilityTest.ASIENTOS);

        List<AsientoDTO> asientosSavedDTO = asientoServiceImpl.obtenerAsientos();

        assertEquals(2, asientosSavedDTO.size());
        assertEquals("A1", asientosSavedDTO.get(0).getUbicacion());
    }

    @Test
    public void obtenerAsientosNotOk() {
        given(asientoRepository.findAll()).willReturn(AsientoUtilityTest.ASIENTOS_VACIO);

        List<AsientoDTO> asientosSavedDTO = asientoServiceImpl.obtenerAsientos();

        assertEquals(0, asientosSavedDTO.size());
    }

    @Test
    public void obtenerAsientosActivosOk() {
        given(asientoRepository.findAllByEstado("A")).willReturn(AsientoUtilityTest.ASIENTOS);

        List<AsientoDTO> asientosSavedDTO = asientoServiceImpl.obtenerAsientosActivos();

        assertEquals(2, asientosSavedDTO.size());
        assertEquals("A1", asientosSavedDTO.get(0).getUbicacion());
    }

    @Test
    public void obtenerAsientosActivosNotOk() {
        given(asientoRepository.findAllByEstado("A")).willReturn(AsientoUtilityTest.ASIENTOS_VACIO);

        List<AsientoDTO> asientosSavedDTO = asientoServiceImpl.obtenerAsientosActivos();

        assertEquals(0, asientosSavedDTO.size());
    }

    @Test
    public void obtenerAsientoPorIdOk() throws Exception {
        tipoAsientoRepository.save(TipoAsientoUtilityTest.TIPOASIENTO_UNO);
        asientoRepository.save(AsientoUtilityTest.ASIENTO_UNO);
        avionRepository.save(AvionUtilityTest.AVION_UNO);

        given(asientoRepository.existsById(AsientoUtilityTest.ASIENTO_UNO.getIdAsiento())).willReturn(true);
        given(asientoRepository.getReferenceById(AsientoUtilityTest.ASIENTO_UNO.getIdAsiento())).willReturn(AsientoUtilityTest.ASIENTO_UNO);

        AsientoDTO asientoSavedDTO = asientoServiceImpl.obtenerAsientoPorId(AsientoUtilityTest.ASIENTO_UNO.getIdAsiento());

        assertEquals(AsientoUtilityTest.ASIENTO_UNO.getIdAsiento(), asientoSavedDTO.getIdAsiento());
    }

    @Test
    public void obtenerAsientoPorIdNotOk() {
        given(asientoRepository.existsById(AsientoUtilityTest.ASIENTO_UNO.getIdAsiento())).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> asientoServiceImpl.obtenerAsientoPorId(AsientoUtilityTest.ASIENTO_UNO.getIdAsiento()));
    }

    @Test
    public void actualizarAsientoOk() throws Exception {
        given(tipoAsientoRepository.existsById(TipoAsientoUtilityTest.TIPOASIENTO_UNO.getIdTipoAsiento())).willReturn(true);
        given(tipoAsientoRepository.getReferenceById(TipoAsientoUtilityTest.TIPOASIENTO_UNO.getIdTipoAsiento())).willReturn(TipoAsientoUtilityTest.TIPOASIENTO_UNO);
        given(avionRepository.existsById(AvionUtilityTest.AVION_UNO.getIdAvion())).willReturn(true);
        given(avionRepository.getReferenceById(AvionUtilityTest.AVION_UNO.getIdAvion())).willReturn(AvionUtilityTest.AVION_UNO);
        given(asientoRepository.existsById(AsientoUtilityTest.ASIENTO_UNO.getIdAsiento())).willReturn(true);
        given(asientoRepository.save(AsientoUtilityTest.ASIENTO_UNO)).willReturn(AsientoUtilityTest.ASIENTO_UNO);

        AsientoDTO asientoSavedDTO = asientoServiceImpl.actualizarAsiento(AsientoUtilityTest.ASIENTODTO);

        assertEquals(AsientoUtilityTest.ASIENTO_UNO.getIdAsiento(), asientoSavedDTO.getIdAsiento());
    }

    @Test
    public void actualizarAsientoNotOk() {
        given(asientoRepository.existsById(AsientoUtilityTest.ASIENTO_UNO.getIdAsiento())).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> asientoServiceImpl.actualizarAsiento(AsientoUtilityTest.ASIENTODTO));
    }
}
