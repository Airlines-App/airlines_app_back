package co.edu.usbcali.airlinesapp.mappers;

import co.edu.usbcali.airlinesapp.domain.Trayecto;
import co.edu.usbcali.airlinesapp.dtos.TrayectoDTO;

import java.util.List;
import java.util.stream.Collectors;

public class TrayectoMapper {
    public static TrayectoDTO domainToDTO(Trayecto trayecto) {
        return TrayectoDTO.builder()
                .idTrayecto(trayecto.getIdTrayecto())
                .horaSalida(trayecto.getHoraSalida())
                .horaLlegada(trayecto.getHoraLlegada())
                .estado(trayecto.getEstado())
                .build();
    }

    public static List<TrayectoDTO> domainToDTOList(List<Trayecto> trayectos) {
        return trayectos.stream().map(trayecto -> domainToDTO(trayecto)).collect(Collectors.toList());
    }

    public static Trayecto dtoToDomain(TrayectoDTO trayectoDTO) {
        return Trayecto.builder()
                .idTrayecto(trayectoDTO.getIdTrayecto())
                .horaSalida(trayectoDTO.getHoraSalida())
                .horaLlegada(trayectoDTO.getHoraLlegada())
                .estado(trayectoDTO.getEstado())
                .build();
    }

    public static List<Trayecto> dtoToDomainList(List<TrayectoDTO> trayectosDTO) {
        return trayectosDTO.stream().map(trayectoDTO -> dtoToDomain(trayectoDTO)).collect(Collectors.toList());
    }
}
