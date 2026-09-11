export interface Cita {
  id?: number;
  pacienteId: number;
  medicoId: number;
  fechaHora: string;
  estado?: 'RESERVADA' | 'CONFIRMADA' | 'ATENDIDA' | 'CANCELADA';
  motivo?: string;
}
