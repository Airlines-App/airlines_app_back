package co.edu.usbcali.airlinesapp.mappers;

import co.edu.usbcali.airlinesapp.domain.Vuelo;
import co.edu.usbcali.airlinesapp.dtos.VueloDTO;

import java.util.List;
import java.util.stream.Collectors;

public class VueloMapper {
    public static VueloDTO domainToDTO(Vuelo vuelo) {
        return VueloDTO.builder()
                .idVuelo(vuelo.getIdVuelo())
                .precio(vuelo.getPrecio())
                .horaSalida(vuelo.getHoraSalida())
                .horaLlegada(vuelo.getHoraLlegada())
                .precioAsientoVip(vuelo.getPrecioAsientoVip())
                .precioAsientoNormal(vuelo.getPrecioAsientoNormal())
                .precioAsientoBasico(vuelo.getPrecioAsientoBasico())
                .estado(vuelo.getEstado())
                .build();
    }

    public static List<VueloDTO> domainToDTOList(List<Vuelo> vuelos) {
        return vuelos.stream().map(vuelo -> domainToDTO(vuelo)).collect(Collectors.toList());
    }

    public static Vuelo dtoToDomain(VueloDTO vueloDTO) {
        return Vuelo.builder()
                .idVuelo(vueloDTO.getIdVuelo())
                .precio(vueloDTO.getPrecio())
                .horaSalida(vueloDTO.getHoraSalida())
                .horaLlegada(vueloDTO.getHoraLlegada())
                .precioAsientoVip(vueloDTO.getPrecioAsientoVip())
                .precioAsientoNormal(vueloDTO.getPrecioAsientoNormal())
                .precioAsientoBasico(vueloDTO.getPrecioAsientoBasico())
                .estado(vueloDTO.getEstado())
                .build();
    }

    public static List<Vuelo> dtoToDomainList(List<VueloDTO> vuelosDTO) {
        return vuelosDTO.stream().map(vueloDTO -> dtoToDomain(vueloDTO)).collect(Collectors.toList());
    }
}
