package co.edu.usbcali.airlinesapp.services.implementation;

import co.edu.usbcali.airlinesapp.domain.RolUsuario;
import co.edu.usbcali.airlinesapp.dtos.FacturaDTO;
import co.edu.usbcali.airlinesapp.dtos.RolUsuarioDTO;
import co.edu.usbcali.airlinesapp.mappers.RolUsuarioMapper;
import co.edu.usbcali.airlinesapp.repository.RolUsuarioRepository;
import co.edu.usbcali.airlinesapp.services.interfaces.RolUsuarioService;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RolUsuarioServiceImpl implements RolUsuarioService {
    private final RolUsuarioRepository rolUsuarioRepository;
    private final ModelMapper modelMapper;

    public RolUsuarioServiceImpl(RolUsuarioRepository rolUsuarioRepository, ModelMapper modelMapper) {
        this.rolUsuarioRepository = rolUsuarioRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RolUsuarioDTO guardarRolUsuario(RolUsuarioDTO rolUsuarioDTO) throws Exception {
        RolUsuario rolUsuario = RolUsuarioMapper.dtoToDomain(rolUsuarioDTO);

        if (rolUsuario == null) {
            throw new Exception("El rol de usuario no puede ser nulo");
        } if (rolUsuario.getDescripcion() == null || rolUsuario.getDescripcion().isBlank() || rolUsuario.getDescripcion().trim().isEmpty()) {
            throw new Exception("La descripción del rol de usuario no puede ser nula o vacía");
        } if (rolUsuario.getEstado() == null || rolUsuario.getEstado().isBlank() || rolUsuario.getEstado().trim().isEmpty()) {
            throw new Exception("El estado del rol de usuario no puede ser nulo o vacío");
        }

        return RolUsuarioMapper.domainToDTO(rolUsuarioRepository.save(rolUsuario));
    }

    @Override
    public List<RolUsuarioDTO> obtenerRolUsuarios() {
        return RolUsuarioMapper.domainToDTOList(rolUsuarioRepository.findAll());
    }

    @Override
    public RolUsuarioDTO obtenerRolUsuarioPorId(Integer id) throws Exception {
        if (rolUsuarioRepository.findById(id).isEmpty()) {
            throw new Exception("El rol de usuario con id " + id + " no existe");
        }

        return RolUsuarioMapper.domainToDTO(rolUsuarioRepository.findById(id).get());
    }

    @Override
    public RolUsuarioDTO actualizarRolUsuario(RolUsuarioDTO rolUsuarioDTO) throws Exception {
        RolUsuarioDTO rolUsuarioSavedDTO = obtenerRolUsuarioPorId(rolUsuarioDTO.getIdRolUsuario());

        if (rolUsuarioSavedDTO == null) {
            throw new Exception("El rol de usuario no existe");
        }

        rolUsuarioSavedDTO.setDescripcion(rolUsuarioDTO.getDescripcion());
        rolUsuarioSavedDTO.setEstado(rolUsuarioDTO.getEstado());

        return guardarRolUsuario(rolUsuarioSavedDTO);
    }

    @Override
    public RolUsuarioDTO eliminarRolUsuario(Integer id) throws Exception {
        RolUsuarioDTO rolUsuarioSavedDTO = obtenerRolUsuarioPorId(id);

        if (rolUsuarioSavedDTO == null) {
            throw new Exception("El rol de usuario no existe");
        }

        rolUsuarioSavedDTO.setEstado("I");

        return guardarRolUsuario(rolUsuarioSavedDTO);
    }
}
