# 🐾 Pet Store Application

A Spring Boot-based REST API application for managing a pet store. This application provides endpoints for managing pets, their information, and related operations.

## 🚀 Features

- RESTful API endpoints for pet management
- MySQL database integration
- Spring Data JPA for data persistence
- Lombok for reducing boilerplate code
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

## 📝 Configuration

The application uses the following default configuration:
- Server port: 8080
- Database: MySQL
- JPA properties are configured for development

## 🧪 Testing

Run the tests using:
```bash
mvn test
```

## 📚 API Documentation

The API documentation will be available at:
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👤 Author

- **Joel Tabasco**
  - Email: jtabasco41@gmail.com
  - GitHub: [jtabasco](https://github.com/jtabasco)

## 🙏 Acknowledgments

- Spring Boot team for the amazing framework
- All contributors who have helped with this project 
