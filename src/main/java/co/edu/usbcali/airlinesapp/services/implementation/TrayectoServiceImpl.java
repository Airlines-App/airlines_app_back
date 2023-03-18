package co.edu.usbcali.airlinesapp.services.implementation;

import co.edu.usbcali.airlinesapp.domain.Trayecto;
import co.edu.usbcali.airlinesapp.dtos.TipoAsientoDTO;
import co.edu.usbcali.airlinesapp.dtos.TrayectoDTO;
import co.edu.usbcali.airlinesapp.mappers.TrayectoMapper;
import co.edu.usbcali.airlinesapp.repository.TrayectoRepository;
import co.edu.usbcali.airlinesapp.services.interfaces.TrayectoService;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TrayectoServiceImpl implements TrayectoService {
    private final TrayectoRepository trayectoRepository;
    private final ModelMapper modelMapper;

    public TrayectoServiceImpl(TrayectoRepository trayectoRepository, ModelMapper modelMapper) {
        this.trayectoRepository = trayectoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TrayectoDTO guardarTrayecto(TrayectoDTO trayectoDTO) throws Exception {
        Trayecto trayecto = TrayectoMapper.dtoToDomain(trayectoDTO);

        if (trayecto == null) {
            throw new Exception("El trayecto no puede ser nulo");
        } if (trayecto.getHoraSalida() == null) {
            throw new Exception("La hora de salida del trayecto no puede ser nula");
        } if (trayecto.getHoraLlegada() == null) {
            throw new Exception("La hora de llegada del trayecto no puede ser nula");
        } if (trayecto.getEstado() == null || trayecto.getEstado().isBlank() || trayecto.getEstado().trim().isEmpty()) {
            throw new Exception("El estado del trayecto no puede ser nulo o vacío");
        }

        return TrayectoMapper.domainToDTO(trayectoRepository.save(trayecto));
    }

    @Override
    public List<TrayectoDTO> obtenerTrayectos() {
        return TrayectoMapper.domainToDTOList(trayectoRepository.findAll());
    }

    @Override
    public TrayectoDTO obtenerTrayectoPorId(Integer id) throws Exception {
        if (trayectoRepository.findById(id).isEmpty()) {
            throw new Exception("El trayecto con id " + id + " no existe");
        }

        return TrayectoMapper.domainToDTO(trayectoRepository.findById(id).get());
    }

    @Override
    public TrayectoDTO actualizarTrayecto(TrayectoDTO trayectoDTO) throws Exception {
        TrayectoDTO trayectoSavedDTO = obtenerTrayectoPorId(trayectoDTO.getIdTrayecto());

        if (trayectoSavedDTO == null) {
            throw new Exception("El trayecto no existe");
        }

        trayectoSavedDTO.setHoraSalida(trayectoDTO.getHoraSalida());
        trayectoSavedDTO.setHoraLlegada(trayectoDTO.getHoraLlegada());
        trayectoSavedDTO.setEstado(trayectoDTO.getEstado());

        return guardarTrayecto(trayectoSavedDTO);
    }

    @Override
    public TrayectoDTO eliminarTrayecto(Integer id) throws Exception {
        TrayectoDTO trayectoSavedDTO = obtenerTrayectoPorId(id);

        if (trayectoSavedDTO == null) {
            throw new Exception("El trayecto no existe");
        }

        trayectoSavedDTO.setEstado("I");

        return guardarTrayecto(trayectoSavedDTO);
    }
}
