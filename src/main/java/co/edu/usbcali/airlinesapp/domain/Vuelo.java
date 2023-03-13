package co.edu.usbcali.airlinesapp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "vuelo")
public class Vuelo {
        private static final long serialVersionUID = 1L;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "vuel_id", nullable = false, unique = true)
        private Integer idVuelo;

//        @ManyToOne(optional = false, cascade = CascadeType.ALL)
//        private Aeropuerto aeropuertoOrigen;

//        @ManyToOne(optional = false, cascade = CascadeType.ALL)
//        private Aeropuerto aeropuertoDestino;

        @Column(name = "precio", nullable = false)
        private long precio;

        @Column(name = "hora_salida", nullable = false)
        @CreationTimestamp
        private Date horaSalida;

        @Column(name = "hora_llegada", nullable = false)
        @CreationTimestamp
        private Date horaLlegada;

        @Column(name = "precio_asiento_vip", nullable = false)
        private long precioAsientoVip;

        @Column(name = "precio_asiento_normal", nullable = false)
        private long precioAsientoNormal;

        @Column(name = "precio_asiento_basico", nullable = false)
        private long precioAsientoBasico;

        @Column(name = "estado", nullable = false, length = 1)
        private String estado;
}
