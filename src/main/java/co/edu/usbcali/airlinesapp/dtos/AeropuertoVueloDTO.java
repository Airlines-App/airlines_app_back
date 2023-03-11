package co.edu.usbcali.airlinesapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class AeropuertoVueloDTO {

        private String idAeropuertoSalida;
        private String idAeropuertoLlegada;
        private String idVuelo;
}
