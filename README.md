# Sistema Web de Gestion de Citas Medicas - Clinica Vitalis

Proyecto academico de la carrera de Ingenieria de Sistemas e Informatica.

El sistema reemplaza el aplicativo heredado del ano 2014 de la Clinica Vitalis,
incorporando un portal de autoservicio para que el paciente reserve su cita en
linea y un panel para el personal administrativo.

## Arquitectura

Arquitectura cliente-servidor:

- **Backend**: Spring Boot, expone servicios REST.
- **Frontend**: Angular, consume los servicios REST.
- **Base de datos**: relacional (MySQL / PostgreSQL).

## Estructura del repositorio

    citas-medicas/
      backend/    API REST en Spring Boot
      frontend/   Aplicacion Angular
      docs/       Documentacion y diagramas del proyecto

## Modulos en desarrollo

Servicios REST publicados por el backend:

| Metodo | Ruta | Descripcion |
|--------|------|-------------|
| GET | /api/citas | Lista las citas registradas |
| GET | /api/citas/disponibilidad | Consulta horarios libres por medico y fecha |
| POST | /api/citas | Registra una nueva cita |
| PUT | /api/citas/{id} | Reprograma una cita existente |
| DELETE | /api/citas/{id} | Cancela una cita |
| GET | /api/pacientes | Lista los pacientes registrados |
| POST | /api/pacientes | Registra un paciente |

## Autor

u24323842-bit - codigo U24323842
