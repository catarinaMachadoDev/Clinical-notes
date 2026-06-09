# Clinical Notes API

A RESTful API for managing clinical notes in a healthcare context, built with Java and Spring Boot.

## Tech Stack

- Java 21
- Spring Boot 3.3
- PostgreSQL
- Docker & Docker Compose
- JUnit 5 + Mockito
- Maven

## Features

- Create, read, update and delete clinical notes
- Query notes by patient ID
- Automatic timestamp on note creation
- Full unit test coverage on the service layer
- Fully containerised with Docker

## Running the project

Make sure you have Docker Desktop running, then:

```bash
docker-compose up --build
```

The API will be available at `http://localhost:8181`.

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/notes | Get all notes |
| GET | /api/notes/{id} | Get note by ID |
| GET | /api/notes/patient/{patientId} | Get notes by patient |
| POST | /api/notes | Create a new note |
| PUT | /api/notes/{id} | Update a note |
| DELETE | /api/notes/{id} | Delete a note |

## Example Request

```json
POST /api/notes
{
  "patientId": "P001",
  "content": "Patient reports mild fever and fatigue for 3 days.",
  "authorName": "Catarina Machado"
}
```

## Running Tests

```bash
./mvnw test
```
