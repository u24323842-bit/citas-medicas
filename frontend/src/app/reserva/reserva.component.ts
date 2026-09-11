import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { CitaService } from '../services/cita.service';

/**
 * Portal del paciente: permite consultar la disponibilidad real
 * de cada medico y reservar la cita sin pasar por recepcion.
 */
@Component({
  selector: 'app-reserva',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './reserva.component.html'
})
export class ReservaComponent {

  especialidadId = 0;
  medicoId = 0;
  fecha = '';
  horariosLibres: string[] = [];
  mensaje = '';

  constructor(private citaService: CitaService) {}

  buscarDisponibilidad(): void {
    if (!this.medicoId || !this.fecha) {
      this.mensaje = 'Seleccione un medico y una fecha.';
      return;
    }
    this.citaService.disponibilidad(this.medicoId, this.fecha)
      .subscribe((horarios) => this.horariosLibres = horarios);
  }

  reservar(horario: string): void {
    this.citaService.reservar({
      pacienteId: 1,
      medicoId: this.medicoId,
      fechaHora: horario
    }).subscribe(() => {
      this.mensaje = 'Cita reservada. Se envio el correo de confirmacion.';
      this.buscarDisponibilidad();
    });
  }
}
