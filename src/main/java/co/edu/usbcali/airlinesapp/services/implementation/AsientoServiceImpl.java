package co.edu.usbcali.airlinesapp.services.implementation;

import co.edu.usbcali.airlinesapp.domain.Asiento;
import co.edu.usbcali.airlinesapp.dtos.AsientoDTO;
import co.edu.usbcali.airlinesapp.mappers.AsientoMapper;
import co.edu.usbcali.airlinesapp.repository.AsientoRepository;
import co.edu.usbcali.airlinesapp.services.interfaces.AsientoService;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AsientoServiceImpl implements AsientoService {
    private final AsientoRepository asientoRepository;
    private final ModelMapper modelMapper;

    public AsientoServiceImpl(AsientoRepository asientoRepository, ModelMapper modelMapper) {
        this.asientoRepository = asientoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AsientoDTO> obtenerAsientos() {
        return AsientoMapper.domainToDTOList(asientoRepository.findAll());
    }

    @Override
    public AsientoDTO obtenerAsientoPorId(Integer id) throws Exception {
        if (asientoRepository.findById(id).isEmpty()) {
            throw new Exception("El asiento con id " + id + " no existe");
        }

        return AsientoMapper.domainToDTO(asientoRepository.findById(id).get());
    }

    @Override
    public AsientoDTO guardarAsiento(AsientoDTO asientoDTO) throws Exception {
        Asiento asiento = AsientoMapper.dtoToDomain(asientoDTO);

        if (asiento == null) {
            throw new Exception("El asiento no puede ser nulo");
        } if (asiento.getUbicacion() == null || asiento.getUbicacion().isBlank() || asiento.getUbicacion().trim().isEmpty()) {
            throw new Exception("La ubicación del asiento no puede ser nula o vacía");
        } if (asiento.getPrecio() < 0) {
            throw new Exception("El precio del asiento debe ser mayor a cero");
        } if (asiento.getEstado() == null || asiento.getEstado().isBlank() || asiento.getEstado().trim().isEmpty()) {
            throw new Exception("El estado del asiento no puede ser nulo o vacío");
        }

        return AsientoMapper.domainToDTO(asientoRepository.save(asiento));
    }
}
