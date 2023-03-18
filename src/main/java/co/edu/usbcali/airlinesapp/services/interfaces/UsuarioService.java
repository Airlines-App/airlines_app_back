package co.edu.usbcali.airlinesapp.services.interfaces;

import co.edu.usbcali.airlinesapp.dtos.UsuarioDTO;

import java.util.List;

public interface UsuarioService {
    UsuarioDTO guardarUsuario(UsuarioDTO usuarioDTO) throws Exception;
    List<UsuarioDTO> obtenerUsuarios();
    UsuarioDTO obtenerUsuarioPorId(Integer id) throws Exception;
    UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO) throws Exception;
    UsuarioDTO eliminarUsuario(Integer id) throws Exception;
}
