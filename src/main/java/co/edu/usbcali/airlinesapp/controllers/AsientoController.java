package co.edu.usbcali.airlinesapp.controllers;

import co.edu.usbcali.airlinesapp.services.AsientoService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/asiento")
@Slf4j
public class AsientoController {
    private final AsientoService asientoService;

    public AsientoController(AsientoService asientoService) {
        this.asientoService = asientoService;
    }
}
