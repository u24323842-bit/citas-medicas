import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Cita } from '../models/cita';

/**
 * Consume los servicios REST de citas publicados por el backend.
 */
@Injectable({ providedIn: 'root' })
export class CitaService {

  private readonly api = 'http://localhost:8080/api/citas';

  constructor(private http: HttpClient) {}

  listar(): Observable<Cita[]> {
    return this.http.get<Cita[]>(this.api);
  }

  disponibilidad(medicoId: number, fecha: string): Observable<string[]> {
    return this.http.get<string[]>(`${this.api}/disponibilidad`, {
      params: { medicoId, fecha }
    });
  }

  reservar(cita: Cita): Observable<Cita> {
    return this.http.post<Cita>(this.api, cita);
  }

  reprogramar(id: number, fechaHora: string): Observable<Cita> {
    return this.http.put<Cita>(`${this.api}/${id}`, null, {
      params: { fechaHora }
    });
  }

  cancelar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }
}
