package co.edu.usbcali.airlinesapp.services;

import co.edu.usbcali.airlinesapp.dtos.RolUsuarioDTO;
import co.edu.usbcali.airlinesapp.repository.RolUsuarioRepository;
import co.edu.usbcali.airlinesapp.services.implementation.RolUsuarioServiceImpl;

import co.edu.usbcali.airlinesapp.utility.RolUsuarioUtilityTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class RolUsuarioServiceImplTest {
    @InjectMocks
    private RolUsuarioServiceImpl rolUsuarioServiceImpl;

    @Mock
    private RolUsuarioRepository rolUsuarioRepository;

    @Test
    public void guardarRolUsuarioOk() throws Exception {
        given(rolUsuarioRepository.existsById(RolUsuarioUtilityTest.ROLUSUARIO_UNO.getIdRolUsuario())).willReturn(false);
        given(rolUsuarioRepository.save(RolUsuarioUtilityTest.ROLUSUARIO_UNO)).willReturn(RolUsuarioUtilityTest.ROLUSUARIO_UNO);

        RolUsuarioDTO rolUsuarioSavedDTO = rolUsuarioServiceImpl.guardarRolUsuario(RolUsuarioUtilityTest.ROLUSUARIODTO);

        assertEquals(RolUsuarioUtilityTest.ROLUSUARIO_UNO.getIdRolUsuario(), rolUsuarioSavedDTO.getIdRolUsuario());
    }

    @Test
    public void guardarRolUsuarioNotOk() {
        given(rolUsuarioRepository.existsById(RolUsuarioUtilityTest.ROLUSUARIO_UNO.getIdRolUsuario())).willReturn(true);

        assertThrows(java.lang.Exception.class, () -> rolUsuarioServiceImpl.guardarRolUsuario(RolUsuarioUtilityTest.ROLUSUARIODTO));
    }

    @Test
    public void obtenerRolUsuariosOk() {
        given(rolUsuarioRepository.findAll()).willReturn(RolUsuarioUtilityTest.ROLUSUARIOS);

        List<RolUsuarioDTO> rolUsuariosSavedDTO = rolUsuarioServiceImpl.obtenerRolUsuarios();

        assertEquals(2, rolUsuariosSavedDTO.size());
        assertEquals("Cliente", rolUsuariosSavedDTO.get(0).getDescripcion());
    }

    @Test
    public void obtenerRolUsuariosNotOk() {
        given(rolUsuarioRepository.findAll()).willReturn(RolUsuarioUtilityTest.ROLUSUARIOS_VACIO);

        List<RolUsuarioDTO> rolUsuariosSavedDTO = rolUsuarioServiceImpl.obtenerRolUsuarios();

        assertEquals(0, rolUsuariosSavedDTO.size());
    }

    @Test
    public void obtenerRolUsuariosActivosOk() {
        given(rolUsuarioRepository.findAllByEstado("A")).willReturn(RolUsuarioUtilityTest.ROLUSUARIOS);

        List<RolUsuarioDTO> rolUsuariosSavedDTO = rolUsuarioServiceImpl.obtenerRolUsuariosActivos();

        assertEquals(2, rolUsuariosSavedDTO.size());
        assertEquals("Cliente", rolUsuariosSavedDTO.get(0).getDescripcion());
    }

    @Test
    public void obtenerRolUsuariosActivosNotOk() {
        given(rolUsuarioRepository.findAllByEstado("A")).willReturn(RolUsuarioUtilityTest.ROLUSUARIOS_VACIO);

        List<RolUsuarioDTO> rolUsuariosSavedDTO = rolUsuarioServiceImpl.obtenerRolUsuariosActivos();

        assertEquals(0, rolUsuariosSavedDTO.size());
    }

    @Test
    public void obtenerRolUsuarioPorIdOk() throws Exception {
        rolUsuarioRepository.save(RolUsuarioUtilityTest.ROLUSUARIO_UNO);

        given(rolUsuarioRepository.existsById(RolUsuarioUtilityTest.ROLUSUARIO_UNO.getIdRolUsuario())).willReturn(true);
        given(rolUsuarioRepository.getReferenceById(RolUsuarioUtilityTest.ROLUSUARIO_UNO.getIdRolUsuario())).willReturn(RolUsuarioUtilityTest.ROLUSUARIO_UNO);

        RolUsuarioDTO rolUsuarioSavedDTO = rolUsuarioServiceImpl.obtenerRolUsuarioPorId(RolUsuarioUtilityTest.ROLUSUARIO_UNO.getIdRolUsuario());

        assertEquals(1, rolUsuarioSavedDTO.getIdRolUsuario());
    }

    @Test
    public void obtenerRolUsuarioPorIdNotOk() {
        given(rolUsuarioRepository.existsById(RolUsuarioUtilityTest.ROLUSUARIO_UNO.getIdRolUsuario())).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> rolUsuarioServiceImpl.obtenerRolUsuarioPorId(RolUsuarioUtilityTest.ROLUSUARIO_UNO.getIdRolUsuario()));
    }

    @Test
    public void actualizarRolUsuarioOk() throws Exception {
        given(rolUsuarioRepository.existsById(RolUsuarioUtilityTest.ROLUSUARIO_UNO.getIdRolUsuario())).willReturn(true);
        given(rolUsuarioRepository.save(RolUsuarioUtilityTest.ROLUSUARIO_UNO)).willReturn(RolUsuarioUtilityTest.ROLUSUARIO_UNO);

        RolUsuarioDTO rolUsuarioSavedDTO = rolUsuarioServiceImpl.actualizarRolUsuario(RolUsuarioUtilityTest.ROLUSUARIODTO);

        assertEquals(RolUsuarioUtilityTest.ROLUSUARIO_UNO.getIdRolUsuario(), rolUsuarioSavedDTO.getIdRolUsuario());
    }

    @Test
    public void actualizarRolUsuarioNotOk() {
        given(rolUsuarioRepository.existsById(RolUsuarioUtilityTest.ROLUSUARIO_UNO.getIdRolUsuario())).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> rolUsuarioServiceImpl.actualizarRolUsuario(RolUsuarioUtilityTest.ROLUSUARIODTO));
    }
}
