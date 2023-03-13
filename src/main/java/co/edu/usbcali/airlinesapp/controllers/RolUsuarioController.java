package co.edu.usbcali.airlinesapp.controllers;

import co.edu.usbcali.airlinesapp.dtos.MensajeDTO;
import co.edu.usbcali.airlinesapp.dtos.RolUsuarioDTO;
import co.edu.usbcali.airlinesapp.services.interfaces.RolUsuarioService;

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
@RequestMapping("/rolUsuario")
@Slf4j
public class RolUsuarioController {
    private final RolUsuarioService rolUsuarioService;

    public RolUsuarioController(RolUsuarioService rolUsuarioService) {
        this.rolUsuarioService = rolUsuarioService;
    }

    @GetMapping("/obtener-rolUsuarios")
    public ResponseEntity<List<RolUsuarioDTO>> obtenerRolUsuarios() {
        return new ResponseEntity(rolUsuarioService.obtenerRolUsuarios(), HttpStatus.OK);
    }

    @GetMapping(path = "/guardar-rolUsuario",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity guardarRolUsuario(@RequestBody RolUsuarioDTO rolUsuarioDTO) {
        try {
            return new ResponseEntity(rolUsuarioService.guardarRolUsuario(rolUsuarioDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
}
