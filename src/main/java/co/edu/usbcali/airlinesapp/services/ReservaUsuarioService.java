package co.edu.usbcali.airlinesapp.services;

import co.edu.usbcali.airlinesapp.dtos.ReservaUsuarioDTO;

import java.util.List;

public interface ReservaUsuarioService {
    List<ReservaUsuarioDTO> obtenerReservaUsuarios();
    ReservaUsuarioDTO guardarReservaUsuario(ReservaUsuarioDTO reservaUsuarioDTO) throws Exception;
}
