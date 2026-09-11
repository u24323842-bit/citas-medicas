package pe.edu.utp.citasmedicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.citasmedicas.model.Cita;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    boolean existsByMedicoIdAndFechaHora(Long medicoId, LocalDateTime fechaHora);

    List<Cita> findByMedicoIdAndFechaHoraBetween(Long medicoId,
                                                 LocalDateTime desde,
                                                 LocalDateTime hasta);

    List<Cita> findByPacienteId(Long pacienteId);
}
