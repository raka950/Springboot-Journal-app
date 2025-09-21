# JournalApp – RESTful Journal Management System

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-green)
![Docker](https://img.shields.io/badge/Docker-blue)
![MongoDB](https://img.shields.io/badge/MongoDB-4.4-green)
![Redis](https://img.shields.io/badge/Redis-6.2-orange)
![Kafka](https://img.shields.io/badge/Kafka-3.4-purple)

---

JournalApp is a modern journaling platform built with Spring Boot, integrating multiple technologies to provide a secure, scalable, and efficient journaling experience.

## 🎯 Features

* 🔒 **Secure REST APIs** with JWT and Basic Auth
* 📝 **CRUD Operations** for journal entries (MongoDB & JPA)
* ⚡ **Redis Caching** for high performance
* 📬 **Kafka Messaging** for event-driven architecture
* 📊 **Monitoring & Logging** with Spring Boot Actuator
* 🧪 **Unit & Integration Tests** for maintainability
* 🐳 **Dockerized** for easy deployment

## 🛠️ Tech Stack

| Technology  | Description                   |
| ----------- | ----------------------------- |
| Java 17     | Backend language              |
| Spring Boot | REST API framework            |
| MongoDB     | NoSQL database                |
| Redis       | In-memory caching             |
| Kafka       | Event streaming               |
| Docker      | Containerization              |
| Maven       | Build & dependency management |

## 🚀 Getting Started

### Prerequisites

* Java 17+
* Maven
* Docker (optional)

### Clone Repository

```bash
https://github.com/raka950/Springboot-Journal-app.git
```

### Build & Run

```bash
mvn clean install
mvn spring-boot:run
```

The application will run at [http://localhost:8080](http://localhost:8080)

### Docker Setup (Optional)

```bash
docker-compose up --build
```

This will start the app with MongoDB and Redis containers.

## 🔐 Authentication

* Obtain JWT token via login endpoint
* Use token in `Authorization: Bearer <token>` header for protected endpoints

## 📬 API Testing

Use Postman or any API client. Import a collection (optional) and include JWT in headers for protected routes.

## 🧪 Running Tests

```bash
mvn test
```

## 🌐 Example Requests

* **Default Weather (Patna)**: `GET /user`
* **Custom Weather (Delhi)**: `GET /user?city=Delhi`

## 📂 Project Structure

<details>
<summary>Click to expand</summary>

```
src/
├── main/
│   ├── java/com/example/journalapp/
│   │   ├── controller/
│   │   ├── service/
│   │   ├── repository/
│   │   ├── model/
│   │   ├── config/
│   │   ├── exception/
│   │   ├── security/
│   │   ├── util/
│   │   ├── cache/
│   │   └── messaging/
│   └── resources/
│       ├── application.yml
│       ├── logback-spring.xml
│       └── ...
└── test/
    └── java/com/example/journalapp/
```

</details>

## 📄 License

This project is licensed under the **GPL-3.0 License**.

## 📌 Notes

* Real-time weather API integration for user greeting
* Kafka messaging for asynchronous operations
* Redis caching for frequently accessed data

## 🎬 Demo

![Demo GIF](https://media.giphy.com/media/your-demo-gif.gif)

---

*Made with ❤️ using Spring Boot, Kafka, Redis & MongoDB*
