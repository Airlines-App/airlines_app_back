package co.edu.usbcali.airlinesapp.services.implementation;

import co.edu.usbcali.airlinesapp.domain.Aeropuerto;
import co.edu.usbcali.airlinesapp.dtos.AeropuertoDTO;
import co.edu.usbcali.airlinesapp.mappers.AeropuertoMapper;
import co.edu.usbcali.airlinesapp.repository.AeropuertoRepository;
import co.edu.usbcali.airlinesapp.services.interfaces.AeropuertoService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AeropuertoServiceImpl implements AeropuertoService {
    private final AeropuertoRepository aeropuertoRepository;

    public AeropuertoServiceImpl(AeropuertoRepository aeropuertoRepository) {
        this.aeropuertoRepository = aeropuertoRepository;
    }

    @Override
    public List<AeropuertoDTO> obtenerAeropuertos() {
        return AeropuertoMapper.domainToDTOList(aeropuertoRepository.findAll());
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
