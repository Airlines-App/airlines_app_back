package co.edu.usbcali.airlinesapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class VueloDTO {
    private String idVuelo;
    private String idAvion;
    private long precio;
    private String origen;
    private String destino;
    private String horaSalida;
    private String horaLlegada;
    private long precioAsientoVip;
    private long precioAsientoNormal;
    private long precioAsientoBasico;
}
