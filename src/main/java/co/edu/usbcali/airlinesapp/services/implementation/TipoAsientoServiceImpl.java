package co.edu.usbcali.airlinesapp.services.implementation;

import co.edu.usbcali.airlinesapp.domain.TipoAsiento;
import co.edu.usbcali.airlinesapp.dtos.TipoAsientoDTO;
import co.edu.usbcali.airlinesapp.mappers.TipoAsientoMapper;
import co.edu.usbcali.airlinesapp.repository.TipoAsientoRepository;
import co.edu.usbcali.airlinesapp.services.interfaces.TipoAsientoService;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TipoAsientoServiceImpl implements TipoAsientoService {
    private final TipoAsientoRepository tipoAsientoService;
    private final ModelMapper modelMapper;

    public TipoAsientoServiceImpl(TipoAsientoRepository tipoAsientoService, ModelMapper modelMapper) {
        this.tipoAsientoService = tipoAsientoService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TipoAsientoDTO> obtenerTipoAsientos() {
        return TipoAsientoMapper.domainToDTOList(tipoAsientoService.findAll());
    }

    @Override
    public TipoAsientoDTO obtenerTipoAsientoPorId(Integer id) throws Exception {
        if (tipoAsientoService.findById(id).isEmpty()) {
            throw new Exception("El tipo de asiento con id " + id + " no existe");
        }

        return TipoAsientoMapper.domainToDTO(tipoAsientoService.findById(id).get());

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
}
