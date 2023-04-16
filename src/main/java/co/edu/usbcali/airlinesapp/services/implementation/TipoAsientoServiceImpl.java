package co.edu.usbcali.airlinesapp.services.implementation;

import co.edu.usbcali.airlinesapp.domain.TipoAsiento;
import co.edu.usbcali.airlinesapp.dtos.TipoAsientoDTO;
import co.edu.usbcali.airlinesapp.mappers.TipoAsientoMapper;
import co.edu.usbcali.airlinesapp.repository.TipoAsientoRepository;
import co.edu.usbcali.airlinesapp.services.interfaces.TipoAsientoService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TipoAsientoServiceImpl implements TipoAsientoService {
    private final TipoAsientoRepository tipoAsientoService;

    public TipoAsientoServiceImpl(TipoAsientoRepository tipoAsientoService) {
        this.tipoAsientoService = tipoAsientoService;
    }

    @Override
    public TipoAsientoDTO guardarTipoAsiento(TipoAsientoDTO tipoAsientoDTO) throws Exception {
        TipoAsiento tipoAsiento = TipoAsientoMapper.dtoToDomain(tipoAsientoDTO);

        if (tipoAsiento == null) {
            throw new Exception("El tipo de asiento no puede ser nulo");
        } if (tipoAsiento.getDescripcion() == null || tipoAsiento.getDescripcion().isBlank() || tipoAsiento.getDescripcion().trim().isEmpty()) {
            throw new Exception("La descripción del tipo de asiento no puede ser nula o vacía");
        } if (tipoAsiento.getEstado() == null || tipoAsiento.getEstado().isBlank() || tipoAsiento.getEstado().trim().isEmpty()) {
            throw new Exception("El estado del tipo de asiento no puede ser nulo o vacío");
        }

        return TipoAsientoMapper.domainToDTO(tipoAsientoService.save(tipoAsiento));
    }

    @Override
    public List<TipoAsientoDTO> obtenerTipoAsientos() {
        return TipoAsientoMapper.domainToDTOList(tipoAsientoService.findAll());
    }

    @Override
    public List<TipoAsientoDTO> obtenerTipoAsientosActivos() {
        return TipoAsientoMapper.domainToDTOList(tipoAsientoService.findAllByEstado("A"));
    }

    @Override
    public TipoAsientoDTO obtenerTipoAsientoPorId(Integer id) throws Exception {
        if (!tipoAsientoService.existsById(id)) {
            throw new Exception("El tipo de asiento con id " + id + " no existe");
        }

        return TipoAsientoMapper.domainToDTO(tipoAsientoService.getReferenceById(id));

    }

    @Override
    public TipoAsientoDTO actualizarTipoAsiento(TipoAsientoDTO tipoAsientoDTO) throws Exception {
        TipoAsientoDTO tipoAsientoSavedDTO = obtenerTipoAsientoPorId(tipoAsientoDTO.getIdTipoAsiento());

        if (tipoAsientoSavedDTO == null) {
            throw new Exception("El tipo de asiento no existe");
        }

        tipoAsientoSavedDTO.setDescripcion(tipoAsientoDTO.getDescripcion());
        tipoAsientoSavedDTO.setEstado(tipoAsientoDTO.getEstado());

        return guardarTipoAsiento(tipoAsientoSavedDTO);
    }

    @Override
    public TipoAsientoDTO eliminarTipoAsiento(Integer id) throws Exception {
        TipoAsientoDTO tipoAsientoSavedDTO = obtenerTipoAsientoPorId(id);

        if (tipoAsientoSavedDTO == null) {
            throw new Exception("El tipo de asiento no existe");
        }

        tipoAsientoSavedDTO.setEstado("I");

        return guardarTipoAsiento(tipoAsientoSavedDTO);
    }
}
