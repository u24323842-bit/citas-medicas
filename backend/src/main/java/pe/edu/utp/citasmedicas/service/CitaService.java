package pe.edu.utp.citasmedicas.service;

import org.springframework.stereotype.Service;
import pe.edu.utp.citasmedicas.model.Cita;
import pe.edu.utp.citasmedicas.model.EstadoCita;
import pe.edu.utp.citasmedicas.repository.CitaRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Logica de agendamiento de citas. Concentra la validacion de
 * disponibilidad que en el sistema heredado se hacia de forma manual
 * en recepcion, que es el origen de la duplicidad de citas.
 */
@Service
public class CitaService {

    private static final LocalTime HORA_INICIO = LocalTime.of(8, 0);
    private static final LocalTime HORA_FIN = LocalTime.of(20, 0);
    private static final int MINUTOS_POR_CITA = 30;

    private final CitaRepository citaRepository;

    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    /**
     * Registra una cita siempre que el horario del medico este libre.
     */
    public Cita reservar(Cita cita) {
        if (citaRepository.existsByMedicoIdAndFechaHora(
                cita.getMedico().getId(), cita.getFechaHora())) {
            throw new IllegalStateException(
                    "El medico ya tiene una cita registrada en ese horario.");
        }
        cita.setEstado(EstadoCita.RESERVADA);
        return citaRepository.save(cita);
    }

    /**
     * Devuelve los horarios libres de un medico en una fecha dada.
     */
    public List<LocalDateTime> disponibilidad(Long medicoId, LocalDateTime fecha) {
        LocalDateTime desde = fecha.toLocalDate().atTime(HORA_INICIO);
        LocalDateTime hasta = fecha.toLocalDate().atTime(HORA_FIN);

        List<Cita> ocupadas =
                citaRepository.findByMedicoIdAndFechaHoraBetween(medicoId, desde, hasta);

        List<LocalDateTime> libres = new ArrayList<>();
        for (LocalDateTime h = desde; h.isBefore(hasta); h = h.plusMinutes(MINUTOS_POR_CITA)) {
            final LocalDateTime horario = h;
            boolean ocupado = ocupadas.stream()
                    .anyMatch(c -> c.getFechaHora().equals(horario)
                            && c.getEstado() != EstadoCita.CANCELADA);
            if (!ocupado) {
                libres.add(horario);
            }
        }
        return libres;
    }

    public Cita reprogramar(Long id, LocalDateTime nuevaFechaHora) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La cita no existe."));
        if (citaRepository.existsByMedicoIdAndFechaHora(
                cita.getMedico().getId(), nuevaFechaHora)) {
            throw new IllegalStateException("El nuevo horario ya esta ocupado.");
        }
        cita.setFechaHora(nuevaFechaHora);
        return citaRepository.save(cita);
    }

    public void cancelar(Long id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La cita no existe."));
        cita.setEstado(EstadoCita.CANCELADA);
        citaRepository.save(cita);
    }

    public List<Cita> listar() {
        return citaRepository.findAll();
    }
}
