package pe.edu.utp.citasmedicas.model;

/**
 * Estados por los que pasa una cita desde su reserva
 * hasta su atencion o cancelacion.
 */
public enum EstadoCita {
    RESERVADA,
    CONFIRMADA,
    ATENDIDA,
    CANCELADA
}
