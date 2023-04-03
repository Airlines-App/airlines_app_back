package co.edu.usbcali.airlinesapp.repository;

import co.edu.usbcali.airlinesapp.domain.Reserva;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    List<Reserva> findAllByEstado(String estado);
    List<Reserva> findAllByVuelo(Integer idVuelo);
    List<Reserva> findAllByAsiento(Integer idAsiento);
}
