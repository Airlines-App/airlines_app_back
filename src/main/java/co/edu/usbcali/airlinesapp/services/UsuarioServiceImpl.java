package co.edu.usbcali.airlinesapp.services;

import co.edu.usbcali.airlinesapp.dtos.UsuarioDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {
    @Override
    public List<UsuarioDTO> obtenerUsuarios() {
        return null;
    }

    @Override
    public UsuarioDTO guardarUsuario(UsuarioDTO usuarioDTO) throws Exception {
        return null;
    }
}
