package co.edu.usbcali.airlinesapp.utility;

import co.edu.usbcali.airlinesapp.domain.TipoAsiento;

public class TipoAsientoUtilityTest {
    public static TipoAsiento TIPOASIENTO_UNO = TipoAsiento.builder()
            .idTipoAsiento(1)
            .descripcion("Ejecutivo")
            .estado("A")
            .build();
}
