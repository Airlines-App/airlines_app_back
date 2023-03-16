package co.edu.usbcali.airlinesapp.services.implementation;

import co.edu.usbcali.airlinesapp.domain.Factura;
import co.edu.usbcali.airlinesapp.dtos.FacturaDTO;
import co.edu.usbcali.airlinesapp.mappers.AsientoMapper;
import co.edu.usbcali.airlinesapp.mappers.FacturaMapper;
import co.edu.usbcali.airlinesapp.repository.FacturaRepository;
import co.edu.usbcali.airlinesapp.services.interfaces.FacturaService;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FacturaServiceImpl implements FacturaService {
    private final FacturaRepository facturaRepository;
    private final ModelMapper modelMapper;

    public FacturaServiceImpl(FacturaRepository facturaRepository, ModelMapper modelMapper) {
        this.facturaRepository = facturaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<FacturaDTO> obtenerFacturas() {
        return FacturaMapper.domainToDTOList(facturaRepository.findAll());
    }

    @Override
    public FacturaDTO obtenerFacturaPorId(Integer id) throws Exception {
        if (facturaRepository.findById(id).isPresent()) {
            return FacturaMapper.domainToDTO(facturaRepository.findById(id).get());
        } else {
            throw new Exception("La factura con id " + id + " no existe");
        }
    }

    @Override
    public FacturaDTO guardarFactura(FacturaDTO facturaDTO) throws Exception {
        Factura factura = FacturaMapper.dtoToDomain(facturaDTO);

        if (factura == null) {
            throw new Exception("La factura no puede ser nula");
        } if (factura.getFecha() == null) {
            throw new Exception("La fecha de la factura no puede ser nula");
        } if (factura.getEstado() == null || factura.getEstado().isBlank() || factura.getEstado().trim().isEmpty()) {
            throw new Exception("El estado de la factura no puede ser nulo o vacío");
        }

        return FacturaMapper.domainToDTO(facturaRepository.save(factura));
    }
}
