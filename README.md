# 📦 Spring Boot Pricing Service

Este proyecto es una aplicación RESTful desarrollada en Spring Boot que permite consultar el precio aplicable de un producto en una determinada fecha y para una marca específica. La aplicación usa una base de datos en memoria H2 y proporciona un endpoint REST para realizar las consultas.

---

## 📌 Requisitos

Para ejecutar esta aplicación, necesitas tener instalado:
- Java 17 o superior
- Maven 3.8+

---

## 📂 Estructura del Proyecto

```
├── src/main/java/com/inditex
│   ├── controller/
│   │   ├── GlobalExceptionHandler.java
│   │   ├── PriceApi.java
│   │   ├── PriceController.java
│   ├── exception/
│   │   ├── PriceNotFoundException.java
│   ├── model/
│   │   ├── Price.java
│   ├── repository/
│   │   ├── PriceRepository.java
│   ├── service/
│   │   ├── PriceService.java
│   ├── InditexApplication.java
├── src/main/resources/
│   ├── application.properties
│   ├── data.sql
├── src/test/java/com/inditex
│   ├── controller/
│   │   ├── PriceControllerTest.java
│   ├── service/
│   │   ├── PriceServiceTest.java
├── pom.xml
└── README.md
```

---

## 📊 Base de Datos

Se utiliza **H2** como base de datos en memoria, la cual se inicializa con los siguientes datos:

| BRAND_ID | START_DATE         | END_DATE           | PRICE_LIST | PRODUCT_ID | PRIORITY | PRICE  | CURR |
|----------|--------------------|--------------------|------------|------------|----------|--------|------|
| 1        | 2020-06-14 00:00:00 | 2020-12-31 23:59:59 | 1          | 35455      | 0        | 35.50  | EUR  |
| 1        | 2020-06-14 15:00:00 | 2020-06-14 18:30:00 | 2          | 35455      | 1        | 25.45  | EUR  |
| 1        | 2020-06-15 00:00:00 | 2020-06-15 11:00:00 | 3          | 35455      | 1        | 30.50  | EUR  |
| 1        | 2020-06-15 16:00:00 | 2020-12-31 23:59:59 | 4          | 35455      | 1        | 38.95  | EUR  |

Los datos se encuentran en [`data.sql`](src/main/resources/data.sql).

---

## 🚀 Ejecución de la Aplicación

Para ejecutar la aplicación, usa el siguiente comando:

```bash
mvn spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`.

---

## 📡 Endpoint REST

### Consultar precio de un producto

**Endpoint:**
```
GET /api/prices
```

**Parámetros de entrada:**
- `date` (Formato: `yyyy-MM-dd HH:mm:ss`)
- `productId` (ID del producto)
- `brandId` (ID de la marca)

**Ejemplo de llamada:**
```bash
curl -X GET "http://localhost:8080/api/prices?date=2020-06-14 16:00:00&productId=35455&brandId=1"
```

**Respuesta esperada:**
```json
{
  "productId": 35455,
  "brandId": 1,
  "priceList": 2,
  "startDate": "2020-06-14 15:00:00",
  "endDate": "2020-06-14 18:30:00",
  "price": 25.45
}
```

---

## 🧪 Pruebas y Cobertura de Código

Se han desarrollado pruebas unitarias y de integración en los siguientes archivos:

- **Controlador:** [`PriceControllerTest.java`](src/test/java/com/inditex/controller/PriceControllerTest.java)
- **Servicio:** [`PriceServiceTest.java`](src/test/java/com/inditex/service/PriceServiceTest.java)

Los siguientes casos de prueba han sido implementados:

| Test | Fecha de consulta | Producto | Marca | Resultado esperado |
|------|------------------|----------|-------|-------------------|
| 1    | 2020-06-14 10:00:00 | 35455 | 1 | Tarifa 1 - 35.50 EUR |
| 2    | 2020-06-14 16:00:00 | 35455 | 1 | Tarifa 2 - 25.45 EUR |
| 3    | 2020-06-14 21:00:00 | 35455 | 1 | Tarifa 1 - 35.50 EUR |
| 4    | 2020-06-15 10:00:00 | 35455 | 1 | Tarifa 3 - 30.50 EUR |
| 5    | 2020-06-16 21:00:00 | 35455 | 1 | Tarifa 4 - 38.95 EUR |

Para ejecutar las pruebas:

```bash
mvn test
```

Además, se ha configurado **JaCoCo** para asegurar que al menos el **50% del código y las ramas de ejecución** estén cubiertas por pruebas.

---

## 🛠️ Análisis de Código con SonarQube

Se ha utilizado **SonarQube** para analizar la calidad del código.  
Resultado del análisis:
- **1 issue encontrado en `PriceController.java`**: Se recomienda **reemplazar la inyección de dependencias por campo con inyección por constructor**.
- No se han encontrado vulnerabilidades críticas ni bugs graves.

Para ejecutar el análisis con SonarQube:

```bash
mvn clean verify sonar:sonar
```

---

## 📜 Licencia

Este proyecto está bajo la **Licencia MIT**.

---

¡Gracias por usar esta aplicación! 🚀
