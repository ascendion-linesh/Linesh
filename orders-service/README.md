# Order Service - E-Commerce Microservice

This Spring Boot microservice manages order placement for an e-commerce system, integrating with user and rewards (Talon.One) services, persisting orders in PostgreSQL, and publishing order events to Kafka.

## Features
- Place orders via REST API
- Integrates with `user-service` (user details) and `rewards-service` (discounts via Talon.One)
- Persists orders to PostgreSQL
- Publishes order events to Kafka for downstream processing
- Robust error handling and logging
- API documentation via Swagger/OpenAPI

## Folder Structure
```
orders-service/src/
└── main/
    ├── java/com/example/orderservice/
    │   ├── controller/
    │   ├── dto/
    │   ├── entity/
    │   ├── exception/
    │   ├── repository/
    │   ├── service/
    │   └── talonone/
    │   └── OrderServiceApplication.java
    └── resources/
        ├── application.yml
        └── static/
```

## API Endpoints
### POST /orders
Place a new order.

**Request:**
```
{
  "userId": 12345,
  "cartItems": [
    { "productId": "p1", "quantity": 2 },
    { "productId": "p2", "quantity": 1 }
  ]
}
```

**Response:**
```
{
  "orderId": 67890,
  "userId": 12345,
  "cartItems": [ ... ],
  "totalAmount": 150.0,
  "discount": 10.0,
  "createdAt": "2023-10-01T12:34:56Z"
}
```

## Kafka Event
Order events are published to the `orders` topic in Kafka.

## Database
- PostgreSQL
- Flyway migration script: `V1__create_orders_table.sql`

## Configuration
See `src/main/resources/application.yml` for database, Kafka, and Feign client settings.

## Running the Service
1. Ensure PostgreSQL and Kafka are running.
2. Build the project:
   ```
   ./mvnw clean package
   ```
3. Run the application:
   ```
   ./mvnw spring-boot:run
   ```
4. Access Swagger UI at `http://localhost:8081/swagger-ui.html` (if springdoc-openapi is included).

## Testing
- Unit and integration tests are provided in the `src/test` directory.
- Run tests with:
  ```
  ./mvnw test
  ```

## Environment
- Java 17+
- Spring Boot 3.2.x+

---

**This completes the fully functional Spring Boot microservice named `order-service` with order placement, user/rewards integration, Kafka events, and best practices.**
