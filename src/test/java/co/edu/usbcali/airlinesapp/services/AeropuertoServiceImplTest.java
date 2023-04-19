package co.edu.usbcali.airlinesapp.services;

import co.edu.usbcali.airlinesapp.domain.Aeropuerto;
import co.edu.usbcali.airlinesapp.dtos.AeropuertoDTO;
import co.edu.usbcali.airlinesapp.mappers.AeropuertoMapper;
import co.edu.usbcali.airlinesapp.repository.AeropuertoRepository;
import co.edu.usbcali.airlinesapp.services.interfaces.AeropuertoService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AeropuertoServiceImplTest {
    @Autowired
    private AeropuertoService aeropuertoService;

    @MockBean
    private AeropuertoRepository aeropuertoRepository;

    @Test
    public void guardarAeropuertoOk() throws Exception {
        Aeropuerto aeropuerto = Aeropuerto.builder()
                .idAeropuerto(1)
                .nombre("Aeropuerto Internacional El Dorado")
                .iata("BOG")
                .ubicacion("Bogotá")
                .estado("A")
                .build();

        Mockito.when(aeropuertoRepository.existsById(1)).thenReturn(false);
        Mockito.when(aeropuertoRepository.save(aeropuerto)).thenReturn(aeropuerto);

        AeropuertoDTO aeropuertoDTO = aeropuertoService.guardarAeropuerto(AeropuertoMapper.domainToDTO(aeropuerto));

        assertEquals(1, aeropuertoDTO.getIdAeropuerto());
    }

    @Test
    public void guardarAeropuertoNotOk() {
        Aeropuerto aeropuerto = Aeropuerto.builder()
                .idAeropuerto(1)
                .nombre("Aeropuerto Internacional El Dorado")
                .iata("BOG")
                .ubicacion("Bogotá")
                .estado("A")
                .build();

        Mockito.when(aeropuertoRepository.existsById(1)).thenReturn(true);

        assertThrows(java.lang.Exception.class, () -> aeropuertoService.guardarAeropuerto(AeropuertoMapper.domainToDTO(aeropuerto)));
    }

    @Test
    public void obtenerAeropuertosOk() {
        Aeropuerto.builder()
                .idAeropuerto(1)
                .nombre("Aeropuerto Internacional El Dorado")
                .iata("BOG")
                .ubicacion("Bogotá")
                .estado("A")
                .build();

        List<Aeropuerto> aeropuertos = Arrays.asList(Aeropuerto.builder()
                        .idAeropuerto(1)
                        .nombre("Aeropuerto Internacional El Dorado")
                        .iata("BOG")
                        .ubicacion("Bogotá")
                        .estado("A")
                        .build(),
                Aeropuerto.builder()
                        .idAeropuerto(2)
                        .nombre("Aeropuerto Internacional Alfonso Bonilla Aragón")
                        .iata("COL")
                        .ubicacion("Santiago de Cali")
                        .estado("A")
                        .build());

        Mockito.when(aeropuertoRepository.findAll()).thenReturn(aeropuertos);

        List<AeropuertoDTO> aeropuertosDTO = aeropuertoService.obtenerAeropuertos();

        assertEquals(2, aeropuertosDTO.size());
        assertEquals("Aeropuerto Internacional El Dorado", aeropuertosDTO.get(0).getNombre());
    }

    @Test
    public void obtenerAeropuertosNotOk() {
        List<Aeropuerto> aeropuertos = Arrays.asList();

        Mockito.when(aeropuertoRepository.findAll()).thenReturn(aeropuertos);

        List<AeropuertoDTO> aeropuertosDTO = aeropuertoService.obtenerAeropuertos();

        assertEquals(0, aeropuertosDTO.size());
    }

    @Test
    public void obtenerAeropuertoPorIdOk() throws Exception {
        Aeropuerto aeropuerto = Aeropuerto.builder()
                .idAeropuerto(1)
                .nombre("Aeropuerto Internacional El Dorado")
                .iata("BOG")
                .ubicacion("Bogotá")
                .estado("A")
                .build();

        Mockito.when(aeropuertoRepository.existsById(1)).thenReturn(true);
        Mockito.when(aeropuertoRepository.getReferenceById(1)).thenReturn(aeropuerto);

        AeropuertoDTO aeropuertoDTO = aeropuertoService.obtenerAeropuertoPorId(1);

        assertEquals(1, aeropuertoDTO.getIdAeropuerto());
    }

    @Test
    public void obtenerAeropuertoPorIdNotOk() {
        Mockito.when(aeropuertoRepository.existsById(1)).thenReturn(false);

        assertThrows(java.lang.Exception.class, () -> aeropuertoService.obtenerAeropuertoPorId(1));
    }
}
