package co.edu.usbcali.airlinesapp.services.implementation;

import co.edu.usbcali.airlinesapp.domain.Avion;
import co.edu.usbcali.airlinesapp.dtos.AvionDTO;
import co.edu.usbcali.airlinesapp.mappers.AvionMapper;
import co.edu.usbcali.airlinesapp.repository.AvionRepository;
import co.edu.usbcali.airlinesapp.services.interfaces.AvionService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AvionServiceImpl implements AvionService {
    private final AvionRepository avionRepository;

    public AvionServiceImpl(AvionRepository avionRepository) {
        this.avionRepository = avionRepository;
    }

    @Override
    public AvionDTO guardarAvion(AvionDTO avionDTO) throws Exception {
        Avion avion = AvionMapper.dtoToDomain(avionDTO);

        if (avion == null) {
            throw new Exception("El avión no puede ser nulo");
        } if (avion.getModelo() == null || avion.getModelo().isBlank() || avion.getModelo().trim().isEmpty()) {
            throw new Exception("El modelo del avión no puede ser nulo o vacío");
        } if (avion.getAerolinea() == null || avion.getAerolinea().isBlank() || avion.getAerolinea().trim().isEmpty()) {
            throw new Exception("La aerolínea del avíón no puede ser nula o vacía");
        } if (avion.getEstado() == null || avion.getEstado().isBlank() || avion.getEstado().trim().isEmpty()) {
            throw new Exception("El estado del avión no puede ser nulo o vacío");
        }

        return AvionMapper.domainToDTO(avionRepository.save(avion));
    }

    @Override
    public List<AvionDTO> obtenerAviones() {
        return AvionMapper.domainToDTOList(avionRepository.findAll());
    }

    @Override
    public List<AvionDTO> obtenerAvionesActivos() {
        return AvionMapper.domainToDTOList(avionRepository.findAllByEstado("A"));
    }

    @Override
    public AvionDTO obtenerAvionPorId(Integer id) throws Exception {
        if (!avionRepository.existsById(id)) {
            throw new Exception("El avión con id " + id + " no existe");
        }

        return AvionMapper.domainToDTO(avionRepository.getReferenceById(id));
    }

    @Override
    public AvionDTO actualizarAvion(AvionDTO avionDTO) throws Exception {
        AvionDTO avionSavedDTO = obtenerAvionPorId(avionDTO.getIdAvion());

        if (avionSavedDTO == null) {
            throw new Exception("El avión no existe");
        }

        avionSavedDTO.setModelo(avionDTO.getModelo());
        avionSavedDTO.setAerolinea(avionDTO.getAerolinea());
        avionSavedDTO.setEstado(avionDTO.getEstado());

        return guardarAvion(avionSavedDTO);
    }

    @Override
    public AvionDTO eliminarAvion(Integer id) throws Exception {
        AvionDTO avionSavedDTO = obtenerAvionPorId(id);

        if (avionSavedDTO == null) {
            throw new Exception("El avión no existe");
        }

        avionSavedDTO.setEstado("I");

        return guardarAvion(avionSavedDTO);
    }
}
