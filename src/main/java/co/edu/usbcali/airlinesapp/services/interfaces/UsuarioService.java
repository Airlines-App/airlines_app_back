package co.edu.usbcali.airlinesapp.services.interfaces;

import co.edu.usbcali.airlinesapp.dtos.UsuarioDTO;

import java.util.List;

public interface UsuarioService {
    List<UsuarioDTO> obtenerUsuarios();
    UsuarioDTO obtenerUsuarioPorId(Integer id) throws Exception;
    UsuarioDTO guardarUsuario(UsuarioDTO usuarioDTO) throws Exception;
}
