package co.edu.usbcali.airlinesapp.controllers;

import co.edu.usbcali.airlinesapp.dtos.MensajeDTO;
import co.edu.usbcali.airlinesapp.dtos.ReservaDTO;
import co.edu.usbcali.airlinesapp.services.interfaces.ReservaService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reserva")
@Slf4j
public class ReservaController {
    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping("/obtener-reservas")
    public ResponseEntity<List<ReservaDTO>> obtenerReservas() {
        return new ResponseEntity(reservaService.obtenerReservas(), HttpStatus.OK);
    }

    @GetMapping(path = "/guardar-reserva",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity guardarReserva(@RequestBody ReservaDTO reservaDTO) {
        try {
            return new ResponseEntity(reservaService.guardarReserva(reservaDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
}
