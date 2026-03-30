# Hospital JPA - Docker Setup

This document explains how to run the Hospital JPA application using Docker.

## Prerequisites

- Docker installed on your system
- Docker Compose (comes with Docker Desktop)

## Quick Start

### Option 1: Using Docker Compose (Recommended)

1. **Build the application JAR file:**
   ```bash
   cd hospital
   ./mvnw clean package -DskipTests
   cd ..
   ```

2. **Start the application with Docker Compose:**
   ```bash
   docker compose up -d
   ```

3. **Access the application:**
   - Application: http://localhost:8086
   - H2 Console: http://localhost:8086/h2-console
     - JDBC URL: `jdbc:h2:mem:hospital`
     - Username: `SA`
     - Password: (leave empty)

4. **View logs:**
   ```bash
   docker compose logs -f hospital-app
   ```

5. **Stop the application:**
   ```bash
   docker compose down
   ```

### Option 2: Using Docker CLI

1. **Build the application JAR file:**
   ```bash
   cd hospital
   ./mvnw clean package -DskipTests
   cd ..
   ```

2. **Build the Docker image:**
   ```bash
   docker build -t hospital-jpa:latest .
   ```

3. **Run the container:**
   ```bash
   docker run -d -p 8086:8086 --name hospital-app hospital-jpa:latest
   ```

4. **View logs:**
   ```bash
   docker logs -f hospital-app
   ```

5. **Stop and remove the container:**
   ```bash
   docker stop hospital-app
   docker rm hospital-app
   ```

## Configuration

### Environment Variables

You can customize the application by setting environment variables:

- `SPRING_PROFILES_ACTIVE`: Set the active Spring profile (default: `default`)
- `JAVA_OPTS`: JVM options (default: `-Xmx512m -Xms256m`)

Example with custom environment variables:
```bash
docker run -d -p 8086:8086 \
  -e SPRING_PROFILES_ACTIVE=prod \
  -e JAVA_OPTS="-Xmx1g -Xms512m" \
  --name hospital-app \
  hospital-jpa:latest
```

## Troubleshooting

### Port Already in Use

If port 8086 is already in use, you can map to a different port:
```bash
docker run -d -p 8087:8086 --name hospital-app hospital-jpa:latest
```

### Rebuild After Code Changes

After making code changes:
1. Rebuild the JAR: `cd hospital && ./mvnw clean package -DskipTests && cd ..`
2. Rebuild the Docker image: `docker compose build`
3. Restart: `docker compose up -d`

## Project Structure

```
.
├── Dockerfile              # Docker image configuration
├── docker-compose.yml      # Docker Compose configuration
├── .dockerignore          # Files to exclude from Docker build
└── hospital/              # Spring Boot application
    └── target/
        └── *.jar          # Built application JAR
```

## Notes

- The application uses an in-memory H2 database, so data will be lost when the container stops
- The Dockerfile uses a lightweight Alpine-based JRE image for minimal size
- The application is configured to restart automatically unless stopped manually
