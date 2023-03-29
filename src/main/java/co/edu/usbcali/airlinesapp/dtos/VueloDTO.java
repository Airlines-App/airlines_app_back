package co.edu.usbcali.airlinesapp.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class VueloDTO {
    private Integer idVuelo;
    private Integer idAeropuertoOrigen;
    private Integer idAeropuertoDestino;
    private long precio;
    private LocalDateTime horaSalida;
    private LocalDateTime horaLlegada;
    private long precioAsientoVip;
    private long precioAsientoNormal;
    private long precioAsientoBasico;
    private String estado;
}
