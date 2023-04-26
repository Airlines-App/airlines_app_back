package co.edu.usbcali.airlinesapp.utility;

import co.edu.usbcali.airlinesapp.domain.Factura;
import co.edu.usbcali.airlinesapp.dtos.FacturaDTO;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FacturaUtilityTest {
    public static Factura FACTURA_UNO = Factura.builder()
            .idFactura(1)
            .reserva(ReservaUtilityTest.RESERVA_UNO)
            .fecha(new Date())
            .estado("A")
            .build();

    public static Factura FACTURA_DOS = Factura.builder()
            .idFactura(2)
            .reserva(ReservaUtilityTest.RESERVA_UNO)
            .fecha(new Date())
            .estado("A")
            .build();

    public static FacturaDTO FACTURADTO = FacturaDTO.builder()
            .idFactura(1)
            .idReserva(ReservaUtilityTest.RESERVA_UNO.getIdReserva())
            .fecha(new Date())
            .estado("A")
            .build();

    public static List<Factura> FACTURAS = Arrays.asList(FACTURA_UNO, FACTURA_DOS);

    public static List<Factura> FACTURAS_VACIO = Arrays.asList();
}
