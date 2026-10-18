import { Component, OnInit } from '@angular/core';
import { Cita } from '../models/cita';
import { CitaService } from '../services/cita.service';

@Component({
  selector: 'app-recepcion',
  template: `
    <section class="panel-recepcion">
      <h2>Agenda del Día — Recepción Clínica Vitalis</h2>
      <table>
        <thead>
          <tr><th>Hora</th><th>Paciente</th><th>Médico</th><th>Estado</th></tr>
        </thead>
        <tbody>
          <tr *ngFor="let c of citasDelDia">
            <td>{{ c.fechaHora }}</td>
            <td>{{ c.pacienteNombre }}</td>
            <td>{{ c.medicoNombre }}</td>
            <td>{{ c.estado }}</td>
          </tr>
        </tbody>
      </table>
    </section>
  `
})
export class RecepcionComponent implements OnInit {
  citasDelDia: Cita[] = [];
  constructor(private citaService: CitaService) {}
  ngOnInit(): void {
    this.citaService.listar().subscribe(data => this.citasDelDia = data);
  }
}
