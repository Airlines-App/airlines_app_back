package co.edu.usbcali.airlinesapp.utility;

import co.edu.usbcali.airlinesapp.domain.Factura;
import co.edu.usbcali.airlinesapp.dtos.FacturaDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FacturaUtilityTest {
    public static Integer ID_UNO = 1;
    public static Date FECHA_UNO = new Date();
    public static String ESTADO_UNO = "A";
    public static Integer FACTURAS_SIZE = 2;
    public static Integer FACTURAS_VACIO_SIZE = 0;

    public static String FECHA_FUTURO = "2023-11-27 08:00";
    public static String PATTERN_FECHA = "yyyy-MM-dd HH:mm";
    public static Date FECHA_FUTURO_DATE;

    static {
        try {
            FECHA_FUTURO_DATE = new SimpleDateFormat(PATTERN_FECHA).parse(FECHA_FUTURO);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Factura FACTURA_UNO = Factura.builder()
            .idFactura(1)
            .reserva(ReservaUtilityTest.RESERVA_UNO)
            .fecha(FECHA_FUTURO_DATE)
            .estado("A")
            .build();

    public static Factura FACTURA_DOS = Factura.builder()
            .idFactura(2)
            .reserva(ReservaUtilityTest.RESERVA_UNO)
            .fecha(FECHA_FUTURO_DATE)
            .estado("A")
            .build();

    public static FacturaDTO FACTURADTO = FacturaDTO.builder()
            .idFactura(1)
            .idReserva(ReservaUtilityTest.ID_UNO)
            .fecha(FECHA_FUTURO_DATE)
            .estado("A")
            .build();

    public static List<Factura> FACTURAS = Arrays.asList(FACTURA_UNO, FACTURA_DOS);

    public static List<Factura> FACTURAS_VACIO = Arrays.asList();
}
