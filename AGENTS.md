# AGENTS.md — Rastreador de Precios en Microservicios (OHO)

> Fuente de buenas prácticas: videos `H3gH_Fe6xvs` (programar con agentes IA: 84% usa IA, 41% código generado, solo 36% confiable → verificar, fundamentos, orquestar no copiar-pegar) y `aDJNG_f2C3E` (OpenCode como agente con herramientas reales: read/write/edit/execute, LSP, multisesión, `/new /copy /export /share`, docs-first).

## 1. Principios guardarraíl (obligatorios para agentes y humanos)

1. **Fundamentos > prompt:** no generar código sin entender Boot/Cloud/Jakarta. Todo cambio debe compilar y respetar Jakarta EE.
2. **Orquestar, no copiar-pegar:** cada aporte IA debe ser supervisado, probado y explicado.
3. **Verificación antes de síntesis:** `mvn validate/test`, `npm run build/lint`, leer archivos reales. Si hay contradicción, manda la evidencia.
4. **Docs-first:** consultar doc oficial (Spring, Next.js) antes de inventar dependencias. No inventar URLs.
5. **Seguridad por defecto:** nada de secretos hardcodeados. Usar variables de entorno + Config Server. JWT con clave persistente, expiración explícita.
6. **Cambios pequeños y trazables:** un hito = un commit convencional (`docs:`, `fix:`, `feat:`, `chore:`). No mezclar infra con lógica.
7. **No crear archivos innecesarios:** preferir `edit` sobre `write`. No añadir `Dockerfile/docker-compose` sin pedirlo (fase actual solo base).

## 2. Arquitectura y rol de cada microservicio

| Módulo | Puerto | Rol | Dependencias clave | Reglas |
|---|---|---|---|---|
| `backend/eureka-server` | `8761` | Discovery. `register-with-eureka:false`, `fetch-registry:false`. | `spring-cloud-starter-netflix-eureka-server` solo (sin `spring-cloud-starter` genérico) | `@EnableEurekaServer` obligatorio. `defaultZone` con `/eureka/` final. |
| `backend/config-server` | `8888` | Config centralizada Git. | `spring-cloud-config-server`, `eureka-client` | `@EnableConfigServer` obligatorio. `uri` externa + `${CONFIG_REPO_PAT}`. `clone-on-start:true` solo si repo accesible. |
| `backend/gateway-server` | `8080` | Entrada única, rutas `lb://`, CORS, validación JWT. | `spring-cloud-starter-gateway` (WebFlux), `eureka-client`, `spring-boot-starter-oauth2-resource-server` o `jjwt` (pendiente) | Rutas: `/api/usuarios/** -> SERVICIO-USUARIOS`, `/api/rastreo/** -> SERVICIO-RASTREO` (pendiente). `instance-id:${...}` con `${}`, no `$(`. No mezclar `starter-web` servlet. |
| `backend/servicio-usuarios` | `0` (random/Eureka) | Auth, usuarios, organizaciones, invitaciones. Emite JWT. | `web, data-jpa, validation, security, eureka-client, config-client, mysql, jjwt-api/impl/jackson:0.12.5, h2:test` | `SecurityFilterChain` stateless obligatorio (hoy ausente → Boot bloquea). `JwtUtil` con clave de `${jwt.secret}`. `datasource.url` JDBC completa. `ddl-auto:update` solo dev. |
| `backend/servicio-rastreo` | `0` | Productos, listados, rastreadores, historial, alertas. APIs externas + scraping + scheduling. | actuales + pendiente: `jsoup`, `spring-boot-starter-webflux` (WebClient), `security+jjwt`, `scheduling` | `@EnableScheduling` para `proximoRastreo/frecuenciaRastreo`. `MetodoExtraccion.API vs SCRAPING`, `Plataforma` enum. Validar `Estado*` con `valueOf` + `BadRequestException`. |
| `frontend/` | `3000` | Next.js 15.3.3 + React 19 + Tailwind v4. Llama solo al Gateway. | `axios, react-hook-form+zod, radix, next` | `NEXT_PUBLIC_API_GATEWAY_URL` obligatorio. `src/services/api.js` interceptor `Bearer`. No llamar directo a microservicios. |

Orden de arranque: `eureka:8761 → config:8888 → gateway:8080 → usuarios/rastreo`.

## 3. Estándares de código

* **Toolchain:** `Java 21` (`<java.version>21</java.version>` en `backend/pom.xml`), Boot `3.4.3`, Cloud `2024.0.1`, Node `20+`. Local con Java 26 no soportado.
* **Java:** `jakarta.persistence.*`, `jakarta.validation.*` (nunca `javax.*` en Boot 3+). Lombok + `annotationProcessorPaths`. Nombre archivo = clase pública. Constructor = nombre de clase (`RastreadorServiceImpl`, no `RastreadorService`). DTOs `*RequestDTO/*ResponseDTO`, excepciones `ResourceNotFound/BadRequest/Unauthorized + GlobalExceptionHandler`.
* **Config:** `application.yml` con `${VAR:default-dev}`, `instance-id:${spring.application.name}:${random.value}`, `defaultZone:http://localhost:8761/eureka/`, `config.import:optional:configserver:discovery/` + `service-id:CONFIG-SERVER`.
* **Frontend:** TypeScript estricto, `@/*`, `eslint-config-next`, Tailwind v4 `@tailwindcss/postcss`.
* **Tests:** `spring-boot-starter-test + h2:test`, `@SpringBootTest` con perfil test, no depender de RDS/MySQL real.

## 4. Workflow del agente (OpenCode)

1. Explorar con `read/glob/grep` antes de editar.
2. Editar mínimo, verificar con `shell` (`mvn -q validate`, `npm run lint` si hay toolchain).
3. Commit limpio por hito: `git add <scope> && git commit -m "tipo: mensaje"`.
4. Push solo al final de fase con `git push origin main`.
5. Reportar progreso corto, con `path:línea` en cada hallazgo.

## 5. Prohibido

* Hardcodear `JWT_SECRET`, `DB_PASSWORD`, `CONFIG_REPO_PAT`, usuario GitHub en yml.
* Usar `server.port:0` sin entender que dificulta debug; documentarlo.
* Añadir `spring-cloud-starter` a `eureka-server`, `starter-web` a `gateway`, o `javax.*` nuevo.
* `git push --force`, commits gigantes `fix all`.
