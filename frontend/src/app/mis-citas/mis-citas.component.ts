import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CitaService } from '../services/cita.service';
import { Cita } from '../models/cita';

/**
 * Modulo "Mis citas": el paciente reprograma o cancela su cita
 * sin necesidad de llamar a recepcion.
 */
@Component({
  selector: 'app-mis-citas',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './mis-citas.component.html'
})
export class MisCitasComponent implements OnInit {

  citas: Cita[] = [];

  constructor(private citaService: CitaService) {}

  ngOnInit(): void {
    this.cargar();
  }

  cargar(): void {
    this.citaService.listar().subscribe((citas) => this.citas = citas);
  }

  cancelar(cita: Cita): void {
    if (cita.id) {
      this.citaService.cancelar(cita.id).subscribe(() => this.cargar());
    }
  }
}
