# CUNOC Library REST API
Este proyecto es el backend de la API REST para la gestión de una biblioteca en el Centro Universitario de Occidente (CUNOC). La API permite la administración de usuarios (estudiantes y bibliotecarios), libros, préstamos, reservaciones, y otras funcionalidades relacionadas con la operación de una biblioteca.

## Características

- **Gestión de Usuarios**: Registro, actualización y eliminación de usuarios (estudiantes y bibliotecarios).
- **Gestión de Libros**: Creación, actualización, eliminación y consulta de información de libros.
- **Préstamos**: Gestión de préstamos de libros, incluyendo el registro, actualización y finalización de préstamos.
- **Reservaciones**: Permite a los estudiantes realizar y gestionar reservaciones de libros.
- **Autenticación y Autorización**: Manejo de autenticación de usuarios mediante JWT.
- **Historial**: Registro de histórico de préstamos y reservaciones.

## Tecnologías Utilizadas

- **Spring Boot**: Framework principal para la construcción del backend.
- **JWT**: Para la autenticación y autorización.
- **JPA/Hibernate**: Para la persistencia de datos.
- **MySQL**: Base de datos relacional.
- **Maven**: Gestión de dependencias.

## Requisitos Previos

- **Java 19**
- **Maven 3.6.3 o superior**
- **MySQL 8.0 o superior**

## Instalación

1. **Clonar el Repositorio**

```bash
git clone https://github.com/ErikssonHerlo/AyD2-CUNOC-Library-API.git
cd AyD2-CUNOC-Library-API
```

2. **Configurar la Base de Datos**

   Crear una base de datos en MySQL y actualizar el archivo `application.properties.example` con las credenciales correspondientes. Luego, renombrar el archivo a `application.properties`.

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/biblioteca_cunoc
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Construir y Ejecutar la Aplicación**

```bash
mvn clean install
mvn spring-boot:run
```

## Documentación de la API

La documentación de la API se encuentra disponible en la siguiente URL una vez que el servidor esté en ejecución: `http://localhost:8080/swagger-ui.html`

## Frontend

El frontend de este proyecto está disponible en otro repositorio. Puedes encontrarlo en el siguiente enlace:

[Repositorio del Frontend](https://github.com/ErikssonHerlo/AyD2-CUNOC-Library-Frontend)

## Contribuciones

Las contribuciones son bienvenidas. Por favor, sigue los siguientes pasos para contribuir:

1. Haz un fork del proyecto.
2. Crea una nueva rama (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza tus cambios y haz commit (`git commit -am 'Añadir nueva funcionalidad'`).
4. Haz push a la rama (`git push origin feature/nueva-funcionalidad`).
5. Abre un Pull Request.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.

---

Este README proporciona una visión general del proyecto y cómo configurarlo. Para más detalles, consulta la documentación completa incluida en el repositorio.