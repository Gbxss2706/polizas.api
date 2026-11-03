# Documentación de Pólizas API

API REST diseñada para la gestión integral de pólizas de arrendamiento (tanto individuales como colectivas) y sus respectivos riesgos asociados.

---

## Stack Tecnológico
* **Framework:** Spring Boot 3
* **Lenguaje:** Java 21
* **Persistencia:** Spring Data JPA
* **Base de Datos:** H2 (en memoria)

---

## Cómo Ejecutar la Aplicación

1. **Clonar e iniciar el proyecto:**
   ```bash
   ./mvnw spring-boot:run
   ```

2. **URLs de Acceso:**
    * **API Base:** `http://localhost:8080`
    * **Consola H2:** `http://localhost:8080/h2-console`
        * *JDBC URL:* `jdbc:h2:mem:polizasdb`
        * *Usuario:* `sa`
        * *Contraseña:* *(vacía)*


---

## Autenticación y Seguridad

Todas las peticiones HTTP realizadas a la API requieren obligatoriamente el siguiente header de seguridad:

```http
x-api-key: 123456
```

---

## Endpoints de la API

| Método | Ruta | Descripción |
| :--- | :--- | :--- |
| `GET` | `/polizas?tipo=&estado=` | Lista las pólizas aplicando filtros opcionales por tipo o estado. |
| `GET` | `/polizas/{id}/riesgos` | Lista todos los riesgos asociados a una póliza específica. |
| `POST` | `/polizas/{id}/renovar` | Renueva la póliza aplicando IPC al canon y prima; cambia su estado a `RENOVADA`. |
| `POST` | `/polizas/{id}/cancelar` | Cancela la póliza especificada y todos sus riesgos asociados. |
| `POST` | `/polizas/{id}/riesgos` | Agrega un nuevo riesgo (válido **solo** para pólizas colectivas). |
| `POST` | `/riesgos/{id}/cancelar` | Cancela un riesgo específico. |
| `POST` | `/core-mock/evento` | Mock del CORE: registra el evento correspondiente en el archivo de log. |

### Ejemplo de Cuerpo de Petición (Request Body)

* **`POST /polizas/{id}/riesgos`**
  ```json
  {
    "direccionInmueble": "Calle 100 # 15-20",
    "nombreArrendatario": "María Fernández"
  }
  ```

---

## Manejo de Errores

La API cuenta con un formato unificado para la gestión de errores según el código de respuesta HTTP:

* **`400 Bad Request`**: Error de validación en los datos de entrada.
* **`401 Unauthorized`**: Falta o es inválida la API Key en el header.
* **`404 Not Found`**: El recurso solicitado (póliza o riesgo) no existe.
* **`409 Conflict`**: Infracción de alguna regla de negocio.

---

## Reglas de Negocio

1. **Renovación:** No se puede renovar una póliza que se encuentre en estado cancelado.
2. **Cancelación en cascada:** Cancelar una póliza implica la cancelación automática de todos sus riesgos asociados.
3. **Restricción de Riesgos:** Solo se pueden agregar nuevos riesgos a pólizas de tipo colectivo; las pólizas individuales manejan estrictamente un único riesgo.

---

## Integración con el CORE

Toda operación que modifique el estado de las entidades notifica de manera síncrona/asíncrona al CORE mediante la interfaz `CoreClient`. El mock implementado (`/core-mock/evento`) se encarga exclusivamente de registrar dicho evento en los logs del sistema.

---

## Supuestos y Consideraciones

* **Definición de Riesgo:** Un riesgo representa un inmueble arrendado, el cual vincula a un arrendatario (asegurado) y a un arrendador (beneficiario).
* **Valores Monetarios:** Todos los valores se manejan en pesos colombianos representados como enteros (`Long`).
* **IPC:** Se aplica un porcentaje de IPC fijo del **5,2 %** para las renovaciones.
* **Cancelación Individual:** Si se cancela el único riesgo existente en una póliza individual, la póliza se cancelará automáticamente.
* **Creación de Pólizas:** La funcionalidad de crear nuevas pólizas queda fuera del alcance actual; todas se inicializan mediante el script `data.sql`.
---

## TESTS

En la carpeta postman se encuentran los tests realizados en formato JSON listos para importar en postman

---