package co.edu.usbcali.airlinesapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@ToString
public class FacturaDTO {
    private Integer idFactura;

//    private Integer idUsuario;
    private Date fecha;
    private String estado;
}
