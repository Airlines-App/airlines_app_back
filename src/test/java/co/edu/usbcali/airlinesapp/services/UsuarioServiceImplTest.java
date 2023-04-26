package co.edu.usbcali.airlinesapp.services;

import co.edu.usbcali.airlinesapp.dtos.UsuarioDTO;
import co.edu.usbcali.airlinesapp.repository.RolUsuarioRepository;
import co.edu.usbcali.airlinesapp.repository.UsuarioRepository;
import co.edu.usbcali.airlinesapp.services.implementation.UsuarioServiceImpl;

import co.edu.usbcali.airlinesapp.utility.RolUsuarioUtilityTest;
import co.edu.usbcali.airlinesapp.utility.UsuarioUtilityTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class UsuarioServiceImplTest {
    @InjectMocks
    private UsuarioServiceImpl usuarioServiceImpl;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private RolUsuarioRepository rolUsuarioRepository;

    @Test
    public void guardarUsuarioOk() throws Exception {
        given(rolUsuarioRepository.existsById(RolUsuarioUtilityTest.ROLUSUARIO_UNO.getIdRolUsuario())).willReturn(true);
        given(rolUsuarioRepository.getReferenceById(RolUsuarioUtilityTest.ROLUSUARIO_UNO.getIdRolUsuario())).willReturn(RolUsuarioUtilityTest.ROLUSUARIO_UNO);
        given(usuarioRepository.existsById(UsuarioUtilityTest.USUARIO_UNO.getIdUsuario())).willReturn(false);
        given(usuarioRepository.save(UsuarioUtilityTest.USUARIO_UNO)).willReturn(UsuarioUtilityTest.USUARIO_UNO);

        UsuarioDTO usuarioSavedDTO = usuarioServiceImpl.guardarUsuario(UsuarioUtilityTest.USUARIODTO);

        assertEquals(UsuarioUtilityTest.USUARIO_UNO.getIdUsuario(), usuarioSavedDTO.getIdUsuario());
    }

    @Test
    public void guardarUsuarioNotOk() {
        given(usuarioRepository.existsById(UsuarioUtilityTest.USUARIO_UNO.getIdUsuario())).willReturn(true);

        assertThrows(java.lang.Exception.class, () -> usuarioServiceImpl.guardarUsuario(UsuarioUtilityTest.USUARIODTO));
    }

    @Test
    public void obtenerUsuariosOk() {
        given(usuarioRepository.findAll()).willReturn(UsuarioUtilityTest.USUARIOS);

        List<UsuarioDTO> usuariosSavedDTO = usuarioServiceImpl.obtenerUsuarios();

        assertEquals(2, usuariosSavedDTO.size());
        assertEquals("123456789", usuariosSavedDTO.get(0).getCedula());
    }

    @Test
    public void obtenerUsuariosNotOk() {
        given(usuarioRepository.findAll()).willReturn(UsuarioUtilityTest.USUARIOS_VACIO);

        List<UsuarioDTO> usuariosSavedDTO = usuarioServiceImpl.obtenerUsuarios();

        assertEquals(0, usuariosSavedDTO.size());
    }

    @Test
    public void obtenerUsuariosActivosOk() {
        given(usuarioRepository.findAllByEstado("A")).willReturn(UsuarioUtilityTest.USUARIOS);

        List<UsuarioDTO> usuariosSavedDTO = usuarioServiceImpl.obtenerUsuariosActivos();

        assertEquals(2, usuariosSavedDTO.size());
        assertEquals("123456789", usuariosSavedDTO.get(0).getCedula());
    }

    @Test
    public void obtenerUsuariosActivosNotOk() {
        given(usuarioRepository.findAllByEstado("A")).willReturn(UsuarioUtilityTest.USUARIOS_VACIO);

        List<UsuarioDTO> usuariosSavedDTO = usuarioServiceImpl.obtenerUsuariosActivos();

        assertEquals(0, usuariosSavedDTO.size());
    }

    @Test
    public void obtenerUsuarioPorIdOk() throws Exception {
        rolUsuarioRepository.save(RolUsuarioUtilityTest.ROLUSUARIO_UNO);
        usuarioRepository.save(UsuarioUtilityTest.USUARIO_UNO);

        given(usuarioRepository.existsById(UsuarioUtilityTest.USUARIO_UNO.getIdUsuario())).willReturn(true);
        given(usuarioRepository.getReferenceById(UsuarioUtilityTest.USUARIO_UNO.getIdUsuario())).willReturn(UsuarioUtilityTest.USUARIO_UNO);

        UsuarioDTO usuarioSavedDTO = usuarioServiceImpl.obtenerUsuarioPorId(UsuarioUtilityTest.USUARIO_UNO.getIdUsuario());

        assertEquals(UsuarioUtilityTest.USUARIO_UNO.getIdUsuario(), usuarioSavedDTO.getIdUsuario());
    }

    @Test
    public void obtenerUsuarioPorIdNotOk() {
        given(usuarioRepository.existsById(UsuarioUtilityTest.USUARIO_UNO.getIdUsuario())).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> usuarioServiceImpl.obtenerUsuarioPorId(UsuarioUtilityTest.USUARIO_UNO.getIdUsuario()));
    }

    @Test
    public void obtenerUsuarioPorCedulaOk() throws Exception {
        rolUsuarioRepository.save(RolUsuarioUtilityTest.ROLUSUARIO_UNO);
        usuarioRepository.save(UsuarioUtilityTest.USUARIO_UNO);

        given(usuarioRepository.existsByCedula(UsuarioUtilityTest.USUARIO_UNO.getCedula())).willReturn(true);
        given(usuarioRepository.getReferenceByCedula(UsuarioUtilityTest.USUARIO_UNO.getCedula())).willReturn(UsuarioUtilityTest.USUARIO_UNO);

        UsuarioDTO usuarioSavedDTO = usuarioServiceImpl.obtenerUsuarioPorCedula(UsuarioUtilityTest.USUARIO_UNO.getCedula());

        assertEquals(UsuarioUtilityTest.USUARIO_UNO.getCedula(), usuarioSavedDTO.getCedula());
    }

    @Test
    public void obtenerUsuarioPorCedulaNotOk() throws Exception {
        given(usuarioRepository.existsByCedula(UsuarioUtilityTest.USUARIO_UNO.getCedula())).willReturn(false);

        assertNull(usuarioServiceImpl.obtenerUsuarioPorCedula(UsuarioUtilityTest.USUARIO_UNO.getCedula()));
    }

    @Test
    public void actualizarUsuarioOk() throws Exception {
        given(rolUsuarioRepository.existsById(RolUsuarioUtilityTest.ROLUSUARIO_UNO.getIdRolUsuario())).willReturn(true);
        given(rolUsuarioRepository.getReferenceById(RolUsuarioUtilityTest.ROLUSUARIO_UNO.getIdRolUsuario())).willReturn(RolUsuarioUtilityTest.ROLUSUARIO_UNO);
        given(usuarioRepository.existsById(UsuarioUtilityTest.USUARIO_UNO.getIdUsuario())).willReturn(true);
        given(usuarioRepository.save(UsuarioUtilityTest.USUARIO_UNO)).willReturn(UsuarioUtilityTest.USUARIO_UNO);

        UsuarioDTO usuarioSavedDTO = usuarioServiceImpl.actualizarUsuario(UsuarioUtilityTest.USUARIODTO);

        assertEquals(UsuarioUtilityTest.USUARIO_UNO.getIdUsuario(), usuarioSavedDTO.getIdUsuario());
    }

    @Test
    public void actualizarUsuarioNotOk() {
        given(usuarioRepository.existsById(UsuarioUtilityTest.USUARIO_UNO.getIdUsuario())).willReturn(false);

        assertThrows(java.lang.Exception.class, () -> usuarioServiceImpl.actualizarUsuario(UsuarioUtilityTest.USUARIODTO));
    }
}
