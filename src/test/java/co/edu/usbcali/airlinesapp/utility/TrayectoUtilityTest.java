package co.edu.usbcali.airlinesapp.utility;

import co.edu.usbcali.airlinesapp.domain.Trayecto;
import co.edu.usbcali.airlinesapp.dtos.TrayectoDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TrayectoUtilityTest {
    public static Integer ID_UNO = 1;
    public static Date HORA_SALIDA_UNO = new Date();
    public static Date HORA_LLEGADA_UNO = new Date();
    public static String ESTADO_UNO = "A";
    public static Integer TRAYECTOS_SIZE = 2;
    public static Integer TRAYECTOS_VACIO_SIZE = 0;
    public static String FECHA_FUTURO = "2023-11-27 08:00";
    public static String FECHA_FUTURO_DOS = "2023-12-27 10:00";
    public static String PATTERN_FECHA = "yyyy-MM-dd HH:mm";
    public static Date FECHA_FUTURO_DATE;
    public static Date FECHA_FUTURO_DATE_DOS;

    static {
        try {
            FECHA_FUTURO_DATE = new SimpleDateFormat(PATTERN_FECHA).parse(FECHA_FUTURO);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    static {
        try {
            FECHA_FUTURO_DATE_DOS = new SimpleDateFormat(PATTERN_FECHA).parse(FECHA_FUTURO_DOS);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Trayecto TRAYECTO_UNO = Trayecto.builder()
            .idTrayecto(1)
            .avion(AvionUtilityTest.AVION_UNO)
            .aeropuertoOrigen(AeropuertoUtilityTest.AEROPUERTO_UNO)
            .aeropuertoDestino(AeropuertoUtilityTest.AEROPUERTO_DOS)
            .horaSalida(FECHA_FUTURO_DATE)
            .horaLlegada(FECHA_FUTURO_DATE_DOS)
            .vuelo(VueloUtilityTest.VUELO_UNO)
            .estado("A")
            .build();

    public static Trayecto TRAYECTO_DOS = Trayecto.builder()
            .idTrayecto(2)
            .avion(AvionUtilityTest.AVION_UNO)
            .aeropuertoOrigen(AeropuertoUtilityTest.AEROPUERTO_UNO)
            .aeropuertoDestino(AeropuertoUtilityTest.AEROPUERTO_DOS)
            .horaSalida(FECHA_FUTURO_DATE)
            .horaLlegada(FECHA_FUTURO_DATE_DOS)
            .vuelo(VueloUtilityTest.VUELO_UNO)
            .estado("A")
            .build();

    public static TrayectoDTO TRAYECTODTO = TrayectoDTO.builder()
            .idTrayecto(1)
            .idAvion(AvionUtilityTest.AVION_UNO.getIdAvion())
            .idAeropuertoOrigen(AeropuertoUtilityTest.AEROPUERTO_UNO.getIdAeropuerto())
            .idAeropuertoDestino(AeropuertoUtilityTest.AEROPUERTO_DOS.getIdAeropuerto())
            .horaSalida(FECHA_FUTURO_DATE)
            .horaLlegada(FECHA_FUTURO_DATE_DOS)
            .idVuelo(VueloUtilityTest.VUELO_UNO.getIdVuelo())
            .estado("A")
            .build();

    public static List<Trayecto> TRAYECTOS = Arrays.asList(TRAYECTO_UNO, TRAYECTO_DOS);

    public static List<Trayecto> TRAYECTOS_VACIO = Arrays.asList();
}
