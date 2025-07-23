# Customer Inventory API

This is a Spring Boot application that exposes a REST endpoint to return customer inventory information based on the provided customer profile's integrationId. The API supports optional query parameters to include profile, referrals, coupons, loyalty, giveaways, and achievements in the response.

## Features
- Single REST endpoint: `GET /api/v1/customer_profiles/{integrationId}/inventory`
- Optional query parameters: `profile`, `referrals`, `coupons`, `loyalty`, `giveaways`, `achievements`
- API Key authentication (via `Authorization` header, must start with `api_key_v1`)
- Simulated response structure for demonstration

## Prerequisites
- Java 17+
- Maven 3.6+

## Build & Run

1. **Clone the repository:**
   ```bash
   git clone https://github.com/ascendion-linesh/Linesh.git
   cd Linesh/customer-inventory
   ```
2. **Build the project:**
   ```bash
   mvn clean package
   ```
3. **Run the application:**
   ```bash
   mvn spring-boot:run
   # or
   java -jar target/customer-inventory-1.0.0.jar
   ```

## Usage

### Request
```
GET http://localhost:8080/api/v1/customer_profiles/{integrationId}/inventory?profile=true&referrals=true
Authorization: api_key_v1 YOUR_API_KEY
```

### Query Parameters
- `profile` (boolean): Include customer profile information
- `referrals` (boolean): Include referral information
- `coupons` (boolean): Include coupon information
- `loyalty` (boolean): Include loyalty information
- `giveaways` (boolean): Include giveaways information
- `achievements` (boolean): Include achievement information

### Example Response
```
{
  "profile": {
    "id": 6,
    "created": "2020-02-07T08:15:22Z",
    "integrationId": "customer1",
    "attributes": {},
    "accountId": 31,
    "closedSessions": 3,
    "totalSales": 299.99,
    "loyaltyMemberships": [],
    "audienceMemberships": [],
    "lastActivity": "2020-02-08T14:15:20Z",
    "sandbox": false
  },
  "referrals": [{}]
}
```

## Notes
- The API key must be provided in the `Authorization` header and must start with `api_key_v1`.
- The application is stateless and does not persist data.

## License
MIT
