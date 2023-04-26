package co.edu.usbcali.airlinesapp.utility;

import co.edu.usbcali.airlinesapp.domain.Reserva;
import co.edu.usbcali.airlinesapp.dtos.ReservaDTO;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ReservaUtilityTest {
    public static Reserva RESERVA_UNO = Reserva.builder()
            .idReserva(1)
            .vuelo(VueloUtilityTest.VUELO_UNO)
            .asiento(AsientoUtilityTest.ASIENTO_UNO)
            .usuario(UsuarioUtilityTest.USUARIO_UNO)
            .precioTotal(100000)
            .estadoPago("A")
            .fecha(new Date())
            .estado("A")
            .build();

    public static Reserva RESERVA_DOS = Reserva.builder()
            .idReserva(2)
            .vuelo(VueloUtilityTest.VUELO_UNO)
            .asiento(AsientoUtilityTest.ASIENTO_UNO)
            .usuario(UsuarioUtilityTest.USUARIO_UNO)
            .precioTotal(150000)
            .estadoPago("A")
            .fecha(new Date())
            .estado("A")
            .build();

    public static ReservaDTO RESERVADTO = ReservaDTO.builder()
            .idReserva(1)
            .idVuelo(VueloUtilityTest.VUELO_UNO.getIdVuelo())
            .idAsiento(AsientoUtilityTest.ASIENTO_UNO.getIdAsiento())
            .idUsuario(UsuarioUtilityTest.USUARIO_UNO.getIdUsuario())
            .precioTotal(100000)
            .estadoPago("A")
            .fecha(new Date())
            .estado("A")
            .build();

    public static List<Reserva> RESERVAS = Arrays.asList(RESERVA_UNO, RESERVA_DOS);

    public static List<Reserva> RESERVAS_VACIO = Arrays.asList();
}
