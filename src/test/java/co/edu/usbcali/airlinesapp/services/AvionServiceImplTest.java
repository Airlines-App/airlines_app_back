package co.edu.usbcali.airlinesapp.services;

import co.edu.usbcali.airlinesapp.dtos.AvionDTO;
import co.edu.usbcali.airlinesapp.repository.AvionRepository;
import co.edu.usbcali.airlinesapp.services.implementation.AvionServiceImpl;

import co.edu.usbcali.airlinesapp.utility.AeropuertoUtilityTest;
import co.edu.usbcali.airlinesapp.utility.AvionUtilityTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class AvionServiceImplTest {
    @InjectMocks
    private AvionServiceImpl avionServiceImpl;

    @Mock
    private AvionRepository avionRepository;

    @Test
    public void guardarAvionOk() throws Exception {
        given(avionRepository.existsById(AvionUtilityTest.AVION_UNO.getIdAvion())).willReturn(false);
        given(avionRepository.save(AvionUtilityTest.AVION_UNO)).willReturn(AvionUtilityTest.AVION_UNO);

        AvionDTO avionSavedDTO = avionServiceImpl.guardarAvion(AvionUtilityTest.AVIONDTO);

        assertEquals(AvionUtilityTest.AVION_UNO.getIdAvion(), avionSavedDTO.getIdAvion());
    }

    @Test
    public void guardarAvionNotOk() {
        given(avionRepository.existsById(AvionUtilityTest.AVION_UNO.getIdAvion())).willReturn(true);

        assertThrows(java.lang.Exception.class, () -> avionServiceImpl.guardarAvion(AvionUtilityTest.AVIONDTO));
    }

    @Test
    public void obtenerAvionesOk() {
        given(avionRepository.findAll()).willReturn(AvionUtilityTest.AVIONES);

        List<AvionDTO> avionesSavedDTO = avionServiceImpl.obtenerAviones();

        assertEquals(2, avionesSavedDTO.size());
        assertEquals("Boeing 737", avionesSavedDTO.get(0).getModelo());
    }

    @Test
    public void obtenerAvionesNotOk() {
        given(avionRepository.findAll()).willReturn(AvionUtilityTest.AVIONES_VACIO);

        List<AvionDTO> avionesDTO = avionServiceImpl.obtenerAviones();

        assertEquals(0, avionesDTO.size());
    }

    @Test
    public void obtenerAvionesActivosOk() {
        given(avionRepository.findAllByEstado("A")).willReturn(AvionUtilityTest.AVIONES);

        List<AvionDTO> avionesSavedDTO = avionServiceImpl.obtenerAvionesActivos();

        assertEquals(2, avionesSavedDTO.size());
        assertEquals("Boeing 737", avionesSavedDTO.get(0).getModelo());
    }

    @Test
    public void obtenerAvionesActivosNotOk() {
        given(avionRepository.findAllByEstado("A")).willReturn(AvionUtilityTest.AVIONES_VACIO);

        List<AvionDTO> avionesSavedDTO = avionServiceImpl.obtenerAvionesActivos();

        assertEquals(0, avionesSavedDTO.size());
    }

    @Test
    public void obtenerAvionPorIdOk() throws Exception {
        avionRepository.save(AvionUtilityTest.AVION_UNO);

        given(avionRepository.existsById(AvionUtilityTest.AVION_UNO.getIdAvion())).willReturn(true);
        given(avionRepository.getReferenceById(AvionUtilityTest.AVION_UNO.getIdAvion())).willReturn(AvionUtilityTest.AVION_UNO);

        AvionDTO avionDTO = avionServiceImpl.obtenerAvionPorId(AvionUtilityTest.AVION_UNO.getIdAvion());

        assertEquals(AvionUtilityTest.AVION_UNO.getIdAvion(), avionDTO.getIdAvion());
    }

    @Test
    public void obtenerAvionPorIdNotOk() {
        given(avionRepository.existsById(AvionUtilityTest.AVION_UNO.getIdAvion())).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> avionServiceImpl.obtenerAvionPorId(AvionUtilityTest.AVION_UNO.getIdAvion()));
    }

    @Test
    public void actualizarAvionOk() throws Exception {
        given(avionRepository.existsById(AvionUtilityTest.AVION_UNO.getIdAvion())).willReturn(true);
        given(avionRepository.save(AvionUtilityTest.AVION_UNO)).willReturn(AvionUtilityTest.AVION_UNO);

        AvionDTO avionSavedDTO = avionServiceImpl.actualizarAvion(AvionUtilityTest.AVIONDTO);

        assertEquals(AeropuertoUtilityTest.AEROPUERTODTO.getIdAeropuerto(), avionSavedDTO.getIdAvion());
    }

    @Test
    public void actualizarAvionNotOk() {
        given(avionRepository.existsById(AvionUtilityTest.AVION_UNO.getIdAvion())).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> avionServiceImpl.actualizarAvion(AvionUtilityTest.AVIONDTO));
    }
}
