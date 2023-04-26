package co.edu.usbcali.airlinesapp.services;

import co.edu.usbcali.airlinesapp.dtos.VueloDTO;
import co.edu.usbcali.airlinesapp.repository.AeropuertoRepository;
import co.edu.usbcali.airlinesapp.repository.VueloRepository;
import co.edu.usbcali.airlinesapp.services.implementation.VueloServiceImpl;

import co.edu.usbcali.airlinesapp.utility.AeropuertoUtilityTest;
import co.edu.usbcali.airlinesapp.utility.VueloUtilityTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class VueloServiceImplTest {
    @InjectMocks
    private VueloServiceImpl vueloServiceImpl;

    @Mock
    private VueloRepository vueloRepository;

    @Mock
    private AeropuertoRepository aeropuertoRepository;

    @Test
    public void guardarVueloOk() throws Exception {
        given(aeropuertoRepository.existsById(AeropuertoUtilityTest.AEROPUERTO_UNO.getIdAeropuerto())).willReturn(true);
        given(aeropuertoRepository.getReferenceById(AeropuertoUtilityTest.AEROPUERTO_UNO.getIdAeropuerto())).willReturn(AeropuertoUtilityTest.AEROPUERTO_UNO);
        given(aeropuertoRepository.existsById(AeropuertoUtilityTest.AEROPUERTO_DOS.getIdAeropuerto())).willReturn(true);
        given(aeropuertoRepository.getReferenceById(AeropuertoUtilityTest.AEROPUERTO_DOS.getIdAeropuerto())).willReturn(AeropuertoUtilityTest.AEROPUERTO_DOS);
        given(vueloRepository.existsById(VueloUtilityTest.VUELO_UNO.getIdVuelo())).willReturn(false);
        given(vueloRepository.save(VueloUtilityTest.VUELO_UNO)).willReturn(VueloUtilityTest.VUELO_UNO);

        VueloDTO vueloSavedDTO = vueloServiceImpl.guardarVuelo(VueloUtilityTest.VUELODTO);

        assertEquals(VueloUtilityTest.VUELO_UNO.getIdVuelo(), vueloSavedDTO.getIdVuelo());
    }

    @Test
    public void guardarVueloNotOk() {
        given(vueloRepository.existsById(VueloUtilityTest.VUELO_UNO.getIdVuelo())).willReturn(true);

        assertThrows(java.lang.Exception.class, () -> vueloServiceImpl.guardarVuelo(VueloUtilityTest.VUELODTO));
    }

    @Test
    public void obtenerVuelosOk() {
        given(vueloRepository.findAll()).willReturn(VueloUtilityTest.VUELOS);

        List<VueloDTO> vuelosSavedDTO = vueloServiceImpl.obtenerVuelos();

        assertEquals(2, vuelosSavedDTO.size());
        assertEquals(100000, vuelosSavedDTO.get(0).getPrecio());
    }

    @Test
    public void obtenerVuelosNotOk() {
        given(vueloRepository.findAll()).willReturn(VueloUtilityTest.VUELOS_VACIO);

        List<VueloDTO> vuelosSavedDTO = vueloServiceImpl.obtenerVuelos();

        assertEquals(0, vuelosSavedDTO.size());
    }

    @Test
    public void obtenerVuelosActivosOk() {
        given(vueloRepository.findAllByEstado("A")).willReturn(VueloUtilityTest.VUELOS);

        List<VueloDTO> vuelosSavedDTO = vueloServiceImpl.obtenerVuelosActivos();

        assertEquals(2, vuelosSavedDTO.size());
        assertEquals(100000, vuelosSavedDTO.get(0).getPrecio());
    }

    @Test
    public void obtenerVuelosActivosNotOk() {
        given(vueloRepository.findAllByEstado("A")).willReturn(VueloUtilityTest.VUELOS_VACIO);

        List<VueloDTO> vuelosSavedDTO = vueloServiceImpl.obtenerVuelosActivos();

        assertEquals(0, vuelosSavedDTO.size());
    }

    @Test
    public void obtenerVueloPorIdOk() throws Exception {
        aeropuertoRepository.save(AeropuertoUtilityTest.AEROPUERTO_UNO);
        aeropuertoRepository.save(AeropuertoUtilityTest.AEROPUERTO_DOS);
        vueloRepository.save(VueloUtilityTest.VUELO_UNO);

        given(vueloRepository.existsById(VueloUtilityTest.VUELO_UNO.getIdVuelo())).willReturn(true);
        given(vueloRepository.getReferenceById(VueloUtilityTest.VUELO_UNO.getIdVuelo())).willReturn(VueloUtilityTest.VUELO_UNO);

        VueloDTO vueloSavedDTO = vueloServiceImpl.obtenerVueloPorId(VueloUtilityTest.VUELO_UNO.getIdVuelo());

        assertEquals(VueloUtilityTest.VUELO_UNO.getIdVuelo(), vueloSavedDTO.getIdVuelo());
    }

    @Test
    public void obtenerVueloPorIdNotOk() {
        given(vueloRepository.existsById(VueloUtilityTest.VUELO_UNO.getIdVuelo())).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> vueloServiceImpl.obtenerVueloPorId(VueloUtilityTest.VUELO_UNO.getIdVuelo()));
    }

    @Test
    public void actualizarVueloOk() throws Exception {
        given(aeropuertoRepository.existsById(AeropuertoUtilityTest.AEROPUERTO_UNO.getIdAeropuerto())).willReturn(true);
        given(aeropuertoRepository.getReferenceById(AeropuertoUtilityTest.AEROPUERTO_UNO.getIdAeropuerto())).willReturn(AeropuertoUtilityTest.AEROPUERTO_UNO);
        given(aeropuertoRepository.existsById(AeropuertoUtilityTest.AEROPUERTO_DOS.getIdAeropuerto())).willReturn(true);
        given(aeropuertoRepository.getReferenceById(AeropuertoUtilityTest.AEROPUERTO_DOS.getIdAeropuerto())).willReturn(AeropuertoUtilityTest.AEROPUERTO_DOS);
        given(vueloRepository.existsById(VueloUtilityTest.VUELO_UNO.getIdVuelo())).willReturn(true);
        given(vueloRepository.save(VueloUtilityTest.VUELO_UNO)).willReturn(VueloUtilityTest.VUELO_UNO);

        VueloDTO vueloSavedDTO = vueloServiceImpl.actualizarVuelo(VueloUtilityTest.VUELODTO);

        assertEquals(VueloUtilityTest.VUELO_UNO.getIdVuelo(), vueloSavedDTO.getIdVuelo());
    }

    @Test
    public void actualizarVueloNotOk() {
        given(vueloRepository.existsById(VueloUtilityTest.VUELO_UNO.getIdVuelo())).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> vueloServiceImpl.actualizarVuelo(VueloUtilityTest.VUELODTO));
    }
}
