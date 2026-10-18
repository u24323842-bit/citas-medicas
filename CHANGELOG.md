# Changelog — Sistema Web de Gestión de Citas Médicas (Clínica Vitalis)

Todos los cambios relevantes de este proyecto se documentan en este archivo siguiendo [Semantic Versioning](https://semver.org/lang/es/).

## [v1.0.0] - 2026-10-17

### Añadido (Added)
- **Backend (Spring Boot):**
  - Entidades de dominio: `Especialidad`, `Paciente`, `Medico`, `Cita` y `EstadoCita`.
  - Repositorios JPA (`PacienteRepository`, `MedicoRepository`, `CitaRepository`).
  - Lógica de negocio en `CitaService` con validación de disponibilidad en tiempo real y bloqueo de franja horaria.
  - Controladores REST: `CitaController` (`/api/citas`), `PacienteController` (`/api/pacientes`) y `MedicoController` (`/api/medicos`).
  - Configuración de CORS y servicio de notificaciones por correo electrónico.
- **Frontend (Angular):**
  - Modelo `Cita` y cliente HTTP `CitaService`.
  - Componente de autoservicio `ReservaComponent` y vista del paciente `MisCitasComponent`.
  - Componente administrativo `RecepcionComponent` para supervisión de la agenda diaria.
- **DevOps y Documentación:**
  - Plantilla de integración continua para GitHub Actions (`docs/ci/ci-workflow.yml`).
  - Documentación de procesos de negocio BPMN (AS-IS y TO-BE) y diagramas UML de diseño.

### Corregido (Fixed)
- Unificación de la documentación de arquitectura y módulos en `README.md` tras resolución de conflicto entre ramas `feature/backend-citas` y `feature/frontend-portal`.
