package co.edu.usbcali.airlinesapp.controllers;

import co.edu.usbcali.airlinesapp.services.ReservaUsuarioService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reserva-usuario")
@Slf4j
public class ReservaUsuarioController {
    private final ReservaUsuarioService reservaUsuarioService;

    public ReservaUsuarioController(ReservaUsuarioService reservaUsuarioService) {
        this.reservaUsuarioService = reservaUsuarioService;
    }
}
