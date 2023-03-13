package co.edu.usbcali.airlinesapp.controllers;

import co.edu.usbcali.airlinesapp.dtos.MensajeDTO;
import co.edu.usbcali.airlinesapp.dtos.TrayectoDTO;
import co.edu.usbcali.airlinesapp.services.interfaces.TrayectoService;

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
@RequestMapping("/trayecto")
@Slf4j
public class TrayectoController {
    private final TrayectoService trayectoService;

    public TrayectoController(TrayectoService trayectoService) {
        this.trayectoService = trayectoService;
    }

    @GetMapping("/obtener-trayectos")
    public ResponseEntity<List<TrayectoDTO>> obtenerTrayectos() {
        return new ResponseEntity(trayectoService.obtenerTrayectos(), HttpStatus.OK);
    }

    @GetMapping(path = "/guardar-trayecto",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity guardarTrayecto(@RequestBody TrayectoDTO trayectoDTO) {
        try {
            return new ResponseEntity(trayectoService.guardarTrayecto(trayectoDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
}
