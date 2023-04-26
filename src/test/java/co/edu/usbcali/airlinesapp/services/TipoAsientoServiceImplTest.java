package co.edu.usbcali.airlinesapp.services;

import co.edu.usbcali.airlinesapp.dtos.TipoAsientoDTO;
import co.edu.usbcali.airlinesapp.repository.TipoAsientoRepository;
import co.edu.usbcali.airlinesapp.services.implementation.TipoAsientoServiceImpl;

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
public class TipoAsientoServiceImplTest {
    @InjectMocks
    private TipoAsientoServiceImpl tipoAsientoServiceImpl;

    @Mock
    private TipoAsientoRepository tipoAsientoRepository;

    @Test
    public void guardarTipoAsientoOk() throws Exception {
        given(tipoAsientoRepository.existsById(TipoAsientoUtilityTest.TIPOASIENTO_UNO.getIdTipoAsiento())).willReturn(false);
        given(tipoAsientoRepository.save(TipoAsientoUtilityTest.TIPOASIENTO_UNO)).willReturn(TipoAsientoUtilityTest.TIPOASIENTO_UNO);

        TipoAsientoDTO tipoAsientoSavedDTO = tipoAsientoServiceImpl.guardarTipoAsiento(TipoAsientoUtilityTest.TIPOASIENTODTO);

        assertEquals(TipoAsientoUtilityTest.TIPOASIENTO_UNO.getIdTipoAsiento(), tipoAsientoSavedDTO.getIdTipoAsiento());
    }

    @Test
    public void guardarTipoAsientoNotOk() {
        given(tipoAsientoRepository.existsById(TipoAsientoUtilityTest.TIPOASIENTO_UNO.getIdTipoAsiento())).willReturn(true);

        assertThrows(java.lang.Exception.class, () -> tipoAsientoServiceImpl.guardarTipoAsiento(TipoAsientoUtilityTest.TIPOASIENTODTO));
    }

    @Test
    public void obtenerTipoAsientosOk() {
        given(tipoAsientoRepository.findAll()).willReturn(TipoAsientoUtilityTest.TIPOASIENTOS);

        List<TipoAsientoDTO> tipoAsientosSavedDTO = tipoAsientoServiceImpl.obtenerTipoAsientos();

        assertEquals(2, tipoAsientosSavedDTO.size());
        assertEquals("Ejecutivo", tipoAsientosSavedDTO.get(0).getDescripcion());
    }

    @Test
    public void obtenerTipoAsientosNotOk() {
        given(tipoAsientoRepository.findAll()).willReturn(TipoAsientoUtilityTest.TIPOASIENTOS_VACIO);

        List<TipoAsientoDTO> tipoAsientosSavedDTO = tipoAsientoServiceImpl.obtenerTipoAsientos();

        assertEquals(0, tipoAsientosSavedDTO.size());
    }

    @Test
    public void obtenerTipoAsientosActivosOk() {
        given(tipoAsientoRepository.findAllByEstado("A")).willReturn(TipoAsientoUtilityTest.TIPOASIENTOS);

        List<TipoAsientoDTO> tipoAsientosSavedDTO = tipoAsientoServiceImpl.obtenerTipoAsientosActivos();

        assertEquals(2, tipoAsientosSavedDTO.size());
        assertEquals("Ejecutivo", tipoAsientosSavedDTO.get(0).getDescripcion());
    }

    @Test
    public void obtenerTipoAsientosActivosNotOk() {
        given(tipoAsientoRepository.findAllByEstado("A")).willReturn(TipoAsientoUtilityTest.TIPOASIENTOS_VACIO);

        List<TipoAsientoDTO> tipoAsientosSavedDTO = tipoAsientoServiceImpl.obtenerTipoAsientosActivos();

        assertEquals(0, tipoAsientosSavedDTO.size());
    }

    @Test
    public void obtenerTipoAsientoPorIdOk() throws Exception {
        tipoAsientoRepository.save(TipoAsientoUtilityTest.TIPOASIENTO_UNO);

        given(tipoAsientoRepository.existsById(TipoAsientoUtilityTest.TIPOASIENTO_UNO.getIdTipoAsiento())).willReturn(true);
        given(tipoAsientoRepository.getReferenceById(TipoAsientoUtilityTest.TIPOASIENTO_UNO.getIdTipoAsiento())).willReturn(TipoAsientoUtilityTest.TIPOASIENTO_UNO);

        TipoAsientoDTO tipoAsientoSavedDTO = tipoAsientoServiceImpl.obtenerTipoAsientoPorId(TipoAsientoUtilityTest.TIPOASIENTO_UNO.getIdTipoAsiento());

        assertEquals("Ejecutivo", tipoAsientoSavedDTO.getDescripcion());
    }

    @Test
    public void obtenerTipoAsientoPorIdNotOk() {
        given(tipoAsientoRepository.existsById(TipoAsientoUtilityTest.TIPOASIENTO_UNO.getIdTipoAsiento())).willReturn(false);

        assertThrows(Exception.class, () -> tipoAsientoServiceImpl.obtenerTipoAsientoPorId(TipoAsientoUtilityTest.TIPOASIENTO_UNO.getIdTipoAsiento()));
    }

    @Test
    public void actualizarTipoAsientoOk() throws Exception {
        given(tipoAsientoRepository.existsById(TipoAsientoUtilityTest.TIPOASIENTO_UNO.getIdTipoAsiento())).willReturn(true);
        given(tipoAsientoRepository.save(TipoAsientoUtilityTest.TIPOASIENTO_UNO)).willReturn(TipoAsientoUtilityTest.TIPOASIENTO_UNO);

        TipoAsientoDTO tipoAsientoSavedDTO = tipoAsientoServiceImpl.actualizarTipoAsiento(TipoAsientoUtilityTest.TIPOASIENTODTO);

        assertEquals(TipoAsientoUtilityTest.TIPOASIENTO_UNO.getIdTipoAsiento(), tipoAsientoSavedDTO.getIdTipoAsiento());
    }

    @Test
    public void actualizarTipoAsientoNotOk() {
        given(tipoAsientoRepository.existsById(TipoAsientoUtilityTest.TIPOASIENTO_UNO.getIdTipoAsiento())).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> tipoAsientoServiceImpl.actualizarTipoAsiento(TipoAsientoUtilityTest.TIPOASIENTODTO));
    }
}
