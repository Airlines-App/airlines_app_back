package co.edu.usbcali.airlinesapp.services.implementation;

import co.edu.usbcali.airlinesapp.domain.Usuario;
import co.edu.usbcali.airlinesapp.dtos.UsuarioDTO;
import co.edu.usbcali.airlinesapp.mappers.UsuarioMapper;
import co.edu.usbcali.airlinesapp.repository.UsuarioRepository;
import co.edu.usbcali.airlinesapp.services.interfaces.UsuarioService;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UsuarioDTO> obtenerUsuarios() {
        return UsuarioMapper.domainToDTOList(usuarioRepository.findAll());
    }

    @Override
    public UsuarioDTO guardarUsuario(UsuarioDTO usuarioDTO) throws Exception {
        Usuario usuario = UsuarioMapper.dtoToDomain(usuarioDTO);

        if (usuario == null) {
            throw new Exception("El usuario no puede ser nulo");
        } if (usuario.getCedula() == null || usuario.getCedula().isBlank() || usuario.getNombre().trim().isEmpty()) {
            throw new Exception("La cédula del usuario no puede ser nula o vacía");
        } if (usuario.getNombre() == null || usuario.getNombre().isBlank() || usuario.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre del usuario no puede ser nulo o vacío");
        } if (usuario.getApellido() == null || usuario.getApellido().isBlank() || usuario.getApellido().trim().isEmpty()) {
            throw new Exception("El apellido del usuario no puede ser nulo o vacío");
        } if (usuario.getCorreo() == null || usuario.getCorreo().isBlank() || usuario.getCorreo().trim().isEmpty()) {
            throw new Exception("El correo del usuario no puede ser nulo o vacío");
        } if (usuario.getEstado() == null || usuario.getEstado().isBlank() || usuario.getEstado().trim().isEmpty()) {
            throw new Exception("El estado del usuario no puede ser nulo o vacío");
        }

        return UsuarioMapper.domainToDTO(usuarioRepository.save(usuario));
    }
}
