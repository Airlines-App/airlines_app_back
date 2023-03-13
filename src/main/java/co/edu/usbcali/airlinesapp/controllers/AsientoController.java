package co.edu.usbcali.airlinesapp.controllers;

import co.edu.usbcali.airlinesapp.dtos.AsientoDTO;
import co.edu.usbcali.airlinesapp.dtos.MensajeDTO;
import co.edu.usbcali.airlinesapp.services.interfaces.AsientoService;

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
@RequestMapping("/asiento")
@Slf4j
public class AsientoController {
    private final AsientoService asientoService;

    public AsientoController(AsientoService asientoService) {
        this.asientoService = asientoService;
    }

    @GetMapping("/obtener-asientos")
    public ResponseEntity<List<AsientoDTO>> obtenerAsientos() {
        return new ResponseEntity(asientoService.obtenerAsientos(), HttpStatus.OK);
    }

    @GetMapping(path = "/guardar-asiento",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity guardarAsiento(@RequestBody AsientoDTO asientoDTO) {
        try {
            return new ResponseEntity(asientoService.guardarAsiento(asientoDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
}
