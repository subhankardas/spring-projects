# 🎬 Spring Boot gRPC Multi-Module Application

This project demonstrates a multi-module Spring Boot setup where one service (Movie Service) exposes **gRPC endpoints**, and another service (Booking Service) acts as a **gRPC client** to consume them. It includes examples of all four types of gRPC calls:

- Unary RPC
- Server-streaming RPC
- Client-streaming RPC
- Bi-directional streaming RPC

---

## 📁 Project Structure

```

spring-boot-grpc/
├── common-proto/          # Shared proto files & generated classes
├── movie-service/         # gRPC server exposing movie-related services
├── booking-service/       # gRPC client calling movie-service

````

---

## 📦 Modules

### 1. `common-proto`
Holds the `.proto` definitions and generates gRPC Java classes using `protobuf-maven-plugin`.

### 2. `movie-service`
Spring Boot application that:
- Uses `H2` in-memory database
- Exposes gRPC endpoints using the generated proto stubs
- Implements all 4 gRPC call types

### 3. `booking-service`
Spring Boot client app that:
- Uses gRPC stubs from `common-proto`
- Connects to `movie-service` via gRPC
- Calls and demonstrates unary, streaming, and bidi-streaming RPC

---

## 🚀 Getting Started

### 🛠️ Prerequisites

- Java 17+
- Maven 3.6+
- gRPC plugin (managed via Maven)
- Port 9090 open for gRPC server

---

## 📥 Build Instructions

From the root of the project:

```bash
# Clean and install all modules
mvn clean install -Padd-generated-sources
````

### Run `movie-service` (gRPC Server)

```bash
mvn spring-boot:run -pl movie-service
```

### Run `booking-service` (gRPC Client)

```bash
mvn spring-boot:run -pl booking-service
```

---

## 📝 gRPC Protobuf Overview

```protobuf
service MovieService {
  rpc getMovie(GetMovieRequest) returns (MovieDetails);                         // Unary
  rpc getMoviesByGenre(GetMoviesByGenreRequest) returns (stream MovieDetails);  // Server streaming
  rpc saveMovies(stream MovieDetails) returns (SaveMoviesResponse);             // Client streaming
  rpc getRatings(stream GetMovieRequest) returns (stream MovieRating);          // Bi-directional
}
```

---

## 🛠 H2 Database Console

The movie-service uses H2 in-memory DB.

H2 Console: `http://localhost:8080/h2-console`
JDBC URL: `jdbc:h2:mem:moviesdb`
Username: `admin`
Password: `password`

---

## 📚 Resources

* [gRPC Java Documentation](https://grpc.io/docs/languages/java/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [H2 Database](https://www.h2database.com/)

---

## 📦 License

MIT License
