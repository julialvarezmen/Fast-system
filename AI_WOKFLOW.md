# AI_WORKFLOW.md: Marco de Trabajo para Fast System

**Proyecto:** FAST SYSTEM – Arquitectura Asíncrona (React / Spring Boot / RabbitMQ / Node / Docker)
**Propósito:** Definir la Estrategia Oficial de Interacción con Inteligencia Artificial (IA) y el flujo de desarrollo.

---

## 1. Metodología: "AI-First" (Arquitectos y Revisores)

La metodología central del proyecto es **"AI-First"**, que promueve la máxima delegación de código repetitivo y *boilerplate* a la Inteligencia Artificial (IA).

| Rol | Responsabilidad | Principio Clave |
| :--- | :--- | :--- |
| **Equipo Humano** | Actuar como **Arquitectos y Revisores**.Enfocarse en la arquitectura, contratos de API, lógica de negocio compleja, y la revisión de calidad, seguridad y rendimiento. |**Prohibido escribir código repetitivo a mano**. |
| **IA (GitHub Copilot)** |Es nuestro **Junior Developer**. Generar el 100% del *boilerplate* y código repetitivo (clases, componentes, estructura base). | - |

---

## 2. Arquitectura Clave y Stack Tecnológico

La arquitectura es de tipo **orientada a eventos/mensajería**  y utiliza el siguiente *stack*:

| Componente | Tecnología | Propósito |
| :--- | :--- | :--- |
| **Frontend** | `React` (Vite + Tailwind) |Interfaz para Cliente (Catálogo/Carrito) y Administrador (Gestión)|
| **Producer (API)** | `Spring Boot` (Java 17+) |Expone la API REST que recibe el pedido (JSON) desde el Frontend|
| **Broker (Mensajería)** | `RabbitMQ` |Gestión de la mensajería, encolando el pedido como un **Mensaje** para procesamiento asíncrono. |
| **Consumer (Worker)** | `Node.js` | Worker que consume la notificación del Broker para procesar el pedido (ej. actualizar BD). |
| **Infraestructura** | `Docker` | Orquestar el entorno de desarrollo y producción, incluyendo RabbitMQ. |

---

## 3. Dinámicas de Interacción y Flujo de Trabajo

El desarrollo se divide en fases con responsabilidades claras.

### 3.1. Fase 1: Preparación y Alineación 
* **Propósito:** Definir el MVP (Catálogo + Admin) y alinear la infraestructura.
* **Acciones:** Crear este documento, levantar la infraestructura base con `docker-compose.yml` (mínimo RabbitMQ), y generar el *Boilerplate* de Spring Boot y React con IA.

### 3.2. Fase 2: Desarrollo (El Núcleo) 
**Producer:** API REST en Spring Boot (Controladores, DTOs).
* **Consumer:** Worker de procesamiento en Node.js.
* **Frontend:** Implementación del Catálogo, Carrito, y Panel Admin (React).
* **QA:** Validación del flujo de pedidos (End-to-End).

### 3.3. Fase 3: Entrega Final
* **Métrica Clave de Éxito:** Alta Concurrencia (100 Pedidos en 10s). El sistema Spring Boot debe encolar pedidos sin bloqueo.
* **Entregables:** Evidencia de Git Flow estricto (`feature/*` $\rightarrow$ `develop` $\rightarrow$ `main`), Stress Test (QA), y Video Demo del flujo Cliente $\rightarrow$ Admin.

---

## 4. Marco de Interacción con IA (AI Workflow)

El uso de IA debe ser estandarizado y trazable.

### 4.1.Herramientas de IA Permitidas y sus Roles
| Herramienta | Rol Principal | Tareas Clave | Reglas |
| :--- | :--- | :--- | :--- |
| **SKAI** | Plantillas de Prompts |Análisis de HU, Desglose de CA, Redacción de *Prompts* estructurados. |Toda interacción compleja iniciará usando plantillas de SKAI. |
| **GitHub Copilot** | Generación de Código | Estructuras base, *Boilerplate*, pruebas unitarias base, configuración Dockerfile| Ningún código será aceptado sin **validación humana**. |
| **ChatGPT** | Arquitectura y Documentación |Resolver dudas técnicas, generar documentación formal, revisar diseño de *endpoints*, depurar *prompts*. | - |
| **Google Gemini** | Validación Cruzada |Verificar respuestas de ChatGPT, comparar arquitecturas, validar código antes de integrarlo. |Cualquier resultado de código o arquitectura debe pasar por validación cruzada |

### 4.2. Metodología de Interacción (5 Pasos)

| Paso | Acción | Herramienta Clave |
| :--- | :--- | :--- |
| **Paso 1** | **Contextualización:** Identificar HU, CA, Módulo (Producer/Worker/Frontend) y Tipo de *Output* deseado. | Equipo Humano |
| **Paso 2** |**Creación del Prompt:** Construir y refinar el *prompt* usando plantillas de SKAI | SKAI (+ ChatGPT/Gemini) |
| **Paso 3** | **Ejecución:** Generar la respuesta usando la IA adecuada (e.g., Copilot para código, ChatGPT para documentación). | IA seleccionada |
| **Paso 4** | **Validación Humana:** Validar correcto funcionamiento, alineación con HU/CA, seguridad e integración. | Todo el equipo (QA Obligatorio) |
| **Paso 5** | **Registro:** Documentar en el repositorio (Issue, Commit). | GitHub |

### 4.3. Políticas de Seguridad y Restricciones 

* La IA **NO** puede definir la arquitectura final sin aprobación del equipo.
* La IA **NO** puede inventar Criterios de Aceptación (CA).
* El código generado por Copilot debe **revisarse siempre**.
* Se debe evitar incluir claves o secretos en *prompts*.

---

## 5. Casos de Uso Específicos de IA

La IA se aplicará en los siguientes módulos para generar el *boilerplate* y asistencia técnica.

| Módulo | Tareas con IA | Herramientas Principales |
| :--- | :--- | :--- |
| **Historias/CA** | Análisis de HU, Refinar CA, Generar preguntas de aclaración, División de tareas[. |SKAI, ChatGPT, Gemini. |
| **Backend Producer** | Crear controladores, generar servicios, configurar RabbitMQ, crear DTOs, pruebas unitarias JUnit. |Copilot, ChatGPT, Gemini. |
| **Worker (Node + TS)** |Estructurar consumidor RabbitMQ, optimizar manejo de errores, lógica de reintentos, patrones de concurrencia simple. | Copilot, ChatGPT, Gemini |
| **Frontend (React)** |Componentes, *Hooks* de estado, manejo de formularios, estilos base (Tailwind), llamadas a la API. |Copilot. |
| **Docker y Deploy** | Construcción de Dockerfiles, `docker-compose.yml`, corrección de errores de redes, optimización de contenedores. |ChatGPT, Gemini. |

---

## 6. Trazabilidad y Roles del Equipo

### 6.1. Trazabilidad de Prompts
Cada interacción relevante debe documentarse con:
* *Prompt* original (plantilla SKAI).
* Respuesta IA.
*Cambios aplicados y Validación humana.
*Referencia al Commit e ID del Issue.

### 6.2. Roles y Responsabilidades con IA 

| Rol | Responsabilidades con IA |
| :--- | :--- |
| **Backend Developer** | Julian Alvarez-Uso de Copilot y ChatGPT para Producer y Worker. |
| **Frontend Developer** | Julian Rodriguez-Uso de Copilot + ChatGPT para UI. |
| **DevOps** |  Docker, redes y despliegues con ayuda de ChatGPT/Gemini. |
| **QA** | Maria Isabel- Maria Paula-Validación técnica, pruebas generadas por IA. |
| **Documentador** | Todos- raducción de resultados IA a documentación formal. |

### 6.3. Registro de Interacciones Relevantes (Ejemplos) 

| Fecha | Módulo | IA Utilizada | Descripción | Validado por | Issue |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 19/11/2025 | Producer | Copilot + ChatGPT | Configuración inicial de RabbitMQ y estructura de endpoints. | Equipo | \#12 |
| 20/11/2025 | Worker | Copilot + Gemini | Generación del consumidor RabbitMQ. | Backend Dev | \#24 |
| 21/11/2025 | Infra | ChatGPT | `docker-compose.yml` inicial. | DevOps | \#41 |

---

## Apéndice A: Prompts SKAI y Ejemplos Detallados

Esta sección contiene plantillas refinadas de *prompts* que se usarán en el proyecto FAST SYSTEM.

### A.1. Prompts SKAI – Análisis y Refinamiento

| Categoría | Prompt Base |
| :--- | :--- |
| **Análisis HU** | `Contexto del Proyecto: FAST SYSTEM – arquitectura asíncrona. Tarea: Analiza la siguiente Historia de Usuario (HU), identifica riesgos, criterios implícitos y dependencias técnicas. [PEGAR HU Y CA]. [cite_start]Objetivo: Entrégame un análisis estructurado (descripción técnica, supuestos, flujo de datos, impacto en módulos, riesgos).`  |
| **Refinamiento CA** | `Contexto: Proyecto FAST SYSTEM. Tarea: Refina los siguientes Criterios de Aceptación para que sean claros, comprobables, técnicamente precisos y alineados al comportamiento del sistema. [PEGAR CA]. [cite_start]Incluye: Criterios funcionales, validaciones, reglas de negocio y respuestas esperadas (HTTP, RabbitMQ).`  |

### A.2. Prompts SKAI – Generación de Código

| Módulo | Prompt Base |
| :--- | :--- |
| **Producer (Spring)** | `Necesito generar un endpoint REST en Spring Boot (FAST SYSTEM). Requisitos: recibir JSON, generar ID único, publicar mensaje en RabbitMQ (cola: task_queue), retornar 202 Accepted. [cite_start]Genera: DTO, Controlador, Servicio, Mensaje publicado a RabbitMQ y Ejemplo de JSON válido.`  |
| **Worker (Node + TS)** | `Genera un consumidor RabbitMQ para FAST SYSTEM. Requisitos: Conectarse a RabbitMQ, escuchar la cola task_queue, procesar el payload, guardar resultado en Postgres. Usar TypeScript. Incluye: Código completo, Manejo de errores y Retries.`|
| **Docker Compose** | `Necesito un archivo docker-compose.yml para FAST SYSTEM que incluya: RabbitMQ (management), Postgres, Producer (Spring Boot), Worker (Node) y Frontend (React). Requisitos: Redes correctas, dependencias declaradas, variables de entorno y volúmenes para persistencia.`  |

### A.3. Prompts para Validación Cruzada (Gemini)

| Tarea | Prompt Específico |
| :--- | :--- |
| **Validación de Código** | `Valida el siguiente código generado por otra IA para el proyecto FAST SYSTEM. [PEGAR CÓDIGO]. Evalúa: Correctitud, Seguridad, Manejo de errores, Buenas prácticas y Compatibilidad con el stack.`|
| **Validación de Arquitectura** | `Verifica si el siguiente diseño de arquitectura cumple con una solución asíncrona (React → Spring Boot → RabbitMQ → Node Worker → Postgres → Docker). [PEGAR ARQUITECTURA]. Identifica errores, mejoras y recomendaciones.`  |

### A.4. Prompts para Asistencia y Documentación (ChatGPT)

| Tarea | Prompt Específico |
| :--- | :--- |
| **Documentación Flujo** | `Explica detalladamente el flujo de datos del proyecto FAST SYSTEM (Frontend → Worker). Incluye: 5 etapas, Tecnología por etapa, Riesgos y Validaciones técnicas.` |
| **Análisis de Arquitectura** | `En el proyecto FAST SYSTEM, dime si la siguiente arquitectura es correcta y qué puedo mejorar: [DESCRIBIR ARQUITECTURA].Evalúa: Buenas prácticas, Escalabilidad, Concurrencia y Manejo de colas.`|

---

**Nota Final:** Este documento es un **documento vivo**, actualizado continuamente durante el proyecto.
