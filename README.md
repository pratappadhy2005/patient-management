# 🏥 Patient Management Microservices System

A modular, event-driven **Patient Management System** built using Spring Boot microservices architecture. This project leverages **Apache Kafka** for asynchronous messaging, **PostgreSQL** for data persistence, and **RPC (via Feign or gRPC)** for direct service communication.

---

## 📦 Architecture Overview

### Microservices:

| Service              | Description                                                                 |
|----------------------|-----------------------------------------------------------------------------|
| `patient-service`    | Manages patient records and personal details.                              |
| `appointment-service`| Handles patient appointments, scheduling, and cancellations.               |
| `doctor-service`     | Maintains doctor information and availability.                             |
| `notification-service`| Sends alerts via email/SMS when appointments are booked or updated.       |
| `billing-service`    | Generates invoices and handles billing records.                            |
| `gateway-service`    | API Gateway for routing, load balancing, and auth (via Spring Cloud Gateway). |
| `registry-service`   | Service discovery using Netflix Eureka or Consul.                          |
| `config-service`     | Centralized configuration for all services (Spring Cloud Config).          |

---

## 🛠️ Tech Stack

- **Java 21 + Spring Boot 3.x**
- **PostgreSQL** – Data persistence for core services
- **Apache Kafka** – Event-driven communication
- **Feign Client** or **gRPC** – For synchronous RPC communication between services
- **Spring Cloud Netflix** – Service Discovery, Config, Gateway
- **Docker & Docker Compose** – Containerized development
- **Lombok**, **MapStruct**, **Spring Security**, **JWT**

---

## 🔄 Communication Patterns

### 📤 Kafka Events
- Asynchronous, decoupled event-based communication:
    - `patient-created` → consumed by `appointment-service`, `billing-service`
    - `appointment-booked` → triggers `notification-service`
    - `invoice-generated` → triggers audit logs

### 🔗 RPC (Feign or gRPC)
- Direct inter-service calls:
    - `appointment-service` → `doctor-service` (Check doctor availability)
    - `billing-service` → `patient-service` (Get patient info)
    - `gateway-service` → Routes RPC/REST calls to internal services

---

## 🧪 Local Development

### 🚀 Start Services (Using Docker Compose)
```bash
docker-compose up --build
