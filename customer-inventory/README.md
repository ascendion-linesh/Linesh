# Customer Inventory API

This Spring Boot application provides a REST API to retrieve customer inventory information, including profile, referrals, loyalty, coupons, giveaways, and achievements, based on the provided integrationId. The API is designed to mimic the Talon.One customer inventory endpoint structure.

## Features
- Retrieve customer profile by integrationId
- Optionally include referrals, coupons, loyalty, giveaways, and achievements in the response
- Structured JSON response
- Basic error handling for unauthorized and not found cases

## Prerequisites
- Java 17+
- Maven 3.6+

## Running the Application

1. Clone the repository:
   ```
   git clone https://github.com/ascendion-linesh/Linesh.git
   cd Linesh/customer-inventory
   ```
2. Build the application:
   ```
   mvn clean package
   ```
3. Run the application:
   ```
   java -jar target/customer-inventory-0.0.1-SNAPSHOT.jar
   ```

## API Usage

### Endpoint
```
GET /v1/customer_profiles/{integrationId}/inventory
```

#### Path Parameters
- `integrationId` (string): The integration ID of the customer profile.

#### Query Parameters (all optional, boolean)
- `profile`: Include customer profile information
- `referrals`: Include referral information
- `coupons`: Include coupon information
- `loyalty`: Include loyalty information
- `giveaways`: Include giveaways information
- `achievements`: Include achievement information

#### Example Request
```
GET http://localhost:8080/v1/customer_profiles/customer1/inventory?profile=true&referrals=true
```

#### Example Response
```
{
  "profile": { ... },
  "loyalty": { ... },
  "referrals": [ ... ],
  "coupons": [ ... ],
  "giveaways": [ ... ],
  "achievements": [ ... ]
}
```

## Notes
- This application uses in-memory data for demonstration.
- Extend the repository and service layers to connect to a real database as needed.

## License
MIT
