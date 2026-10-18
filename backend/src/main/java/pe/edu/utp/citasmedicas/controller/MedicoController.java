package pe.edu.utp.citasmedicas.controller;

import org.springframework.web.bind.annotation.*;
import pe.edu.utp.citasmedicas.model.Medico;
import pe.edu.utp.citasmedicas.repository.MedicoRepository;

import java.util.List;

/**
 * Servicios REST para consulta de medicos activos por especialidad.
 */
@RestController
@RequestMapping("/api/medicos")
@CrossOrigin(origins = "http://localhost:4200")
public class MedicoController {

    private final MedicoRepository medicoRepository;

    public MedicoController(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @GetMapping
    public List<Medico> listarPorEspecialidad(@RequestParam(required = false) Long especialidadId) {
        if (especialidadId != null) {
            return medicoRepository.findByEspecialidadIdAndActivoTrue(especialidadId);
        }
        return medicoRepository.findAll();
    }
}
