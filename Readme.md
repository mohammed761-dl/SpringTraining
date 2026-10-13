# SpringStudyEmployeeM 🚀

## Spring Boot — Mn Zero 7ta Professional

Had repository howa projet personnel li drto bach nt3ellem Spring Boot b tari9a pratique, nfhem kifach REST APIs
kaykhdmo, w nbni backend applications b architecture n9iya w professional.

L'objectif machi ghir nkteb code kaykhdem, walakin nfhem **3lach kanst3mlo kol concept, kifach kaykhdem, w fin khasni
nst3mlo f real-world projects.**

Had README howa documentation dyal projet w f nafs lwa9t notebook dyali bach nrje3 lih f ay wa9t.

---

# Table of Contents

1. Introduction to Spring Boot
2. Spring vs Spring Boot
3. Project Architecture (MVC)
4. REST API & HTTP Methods
5. HTTP Status Codes
6. Spring Boot Annotations
7. Dependency Injection & IoC
8. Spring Beans
9. Controller, Service & Repository
10. DTO & Entity
11. Database & Spring Data JPA
12. Relationships
13. Validation
14. Exception Handling
15. Spring Security
16. JWT Authentication
17. Testing
18. Advanced Spring Boot
19. Professional Backend Architecture
20. Roadmap: Beginner to Elite

---

# Chapter 01 — Introduction to Spring Boot

## Chno howa Spring Boot?

Spring Boot howa framework مبني 3la Spring Framework, kay3awenna nbniw Java backend applications b configuration 9lila.

B Spring Boot n9dro nsaybo:

- REST APIs
- Web Applications
- Microservices
- Enterprise Applications
- Backend dyal Mobile Applications

### 3lach kanst3mlo Spring Boot?

7it kayوفر lina:

- Auto Configuration
- Embedded Server (Tomcat by default f Spring MVC Web Starter)
- Dependency Injection
- Database Integration
- Security Integration
- Production-ready Features

### Important Concept

Spring Boot maشي howa Spring Framework kamel. Howa kayسهّل configuration w startup dyal Spring applications.

---

# Chapter 02 — Spring vs Spring Boot

| Spring Framework                  | Spring Boot              |
|-----------------------------------|--------------------------|
| Framework principal               | Mabni 3la Spring         |
| Configuration kat9der tkoun ktira | Auto Configuration       |
| Setup manual f بزاف dyal cases    | Starter Dependencies     |
| Kayوفر ecosystem                  | Kayسهّل استعمال ecosystem |

### Chno howa Embedded Server?

F Spring Boot n9dro nkhdmo application directement bla ma ninstalliw Tomcat بوحدو.

```bash
mvn spring-boot:run
```

Default port:

```text
http://localhost:8080
```

N9dro nبدلو port f `application.properties`:

```properties
server.port=8081
```

---

# Chapter 03 — Project Architecture (MVC)

MVC kat3ni:

- Model
- View
- Controller

## Chno howa MVC?

MVC howa architectural pattern kay9sem responsibilities bach code ykoun organized.

### Model

Kayrepresenti data w domain dyal application.

Example:

```java
public class Employee {

    private UUID id;
    private String firstname;
    private String lastname;
    private String email;
}
```

### View

Hya presentation layer li user kaychouf.

F REST API dyalna, kanرجعو JSON بدل HTML View. Frontend (React, Angular...) y9der yستهلك had JSON.

### Controller

Howa li kayستقبل HTTP requests w kayرجع responses.

Example:

```java

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @GetMapping
    public ResponseEntity<String> getEmployees() {
        return ResponseEntity.ok("Employees");
    }
}
```

## Professional Layered Architecture

F real projects, MVC بوحدها machi كافية باش نشرحو organization dyal backend. Ghaliban kanst3mlo layered architecture:

```text
Client (Frontend / Postman)
          |
          v
      Controller
          |
          v
       Service
          |
          v
      Repository
          |
          v
       Database
```

### Responsibilities

**Controller:** Kayستقبل requests, kayدير input validation w kayرجع HTTP responses.

**Service:** Fih Business Logic.

**Repository:** Kayتواصل m3a Database.

**Entity:** Katrepresenti domain object, w m3a JPA katكون mapped l database.

**DTO:** Object li kanst3mloh bach nنقلو data bin API w client bla ma nkشفو Entity مباشرة.

### Recommended Project Structure

```text
src/main/java/com/spring/trainingspring/
│
├── controller/
│   └── EmployeeController.java
│
├── service/
│   ├── EmployeeService.java
│   └── impl/
│       └── EmployeeServiceImpl.java
│
├── repository/
│   └── EmployeeRepository.java
│
├── entities/
│   └── Employee.java
│
├── dto/
│   ├── EmployeeRequestDTO.java
│   └── EmployeeResponseDTO.java
│
├── exception/
│   ├── GlobalExceptionHandler.java
│   └── ResourceNotFoundException.java
│
├── config/
│
└── TrainingSpringApplication.java
```

**Important:** F projet actuel, kanستعملو ArrayList f Controller. Hadi مناسبة للتعلم, walakin data katضيع fach
application katrestarta. Hadchi 3lach ghadi nحتاجو Database w Repository layer.

---

# Chapter 04 — REST API & HTTP Methods

REST howa architectural style kanستعملوه bach نبنيو APIs li kayتواصلو b HTTP.

## CRUD Operations

CRUD kat3ni:

- Create
- Read
- Update
- Delete

| HTTP Method | Action         | Example                |
|-------------|----------------|------------------------|
| GET         | Read           | GET /employees         |
| POST        | Create         | POST /employees        |
| PUT         | Replace/Update | PUT /employees/{id}    |
| PATCH       | Partial Update | PATCH /employees/{id}  |
| DELETE      | Delete         | DELETE /employees/{id} |

### GET

Kanجيبو data mn server.

```java

@GetMapping
public ResponseEntity<ArrayList<Employee>> getAllEmployees() {
    return ResponseEntity.ok(employees);
}
```

### POST

Kanزيدو resource jdid.

```java

@PostMapping
public ResponseEntity<Employee> createEmployee(
        @RequestBody Employee employee) {

    employee.setId(UUID.randomUUID());
    employees.add(employee);

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(employee);
}
```

### PUT vs PATCH

PUT: Kayستعمل عادة باش nبدلو representation kamla dyal resource.

PATCH: Kayبدل ghir fields محددين.

Example:

```json
{
  "firstname": "Youssef"
}
```

Hada y9der ykoun PATCH bach nبدلو ghir firstname.

### Important REST Rules

- GET khaso ykoun safe (ma yبدلch data).
- PUT w DELETE khas operations dyalhom ykouno idempotent.
- POST generally machi idempotent.
- Khass URLs يمثلّو resources, machi actions.

Good:

```text
GET /employees
GET /employees/{id}
POST /employees
DELETE /employees/{id}
```

Avoid:

```text
/getAllEmployees
/deleteEmployee
/createNewEmployee
```

---

# Chapter 05 — HTTP Status Codes (IMPORTANT)

HTTP Status Code howa number kayرجعو server bach yشرح result dyal request.

Kaynin 5 categories:

| Category | Meaning       |
|----------|---------------|
| 1xx      | Informational |
| 2xx      | Success       |
| 3xx      | Redirection   |
| 4xx      | Client Error  |
| 5xx      | Server Error  |

## 1xx — Informational

Had codes kayعطيو information 3la request processing.

| Code | Name                | Explanation               |
|------|---------------------|---------------------------|
| 100  | Continue            | Client y9der yكمل request |
| 101  | Switching Protocols | Server وافق yبدل protocol |
| 102  | Processing          | Request mazal kayt3الج    |
| 103  | Early Hints         | Hints 9bel final response |

## 2xx — Success

| Code | Name                          | Explanation                                       |
|------|-------------------------------|---------------------------------------------------|
| 200  | OK                            | Request نجح                                       |
| 201  | Created                       | Resource tخلق                                     |
| 202  | Accepted                      | Request تقبل ولكن processing mazal                |
| 203  | Non-Authoritative Information | Response information tبدلات mn intermediary       |
| 204  | No Content                    | Success bla response body                         |
| 205  | Reset Content                 | Client khaso reset document view                  |
| 206  | Partial Content               | Server رجع part mn resource                       |
| 207  | Multi-Status                  | Multiple resource statuses (WebDAV)               |
| 208  | Already Reported              | Resource سبق تذكر f WebDAV response               |
| 226  | IM Used                       | Server رجع representation b instance manipulation |

### Spring Boot Examples

```java
// 200
return ResponseEntity.ok(employee);

// 201
return ResponseEntity.

status(HttpStatus.CREATED)
        .

body(employee);

// 202
return ResponseEntity.

accepted().

build();

// 204
return ResponseEntity.

noContent().

build();
```

## 3xx — Redirection

| Code | Name               | Explanation                            |
|------|--------------------|----------------------------------------|
| 300  | Multiple Choices   | Resource 3ndo multiple representations |
| 301  | Moved Permanently  | URL تبدل نهائياً                        |
| 302  | Found              | Temporary redirect                     |
| 303  | See Other          | Redirect l resource akhor b GET        |
| 304  | Not Modified       | Cached version mazal valid             |
| 305  | Use Proxy          | Deprecated                             |
| 306  | Unused             | Reserved                               |
| 307  | Temporary Redirect | Temporary redirect b nafs method       |
| 308  | Permanent Redirect | Permanent redirect b nafs method       |

## 4xx — Client Errors

Had category mohimma بزاف f REST APIs.

| Code | Name                            | Explanation                                    |
|------|---------------------------------|------------------------------------------------|
| 400  | Bad Request                     | Request invalid                                |
| 401  | Unauthorized                    | Authentication khasha ola failed               |
| 402  | Payment Required                | Reserved                                       |
| 403  | Forbidden                       | Client ma3ndoch permission                     |
| 404  | Not Found                       | Resource makaynch                              |
| 405  | Method Not Allowed              | HTTP method mamسموحch                          |
| 406  | Not Acceptable                  | Server ma9derch يرجع acceptable representation |
| 407  | Proxy Authentication Required   | Proxy kayطلب authentication                    |
| 408  | Request Timeout                 | Request timeout                                |
| 409  | Conflict                        | Conflict m3a current resource state            |
| 410  | Gone                            | Resource ma بقاش available permanently         |
| 411  | Length Required                 | Content-Length required                        |
| 412  | Precondition Failed             | Request condition failed                       |
| 413  | Content Too Large               | Request content kbir                           |
| 414  | URI Too Long                    | URL twila بزاف                                 |
| 415  | Unsupported Media Type          | Content format unsupported                     |
| 416  | Range Not Satisfiable           | Requested range invalid                        |
| 417  | Expectation Failed              | Expect header ma تحققch                        |
| 418  | I'm a Teapot                    | Special HTTP status (joke)                     |
| 421  | Misdirected Request             | Request مشى server ghalet                      |
| 422  | Unprocessable Content           | Content مفهوم ولكن processing failed           |
| 423  | Locked                          | Resource locked                                |
| 424  | Failed Dependency               | Operation failed بسبب dependency               |
| 425  | Too Early                       | Server ma بغاش يعالج request بكري              |
| 426  | Upgrade Required                | Protocol upgrade required                      |
| 428  | Precondition Required           | Request khaso condition                        |
| 429  | Too Many Requests               | Rate limit exceeded                            |
| 431  | Request Header Fields Too Large | Headers kbar بزاف                              |
| 451  | Unavailable For Legal Reasons   | Resource blocked لأسباب قانونية                |

### Difference Between 401 and 403

401: Mazal ma authenticated-ch ola credentials invalid.

403: Request مفهوم ولكن access ممنوع.

### Example — 404

```java
if(employee ==null){
        return ResponseEntity.

notFound().

build();
}
```

### Example — 409

Ila bghina nمنعو duplicate email:

```java
return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .

build();
```

Khassna طبعا nتحققو mn duplicate 9bel ma nرجعو had status.

## 5xx — Server Errors

| Code | Name                            | Explanation                             |
|------|---------------------------------|-----------------------------------------|
| 500  | Internal Server Error           | Unexpected server error                 |
| 501  | Not Implemented                 | Functionality unsupported               |
| 502  | Bad Gateway                     | Invalid upstream response               |
| 503  | Service Unavailable             | Service temporarily unavailable         |
| 504  | Gateway Timeout                 | Upstream response timeout               |
| 505  | HTTP Version Not Supported      | HTTP version unsupported                |
| 506  | Variant Also Negotiates         | Content negotiation configuration error |
| 507  | Insufficient Storage            | Storage ma كافياش                       |
| 508  | Loop Detected                   | Infinite loop detected                  |
| 510  | Not Extended                    | Additional request extensions required  |
| 511  | Network Authentication Required | Network authentication required         |

Note: Had tables katجمع standard HTTP status codes, including WebDAV w specialized codes. Machi kol code ghadi تحتاجو f
Employee API.

## Status Codes dyal Employee Project

| Operation | Success | Possible Failure |
|-----------|---------|------------------|
| GET All   | 200     | 500              |
| GET By ID | 200     | 400 / 404        |
| POST      | 201     | 400 / 409        |
| PUT       | 200     | 400 / 404        |
| PATCH     | 200     | 400 / 404        |
| DELETE    | 204     | 404              |

Had failures hiya API design targets; validation w exception handling khasna nimplementiw-hom.

### Important Note

Matرجعch 200 f kolchi. HTTP status howa part mn API contract.

---

# Chapter 06 — Spring Boot Annotations

Annotations homa metadata kanزيدوهم l Java classes/methods bach Spring yعرف kifach yتعامل m3ahom.

## Main Annotations

| Annotation             | Role                           |
|------------------------|--------------------------------|
| @SpringBootApplication | Main Spring Boot configuration |
| @RestController        | REST Controller                |
| @Controller            | MVC Controller                 |
| @Service               | Service Layer                  |
| @Repository            | Persistence Layer              |
| @Component             | Generic Spring Bean            |
| @Autowired             | Dependency Injection           |
| @GetMapping            | GET Request                    |
| @PostMapping           | POST Request                   |
| @PutMapping            | PUT Request                    |
| @PatchMapping          | PATCH Request                  |
| @DeleteMapping         | DELETE Request                 |
| @RequestMapping        | Mapping dyal URLs              |
| @PathVariable          | Value mn URL path              |
| @RequestParam          | Value mn query parameters      |
| @RequestBody           | Request body to Java object    |
| @ResponseStatus        | Define HTTP status             |
| @Valid                 | Trigger validation             |
| @RestControllerAdvice  | Global REST exception handling |
| @ExceptionHandler      | Handle exception               |

## @PathVariable vs @RequestParam

PathVariable:

```java

@GetMapping("/{id}")
public Employee getEmployee(@PathVariable UUID id) {
    // ...
}
```

URL:

```text
/employees/550e8400-e29b-41d4-a716-446655440000
```

RequestParam:

```java

@GetMapping("/search")
public String search(@RequestParam String name) {
    return name;
}
```

URL:

```text
/employees/search?name=Ahmed
```

---

# Chapter 07 — Dependency Injection & IoC

## Chno howa IoC?

Inversion of Control katعني belli Spring container howa li kayتحكم f creation w lifecycle dyal managed objects.

Bla Spring:

```java
EmployeeService service = new EmployeeService();
```

M3a Spring, framework y9der yخلق Service w yحقنو f Controller.

## Dependency Injection

Dependency Injection hiya technique fin object kayتوصل b dependencies dyalo mn barra, بدل ma yخلقhom بوحدو.

Example:

```java

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
```

Spring kayinjecti EmployeeService automatically ila kayn Bean wa7ed compatible.

### 3lach Constructor Injection?

- Easy Testing
- Clear Dependencies
- Supports final fields
- Better Maintainability

**Best Practice:** F required dependencies, prefer Constructor Injection 3la Field Injection.

---

# Chapter 08 — Spring Beans

Bean howa object li Spring container kaymanage lifecycle dyalo.

Examples:

```java

@Service
public class EmployeeService {
}
```

```java

@Repository
public interface EmployeeRepository
        extends JpaRepository<Employee, UUID> {
}
```

## Bean Scopes

| Scope       | Explanation                                                          |
|-------------|----------------------------------------------------------------------|
| Singleton   | One Bean instance per Spring container and bean definition (default) |
| Prototype   | New instance whenever requested mn container                         |
| Request     | One instance per HTTP request                                        |
| Session     | One instance per HTTP session                                        |
| Application | One instance per ServletContext                                      |
| WebSocket   | One instance per WebSocket session                                   |

Important: Singleton Bean ma kayعنيch automatically thread-safe.

Matخزنch user-specific mutable data f singleton Controller ola Service bla synchronization w design مناسب.

---

# Chapter 09 — Controller, Service & Repository

Professional Spring Boot application khas responsibilities dyalha ykouno separated.

## Controller

Kayتعامل m3a HTTP.

## Service

Kayحتوي 3la business logic.

## Repository

Kayتعامل m3a database.

Example:

```java
public interface EmployeeRepository
        extends JpaRepository<Employee, UUID> {
}
```

Spring Data JPA kayوفر methods b7al:

```java
findAll();

findById(id);

save(employee);

deleteById(id);

existsById(id);
```

Machi ضروري nكتبو SQL manually f simple CRUD operations.

---

# Chapter 10 — Entity vs DTO

## Entity

Katمثل data model, w ila kanستعملو JPA katكون mapped l database.

```java

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstname;
    private String email;
}
```

## DTO

Data Transfer Object howa object kanستعملوه bach nحددو chno client يرسل ola يتوصل bih.

Example:

```java
public record EmployeeRequestDTO(
        String firstname,
        String lastname,
        String email
) {
}
```

### 3lach DTO?

- Ma nكشفوش internal database structure.
- Nتحكمو f API response.
- Nسهلو validation.
- Nفصلو API contract 3la persistence model.
- Nمنعو client yبدل fields li makhassoch yبدل.

Important: Ma tkhallish client yحدد Employee ID ila server howa المسؤول 3la generation dyalo.

---

# Chapter 11 — Database & Spring Data JPA

Projet dyalna daba kayستعمل ArrayList.

```java
ArrayList<Employee> employees = new ArrayList<>();
```

Problem: Data katضيع fach application katrestarta.

Solution: Database.

Technologies li khasni nt3ellem:

1. PostgreSQL
2. MySQL
3. Spring Data JPA
4. Hibernate
5. SQL
6. Flyway / Liquibase

## JPA vs Hibernate

JPA: Specification dyal Java Persistence.

Hibernate: ORM framework li kayimplementi JPA.

Spring Data JPA: Kayسهّل creation dyal repository layer فوق JPA.

### Important JPA Annotations

```java
@Entity
@Table(name = "employees")
@Id
@GeneratedValue
@Column
@OneToOne
@OneToMany
@ManyToOne
@ManyToMany
@JoinColumn
@Transactional
```

Khassni nfhem:

- Persistence Context
- Entity Lifecycle
- Dirty Checking
- Lazy vs Eager Loading
- N+1 Query Problem
- Transactions
- Pagination
- Optimistic Locking

---

# Chapter 12 — Database Relationships

Relationships kayحددو kifach tables مرتبطين.

| Relationship | Example                    |
|--------------|----------------------------|
| OneToOne     | Employee — EmployeeProfile |
| OneToMany    | Department — Employees     |
| ManyToOne    | Employee — Department      |
| ManyToMany   | Employee — Projects        |

Example:

```java

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "department_id")
private Department department;
```

Employee wa7ed kayنتمي l Department wa7da, w Department t9der يكون فيها multiple employees.

**Important:** Ma nخلقوش Department ID random f kol Employee bla ma ykoun Department موجود فعلاً f database.

---

# Chapter 13 — Validation

Validation katحمينا mn invalid data.

Spring Boot kayدعم Jakarta Bean Validation b starter dyalo.

Example:

```java
public record EmployeeRequestDTO(

        @NotBlank
        String firstname,

        @NotBlank
        String lastname,

        @Email
        @NotBlank
        String email

) {
}
```

Controller:

```java

@PostMapping
public ResponseEntity<EmployeeResponseDTO> create(
        @Valid @RequestBody EmployeeRequestDTO request) {

    // Call service and return created employee
}
```

Useful Annotations:

- @NotNull
- @NotBlank
- @NotEmpty
- @Email
- @Size
- @Min
- @Max
- @Past
- @Future
- @Pattern

Validation errors dyal request inputs غالباً kanرجعو lihom 400 Bad Request.

---

# Chapter 14 — Exception Handling

F professional backend, ma بغيناش exception handling ykoun duplicated f kol Controller.

Kanستعملو:

```java
@RestControllerAdvice
```

Example:

```java

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(
            ResourceNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
}
```

### Important Concepts

- Custom Exceptions
- Global Exception Handler
- Consistent Error Response
- HTTP Problem Details (RFC 9457)
- Validation Error Handling
- Logging Exceptions
- Avoid Exposing Stack Traces to Clients

---

# Chapter 15 — Spring Security

Security machi optional f production backend.

Spring Security kayوفر:

- Authentication
- Authorization
- Password Encoding
- Security Filter Chain
- CSRF Protection
- Session Management
- OAuth2 Integration

## Authentication vs Authorization

Authentication:

Chkoun nta?

Authorization:

Chno مسموح lik tdir?

Example:

Employee y9der yشوف profile dyalo.

Admin y9der yزيد w yمسح employees.

### Important Security Concepts

- SecurityFilterChain
- PasswordEncoder
- BCrypt
- Roles & Authorities
- Method Security
- CORS vs CSRF
- Least Privilege

---

# Chapter 16 — JWT Authentication

JWT = JSON Web Token.

JWT howa token format, machi authentication protocol بوحدو.

Typical Flow:

```text
User
 |
 v
Login Request
 |
 v
Authentication
 |
 v
JWT Generated
 |
 v
Client Receives Token
 |
 v
Protected API Request
 |
 v
JWT Validation
 |
 v
Authorized Response
```

Topics khasni nfhem:

- Access Token
- Refresh Token
- Token Expiration
- Token Signature
- Secret / Private Key
- Token Validation
- Refresh Token Rotation
- Logout / Revocation Strategy

**Security Rule:** Matخزنch passwords plain text. Matحطch JWT secrets f GitHub.

---

# Chapter 17 — Testing

Professional Developer ma kayكتبch code bla tests.

## Types of Tests

### Unit Tests

Kanختبرو class ola method بوحدها.

Tools:

- JUnit 5
- Mockito

### Integration Tests

Kanختبرو multiple components m3a ba3diyathom.

Tools:

- Spring Boot Test
- MockMvc
- Testcontainers

### API Testing

Tools:

- Postman
- Bruno
- REST Assured

## Tests li khasni nزيد l Employee API

- Get all employees.
- Get existing employee.
- Get nonexistent employee → 404.
- Create valid employee → 201.
- Create invalid employee → 400.
- Update existing employee → 200.
- Delete employee → 204.
- Delete nonexistent employee → 404.
- Duplicate email → 409 (ila implemented).
- Unauthorized access → 401 (mlli nزيد Security).

---

# Chapter 18 — Advanced Spring Boot

Mlli nكمل fundamentals, khasni ندخل l advanced topics.

## Topics

### 1. Transactions

```java
@Transactional
```

Nفهمو atomicity, rollback w database consistency.

### 2. Pagination & Sorting

```java
Pageable
Page<Employee>
```

Bla ma nرجعو thousands of records f request wa7ed.

### 3. Caching

- Spring Cache
- Redis
- Cache Eviction
- Cache Invalidation

### 4. Async Processing

- @Async
- CompletableFuture
- Thread Pools
- Virtual Threads

### 5. Scheduling

```java
@Scheduled
```

### 6. API Documentation

- OpenAPI
- Swagger UI

### 7. Monitoring

- Spring Boot Actuator
- Micrometer
- Prometheus
- Grafana

### 8. Database Migrations

- Flyway
- Liquibase

### 9. Messaging

- RabbitMQ
- Apache Kafka

### 10. Docker

Containerization dyal Spring Boot application w database.

---

# Chapter 19 — Professional Backend Architecture

## Clean Architecture

Goal: Business Logic matكونch مرتبطة مباشرة b database ola HTTP framework.

```text
        API / Controller
              |
              v
       Application Layer
              |
              v
         Domain Layer
              ^
              |
      Infrastructure Layer
```

Khassni nتعلم:

- SOLID Principles
- Separation of Concerns
- Dependency Inversion
- Design Patterns
- Clean Code
- Hexagonal Architecture
- Domain Driven Design (DDD)
- Modular Monolith
- Microservices

### Microservices

Machi kol projet khaso Microservices.

Lazem nfhem monolith مزيان 9bel ma nنتقل l distributed systems.

Advanced Topics:

- API Gateway
- Service Discovery
- Distributed Tracing
- Circuit Breaker
- Event-Driven Architecture
- Eventual Consistency
- Saga Pattern
- Idempotency
- Retry & Timeout Strategies

---

# Chapter 20 — Roadmap: Beginner to Elite

Had roadmap hiya learning path, machi official certification levels.

## Level 1 — Beginner

- [x] Create Spring Boot Project
- [x] Understand @RestController
- [x] GET Request
- [x] POST Request
- [x] PUT Request
- [x] DELETE Request
- [x] ResponseEntity
- [x] Basic HTTP Status Codes
- [ ] Dependency Injection
- [ ] Spring Beans
- [ ] Controller / Service Separation

**Project:** Employee CRUD using ArrayList.

## Level 2 — Intermediate

- [ ] PostgreSQL
- [ ] Spring Data JPA
- [ ] Hibernate
- [ ] DTO
- [ ] Validation
- [ ] Global Exception Handling
- [ ] Entity Relationships
- [ ] Pagination
- [ ] Swagger / OpenAPI
- [ ] Unit Testing

**Project:** Employee Management System with PostgreSQL.

## Level 3 — Advanced

- [ ] Spring Security
- [ ] JWT
- [ ] Role-Based Authorization
- [ ] Integration Testing
- [ ] Docker
- [ ] Redis
- [ ] Flyway
- [ ] CI/CD
- [ ] Logging & Monitoring
- [ ] Performance Optimization

**Project:** Secure HR Management System.

## Level 4 — Professional

- [ ] Clean Architecture
- [ ] SOLID
- [ ] Design Patterns
- [ ] Advanced SQL
- [ ] Database Indexing
- [ ] Transaction Management
- [ ] Concurrency
- [ ] Testcontainers
- [ ] Observability
- [ ] Production Deployment
- [ ] API Versioning

**Project:** Production-Ready HR Platform.

## Level 5 — Elite / Expert

- [ ] Distributed Systems
- [ ] Microservices
- [ ] Kafka
- [ ] Event-Driven Architecture
- [ ] Kubernetes
- [ ] Distributed Tracing
- [ ] High Availability
- [ ] Scalability
- [ ] Resilience Patterns
- [ ] System Design
- [ ] Advanced JVM
- [ ] Memory Management
- [ ] Performance Profiling
- [ ] Security Hardening
- [ ] Cloud Architecture (AWS / Azure / GCP)

**Final Project:** Scalable Enterprise HR Platform.

---

# Golden Rules — Hajat Khassni Ntfkkerhom Dima

1. Controller khaso ykoun thin. Business Logic f Service.
2. Matرجعch Entity directement f public APIs ila DTO أنسب.
3. Matخزنch passwords plain text.
4. Matحطch secrets f GitHub.
5. Kol resource makaynch khaso appropriate 404.
6. Create success غالباً 201.
7. Delete success bla body غالباً 204.
8. Validate input dyal client.
9. Matثقch f data jaya mn frontend.
10. Tests جزء mn development, machi optional extra.
11. Database schema khasها migrations.
12. Logs khasها تكون مفيدة bla sensitive data.
13. Matستعملch Microservices ghir 7it popular.
14. Fhem SQL mزيان 9bel ma تعتمد 100% 3la ORM.
15. Always understand WHY before HOW.

---

# Final Goal

L'objectif dyali howa nنتقل mn:

```text
Java Beginner
      |
      v
Spring Boot Beginner
      |
      v
REST API Developer
      |
      v
Backend Developer
      |
      v
Professional Java Developer
      |
      v
Senior Backend Engineer
      |
      v
Software Architect / Expert
```

**Code li kaykhdem howa lbedaya. Code li maintainable, secure, testable w scalable howa l'objectif.**

Keep Learning. Keep Building. 🚀

# SpringStudyEmployeeM — Project Evolution & MVC Architecture

## 1. Goal dyal had document

Had document kayشرح kifach nطوّرو projet Employee Management API b Spring Boot, men version simple katst3mel
`ArrayList`, l version katst3mel PostgreSQL, 7tta l version professionnelle li fiha security, DTOs, tests, Docker w
CI/CD.

L'objectif machi ghir nkhdmo code kaykhdem. L'objectif howa nfahmo:

- Kifach request katdouz f Spring Boot.
- Chno howa MVC.
- Chno lfar9 bin MVC w Layered Architecture.
- 3lach kansta3mlo Controller, Service, Repository, Entity w DTO.
- Kifach n9smo projet bach ykoun maintainable w scalable.
- Chno khasna nt3elmo bach nwliw kanbniw REST APIs professionnelles.

---

# 2. Project Evolution — 3 Versions

## Version 1 — Current Version

### Architecture

Client / Postman → EmployeeController → ArrayList

F had version, kanst9blo HTTP requests men Postman, w `EmployeeController` kaydir traitement dyal request w kay9ra w
kaykteb data f `ArrayList`.

### Example

```java

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final List<Employee> employees = new ArrayList<>();

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employees;
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        employees.add(employee);
        return employee;
    }
}
```

### Kifach katkhddem?

1. Client kayseft request, par exemple `GET /employees`.
2. Spring Boot kaywssel request l `EmployeeController`.
3. Controller kay9ra data men `ArrayList`.
4. Spring kayconverti result l JSON.
5. Client kayst9bel response.

### Advantages

- Simple w sahla bach nbda.
- Kat3ellemna HTTP methods: GET, POST, PUT, DELETE.
- Kat3ellemna routing b annotations.
- Kat3ellemna kifach Spring kayconverti Java objects l JSON.
- Ma7tajinch database configuration f lbdya.

### Limitations

**1. Data katmchi mlli l'application katrestarta.**

`ArrayList` kat3ich ghir f memory dyal application. Ila tfi application w 3awdti ch3eltiha, data li tzadat katmchi.

**2. Controller kaydir bzaf dyal responsibilities.**

Ila Controller kaydir validation, business logic, data storage w HTTP handling, class katwli kbira w s3iba f
maintenance.

**3. Ma kaynach persistence permanente.**

Ma 3ndnach database bach nkhznou data durable.

**4. Testing w scalability kaywliw as3ab.**

Mlli projet kaykber, khassna n9smo responsibilities w n3tiw kol layer wa7d lkhadma dyalha.

### Important Note

Version 1 maشي khayba. Hiya étape pédagogique. Lhadaf howa nfahmo REST API w Spring Boot 9bel ma nzido complexity.

---

# 3. MVC — Model, View, Controller

## 3.1 Chno howa MVC?

MVC اختصار dyal:

- **M = Model**
- **V = View**
- **C = Controller**

MVC howa design pattern kay9sem application l responsibilities bach code yb9a organisé w sahal f development w
maintenance.

## 3.2 Model

Model kayrepresenti data w concepts dyal application.

F Employee project, `Employee` y9der ykoun Model:

```java
public class Employee {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;

    // Constructors, getters and setters
}
```

Model kaymattel Employee f application.

**Important:** f Spring Boot, lkelma `Model` 3ndha ma3na zayed 7sab context. F MVC classique, Model kay3ni data w
application state. F layered architecture, kanl9aw zada `Entity` w `Domain Model`, li maشي dima nafs lconcept
exactement.

## 3.3 View

View hiya presentation dyal data li kaychouf user.

F application web classique, View t9der tkoun:

- HTML page.
- Thymeleaf template.
- JSP page.

Example dyal flow classique:

Browser → Controller → Model → View → HTML response

Controller kayjib data, kaypassiha l View, w View katrenderi HTML bach user ychouf page.

### Wach REST API 3ndha View?

F REST API, غالباً ma kanrenderiwch HTML pages. Kanrj3o JSON responses.

Example:

```json
{
  "id": "123",
  "firstName": "Ali",
  "lastName": "Amrani",
  "email": "ali@example.com"
}
```

Hna Postman, browser, mobile app, ola frontend React kayستهلك JSON.

Donc, f REST API b Spring Boot:

- Controller kayتعامل m3a HTTP requests.
- Object li kayrj3 Controller kaytconverta l JSON automatiquement b Jackson.
- JSON howa response representation, machi darori `View` classique b7al Thymeleaf.

**Important:** Ma khasnach nخلطو bin MVC classique li kayrenderi HTML w REST API li katrj3 JSON. Spring MVC kay9der
ykhdem بجوج الطرق, walakin l usage kayختلف.

---

# 4. Controller — Chno howa role dyalo?

Controller howa layer li katst9bel HTTP requests w katربط HTTP world m3a application logic.

F Spring Boot, kanst3mlo:

- `@RestController`
- `@RequestMapping`
- `@GetMapping`
- `@PostMapping`
- `@PutMapping`
- `@PatchMapping`
- `@DeleteMapping`
- `@PathVariable`
- `@RequestParam`
- `@RequestBody`

Example:

```java

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable UUID id) {
        // Eventually delegate to a service
        return null;
    }
}
```

### Responsibilities dyal Controller

- Yحدد endpoint.
- Y9ra parameters men URL.
- Y9ra request body.
- Ydir basic HTTP-level handling.
- Y3ayet l Service.
- Yrj3 response b status code monasib.

### Chno ma khasch Controller ydir?

Ma khasch ykoun fih business logic kbira.

Example dyal logic li a7san tkon f Service:

- T2ekked wach email deja kayn.
- T7seb salary bonus.
- Tقرر wach employee y9der ytbdel.
- Tنسق plusieurs operations 3la database.

Controller khaso yb9a khfif.

**Golden Rule:** Controller kayfhem HTTP. Service kayfhem business rules.

---

# 5. MVC vs Layered Architecture

Had lfar9 مهم بزاف.

## MVC

MVC kayركز 3la kifach n9smo responsibilities dyal application, b l'approche Model, View, Controller.

## Layered Architecture

Layered Architecture kat9sem application l layers 7sab responsibility technique aw métier.

Common layers:

1. Controller / Presentation Layer
2. Service / Business Layer
3. Repository / Persistence Layer
4. Database

Had jouj concepts maشي competitors. Y9dro ykhdmo m3a b3diyathom.

F REST API b Spring Boot, n9dro n9olo:

- Spring MVC kayتعامل m3a web requests w responses.
- Layered Architecture katنظم code dyal application.
- Controller, Service w Repository homa components li kay3awnona nطبقو had separation.

---

# 6. Kifach HTTP Request katdouz f Spring MVC?

Example: `GET /employees/123`

### Step 1 — Client

Postman kayseft HTTP request.

### Step 2 — DispatcherServlet

Spring MVC kayستعمل `DispatcherServlet` bach yنسق processing dyal HTTP requests.

`DispatcherServlet` howa front controller dyal Spring MVC.

### Step 3 — Handler Mapping

Spring kayحدد controller method li katناسب request, 7sab URL, HTTP method w configuration.

### Step 4 — Controller

EmployeeController kayستقبل request w kay9ra `id`.

### Step 5 — Service

Controller kay3ayet l EmployeeService bach yطبق business rules.

### Step 6 — Repository

Service kay3ayet l EmployeeRepository bach yجيب data men database.

### Step 7 — Database

Repository kayنفذ query, PostgreSQL katrj3 result.

### Step 8 — Response

Result kayرجع men Repository l Service, men Service l Controller, w Spring kayconvertih l JSON.

Flow:

Client → DispatcherServlet → Controller → Service → Repository → PostgreSQL

Response katرجع f direction l3aks.

**Note:** F Version 1, Service w Repository maزال ma kaynach. Controller kayتعامل m3a ArrayList مباشرة. F Version 2,
ghadi nzido had layers.

---

# 7. Version 2 — Layered Architecture + PostgreSQL

## 7.1 Target Architecture

Client / Postman → Controller → Service → Repository → PostgreSQL

Had version hiya l'objectif l9rib.

L'objectif howa nفصلو responsibilities, w nبدلو in-memory ArrayList b database permanente.

## 7.2 Controller

Controller kayتعامل m3a HTTP.

Example:

```java

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}
```

Hna Controller ma kayتعاملch directement m3a database. Kay3ayet l Service.

## 7.3 Service

Service kayحتافظ b business logic.

```java

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
```

### 3lach Service?

- Kayجمع business rules f blassa wa7da.
- Kayخلي Controller simple.
- Kayسهل unit testing.
- Kayقدر ينسق operations men ktar men Repository.
- Kayخلي business logic ma مربوطةch b HTTP.

**Important:** Maشي kol method khasها business logic kbira. Service y9der f lbdya ykoun simple delegation l Repository,
w hadchi normal.

## 7.4 Repository

Repository kayتعامل m3a persistence.

Ila kanst3mlo Spring Data JPA:

```java

@Repository
public interface EmployeeRepository
        extends JpaRepository<Employee, UUID> {
}
```

`JpaRepository` kay3tina methods جاهزين, b7al:

- `findAll()`
- `findById(id)`
- `save(employee)`
- `deleteById(id)`
- `existsById(id)`

Ma 7tajinch nكتب implementation dyal kol method.

**Important:** Type `UUID` khaso yوافق type dyal ID f Employee entity. Ila ID dyalek `Long`, khass tكتب
`JpaRepository<Employee, Long>`.

## 7.5 PostgreSQL

PostgreSQL hiya relational database katخزن data f tables.

Example table:

| id  | first_name | last_name | email           |
|-----|------------|-----------|-----------------|
| 123 | Ali        | Amrani    | ali@example.com |

B3كس ArrayList, data katبقى مخزنة f database 7tta ila application restartat.

## 7.6 Entity

M3a JPA, `Employee` غالباً katwli Entity:

```java

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;
    private String lastName;
    private String email;

    // Constructors, getters and setters
}
```

Had example kayحتاج JPA dependencies w configuration monasiba.

Entity katmattel database persistence structure. F projects sghar, Entity t9der tكون hiya nafs class li katستعمل f API.
F projects kbira, kanفصلو Entity 3la DTO.

## 7.7 Application Configuration

Typical dependencies:

- Spring Web
- Spring Data JPA
- PostgreSQL Driver
- Validation

Database configuration f `application.properties` t9der tkoun b7al:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/employee_db
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Had configuration ghir example local development. F production, khass secrets yjiw men environment variables aw secret
manager. Ma t7ettch passwords f Git.

`ddl-auto=update` maشي howa l'option l'afdal f production. M3a projects sérieux, migrations b7al Flyway aw Liquibase
kay3tiw control a7san 3la schema changes.

---

# 8. Version 3 — Professional Architecture

## 8.1 Target Architecture

Client / Frontend  
↓  
REST API + Security + DTO  
↓  
Controller  
↓  
Service / Business Logic  
↓  
Domain Model  
↓  
Repository / Persistence  
↓  
PostgreSQL

W 7da had layers kaynin:

- Automated Tests
- Docker
- CI/CD
- Logging and Monitoring
- Database Migrations
- Documentation

Had version maشي ghir zyada dyal classes. Hiya architecture li katسهل security, testing, maintenance, deployment w
evolution dyal projet.

## 8.2 DTO — Data Transfer Object

DTO howa object kayحدد data li kanستقبلو aw kanرجعو men API.

Example:

```java
public record CreateEmployeeRequest(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank @Email String email
) {
}
```

Controller y9der يستقبل DTO:

```java

@PostMapping
public EmployeeResponse createEmployee(
        @Valid @RequestBody CreateEmployeeRequest request) {
    return employeeService.createEmployee(request);
}
```

`EmployeeResponse` t9der tkoun DTO dyal response.

### 3lach DTO?

- Ma kanكشفوش kolchi li kayn f Entity.
- Kanحددو chno client y9der yرسل.
- Kanحددو chno client yقدر ychouf.
- Kanفصلو API contract 3la database structure.
- Kanضيفو validation b wa7d tariqa واضحة.

**Important:** DTO maشي obligatoire f kol projet. Walakin f APIs li ghadi tkber aw li fiha security requirements, kayكون
غالباً مفيد.

## 8.3 Validation

Validation katأكد belli input s7i7 9bel ma nكملو traitement.

Examples:

- First name ma يكونش blank.
- Email يكون format s7i7.
- Salary ma يكونch negative ila business rules katمنع hadchi.
- Required fields يكونو موجودين.

M3a Spring Validation, kanستعملو annotations b7al `@NotBlank`, `@Email`, `@Positive` w `@Valid`.

Khass tكون 3ndna distinction bin validation dyal input format w business rules. Format validation t9der tكون f DTO;
business rules غالباً f Service aw Domain.

## 8.4 Exception Handling

Ma khasch kol Controller method tكرر نفس try/catch logic.

N9dro nستعملو `@RestControllerAdvice` bach nوحدو error responses.

Examples:

- Employee ma kaynach → 404 Not Found.
- Input invalid → 400 Bad Request.
- Email déjà kayn → 409 Conflict.
- Unexpected server error → 500 Internal Server Error.

Error response khaso yكون واضح, bla ma يكشف secrets, SQL details aw stack traces l client.

## 8.5 Security

F professional API, security t9der تشمل:

- Authentication: chkoun nta?
- Authorization: chno مسموح ليك تدير?
- Password hashing ila kaynin accounts.
- Role-based access control.
- Validation dyal inputs.
- CORS configuration ila frontend kayخدم men origin akhor.
- Protection dyal secrets w credentials.

Spring Security kayعاون f had responsibilities.

**Authentication vs Authorization**

Authentication = verification dyal identity.

Authorization = verification dyal permissions.

Example: user logged in, walakin ma 3ndoch permission bach يمسح employee.

## 8.6 Testing

Professional project khaso tests.

### Unit Tests

Kanختبرو business logic f Service بوحدها, b dependencies mockées ila مناسب.

### Integration Tests

Kanختبرو interaction bin layers, b7al Repository m3a database.

### Controller Tests

Kanختبرو HTTP endpoints, request validation, status codes w response body.

Test strategy khasها تناسب projet. Maشي darori kolchi يتختبر بنفس tariqa.

## 8.7 Docker

Docker kayخلي application w dependencies dyalha أسهل f setup w deployment.

N9dro nستعملو Docker Compose bach nشعلو:

- Spring Boot application
- PostgreSQL

F had tariqa, team members y9dro yخدمو b environment qriba l b3diyatha.

## 8.8 CI/CD

CI/CD kayعاونna nأتمتو build, tests w deployment.

Typical pipeline:

1. Developer kaypushi code.
2. Pipeline kaybuildi application.
3. Automated tests kaykhdmo.
4. Quality checks kaytنفدو.
5. Ila kolchi داز, artifact aw Docker image katوجد.
6. Deployment kayوقع 7sab environment w permissions.

Ma khasch ndeployiw code l production ila tests failed.

## 8.9 Monitoring and Logging

Logs kayعاونوna nفهمو chno وقع.

Monitoring kayعاونna nراقبو:

- Application health.
- Response times.
- Error rates.
- Resource consumption.
- Database connectivity.

Spring Boot Actuator kayوفر endpoints dyal health w metrics 7sab configuration. Khass endpoints الحساسة تبقى secured.

---

# 9. HTTP Status Codes Li Khassk T3ref

| Status                    | Meaning                           | Example                     |
|---------------------------|-----------------------------------|-----------------------------|
| 200 OK                    | Request successful                | Employee returned           |
| 201 Created               | Resource created                  | Employee created            |
| 204 No Content            | Success bla response body         | Employee deleted            |
| 400 Bad Request           | Input invalid                     | Invalid email               |
| 401 Unauthorized          | Authentication missing aw invalid | User not authenticated      |
| 403 Forbidden             | Ma 3ndkch permission              | User cannot delete employee |
| 404 Not Found             | Resource ma kaynach               | Employee ID not found       |
| 409 Conflict              | Conflict m3a current state        | Duplicate email             |
| 500 Internal Server Error | Unexpected server failure         | Unhandled exception         |

**Note:** `401` kay3ni authentication issue; `403` kay3ni access denied. F application réelle, status codes khasهم
يتختارو 7sab behavior w API contract.

---

# 10. MVC and Architecture — Important Notes

## Note 1: MVC maشي howa Controller-Service-Repository

MVC = Model, View, Controller.

Controller-Service-Repository = layered organization dyal responsibilities.

F Spring Boot REST API, t9der تستعمل Spring MVC f web layer w layered architecture bach tنظم business logic w
persistence.

## Note 2: `@RestController` maشي View classique

`@RestController` kayرجع response body, غالباً JSON. Spring kayconverti Java object l JSON automatiquement.

`@Controller` t9der تستعملو m3a views b7al Thymeleaf, w t9der zada تستعملو l other web handling patterns 7sab
annotations.

## Note 3: Controller ma khasoch ydir business logic kbira

Controller kayتعامل m3a HTTP w kaydelegui l Service.

Service kayطبق business rules.

Repository kayتعامل m3a persistence.

## Note 4: Entity maشي hiya DTO

Entity katمثل persistence model, w DTO kayمثل data li katدوز bin API w application.

F simple CRUD, y9dro ykouno qrabin. F applications kbira, الفصل binathom kayزيد flexibility w security.

## Note 5: Repository maشي darori yكتب SQL

M3a Spring Data JPA, بزاف dyal CRUD methods kayكونو generated automatiquement.

Queries complexes t9der تحتاج method names, `@Query`, aw custom repository implementation.

## Note 6: Architecture katعاون f testing

Ila Controller kayعتمد 3la Service, n9dro nختبرو Controller بوحدو w nmockiw Service.

Ila Service kayعتمد 3la Repository, n9dro nختبرو business rules dyalo b test doubles aw mocks, w ndيرو integration tests
f blassa مناسبة.

## Note 7: Ma tzidch complexity bla sabab

Maشي kol projet khaso 3 layers kbar, DTOs f kol direction, w architecture compliquée.

Bda simple, walakin ختار separation li katسهل evolution dyal projet.

## Note 8: Constructor Injection

Preferred pattern f Spring howa constructor injection:

```java
private final EmployeeService employeeService;

public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
}
```

Had pattern kayوضح dependencies, kayسهل tests, w kayخلي fields `final`.

## Note 9: Database schema changes

F development, `ddl-auto=update` t9der تكون convenient.

F production, database migrations b Flyway aw Liquibase kay3tiw history واضحة dyal schema changes w control a7san.

## Note 10: API design

Khass endpoints يكونو واضحين, methods monasbin, status codes منطقيين, w error responses consistent.

Examples:

- `GET /employees`
- `GET /employees/{id}`
- `POST /employees`
- `PUT /employees/{id}`
- `DELETE /employees/{id}`

---

# 11. Learning Roadmap

## Phase 1 — Understand Current Version

Checklist:

- [ ] Nfhem `@RestController`.
- [ ] Nfhem `@RequestMapping`.
- [ ] Nfhem GET, POST, PUT, DELETE.
- [ ] Nfhem `@PathVariable` w `@RequestBody`.
- [ ] Nfhem kifach Java objects kaywliw JSON.
- [ ] N9der nجرّب endpoints b Postman.
- [ ] Nfhem limitation dyal `ArrayList`.

## Phase 2 — Introduce Service Layer

Checklist:

- [ ] Nخلق `EmployeeService`.
- [ ] Nنقل business logic men Controller l Service.
- [ ] Nخلي Controller thin.
- [ ] Nستعمل constructor injection.
- [ ] Nختبر behavior dyal Service.

## Phase 3 — Add PostgreSQL and JPA

Checklist:

- [ ] Nثبت PostgreSQL.
- [ ] Nخلق database `employee_db`.
- [ ] Nزيد Spring Data JPA dependency.
- [ ] Nزيد PostgreSQL driver.
- [ ] Nحول Employee l JPA Entity.
- [ ] Nخلق `EmployeeRepository`.
- [ ] Nربط database b configuration.
- [ ] Nبدل ArrayList b Repository.
- [ ] Nجرّب CRUD operations kamlin.

## Phase 4 — Improve API Quality

Checklist:

- [ ] Nزيد DTOs.
- [ ] Nزيد validation.
- [ ] Nوحد exception handling.
- [ ] Nختار HTTP status codes المناسبين.
- [ ] Nكتب tests.
- [ ] Nكتب README فيه setup w API examples.

## Phase 5 — Professionalize

Checklist:

- [ ] Nتعلم Spring Security.
- [ ] Nفهم authentication w authorization.
- [ ] Nستعمل database migrations.
- [ ] Nتعلم Docker w Docker Compose.
- [ ] Nضيف CI pipeline.
- [ ] Nتعلم logging w monitoring.
- [ ] Nوجد configuration مختلفة l development w production.
- [ ] Nأمن secrets w credentials.

---

# 12. Recommended Package Structure

F Version 2, t9der تبدا b structure بسيطة:

```text
com.example.employee
├── controller
│   └── EmployeeController.java
├── service
│   └── EmployeeService.java
├── repository
│   └── EmployeeRepository.java
├── entity
│   └── Employee.java
└── EmployeeApplication.java
```

F Version 3, mlli project يكبر:

```text
com.example.employee
├── controller
│   └── EmployeeController.java
├── dto
│   ├── CreateEmployeeRequest.java
│   ├── UpdateEmployeeRequest.java
│   └── EmployeeResponse.java
├── service
│   └── EmployeeService.java
├── repository
│   └── EmployeeRepository.java
├── entity
│   └── Employee.java
├── exception
│   ├── EmployeeNotFoundException.java
│   └── GlobalExceptionHandler.java
├── config
│   └── SecurityConfig.java
└── EmployeeApplication.java
```

Had structure example, maشي standard wa7ed obligatoire. Package organization khasها تبقى مفهومة w مناسبة l حجم dyal
projet.

---

# 13. Final Mental Model

Tkhayel request `POST /employees`:

1. Client kayseft JSON.
2. Spring MVC kayوصل request l Controller.
3. Controller kayستقبل DTO w kayدير validation.
4. Service kayطبق business rules.
5. Repository kaypersisti Employee.
6. PostgreSQL katخزن data.
7. Application katرجع response JSON b status code مناسب.

Version 1 katعلمك kifach REST API katخدم.

Version 2 katعلمك separation of concerns w persistence.

Version 3 katعلمك kifach تبني application قابلة للصيانة, الاختبار, التأمين, والنشر.

## The Most Important Principle

**Kol class khasها تكون عندها responsibility واضحة.**

- Controller: HTTP.
- Service: business logic.
- Repository: persistence.
- Entity/Domain: business data and concepts.
- DTO: API input/output.
- Database: permanent storage.

Ila فهمتي had separation مزيان، ghadi yولي ساهل 3lik tfhem بزاف dyal Spring Boot projects, tكتب tests, w tzid features
bla ma code yولي متشابك.
