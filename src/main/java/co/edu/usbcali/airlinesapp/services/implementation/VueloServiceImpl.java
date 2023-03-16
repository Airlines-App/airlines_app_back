package co.edu.usbcali.airlinesapp.services.implementation;

import co.edu.usbcali.airlinesapp.domain.Vuelo;
import co.edu.usbcali.airlinesapp.dtos.VueloDTO;
import co.edu.usbcali.airlinesapp.mappers.AsientoMapper;
import co.edu.usbcali.airlinesapp.mappers.VueloMapper;
import co.edu.usbcali.airlinesapp.repository.VueloRepository;
import co.edu.usbcali.airlinesapp.services.interfaces.VueloService;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class VueloServiceImpl implements VueloService {
    private final VueloRepository vueloRepository;
    private final ModelMapper modelMapper;

    public VueloServiceImpl(VueloRepository vueloRepository, ModelMapper modelMapper) {
        this.vueloRepository = vueloRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<VueloDTO> obtenerVuelos() {
        return VueloMapper.domainToDTOList(vueloRepository.findAll());
    }

    @Override
    public VueloDTO obtenerVueloPorId(Integer id) throws Exception {
        if (vueloRepository.findById(id).isPresent()) {
            return VueloMapper.domainToDTO(vueloRepository.findById(id).get());
        } else {
            throw new Exception("El vuelo con id " + id + " no existe");
        }
    }

    @Override
    public VueloDTO guardarVuelo(VueloDTO vueloDTO) throws Exception {
        Vuelo vuelo = VueloMapper.dtoToDomain(vueloDTO);

        if (vuelo == null) {
            throw new Exception("El vuelo no puede ser nulo");
        } if (vuelo.getPrecio() < 0) {
            throw new Exception("El precio del vuelo no puede ser menor a cero");
        } if (vuelo.getHoraSalida() == null) {
            throw new Exception("La hora de salida del vuelo no puede ser nula");
        } if (vuelo.getHoraLlegada() == null) {
            throw new Exception("La hora de llegada del vuelo no puede ser nula");
        } if (vuelo.getPrecioAsientoVip() < 0) {
            throw new Exception("El precio del asiento vip no puede ser menor a cero");
        } if (vuelo.getPrecioAsientoNormal() < 0) {
            throw new Exception("El precio del asiento normal no puede ser menor a cero");
        } if (vuelo.getPrecioAsientoBasico() < 0) {
            throw new Exception("El precio del asiento básico no puede ser menor a cero");
        } if (vuelo.getEstado() == null || vuelo.getEstado().isBlank() || vuelo.getEstado().trim().isEmpty()) {
            throw new Exception("El estado del vuelo no puede ser nulo o vacío");
        }

        return VueloMapper.domainToDTO(vueloRepository.save(vuelo));
    }
}
