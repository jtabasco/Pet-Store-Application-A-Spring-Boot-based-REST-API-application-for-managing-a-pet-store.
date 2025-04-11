# 🐾 Pet Store Application

A Spring Boot-based REST API application for managing a pet store. This application provides endpoints for managing pet stores, customers, employees, and related operations.

## 🚀 Features

- RESTful API endpoints for pet store management
- Create and modify pet store
- MySQL database integration
- Spring Data JPA for data persistence
- Lombok for reducing boilerplate code
- Global error handling
- Comprehensive testing setup

## 🛠️ Technologies Used

- Java 17
- Spring Boot 3.4.4
- Spring Data JPA
- MySQL
- Lombok
- Maven

## 📋 Prerequisites

- JDK 17 or later
- Maven 3.6 or later
- MySQL Server
- Git

## 🔧 Installation

1. Clone the repository:
```bash
git clone https://github.com/jtabasco/pet-store.git
```

2. Navigate to the project directory:
```bash
cd pet-store
```

3. Configure your MySQL database connection in `application.properties`

4. Build the project:
```bash
mvn clean install
```

5. Run the application:
```bash
mvn spring-boot:run
```

## 📝 API Endpoints

### Pet Store Endpoints

- `POST /pet_store` - Create a new pet store
- `PUT /pet_store/{petStoreId}` - Update an existing pet store


### Example Request

```json
{
  "petStoreName": "Happy Pets Store",
  "petStoreAddress": "123 Main Street",
  "petStoreCity": "New York",
  "petStoreState": "NY",
  "petStoreZip": "10001",
  "petStorePhone": "212-555-1234"
}
```

## 🏗️ Project Structure

- `controller` - REST controllers for handling HTTP requests
- `service` - Business logic layer
- `dao` - Data Access Objects for database operations
- `entity` - JPA entities representing database tables
- `model` - Data Transfer Objects (DTOs) for API requests/responses

## 🔍 Error Handling

The application includes a global error handler that provides consistent error responses across all endpoints. For example, when a resource is not found, a 404 Not Found response is returned with a descriptive message.

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👤 Author

- **Joel Tabasco**
  - Email: jtabasco41@gmail.com
  - GitHub: [jtabasco](https://github.com/jtabasco)
  - Video: [Video week 13](https://youtu.be/bmJ8fLF-Z3Y)
  - Video: [Video week 14](https://youtu.be/Tw-xRZWt5tU)

## 🙏 Acknowledgments

- Spring Boot team for the amazing framework
- All contributors who have helped with this project 
