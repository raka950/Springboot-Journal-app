# 📓 JournalApp  

A **modern journaling API** built with **Spring Boot 3, Spring Security 6, and MongoDB**.  
JournalApp makes it easy for users to **create, manage, and secure personal journal entries**—think of it as your private diary backend 📝.

---

## ✨ Features

- 🔐 **User Authentication & Security**
  - Register & login with secure password hashing (BCrypt).
  - Role-based access with Spring Security.

- 🗒 **Journal Management**
  - Create, read, update, and delete journal entries.
  - Retrieve all entries for a specific user.

- 💾 **Database Integration**
  - Powered by MongoDB (supports both local & Atlas).
  - Auto-index creation for efficient queries.

- ⚡ **Developer Friendly**
  - RESTful API ready for frontend or mobile integration.
  - Works smoothly with Postman / cURL for testing.

---

## 🛠 Tech Stack

- **Backend:** Spring Boot 3.x, Spring Security 6.x  
- **Database:** MongoDB / MongoDB Atlas  
- **Language:** Java 17+  
- **Build Tool:** Maven  
- **Other:** Lombok, BCryptPasswordEncoder  

---

## 🚀 Getting Started

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/chotabheeeeem/journalApp.git
cd journalApp
```

### 2️⃣ Configure MongoDB Connection
Edit `src/main/resources/application.properties` depending on your environment:

#### 👉 Local MongoDB
```properties
spring.data.mongodb.uri=mongodb://localhost:27017/journaldb
server.port=8081
```

#### 👉 MongoDB Atlas
```properties
spring.data.mongodb.uri=mongodb+srv://<username>:<password>@cluster0.mongodb.net/journaldb?retryWrites=true&w=majority&ssl=true
server.port=8081
```

### 3️⃣ Run the App
```bash
mvn spring-boot:run
```

---

## 📡 API Endpoints

| Method   | Endpoint                          | Description                  | Auth |
|----------|-----------------------------------|------------------------------|------|
| `POST`   | `/users`                          | Register a new user          | ❌ No |
| `GET`    | `/journal/{userName}`             | Get all entries for a user   | ✅ Yes |
| `POST`   | `/journal/{userName}`             | Create a new journal entry   | ✅ Yes |
| `GET`    | `/journal/id/{id}`                | Get a journal entry by ID    | ✅ Yes |
| `PUT`    | `/journal/id/{userName}/{id}`     | Update a journal entry by ID | ✅ Yes |
| `DELETE` | `/journal/id/{userName}/{id}`     | Delete a journal entry by ID | ✅ Yes |

---

## 🔒 Security Notes
- Passwords are stored **only in hashed form** using BCrypt.  
- API uses **HTTP Basic Auth** and form login.  
- CSRF is disabled for easier API testing in Postman.  

---

## 🌱 Future Improvements
- ✅ JWT-based authentication  
- ✅ Role-based admin panel  
- ✅ Docker & Kubernetes deployment  
- ✅ Frontend integration (React/Angular)  

---

## 🤝 Contributing
Contributions, issues, and feature requests are welcome!  
Feel free to fork this repo and open a PR.  

---

## 📜 License
This project is licensed under the **MIT License**.  
See the [LICENSE](LICENSE) file for details.  

---

> _“Your mind is for having ideas, not holding them.”_ – David Allen  
> JournalApp helps you free your mind by keeping your thoughts safe 🖊️  
