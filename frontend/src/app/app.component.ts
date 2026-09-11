import { Component } from '@angular/core';
import { RouterOutlet, RouterLink } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink],
  template: `
    <header class="cabecera">
      <div class="contenedor">
        <h1>Clinica Vitalis</h1>
        <nav>
          <a routerLink="/reserva">Reservar cita</a>
          <a routerLink="/mis-citas">Mis citas</a>
        </nav>
      </div>
    </header>
    <main class="contenedor">
      <router-outlet></router-outlet>
    </main>
  `,
  styles: [`
    .cabecera {
      background: var(--color-primario);
      color: #fff;
    }
    .cabecera h1 {
      font-size: 20px;
      margin: 0 0 8px;
    }
    nav a {
      color: #fff;
      margin-right: 16px;
      text-decoration: none;
    }
  `]
})
export class AppComponent {
  titulo = 'Sistema de Gestion de Citas Medicas';
}
