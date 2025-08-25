# 📝 Rishman Blog APIs (Spring Boot + JWT Security)

A **Spring Boot backend service** that powers the **Rishman Blog** application.  
It provides secure **RESTful APIs** for managing **users, roles, posts, categories, and comments**, with **JWT-based authentication** and **role-based authorization**.

---

## 🚀 Features

### 🔐 Security
- JWT Authentication (Login & Signup)
- Role-based access control (**Admin & User**)
- Passwords stored securely using **BCrypt**

### 📖 Blog Management
- Create, update, delete, and view posts
- Categorize posts under multiple categories
- Upload images for posts *(future scope)*

### 👥 User Management
- User registration & login
- Manage profiles and roles

### 💬 Comment System
- Add comments on posts
- Fetch comments per post

### ⚙️ Database Support
- **PostgreSQL** (default)
- **MySQL** (switchable via config)

---

## 🛠 Tech Stack

- **Spring Boot 3+**
- **Spring Security + JWT**
- **Hibernate + JPA**
- **PostgreSQL**
- **Maven**
- **Lombok**
- **ModelMapper**
- **JUnit + Mockito** (for testing)

---

## 📂 Project Structure

```
rishman-blog-app-backend/
├── src/
│   ├── main/
│   │   ├── java/com/rishman/blog/
│   │   │   ├── config/          # Security, Swagger, constants
│   │   │   ├── controller/      # REST API endpoints
│   │   │   ├── entity/          # JPA entities (User, Post, Category, etc.)
│   │   │   ├── exception/       # Custom exceptions & global handler
│   │   │   ├── payload/         # DTOs for API requests/responses
│   │   │   ├── repository/      # Spring Data JPA repositories
│   │   │   ├── security/        # JWT utilities, filters, user details
│   │   │   └── service/         # Service interfaces & implementations
│   │   └── resources/
│   │       ├── application.properties        # Default configuration
│   │       ├── application-dev.properties    # Development profile
│   │       └── application-prod.properties   # Production profile
├── Dockerfile                   # Containerization setup
├── pom.xml                      # Maven dependencies & build config
└── README.md                    # Project documentation
```

---

## ⚙️ Prerequisites

- **Java 17+** (required for Spring Boot 3)
- **Maven 3.6+**
- **PostgreSQL** (default)
- **Docker** (optional for containerized deployment)

---

## 🛠 Setup & Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/rishman-blog-app-backend.git
   cd rishman-blog-app-backend
   ```

2. **Create the database**
   ```sql
   CREATE DATABASE rishman_blog;
   ```

3. **Configure database credentials**
   - Update [`application.properties`](src/main/resources/application.properties) with your DB details
   - Use [`application-dev.properties`](src/main/resources/application-dev.properties) for development

---

## ▶️ Running the Application

✅ Deployed on **Render** — Explore all APIs via Swagger:  
**[Live Swagger Docs](https://rishman-blog-app-backend-3.onrender.com/swagger-ui/index.html)**

---
