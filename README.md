# hello-spring

Java 21 + Spring Boot 3.4.12

## Run
- Port: 8081
- DB: Postgres `demo_db` (localhost:5432)
- In IntelliJ Run/Debug Configuration (HelloApplication), set env vars:
    - `DB_USER=postgres`
    - `DB_PASS=your_password`

## Endpoints

- POST `/api/todos`  
  Body (JSON):
  ```json
  { "title": "Learn Spring" }