package co.edu.usbcali.airlinesapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class ReservaDTO {
        private String idReserva;
        private String idVuelo;
        private long precioTotal;
        private boolean estadoPago;
}
