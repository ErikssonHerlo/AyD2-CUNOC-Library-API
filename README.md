# CUNOC Library API

## Descripción

El proyecto CUNOC Library API es un sistema de gestión de bibliotecas desarrollado para gestionar préstamos, devoluciones, moras, reservas y notificaciones de libros. El sistema también proporciona funcionalidades avanzadas de informes y estadísticas sobre el uso de la biblioteca.

## Funcionalidades

1. **Gestión de Usuarios**
   - Registro de nuevos usuarios.
   - Actualización de datos de usuarios.
   - Eliminación de usuarios.
   - Listado de usuarios por rol.
   - Información detallada de un usuario.

2. **Gestión de Libros**
   - Registro de nuevos libros.
   - Actualización de información de libros.
   - Eliminación de libros.
   - Listado de libros por estado.
   - Información detallada de un libro.

3. **Gestión de Préstamos**
   - Registro de nuevos préstamos.
   - Actualización del estado de los préstamos.
   - Devolución de libros prestados.
   - Listado de préstamos por usuario.
   - Listado de préstamos por estado.
   - Verificación de préstamos activos por usuario.

4. **Gestión de Reservas**
   - Registro de nuevas reservas.
   - Actualización del estado de las reservas.
   - Listado de reservas por usuario.
   - Listado de reservas por estado.

5. **Gestión de Pagos**
   - Registro de nuevos pagos.
   - Actualización del estado de los pagos.
   - Listado de pagos por préstamo.
   - Listado de pagos por estado.

6. **Notificaciones**
   - Envío de notificaciones a usuarios cuando un libro reservado está disponible.
   - Envío de notificaciones a usuarios cuando un préstamo está en mora.
   - Listado de notificaciones por usuario.

7. **Informes y Reportes**
   - Listado de préstamos que deben ser entregados el día actual.
   - Listado de préstamos en mora.
   - Reporte de dinero recaudado en un intervalo de tiempo.
   - Reporte de la carrera con más registros de préstamos en un intervalo de tiempo.
   - Listado de moras pagadas por un estudiante en un intervalo de tiempo.
   - Estudiante con más préstamos en un intervalo de tiempo.
   - Listado de libros prestados actualmente a un estudiante.
   - Listado de libros cuyas copias están agotadas.
   - Listado de libros que nunca se han prestado.
   - Listado de estudiantes en sanción.

## Instalación

1. Clonar el repositorio:
    ```bash
    git clone https://github.com/ErikssonHerlo/AyD2-CUNOC-Library-API.git
    ```

2. Navegar al directorio del proyecto:
    ```bash
    cd AyD2-CUNOC-Library-API
    ```

3. Configurar las variables de entorno en `application.properties`:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/CUNOC_LIBRARY
    spring.datasource.username=your_database_username
    spring.datasource.password=your_database_password
    ```

4. Ejecutar las migraciones de la base de datos con Flyway:
    ```bash
    ./mvnw flyway:migrate
    ```

5. Construir y ejecutar la aplicación:
    ```bash
    ./mvnw spring-boot:run
    ```

## Documentación de la API

La documentación de la API está disponible en formato Swagger. Para acceder a la documentación de Swagger, ejecuta la aplicación y navega a la siguiente URL en tu navegador web:

