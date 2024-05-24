# Manual Técnico

El manual técnico incluye detalles sobre la arquitectura del sistema, los componentes principales y las decisiones de diseño. Está disponible en el directorio `docs/manual-tecnico`.

## Ejemplos de Uso de la API

### Registro de un nuevo usuario

```bash
POST /api/v1/user
{
  "username": "student21",
  "full_name": "Luis Perez",
  "career_code": "CS",
  "role": "student",
  "dob": "2001-09-01",
  "password": "password21"
}
```

### Registro de un nuevo préstamo

```bash
POST /api/v1/loan
{
  "isbn_code": "9780062873644",
  "username": "student21",
  "loan_date": "2024-05-24"
}
```

### Devolución de un libro prestado

```bash
PUT /api/v1/loan/return/{loan_id}
{
  "return_date": "2024-05-27"
}
```

### Listado de libros que deben ser entregados el día actual

```bash
GET /api/v1/report/loans-due-today
```

### Listado de préstamos en mora

```bash
GET /api/v1/report/loans-in-default
```

### Total de dinero recaudado en un intervalo de tiempo

```bash
GET /api/v1/report/total-revenue?start_date=2024-05-01&end_date=2024-05-31
```

Para más detalles sobre cómo utilizar la API, consulta la documentación de Swagger y el manual técnico.

## Contribuciones

Si deseas contribuir a este proyecto, por favor sigue los siguientes pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza los cambios necesarios y haz commit (`git commit -m 'Agregar nueva funcionalidad'`).
4. Sube los cambios a tu repositorio (`git push origin feature/nueva-funcionalidad`).
5. Abre un Pull Request.

## Licencia

Este proyecto está licenciado bajo los términos de la licencia MIT. Para más detalles, consulta el archivo `LICENSE`.

```

Este README proporciona una descripción completa del proyecto, instrucciones claras sobre cómo instalar y ejecutar la aplicación, ejemplos de uso de la API y detalles sobre la documentación y el manual técnico. Además, incluye una sección sobre cómo contribuir al proyecto y la licencia del mismo.