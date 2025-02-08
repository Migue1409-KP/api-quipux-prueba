# 🎵 Playlist API

## 📌 Descripción
Este proyecto es una **API REST** para gestionar listas de reproducción y canciones. Proporciona endpoints para la creación, consulta y eliminación de listas de reproducción y canciones.

## 🚀 Instalación y Ejecución
Para correr el proyecto, sigue estos pasos:

### **1️⃣ Clonar el repositorio**
```sh
 git clone <URL_DEL_REPO>
 cd pruebatecnica
```

### **2️⃣ Instalar dependencias**
Este proyecto usa **Maven** para gestionar dependencias. Para instalarlas, ejecuta:
```sh
 mvn clean install
```

### **3️⃣ Ejecutar la aplicación**
Para correr la aplicación localmente en **Spring Boot**, usa el siguiente comando:
```sh
 mvn spring-boot:run
```
La API se ejecutará en `http://localhost:8080`.

## 🔑 Autenticación

- **Un usuario por defecto es insertado automáticamente** al iniciar la aplicación, por lo que no es necesario crear uno.
- Usa las siguientes credenciales para autenticación:
  ```json
  {
    "email": "example@example.com",
    "password": "Example321!"
  }
  ```
- **Los endpoints protegidos requieren autenticación con un token JWT**, exceptuando `login`, `register` y `dummy`.

## 📮 Uso de los Endpoints
Los endpoints de la API pueden ser probados con **Postman**.

### **📌 Autenticación**
| Método | Endpoint          | Descripción          |
|--------|------------------|----------------------|
| `POST` | `/api/v1/users/login` | Iniciar sesión y obtener token JWT |
| `POST` | `/api/v1/users/register` | Registrar un nuevo usuario |

### **📌 Listas de Reproducción** *(Requiere Token JWT)*
| Método | Endpoint          | Descripción          |
|--------|------------------|----------------------|
| `GET`  | `/api/v1/lists/dummy` | Endpoint que trae ejemplo de request |
| `POST` | `/api/v1/lists` | Crear una nueva lista |
| `GET`  | `/api/v1/lists` | Obtener todas las listas |
| `GET`  | `/api/v1/lists/{name}` | Obtener una lista por nombre |
| `DELETE` | `/api/v1/lists/{name}` | Eliminar una lista por su nombre|


## 📜 Tecnologías Utilizadas
✅ **Java 21** – Lenguaje de programación principal.  
✅ **Spring Boot 3.4.2** – Framework para la creación de la API.  
✅ **Spring Security** – Autenticación y autorización con JWT.  
✅ **H2 Database** – Base de datos en memoria para pruebas.  
✅ **Lombok** – Generación automática de código boilerplate.  
✅ **MapStruct** – Conversión de DTOs y entidades.  
✅ **Postman** – Para probar los endpoints.  

## 📧 Contacto
Si tienes alguna duda o sugerencia, no dudes en contactarme.

---
📌 **Recuerda siempre ejecutar la aplicación antes de probar los endpoints en Postman.** 🚀

