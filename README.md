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
- Upload images for posts (future scope)

### 👥 User Management
- User registration & login
- Manage profiles and roles

### 💬 Comment System
- Add comments on posts
- Fetch comments per post

### ⚙️ Database Support
- **PostgreSQL** (default)
- **MySQL** (switchable with config)

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

