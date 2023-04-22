package co.edu.usbcali.airlinesapp.services;

import co.edu.usbcali.airlinesapp.domain.Asiento;
import co.edu.usbcali.airlinesapp.domain.Avion;
import co.edu.usbcali.airlinesapp.domain.TipoAsiento;
import co.edu.usbcali.airlinesapp.dtos.AsientoDTO;
import co.edu.usbcali.airlinesapp.mappers.AsientoMapper;
import co.edu.usbcali.airlinesapp.repository.AsientoRepository;
import co.edu.usbcali.airlinesapp.repository.AvionRepository;
import co.edu.usbcali.airlinesapp.repository.TipoAsientoRepository;
import co.edu.usbcali.airlinesapp.services.implementation.AsientoServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
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
        TipoAsiento tipoAsiento = TipoAsiento.builder()
                .idTipoAsiento(1)
                .descripcion("Ejecutivo")
                .estado("A")
                .build();

        Avion avion = Avion.builder()
                .idAvion(1)
                .modelo("Boeing 737")
                .aerolinea("Avianca")
                .estado("A")
                .build();

        AsientoDTO asientoDTO = AsientoDTO.builder()
                .idAsiento(1)
                .idTipoAsiento(1)
                .idAvion(1)
                .ubicacion("A1")
                .estado("A")
                .build();

        Asiento asiento = AsientoMapper.dtoToDomain(asientoDTO);
        asiento.setTipoAsiento(tipoAsiento);
        asiento.setAvion(avion);

        given(tipoAsientoRepository.existsById(tipoAsiento.getIdTipoAsiento())).willReturn(true);
        given(tipoAsientoRepository.getReferenceById(tipoAsiento.getIdTipoAsiento())).willReturn(tipoAsiento);
        given(asientoRepository.existsById(asientoDTO.getIdAsiento())).willReturn(false);
        given(asientoRepository.save(asiento)).willReturn(asiento);

        AsientoDTO asientoSavedDTO = asientoServiceImpl.guardarAsiento(asientoDTO);

        assertEquals(asientoDTO.getIdAsiento(), asientoSavedDTO.getIdAsiento());
    }

    @Test
    public void guardarAsientoNotOk() {
        TipoAsiento tipoAsiento = TipoAsiento.builder()
                .idTipoAsiento(1)
                .descripcion("Ejecutivo")
                .estado("A")
                .build();

        Avion avion = Avion.builder()
                .idAvion(1)
                .modelo("Boeing 737")
                .aerolinea("Avianca")
                .estado("A")
                .build();

        Asiento asiento = Asiento.builder()
                .idAsiento(1)
                .tipoAsiento(tipoAsiento)
                .avion(avion)
                .ubicacion("A1")
                .estado("A")
                .build();

        Mockito.when(asientoRepository.existsById(1)).thenReturn(true);

        assertThrows(java.lang.Exception.class, () -> asientoServiceImpl.guardarAsiento(AsientoMapper.domainToDTO(asiento)));
    }

    @Test
    public void obtenerAsientosOk() {
        TipoAsiento tipoAsiento = TipoAsiento.builder()
                .idTipoAsiento(1)
                .descripcion("Ejecutivo")
                .estado("A")
                .build();

        Avion avion = Avion.builder()
                .idAvion(1)
                .modelo("Boeing 737")
                .aerolinea("Avianca")
                .estado("A")
                .build();

        Asiento.builder()
                .idAsiento(1)
                .tipoAsiento(tipoAsiento)
                .avion(avion)
                .ubicacion("A1")
                .estado("A")
                .build();

        List<Asiento> asientos = List.of(Asiento.builder()
                        .idAsiento(1)
                        .tipoAsiento(tipoAsiento)
                        .avion(avion)
                        .ubicacion("A1")
                        .estado("A")
                        .build(),
                Asiento.builder()
                        .idAsiento(2)
                        .tipoAsiento(tipoAsiento)
                        .avion(avion)
                        .ubicacion("A2")
                        .estado("A")
                        .build());

        Mockito.when(asientoRepository.findAll()).thenReturn(asientos);

        List<AsientoDTO> asientosDTO = asientoServiceImpl.obtenerAsientos();

        assertEquals(2, asientosDTO.size());
        assertEquals("A1", asientosDTO.get(0).getUbicacion());
    }

    @Test
    public void obtenerAsientosNotOk() {
        List<Asiento> asientos = Arrays.asList();

        Mockito.when(asientoRepository.findAll()).thenReturn(asientos);

        List<AsientoDTO> asientosDTO = asientoServiceImpl.obtenerAsientos();

        assertEquals(0, asientosDTO.size());
    }

    @Test
    public void obtenerAsientoPorIdOk() throws Exception {
        TipoAsiento tipoAsiento = TipoAsiento.builder()
                .idTipoAsiento(1)
                .descripcion("Ejecutivo")
                .estado("A")
                .build();

        Avion avion = Avion.builder()
                .idAvion(1)
                .modelo("Boeing 737")
                .aerolinea("Avianca")
                .estado("A")
                .build();

        Asiento asiento = Asiento.builder()
                .idAsiento(1)
                .tipoAsiento(tipoAsiento)
                .avion(avion)
                .ubicacion("A1")
                .estado("A")
                .build();

        Mockito.when(asientoRepository.existsById(1)).thenReturn(true);
        Mockito.when(asientoRepository.getReferenceById(1)).thenReturn(asiento);

        AsientoDTO asientoDTO = asientoServiceImpl.obtenerAsientoPorId(1);

        assertEquals(1, asientoDTO.getIdAsiento());
    }

    @Test
    public void obtenerAsientoPorIdNotOk() {
        Mockito.when(asientoRepository.existsById(1)).thenReturn(false);

        assertThrows(java.lang.Exception.class, () -> asientoServiceImpl.obtenerAsientoPorId(1));
    }
}
