package co.edu.usbcali.airlinesapp.services;

import co.edu.usbcali.airlinesapp.domain.Aeropuerto;
import co.edu.usbcali.airlinesapp.dtos.AeropuertoDTO;
import co.edu.usbcali.airlinesapp.mappers.AeropuertoMapper;
import co.edu.usbcali.airlinesapp.repository.AeropuertoRepository;
import co.edu.usbcali.airlinesapp.services.implementation.AeropuertoServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
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
        AeropuertoDTO aeropuertoDTO = AeropuertoDTO.builder()
                .idAeropuerto(1)
                .nombre("Aeropuerto Internacional El Dorado")
                .iata("BOG")
                .ubicacion("Bogotá")
                .estado("A")
                .build();

        Aeropuerto aeropuerto = AeropuertoMapper.dtoToDomain(aeropuertoDTO);

        given(aeropuertoRepository.existsById(aeropuertoDTO.getIdAeropuerto())).willReturn(false);
        given(aeropuertoRepository.save(aeropuerto)).willReturn(aeropuerto);

        AeropuertoDTO aeropuertoSavedDTO = aeropuertoServiceImpl.guardarAeropuerto(aeropuertoDTO);

        assertEquals(aeropuertoDTO.getIdAeropuerto(), aeropuertoSavedDTO.getIdAeropuerto());
    }

    @Test
    public void guardarAeropuertoNotOk() {
        AeropuertoDTO aeropuertoDTO = AeropuertoDTO.builder()
                .idAeropuerto(1)
                .nombre("Aeropuerto Internacional El Dorado")
                .iata("BOG")
                .ubicacion("Bogotá")
                .estado("A")
                .build();

        Aeropuerto aeropuerto = AeropuertoMapper.dtoToDomain(aeropuertoDTO);

        given(aeropuertoRepository.existsById(aeropuertoDTO.getIdAeropuerto())).willReturn(true);

        assertThrows(java.lang.Exception.class, () -> aeropuertoServiceImpl.guardarAeropuerto(aeropuertoDTO));
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

        given(aeropuertoRepository.findAll()).willReturn(aeropuertos);

        List<AeropuertoDTO> aeropuertosDTO = aeropuertoServiceImpl.obtenerAeropuertos();

        assertEquals(2, aeropuertosDTO.size());
        assertEquals("Aeropuerto Internacional El Dorado", aeropuertosDTO.get(0).getNombre());
    }

    @Test
    public void obtenerAeropuertosNotOk() {
        List<Aeropuerto> aeropuertos = Arrays.asList();

        given(aeropuertoRepository.findAll()).willReturn(aeropuertos);

        List<AeropuertoDTO> aeropuertosDTO = aeropuertoServiceImpl.obtenerAeropuertos();

        assertEquals(0, aeropuertosDTO.size());
    }

    @Test
    public void obtenerAeropuertosActivosOk() {
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
                        .estado("I")
                        .build());

        given(aeropuertoRepository.findAllByEstado("A")).willReturn(aeropuertos);

        List<AeropuertoDTO> aeropuertosDTO = aeropuertoServiceImpl.obtenerAeropuertosActivos();

        assertEquals(2, aeropuertosDTO.size());
        assertEquals("Aeropuerto Internacional El Dorado", aeropuertosDTO.get(0).getNombre());
    }

    @Test
    public void obtenerAeropuertosActivosNotOk() {
        List<Aeropuerto> aeropuertos = Arrays.asList();

        given(aeropuertoRepository.findAllByEstado("A")).willReturn(aeropuertos);

        List<AeropuertoDTO> aeropuertosDTO = aeropuertoServiceImpl.obtenerAeropuertosActivos();

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

        aeropuertoRepository.save(aeropuerto);

        given(aeropuertoRepository.existsById(aeropuerto.getIdAeropuerto())).willReturn(true);
        given(aeropuertoRepository.getReferenceById(aeropuerto.getIdAeropuerto())).willReturn(aeropuerto);

        AeropuertoDTO aeropuertoDTO = aeropuertoServiceImpl.obtenerAeropuertoPorId(1);

        assertEquals(1, aeropuertoDTO.getIdAeropuerto());
    }

    @Test
    public void obtenerAeropuertoPorIdNotOk() {
        given(aeropuertoRepository.existsById(1)).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> aeropuertoServiceImpl.obtenerAeropuertoPorId(1));
    }
}
