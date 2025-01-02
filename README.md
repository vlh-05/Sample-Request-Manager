# Sample Request Management System

## Overview
The **Sample Request Management System** is a backend API built using **Kotlin** and **Spring Boot**, designed to manage sample requests for suppliers and their customers. It allows customers to create and retrieve product sample requests, with data stored in a database.

---

## Features
- **Create Sample Request**: Submit a new sample request and save it in the database.
- **Retrieve Sample Request by ID**: Fetch the details of an existing request.
- **Error Handling**: Validates input and provides appropriate error messages.
- **Optional Enhancements**:
  - Edit or delete sample requests.
  - Dockerize and deploy the application.
  - Add unit and integration testing.

---

## Tech Stack
- **Language**: Kotlin
- **Framework**: Spring Boot
- **Database**: PostgreSQL
- **Tools**: IntelliJ IDEA, Postman, Docker

---

## Project Phases

### Phase 1: Application Setup
- Set up a Kotlin Spring Boot application.
- Configure dependencies and verify the application runs locally.

### Phase 2: Database Setup
- Design database models:
  - `SampleRequest`
  - `Product`
  - `ProductVariant`
  - `Address`
- Configure a PostgreSQL database.
- Create database tables based on the models.

### Phase 3: API Development
#### CreateSampleRequest Endpoint
- **Request**:
```json
{
  "product_id": 2,
  "variant_id": 3,
  "sample_quantity": "3 bags",
  "sample_application": "Testing",
  "shipping_address": {
    "line_1": "123 Main St",
    "line_2": "",
    "city": "Chicago",
    "state": "IL",
    "zip_code": "60647"
  },
  "additional_information": "Test",
  "requestor": "John Doe"
}
```
## Response Example:
```json
{
  "success": true,
  "message": "successfully created sample request",
  "sampleRequest": { ... }
}
```

## GetSampleRequestByID Endpoint
- Accepts a request with the sample request ID.
- Retrieves the corresponding sample request details from the database.
- Returns an error if the ID does not exist.

### Request Example:
```json
{
  "sample_request_id": 1
}
```

### Response Example:
```json
{
  "success": true,
  "message": "successfully got sample request",
  "sampleRequest": { ... }
}
```

## Phase 4: Bells & Whistles (Optional)
- Implement additional APIs to:
  - Edit an existing sample request.
  - Delete/archive a sample request.
- Dockerize the application and host it on AWS/GCP/Azure.
- Add more fields for data enrichment.
- Write unit and integration tests to validate functionality.

---

## Getting Started

### Prerequisites
- JDK 11 or higher installed.
- Kotlin configured on your machine.
- PostgreSQL/MySQL database installed (or use Docker for database setup).
- IDE like IntelliJ IDEA.

### Setup Instructions
1. Clone the repository:
    ```bash
    git clone <repository-link>
    cd <project-folder>
    ```

2. Configure the database:
    - Update `application.properties` with your database credentials.
    - Example:
        ```properties
        spring.datasource.url=jdbc:postgresql://localhost:5432/sample_requests
        spring.datasource.username=<username>
        spring.datasource.password=<password>
        spring.jpa.hibernate.ddl-auto=update
        ```

3. Build and run the application:
    ```bash
    ./gradlew bootRun
    ```

4. Test the APIs using Postman or cURL:
    - **Create Sample Request:**
        ```bash
        curl -X POST http://localhost:8080/api/sample-request \
             -H "Content-Type: application/json" \
             -d '<JSON-Request-Body>'
        ```
    - **Get Sample Request by ID:**
        ```bash
        curl -X GET http://localhost:8080/api/sample-request/{id}
        ```

---

## Testing
- Thoroughly test the application using Postman or similar tools.
- Write unit tests using JUnit and integration tests using MockMVC (for Spring Boot).

### Example:
```kotlin
@Test
fun testCreateSampleRequest() {
    // Mock API input and validate database state
}
```

---

## Deployment

### 1. Optional: Dockerize the Application
- Create a Dockerfile for the application.
- Build and run the Docker container:
    ```bash
    docker build -t sample-request-api .
    docker run -p 8080:8080 sample-request-api
    ```

### 2. Deploy to Cloud
- Push the Docker image to AWS ECR, GCP Container Registry, or DockerHub.
- Deploy the container on ECS, Kubernetes, or similar platforms.


