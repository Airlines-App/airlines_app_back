package co.edu.usbcali.airlinesapp.services;

import co.edu.usbcali.airlinesapp.dtos.UsuarioDTO;

import java.util.List;

public interface UsuarioService {
    List<UsuarioDTO> obtenerUsuarios();
    UsuarioDTO guardarUsuario(UsuarioDTO usuarioDTO) throws Exception;
}
