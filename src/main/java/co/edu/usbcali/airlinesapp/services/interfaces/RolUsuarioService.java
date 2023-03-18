package co.edu.usbcali.airlinesapp.services.interfaces;

import co.edu.usbcali.airlinesapp.dtos.RolUsuarioDTO;

import java.util.List;

public interface RolUsuarioService {
    List<RolUsuarioDTO> obtenerRolUsuarios();
    RolUsuarioDTO obtenerRolUsuarioPorId(Integer id) throws Exception;
    RolUsuarioDTO guardarRolUsuario(RolUsuarioDTO rolUsuarioDTO) throws Exception;
}
