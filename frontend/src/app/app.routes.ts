import { Routes } from '@angular/router';

import { ReservaComponent } from './reserva/reserva.component';
import { MisCitasComponent } from './mis-citas/mis-citas.component';

export const routes: Routes = [
  { path: '', redirectTo: 'reserva', pathMatch: 'full' },
  { path: 'reserva', component: ReservaComponent },
  { path: 'mis-citas', component: MisCitasComponent }
];
