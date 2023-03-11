package co.edu.usbcali.airlinesapp.controllers;

import co.edu.usbcali.airlinesapp.services.AvionService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avion")
@Slf4j
public class AvionController {
    private final AvionService avionService;

    public AvionController(AvionService avionService) {
        this.avionService = avionService;
    }
}
