package co.edu.usbcali.airlinesapp.utility;

import co.edu.usbcali.airlinesapp.domain.TipoAsiento;
import co.edu.usbcali.airlinesapp.dtos.TipoAsientoDTO;

import java.util.Arrays;
import java.util.List;

public class TipoAsientoUtilityTest {
    public static TipoAsiento TIPOASIENTO_UNO = TipoAsiento.builder()
            .idTipoAsiento(1)
            .descripcion("Ejecutivo")
            .estado("A")
            .build();

    public static TipoAsiento TIPOASIENTO_DOS = TipoAsiento.builder()
            .idTipoAsiento(2)
            .descripcion("Económico")
            .estado("A")
            .build();

    public static TipoAsientoDTO TIPOASIENTODTO = TipoAsientoDTO.builder()
            .idTipoAsiento(1)
            .descripcion("Ejecutivo")
            .estado("A")
            .build();

    public static List<TipoAsiento> TIPOASIENTOS = Arrays.asList(TIPOASIENTO_UNO, TIPOASIENTO_DOS);

    public static List<TipoAsiento> TIPOASIENTOS_VACIO = Arrays.asList();
}
