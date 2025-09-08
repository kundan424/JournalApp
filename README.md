# JournalApp

## Overview

JournalApp is a full-featured journaling application that allows users to create, manage, and reflect on their daily entries. It provides a secure and personal space for users to record their thoughts, experiences, and feelings. The application is built with a modern tech stack, including Spring Boot, MongoDB, and JWT for security.

## Features

* **User Authentication:** Secure user registration and login with JWT-based authentication.
* **Journal Management:** Create, read, update, and delete journal entries.
* **Sentiment Analysis:** Weekly email summaries of the user's overall sentiment based on their journal entries.
* **Weather Integration:** Greets the user with the current weather in their city.
* **Admin Panel:** An admin interface to view all registered users.

## Tech Stack

* **Backend:** Spring Boot, Spring Security, Spring Data MongoDB
* **Database:** MongoDB
* **Caching:** Redis
* **Authentication:** JSON Web Tokens (JWT)
* **Build Tool:** Maven

## Getting Started

### Prerequisites

* Java 17 or later
* Maven 3.2+
* MongoDB
* Redis
* An SMTP server for sending emails (e.g., Gmail's SMTP server)

### Installation

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/your-username/JournalApp.git](https://github.com/your-username/JournalApp.git)
    cd JournalApp
    ```

2.  **Configure the application:**
    Open `src/main/resources/application.properties` and update the following properties:
    ```properties
    # MongoDB Configuration
    spring.data.mongodb.uri=mongodb://localhost:27017/journal-app

    # Redis Configuration
    spring.redis.host=localhost
    spring.redis.port=6379

    # Email Configuration
    spring.mail.host=smtp.gmail.com
    spring.mail.port=587
    spring.mail.username=your-email@gmail.com
    spring.mail.password=your-app-password
    spring.mail.properties.mail.smtp.auth=true
    spring.mail.properties.mail.smtp.starttls.enable=true

    # Weather API Key
    weather.api.key=your_weatherstack_api_key

    # JWT Secret Key
    jwt.secret.key=your_super_secret_key
    ```

3.  **Build and run the application:**
    ```bash
    mvn spring-boot:run
    ```
    The application will be running on `http://localhost:8080`.

## API Endpoints

### Public Endpoints

* `POST /public/signup`: Register a new user.
* `POST /public/login`: Log in and receive a JWT.

### User Endpoints (`/user`)

* `GET /`: Get a greeting with the current weather.
* `PUT /`: Update the authenticated user's profile.
* `DELETE /`: Delete the authenticated user's account.

### Journal Endpoints (`/journal`)

* `GET /`: Get all journal entries for the authenticated user.
* `POST /`: Create a new journal entry.
* `GET /id/{myId}`: Get a journal entry by its ID.
* `DELETE /id/{myId}`: Delete a journal entry by its ID.
* `PUT /id/{myId}`: Update a journal entry by its ID.

### Admin Endpoints (`/admin`)

* `GET /all-users`: Get a list of all users (requires ADMIN role).
* `POST /create-admin-user`: Create a new user with ADMIN privileges.

```
## Project Structure
JournalApp/
├── src/
│ ├── main/
│ │ ├── java/com/example/JournalApp/
│ │ │ ├── controller/ # API controllers
│ │ │ ├── entity/ # MongoDB document models
│ │ │ ├── repository/ # Spring Data repositories
│ │ │ ├── service/ # Business logic
│ │ │ └── ...
│ └── test/
├── .gitignore
├── mvnw
├── pom.xml
└── README.md
```
