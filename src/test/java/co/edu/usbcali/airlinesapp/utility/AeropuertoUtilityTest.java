package co.edu.usbcali.airlinesapp.utility;

import co.edu.usbcali.airlinesapp.domain.Aeropuerto;
import co.edu.usbcali.airlinesapp.dtos.AeropuertoDTO;

import java.util.Arrays;
import java.util.List;

public class AeropuertoUtilityTest {
    public static Aeropuerto AEROPUERTO_UNO = Aeropuerto.builder()
            .idAeropuerto(1)
            .nombre("Aeropuerto Internacional El Dorado")
            .iata("BOG")
            .ubicacion("Bogotá")
            .estado("A")
            .build();

    public static Aeropuerto AEROPUERTO_DOS = Aeropuerto.builder()
            .idAeropuerto(2)
            .nombre("Aeropuerto Internacional Alfonso Bonilla Aragón")
            .iata("COL")
            .ubicacion("Santiago de Cali")
            .estado("A")
            .build();

    public static AeropuertoDTO AEROPUERTODTO = AeropuertoDTO.builder()
            .idAeropuerto(1)
            .nombre("Aeropuerto Internacional El Dorado")
            .iata("BOG")
            .ubicacion("Bogotá")
            .estado("A")
            .build();

    public static List<Aeropuerto> AEROPUERTOS = Arrays.asList(AEROPUERTO_UNO, AEROPUERTO_DOS);

    public static List<Aeropuerto> AEROPUERTOS_VACIO = Arrays.asList();
}
