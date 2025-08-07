# User Service - Loyalty Rewards System

This Spring Boot microservice manages user-related functionalities for a loyalty rewards system, including integration with the Talon.One Campaign Rules Engine.

## Features
- User registration, retrieval, update, and deletion
- Integration with Talon.One for campaign and loyalty management
- PostgreSQL database for user persistence
- RESTful APIs

## Prerequisites
- Java 17+
- Maven 3.8+
- PostgreSQL database running at `localhost:5432/userdb` (default credentials: `postgres`/`postgres`)

## Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/ascendion-linesh/Linesh.git
   cd Linesh/user-service
   ```
2. Update the `application.yml` if your database credentials or Talon.One configuration differ.
3. Build the application:
   ```bash
   mvn clean package
   ```

## Running the Service
- Start the application:
  ```bash
  mvn spring-boot:run
  ```
- The service will be available at `http://localhost:8080`.

## API Endpoints
- `POST /users` - Register a new user
- `GET /users/{id}` - Retrieve user by ID
- `GET /users/email/{email}` - Retrieve user by email
- `PUT /users/{id}` - Update user details
- `DELETE /users/{id}` - Delete user by ID

## Example Request
```
POST /users
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "phone": "1234567890",
  "totalOrders": 5,
  "totalSpent": 250.75
}
```

## Notes
- All DB access is via JPA repositories.
- Use environment variables or secrets management for sensitive config.
- Exception handling and input validation should be extended for production use.

## Build JAR
- The application can be packaged as a JAR:
  ```bash
  mvn clean package
  java -jar target/user-service-*.jar
  ```
