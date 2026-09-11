package pe.edu.utp.citasmedicas.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.citasmedicas.model.Cita;
import pe.edu.utp.citasmedicas.service.CitaService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Servicios REST de agendamiento de citas consumidos
 * por el portal del paciente y por el panel de recepcion.
 */
@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "http://localhost:4200")
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @GetMapping
    public List<Cita> listar() {
        return citaService.listar();
    }

    @GetMapping("/disponibilidad")
    public List<LocalDateTime> disponibilidad(
            @RequestParam Long medicoId,
            @RequestParam LocalDateTime fecha) {
        return citaService.disponibilidad(medicoId, fecha);
    }

    @PostMapping
    public ResponseEntity<Cita> reservar(@Valid @RequestBody Cita cita) {
        return new ResponseEntity<>(citaService.reservar(cita), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Cita reprogramar(@PathVariable Long id,
                            @RequestParam LocalDateTime fechaHora) {
        return citaService.reprogramar(id, fechaHora);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        citaService.cancelar(id);
        return ResponseEntity.noContent().build();
    }
}
