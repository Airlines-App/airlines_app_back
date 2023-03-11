package co.edu.usbcali.airlinesapp.controllers;

import co.edu.usbcali.airlinesapp.services.AeropuertoVueloService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aeropuerto-vuelo")
@Slf4j
public class AeropuertoVueloController {
    private final AeropuertoVueloService aeropuertoVueloService;

    public AeropuertoVueloController(AeropuertoVueloService aeropuertoVueloService) {
        this.aeropuertoVueloService = aeropuertoVueloService;
    }
}
