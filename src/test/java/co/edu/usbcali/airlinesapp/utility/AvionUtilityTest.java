package co.edu.usbcali.airlinesapp.utility;

import co.edu.usbcali.airlinesapp.domain.Avion;
import co.edu.usbcali.airlinesapp.dtos.AvionDTO;

import java.util.Arrays;
import java.util.List;

public class AvionUtilityTest {
    public static Avion AVION_UNO = Avion.builder()
            .idAvion(1)
            .modelo("Boeing 737")
            .aerolinea("Avianca")
            .estado("A")
            .build();

    public static Avion AVION_DOS = Avion.builder()
            .idAvion(2)
            .modelo("Boeing 747")
            .aerolinea("Wingo")
            .estado("A")
            .build();

    public static AvionDTO AVIONDTO = AvionDTO.builder()
            .idAvion(1)
            .modelo("Boeing 737")
            .aerolinea("Avianca")
            .estado("A")
            .build();

    public static List<Avion> AVIONES = Arrays.asList(AVION_UNO, AVION_DOS);

    public static List<Avion> AVIONES_VACIO = Arrays.asList();
}
