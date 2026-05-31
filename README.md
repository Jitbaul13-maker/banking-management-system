# Banking Management System

A RESTful Banking Management System built with Spring Boot, Spring Security, JWT Authentication, JPA/Hibernate, and MySQL.

The project simulates core banking operations such as customer management, account handling, transactions, card management, and fixed deposits while following a layered architecture with DTOs, validation, exception handling, and JWT-based authentication.

---

## Features

### Authentication & Security
- JWT Access Token Authentication
- Refresh Token Mechanism
- Spring Security Integration
- BCrypt Password Encryption
- Stateless Session Management
- Protected REST APIs

### Customer Management
- Register new customers
- Update customer details
- Activate/Deactivate customers
- Retrieve customer information

### Account Management
- Create bank accounts
- View account details
- List all customer accounts
- Activate/Deactivate accounts

### Transaction Management
- Deposit funds
- Withdraw funds
- Balance updates

### Card Management
- Create cards
- View card details
- Activate/Deactivate cards
- List all customer cards

### Fixed Deposit Management
- Create fixed deposits
- View deposit details
- List deposits
- Deposit maturity date calculation

### Validation & Error Handling
- Bean Validation (Jakarta Validation)
- Centralized Exception Handling
- Custom Exceptions
- Meaningful API Responses

---

## Tech Stack

### Backend
- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate

### Database
- PostgreSQL

### Authentication
- JWT (Access & Refresh Tokens)

### Build Tool
- Maven

### Testing Tools
- Postman

---

## Project Structure

```text
src/main/java
├── controllers
├── services
├── repos
├── models
├── DTOs
├── config
├── filter
├── exception
└── enums
```

---

## Authentication Flow

### User Registration

```http
POST /auth/create
```

Creates a new customer account.

### Login

```http
POST /auth/login
```

Returns:
- Access Token (Response Body)
- Refresh Token (HttpOnly Cookie)

### Refresh Access Token

```http
POST /auth/refresh
```

Uses the refresh token cookie to generate a new access token.

### Protected APIs

All endpoints except `/auth/**` require a valid JWT access token.

```http
Authorization: Bearer <access_token>
```

---

## Main API Endpoints

### Customer APIs

```http
GET    /customers
GET    /customers/me
PATCH  /customers/me
PATCH  /customers/{custId}/activate
PATCH  /customers/{custId}/deactivate
```

### Account APIs

```http
POST   /customers/me/accounts
GET    /customers/me/accounts
GET    /customers/me/accounts/{accountId}
PATCH  /customers/{custId}/accounts/{accountId}/activate
PATCH  /customers/{custId}/accounts/{accountId}/deactivate
```

### Transaction APIs

```http
PATCH  /customers/me/accounts/{accountId}/deposit
PATCH  /customers/me/accounts/{accountId}/withdraw
```

### Card APIs

```http
POST   /customers/me/cards
GET    /customers/me/cards
GET    /customers/me/cards/{cardId}
PATCH  /customers/{custId}/cards/{cardId}/activate
PATCH  /customers/{custId}/cards/{cardId}/deactivate
```

### Deposit APIs

```http
POST   /customers/me/deposits
GET    /customers/me/deposits
GET    /customers/me/deposits/{depositId}
DELETE /customers/{custId}/deposits/{depositId}
```

---

## Running Locally

### Clone Repository

```bash
git clone https://github.com/<Jitbaul13-maker>/<banking-management-system>.git
```

### Configure Database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/banking_db
spring.datasource.username=root
spring.datasource.password=password
```

### Configure JWT Secrets

```properties
jwt.access-secret=MY_BASE64_SECRET
jwt.refresh-secret=MY_BASE64_SECRET
```

### Run Application

```bash
mvn spring-boot:run
```

Application starts on:

```text
http://localhost:8080
```

---

## Future Improvements

- Role-Based Authorization (Admin/User)
- Transfer Between Accounts
- Transaction History
- Account Statements
- Swagger/OpenAPI Documentation
- Docker Deployment
- Unit & Integration Testing
- Redis-based Refresh Token Storage
- Audit Logging

---

## Learning Outcomes

This project helped reinforce:

- Spring Boot REST API Development
- Layered Architecture
- JWT Authentication
- Spring Security
- DTO Mapping
- Validation
- Exception Handling
- JPA/Hibernate Relationships
- Database Design
- REST API Best Practices

---

## Author

Soumyajit Baul

- Java Backend Developer
- Spring Boot Enthusiast
- Open to Software Engineering Opportunities
