package co.edu.usbcali.airlinesapp.services.implementation;

import co.edu.usbcali.airlinesapp.domain.Aeropuerto;
import co.edu.usbcali.airlinesapp.dtos.AeropuertoDTO;
import co.edu.usbcali.airlinesapp.mappers.AeropuertoMapper;
import co.edu.usbcali.airlinesapp.repository.AeropuertoRepository;
import co.edu.usbcali.airlinesapp.services.interfaces.AeropuertoService;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Service
@Slf4j
public class AeropuertoServiceImpl implements AeropuertoService {
    private final AeropuertoRepository aeropuertoRepository;
    private final ModelMapper modelMapper;

    public AeropuertoServiceImpl(AeropuertoRepository aeropuertoRepository, ModelMapper modelMapper) {
        this.aeropuertoRepository = aeropuertoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AeropuertoDTO> obtenerAeropuertos() {
        return AeropuertoMapper.domainToDTOList(aeropuertoRepository.findAll());
    }

    @Override
    public AeropuertoDTO obtenerAeropuertoPorId(Integer id) throws Exception {
        if (aeropuertoRepository.findById(id).isPresent()) {
            return AeropuertoMapper.domainToDTO(aeropuertoRepository.findById(id).get());
        } else {
            throw new Exception("El aeropuerto con id " + id + " no existe");
        }
    }

    @Override
    public AeropuertoDTO guardarAeropuerto(AeropuertoDTO aeropuertoDTO) throws Exception {
        Aeropuerto aeropuerto = AeropuertoMapper.dtoToDomain(aeropuertoDTO);

        if (aeropuerto == null) {
            throw new Exception("El aeropuerto no puede ser nulo");
        } if (aeropuerto.getNombre() == null || aeropuerto.getNombre().isBlank() || aeropuerto.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre del aeropuerto no puede ser nulo  o vacío");
        } if (aeropuerto.getIata() == null || aeropuerto.getIata().isBlank() || aeropuerto.getIata().trim().isEmpty()) {
            throw new Exception("El IATA del aeropuerto no puede ser nulo o vacío");
        } if (aeropuerto.getUbicacion() == null || aeropuerto.getUbicacion().isBlank() || aeropuerto.getUbicacion().trim().isEmpty()) {
            throw new Exception("La ubicación del aeropuerto no puede ser nula o vacía");
        } if (aeropuerto.getEstado() == null || aeropuerto.getEstado().isBlank() || aeropuerto.getEstado().trim().isEmpty()) {
            throw new Exception("El estado del aeropuerto no puede ser nulo o vacío");
        }

        return AeropuertoMapper.domainToDTO(aeropuertoRepository.save(aeropuerto));
    }
}
