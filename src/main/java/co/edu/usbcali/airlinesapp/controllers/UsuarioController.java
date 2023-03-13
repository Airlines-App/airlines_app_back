package co.edu.usbcali.airlinesapp.controllers;

import co.edu.usbcali.airlinesapp.dtos.MensajeDTO;
import co.edu.usbcali.airlinesapp.dtos.UsuarioDTO;
import co.edu.usbcali.airlinesapp.services.interfaces.UsuarioService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@Slf4j
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/obtener-usuarios")
    public ResponseEntity<List<UsuarioDTO>> obtenerUsuarios() {
        return new ResponseEntity(usuarioService.obtenerUsuarios(), HttpStatus.OK);
    }

    @PostMapping(path = "/guardar-usuario",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity guardarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            return new ResponseEntity(usuarioService.guardarUsuario(usuarioDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
}
