# User Service - Loyalty Rewards System

This Spring Boot microservice manages user-related functionalities for a loyalty rewards system, including integration with the Talon.One Campaign Rules Engine.

## Folder Structure
```
user-service/src/
└── main/
    ├── java/com/example/userservice/
    │   ├── controller/
    │   ├── dto/
    │   ├── entity/
    │   ├── exception/
    │   ├── repository/
    │   ├── service/
    │   └── talonone/
    └── resources/
        ├── application.yml
        └── static/
```

## Entity Model: User
```java
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private int totalOrders;
    private double totalSpent;
    // Getters and Setters
}
```

## API Endpoints

### POST /users
Register a new user and register them in Talon.One.
#### Request:
```json
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "phone": "1234567890",
  "totalOrders": 5,
  "totalSpent": 250.75
}
```
#### Response:
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com",
  "phone": "1234567890",
  "totalOrders": 5,
  "totalSpent": 250.75
}
```

### GET /users/{id}
Retrieve user by ID.

### GET /users/email/{email}
Retrieve user by email.

### PUT /users/{id}
Update user details.
#### Request:
```json
{
  "name": "Jane Doe",
  "email": "jane.doe@example.com",
  "phone": "9876543210",
  "totalOrders": 10,
  "totalSpent": 500.0
}
```
#### Response:
```json
{
  "id": 1,
  "name": "Jane Doe",
  "email": "jane.doe@example.com",
  "phone": "9876543210",
  "totalOrders": 10,
  "totalSpent": 500.0
}
```

### DELETE /users/{id}
Delete user by ID.

## Database Configuration (application.yml)
```
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/userdb
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
server:
  port: 8080
```

## Talon.One Integration
- User registration triggers a call to Talon.One (see `TalonOneClient.java`).
- Replace the placeholder with real API integration as needed.

## Security & Best Practices
- Input validation should be added to controllers.
- Use environment variables or secrets management for sensitive config.
- All DB access is via JPA repositories.

## Running the Service
- Ensure PostgreSQL is running and accessible.
- Build and run the Spring Boot application (`mvn spring-boot:run` or via your IDE).
- Test endpoints using Postman or Swagger UI (if enabled).
