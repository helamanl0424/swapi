# Star Wars API (SWAPI)

## Introduction

The Star Wars API (SWAPI) is a Spring Boot-based application designed to manage and serve data related to the Star Wars universe. It allows users to interact with a database that stores information about Star Wars characters, their associated starships, and planets. This project demonstrates Spring Boot and JPA's capabilities in handling relational data efficiently.

## Features

- Manage character data, including their names, home planets, and starships.
- Perform CRUD operations on characters, planets, and starships.
- Retrieve detailed associations between characters, their starships, and home planets.
- Custom native SQL queries to demonstrate complex data retrieval in a relational database.

## How to Use

### Prerequisites

- Java 11 or later
- Maven (for building the project)
- PostgreSQL database

### Setting Up

1. **Clone the repository:**

2. **Configure PostgreSQL:**
- Ensure PostgreSQL is installed and running on your machine.
- Create a database if you do not have one and configure the `application.properties` with the correct URL, username, and password.

3. **Build the project:**

4. **Run the application:**
- SwapiApplication is the main class
  
5. **Access the API:**
- The API for characters is accessible via `http://localhost:8080/api/people`.
- Use similar endpoints for accessing starships and planets as configured in your controllers.

### API Endpoints

- **GET `/api/people`**: List all characters.
- **POST `/api/people`**: Add a new character.
- **GET `/api/people/{id}`**: Retrieve a character by ID.
- **PUT `/api/people/{id}`**: Update a character's details.
- **DELETE `/api/people/{id}`**: Delete a character.

Replace `people` with `starships` or `planets` as necessary to interact with other data types.

Unique endpoint for characters only to retrieve a character name with their associated starships'name and home planet's name:
- **GET `/api/people/details/{id}`**

## Possible Improvements

- **Authentication and Authorization**: Implement security measures to protect the API with JWT or OAuth2.
- **API Documentation**: Use Swagger or Spring REST Docs to generate API documentation automatically.
- **Advanced Search Capabilities**: Integrate advanced search options for filtering data based on multiple criteria.
- **Containerization**: Dockerize the application to simplify deployment and ensure consistency across different environments.
- **Continuous Integration/Continuous Deployment (CI/CD)**: Set up pipelines to automate testing and deployment processes.

## Conclusion

This project serves as a foundation for a comprehensive Star Wars API. It is designed to be extended and can be tailored to include more detailed interactions and data types related to the expansive Star Wars universe.

