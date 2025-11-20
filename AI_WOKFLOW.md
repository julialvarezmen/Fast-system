# AI_WORKFLOW.md: Marco de Trabajo para Fast System

**Proyecto:** FAST SYSTEM – Arquitectura Asíncrona (React / Spring Boot / RabbitMQ / Node / Docker)
**Propósito:** Definir la Estrategia Oficial de Interacción con Inteligencia Artificial (IA) y el flujo de desarrollo.

---

## 1. Metodología: "AI-First" (Arquitectos y Revisores)

La metodología central del proyecto es **"AI-First"**, que promueve la máxima delegación de código repetitivo y *boilerplate* a la Inteligencia Artificial (IA).

| Rol | Responsabilidad | Principio Clave |
| :--- | :--- | :--- |
| **Equipo Humano** | Actuar como **Arquitectos y Revisores**.Enfocarse en la arquitectura, contratos de API, lógica de negocio compleja, y la revisión de calidad, seguridad y rendimiento[cite: 5]. | [cite_start]**Prohibido escribir código repetitivo a mano**[cite: 8]. |
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
**Producer:** API REST en Spring Boot (Controladores, DTOs)
* .
* [cite_start]**Consumer:** Worker de procesamiento en Node.js.
* [cite_start]**Frontend:** Implementación del Catálogo, Carrito, y Panel Admin (React)[cite: 28].
* [cite_start]**QA:** Validación del flujo de pedidos (End-to-End)[cite: 31].

### 3.3. [cite_start]Fase 3: Entrega Final [cite: 32]
* [cite_start]**Métrica Clave de Éxito:** Alta Concurrencia (100 Pedidos en 10s)[cite: 33]. [cite_start]El sistema Spring Boot debe encolar pedidos sin bloqueo[cite: 34].
* [cite_start]**Entregables:** Evidencia de Git Flow estricto (`feature/*` $\rightarrow$ `develop` $\rightarrow$ `main`), Stress Test (QA), y Video Demo del flujo Cliente $\rightarrow$ Admin[cite: 36, 37, 38, 40].

---

## 4. Marco de Interacción con IA (AI Workflow)

[cite_start]El uso de IA debe ser estandarizado y trazable[cite: 59, 64].

### 4.1. [cite_start]Herramientas de IA Permitidas y sus Roles [cite: 65]

| Herramienta | Rol Principal | Tareas Clave | Reglas |
| :--- | :--- | :--- | :--- |
| **SKAI** | Plantillas de Prompts | [cite_start]Análisis de HU, Desglose de CA, Redacción de *Prompts* estructurados[cite: 68, 69, 70, 73]. | [cite_start]Toda interacción compleja iniciará usando plantillas de SKAI[cite: 77]. |
| **GitHub Copilot** | Generación de Código | [cite_start]Estructuras base, *Boilerplate*, pruebas unitarias base, configuración Dockerfile[cite: 79, 80, 81, 83, 84]. | [cite_start]Ningún código será aceptado sin **validación humana**[cite: 86]. |
| **ChatGPT** | Arquitectura y Documentación | [cite_start]Resolver dudas técnicas, generar documentación formal, revisar diseño de *endpoints*, depurar *prompts*[cite: 90, 91, 93, 94, 95]. | - |
| **Google Gemini** | Validación Cruzada | [cite_start]Verificar respuestas de ChatGPT, comparar arquitecturas, validar código antes de integrarlo[cite: 98, 99, 100, 101]. | [cite_start]Cualquier resultado de código o arquitectura debe pasar por validación cruzada[cite: 104]. |

### 4.2. [cite_start]Metodología de Interacción (5 Pasos) [cite: 105]

| Paso | Acción | Herramienta Clave |
| :--- | :--- | :--- |
| **Paso 1** | [cite_start]**Contextualización:** Identificar HU, CA, Módulo (Producer/Worker/Frontend) y Tipo de *Output* deseado[cite: 107, 109, 110, 111, 112]. | Equipo Humano |
| **Paso 2** | [cite_start]**Creación del Prompt:** Construir y refinar el *prompt* usando plantillas de SKAI[cite: 115, 116, 123]. | SKAI (+ ChatGPT/Gemini) |
| **Paso 3** | [cite_start]**Ejecución:** Generar la respuesta usando la IA adecuada (e.g., Copilot para código, ChatGPT para documentación)[cite: 125, 127]. | IA seleccionada |
| **Paso 4** | [cite_start]**Validación Humana:** Validar correcto funcionamiento, alineación con HU/CA, seguridad e integración[cite: 128, 130, 131, 132]. | Todo el equipo (QA Obligatorio) |
| **Paso 5** | [cite_start]**Registro:** Documentar en el repositorio (Issue, Commit)[cite: 136, 138, 139]. | GitHub |

### 4.3. [cite_start]Políticas de Seguridad y Restricciones [cite: 181]

* [cite_start]La IA **NO** puede definir la arquitectura final sin aprobación del equipo[cite: 182].
* [cite_start]La IA **NO** puede inventar Criterios de Aceptación (CA)[cite: 183].
* [cite_start]El código generado por Copilot debe **revisarse siempre**[cite: 184].
* [cite_start]Se debe evitar incluir claves o secretos en *prompts*[cite: 186].

---

## 5. Casos de Uso Específicos de IA

[cite_start]La IA se aplicará en los siguientes módulos para generar el *boilerplate* y asistencia técnica[cite: 141].

| Módulo | Tareas con IA | Herramientas Principales |
| :--- | :--- | :--- |
| **Historias/CA** | [cite_start]Análisis de HU, Refinar CA, Generar preguntas de aclaración, División de tareas[cite: 142, 143, 144]. | [cite_start]SKAI, ChatGPT, Gemini[cite: 146, 147, 148]. |
| **Backend Producer** | [cite_start]Crear controladores, generar servicios, configurar RabbitMQ, crear DTOs, pruebas unitarias JUnit[cite: 149, 151, 153, 155]. | [cite_start]Copilot, ChatGPT, Gemini[cite: 157, 158, 159]. |
| **Worker (Node + TS)** | [cite_start]Estructurar consumidor RabbitMQ, optimizar manejo de errores, lógica de reintentos, patrones de concurrencia simple[cite: 160, 162, 163, 165, 166]. | Copilot, ChatGPT, Gemini |
| **Frontend (React)** | [cite_start]Componentes, *Hooks* de estado, manejo de formularios, estilos base (Tailwind), llamadas a la API[cite: 167, 169, 170, 172, 173]. | [cite_start]Copilot[cite: 174]. |
| **Docker y Deploy** | [cite_start]Construcción de Dockerfiles, `docker-compose.yml`, corrección de errores de redes, optimización de contenedores[cite: 175, 177, 178]. | [cite_start]ChatGPT, Gemini[cite: 180]. |

---

## 6. Trazabilidad y Roles del Equipo

### 6.1. [cite_start]Trazabilidad de Prompts [cite: 187]
Cada interacción relevante debe documentarse con:
* [cite_start]*Prompt* original (plantilla SKAI)[cite: 189].
* [cite_start]Respuesta IA[cite: 190].
* [cite_start]Cambios aplicados y Validación humana[cite: 191, 192].
* [cite_start]Referencia al Commit e ID del Issue[cite: 193, 194].

### 6.2. [cite_start]Roles y Responsabilidades con IA [cite: 195]

| Rol | Responsabilidades con IA |
| :--- | :--- |
| **Backend Developer** | Julian Alvarez-Uso de Copilot y ChatGPT para Producer y Worker[cite: 196]. |
| **Frontend Developer** | Julian Rodriguez-Uso de Copilot + ChatGPT para UI[cite: 196]. |
| **DevOps** |  Docker, redes y despliegues con ayuda de ChatGPT/Gemini[cite: 196]. |
| **QA** | Maria Isabel- Maria Paula-Validación técnica, pruebas generadas por IA[cite: 196]. |
| **Documentador** | Todos- raducción de resultados IA a documentación formal[cite: 196]. |

### 6.3. Registro de Interacciones Relevantes (Ejemplos) 

| Fecha | Módulo | IA Utilizada | Descripción | Validado por | Issue |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 19/11/2025 | Producer | Copilot + ChatGPT | Configuración inicial de RabbitMQ y estructura de endpoints[cite: 198]. | Equipo | \#12 |
| 20/11/2025 | Worker | Copilot + Gemini | Generación del consumidor RabbitMQ. | Backend Dev | \#24 |
| 21/11/2025 | Infra | ChatGPT | `docker-compose.yml` inicial. | DevOps | \#41 |

---

## Apéndice A: Prompts SKAI y Ejemplos Detallados

Esta sección contiene plantillas refinadas de *prompts* que se usarán en el proyecto FAST SYSTEM[cite: 223].

### A.1. Prompts SKAI – Análisis y Refinamiento

| Categoría | Prompt Base |
| :--- | :--- |
| **Análisis HU** | `Contexto del Proyecto: FAST SYSTEM – arquitectura asíncrona. Tarea: Analiza la siguiente Historia de Usuario (HU), identifica riesgos, criterios implícitos y dependencias técnicas. [PEGAR HU Y CA]. [cite_start]Objetivo: Entrégame un análisis estructurado (descripción técnica, supuestos, flujo de datos, impacto en módulos, riesgos).` [cite: 225, 226, 229, 230, 234] |
| **Refinamiento CA** | `Contexto: Proyecto FAST SYSTEM. Tarea: Refina los siguientes Criterios de Aceptación para que sean claros, comprobables, técnicamente precisos y alineados al comportamiento del sistema. [PEGAR CA]. [cite_start]Incluye: Criterios funcionales, validaciones, reglas de negocio y respuestas esperadas (HTTP, RabbitMQ).` [cite: 237, 239, 240, 241, 243] |

### A.2. Prompts SKAI – Generación de Código

| Módulo | Prompt Base |
| :--- | :--- |
| **Producer (Spring)** | `Necesito generar un endpoint REST en Spring Boot (FAST SYSTEM). Requisitos: recibir JSON, generar ID único, publicar mensaje en RabbitMQ (cola: task_queue), retornar 202 Accepted. [cite_start]Genera: DTO, Controlador, Servicio, Mensaje publicado a RabbitMQ y Ejemplo de JSON válido.` [cite: 244, 246, 248] |
| **Worker (Node + TS)** | `Genera un consumidor RabbitMQ para FAST SYSTEM. Requisitos: Conectarse a RabbitMQ, escuchar la cola task_queue, procesar el payload, guardar resultado en Postgres. Usar TypeScript. [cite_start]Incluye: Código completo, Manejo de errores y Retries.` [cite: 249, 251, 252] |
| **Docker Compose** | `Necesito un archivo docker-compose.yml para FAST SYSTEM que incluya: RabbitMQ (management), Postgres, Producer (Spring Boot), Worker (Node) y Frontend (React). [cite_start]Requisitos: Redes correctas, dependencias declaradas, variables de entorno y volúmenes para persistencia.` [cite: 253, 255, 256] |

### A.3. Prompts para Validación Cruzada (Gemini)

| Tarea | Prompt Específico |
| :--- | :--- |
| **Validación de Código** | `Valida el siguiente código generado por otra IA para el proyecto FAST SYSTEM. [PEGAR CÓDIGO]. [cite_start]Evalúa: Correctitud, Seguridad, Manejo de errores, Buenas prácticas y Compatibilidad con el stack.` [cite: 273, 274, 277] |
| **Validación de Arquitectura** | `Verifica si el siguiente diseño de arquitectura cumple con una solución asíncrona (React → Spring Boot → RabbitMQ → Node Worker → Postgres → Docker). [PEGAR ARQUITECTURA]. [cite_start]Identifica errores, mejoras y recomendaciones.` [cite: 278, 279, 283] |

### A.4. Prompts para Asistencia y Documentación (ChatGPT)

| Tarea | Prompt Específico |
| :--- | :--- |
| **Documentación Flujo** | `Explica detalladamente el flujo de datos del proyecto FAST SYSTEM (Frontend → Worker). [cite_start]Incluye: 5 etapas, Tecnología por etapa, Riesgos y Validaciones técnicas.` [cite: 258, 259, 260] |
| **Análisis de Arquitectura** | `En el proyecto FAST SYSTEM, dime si la siguiente arquitectura es correcta y qué puedo mejorar: [DESCRIBIR ARQUITECTURA]. [cite_start]Evalúa: Buenas prácticas, Escalabilidad, Concurrencia y Manejo de colas.` [cite: 268, 269, 271] |

---

[cite_start]**Nota Final:** Este documento es un **documento vivo**, actualizado continuamente durante el proyecto[cite: 57].
