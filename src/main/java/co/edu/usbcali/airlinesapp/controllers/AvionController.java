package co.edu.usbcali.airlinesapp.controllers;

import co.edu.usbcali.airlinesapp.dtos.AvionDTO;
import co.edu.usbcali.airlinesapp.dtos.MensajeDTO;
import co.edu.usbcali.airlinesapp.services.interfaces.AvionService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avion")
@Slf4j
public class AvionController {
    private final AvionService avionService;

    public AvionController(AvionService avionService) {
        this.avionService = avionService;
    }

    @GetMapping("/obtener-aviones")
    public ResponseEntity<List<AvionDTO>> obtenerAviones() {
        return new ResponseEntity(avionService.obtenerAviones(), HttpStatus.OK);
    }

    @GetMapping("/obtener-avion/{idAvion}")
    public ResponseEntity<AvionDTO> obtenerAvion(@PathVariable("idAvion") Integer idAvion) {
        try {
            return new ResponseEntity(avionService.obtenerAvionPorId(idAvion), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/guardar-avion",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity guardarAvion(@RequestBody AvionDTO avionDTO) {
        try {
            return new ResponseEntity(avionService.guardarAvion(avionDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
}
