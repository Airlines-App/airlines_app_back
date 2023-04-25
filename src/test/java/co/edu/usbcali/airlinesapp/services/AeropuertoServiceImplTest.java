package co.edu.usbcali.airlinesapp.services;

import co.edu.usbcali.airlinesapp.dtos.AeropuertoDTO;
import co.edu.usbcali.airlinesapp.repository.AeropuertoRepository;
import co.edu.usbcali.airlinesapp.services.implementation.AeropuertoServiceImpl;

import co.edu.usbcali.airlinesapp.utility.AeropuertoUtilityTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class AeropuertoServiceImplTest {
    @InjectMocks
    private AeropuertoServiceImpl aeropuertoServiceImpl;

    @Mock
    private AeropuertoRepository aeropuertoRepository;

    @Test
    public void guardarAeropuertoOk() throws Exception {
        given(aeropuertoRepository.existsById(AeropuertoUtilityTest.AEROPUERTO_UNO.getIdAeropuerto())).willReturn(false);
        given(aeropuertoRepository.save(AeropuertoUtilityTest.AEROPUERTO_UNO)).willReturn(AeropuertoUtilityTest.AEROPUERTO_UNO);

        AeropuertoDTO aeropuertoSavedDTO = aeropuertoServiceImpl.guardarAeropuerto(AeropuertoUtilityTest.AEROPUERTODTO);

        assertEquals(AeropuertoUtilityTest.AEROPUERTO_UNO.getIdAeropuerto(), aeropuertoSavedDTO.getIdAeropuerto());
    }

    @Test
    public void guardarAeropuertoNotOk() {
        given(aeropuertoRepository.existsById(AeropuertoUtilityTest.AEROPUERTO_UNO.getIdAeropuerto())).willReturn(true);

        assertThrows(java.lang.Exception.class, () -> aeropuertoServiceImpl.guardarAeropuerto(AeropuertoUtilityTest.AEROPUERTODTO));
    }

    @Test
    public void obtenerAeropuertosOk() {
        given(aeropuertoRepository.findAll()).willReturn(AeropuertoUtilityTest.AEROPUERTOS);

        List<AeropuertoDTO> aeropuertosSavedDTO = aeropuertoServiceImpl.obtenerAeropuertos();

        assertEquals(2, aeropuertosSavedDTO.size());
        assertEquals("Aeropuerto Internacional El Dorado", aeropuertosSavedDTO.get(0).getNombre());
    }

    @Test
    public void obtenerAeropuertosNotOk() {
        given(aeropuertoRepository.findAll()).willReturn(AeropuertoUtilityTest.AEROPUERTOS_VACIO);

        List<AeropuertoDTO> aeropuertosSavedDTO = aeropuertoServiceImpl.obtenerAeropuertos();

        assertEquals(0, aeropuertosSavedDTO.size());
    }

    @Test
    public void obtenerAeropuertosActivosOk() {
        given(aeropuertoRepository.findAllByEstado("A")).willReturn(AeropuertoUtilityTest.AEROPUERTOS);

        List<AeropuertoDTO> aeropuertosSavedDTO = aeropuertoServiceImpl.obtenerAeropuertosActivos();

        assertEquals(2, aeropuertosSavedDTO.size());
        assertEquals("Aeropuerto Internacional El Dorado", aeropuertosSavedDTO.get(0).getNombre());
    }

    @Test
    public void obtenerAeropuertosActivosNotOk() {
        given(aeropuertoRepository.findAllByEstado("A")).willReturn(AeropuertoUtilityTest.AEROPUERTOS_VACIO);

        List<AeropuertoDTO> aeropuertosSavedDTO = aeropuertoServiceImpl.obtenerAeropuertosActivos();

        assertEquals(0, aeropuertosSavedDTO.size());
    }

    @Test
    public void obtenerAeropuertoPorIdOk() throws Exception {
        aeropuertoRepository.save(AeropuertoUtilityTest.AEROPUERTO_UNO);

        given(aeropuertoRepository.existsById(AeropuertoUtilityTest.AEROPUERTO_UNO.getIdAeropuerto())).willReturn(true);
        given(aeropuertoRepository.getReferenceById(AeropuertoUtilityTest.AEROPUERTO_UNO.getIdAeropuerto())).willReturn(AeropuertoUtilityTest.AEROPUERTO_UNO);

        AeropuertoDTO aeropuertoSavedDTO = aeropuertoServiceImpl.obtenerAeropuertoPorId(AeropuertoUtilityTest.AEROPUERTO_UNO.getIdAeropuerto());

        assertEquals(AeropuertoUtilityTest.AEROPUERTO_UNO.getIdAeropuerto(), aeropuertoSavedDTO.getIdAeropuerto());
    }

    @Test
    public void obtenerAeropuertoPorIdNotOk() {
        given(aeropuertoRepository.existsById(AeropuertoUtilityTest.AEROPUERTO_UNO.getIdAeropuerto())).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> aeropuertoServiceImpl.obtenerAeropuertoPorId(AeropuertoUtilityTest.AEROPUERTO_UNO.getIdAeropuerto()));
    }

    @Test
    public void actualizarAeropuertoOk() throws Exception {
        given(aeropuertoRepository.existsById(AeropuertoUtilityTest.AEROPUERTO_UNO.getIdAeropuerto())).willReturn(true);
        given(aeropuertoRepository.save(AeropuertoUtilityTest.AEROPUERTO_UNO)).willReturn(AeropuertoUtilityTest.AEROPUERTO_UNO);

        AeropuertoDTO aeropuertoSavedDTO = aeropuertoServiceImpl.actualizarAeropuerto(AeropuertoUtilityTest.AEROPUERTODTO);

        assertEquals(AeropuertoUtilityTest.AEROPUERTODTO.getIdAeropuerto(), aeropuertoSavedDTO.getIdAeropuerto());
    }

    @Test
    public void actualizarAeropuertoNotOk() {
        given(aeropuertoRepository.existsById(AeropuertoUtilityTest.AEROPUERTO_UNO.getIdAeropuerto())).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> aeropuertoServiceImpl.actualizarAeropuerto(AeropuertoUtilityTest.AEROPUERTODTO));
    }
}
