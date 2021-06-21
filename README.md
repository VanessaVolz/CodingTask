# CodingTask

CodingTask is a Java 11 REST project with Spring Boot made to accomplish Luxoft client's coding task. It has the main purpose of consuming plain text comma-separated files 
via HTTP and storing it's valid records to H2 in-memory database, and providing HTTP endpoints for getting records by id from database and deleting it, the last one in case 
of service owner authentication.

## Dependencies
- Spring Boot 2.5.1
- Spring Web
- Spring Data JPA
- Spring Security
- H2
- Apache Commons CSV 1.8

## Execution
Please refer to Postman Collection for sending requests [here](CodingTask.postman_collection.json).
