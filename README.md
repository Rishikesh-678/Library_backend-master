
# 📚 Library Book Management System

A simple backend system to manage books in a small library. It allows users to **add**, **view**, **update**, and **delete** books through RESTful APIs built with Spring Boot and MySQL. It also supports **pagination**, **search filters**, and optional **Swagger** documentation.

---

## 🚀 Features

- 📖 Add new books  
- 🔍 View all books (with pagination and filtering)  
- 🔍 Search by title, author, genre, or published year  
- ✏️ Update book details  
- ❌ Delete books  
- 📄 API response contains book details including genre and published year  
- 🛡️ Field validation and global error handling  
- 🧪 REST API testable with Postman  
- 📄 *(Optional)* Swagger UI documentation

---

## 🛠️ Tech Stack

- **Java 21**
- **Spring Boot 3.5.3**
- **Spring Data JPA**
- **MySQL**
- **Apache Maven**
- **Postman**
- *(Optional)* Springdoc OpenAPI for Swagger UI

---

## 🗂️ Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com.example.library/
│   │       ├── controller/
│   │       ├── model/
│   │       ├── repository/
│   │       └── service/
│   └── resources/
│       └── application.properties
└── test/
```

---

## 📦 Setup Instructions

### 1. Clone the project

```bash
git clone https://github.com/Rishikesh-678/library-management-backend.git
cd library-management-backend
```

### 2. MySQL Setup

Create a database in MySQL:

```sql
CREATE DATABASE library_db;
```

### 3. Configure `application.properties`

Update your database credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/library_db
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 4. Build & Run the Application

```bash
./mvnw spring-boot:run
```

Application will run at:  
`http://localhost:8080`

---

## 🔗 API Endpoints

### 📘 Book APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | `/books` | Get all books (with pagination and filters) |
| GET    | `/books/{id}` | Get book by ID |
| POST   | `/books` | Add a new book |
| PUT    | `/books/{id}` | Update book details |
| DELETE | `/books/{id}` | Delete a book |

### 🧮 Pagination & Filtering

**Example:**

```http
GET /books?page=0&size=5&title=Harry&author=Rowling
```

**Supported Query Parameters:**
- `page` – page number (default = 0)
- `size` – number of items per page (default = 10)
- `title`, `author`, `genre`, `publishedYear` – optional filters

---

## 🧪 Example POST Request

```json
POST /books
Content-Type: application/json

{
  "title": "The Hobbit",
  "author": "J.R.R. Tolkien",
  "genre": "Fantasy",
  "publishedYear": 1937,
  "copies": 10
}
```

---

## 🛡️ Error Handling

Common error responses are returned with appropriate HTTP status codes and messages for:
- Validation errors
- Resource not found
- Database connection issues

---

## 📬 Contact

Made with ❤️ by [Rishikesh](https://github.com/Rishikesh-678)  
Feel free to contribute or report issues!
