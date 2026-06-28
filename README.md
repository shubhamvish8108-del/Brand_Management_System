# Brand Management System

A web-based **Brand Management System** developed using **Java Spring Boot**, **Spring MVC**, **Spring Data JPA (Hibernate)**, **Thymeleaf**, and **MySQL**. The application manages a hierarchical structure of **Groups → Chains → Brands** with complete CRUD functionality, validation, filtering, and soft delete support.

---

## Features

### Dashboard
- View total number of Groups, Chains, and Brands
- Display all Brands with their associated Group and Chain
- Filter Brands by Group or Company (Chain) Name

### Group Management
- Add new Groups
- Update Group details
- Activate/Deactivate Groups
- Permanent deletion of inactive Groups
- Unique Group name validation

### Chain Management
- Add Chains under a Group
- Assign unique GST Numbers
- Edit Chain details
- Activate/Deactivate Chains
- Delete inactive Chains
- Duplicate Company Name & GST validation

### Brand Management
- Add Brands under a Chain
- Update Brand details
- Activate/Deactivate Brands
- Permanently delete inactive Brands
- Prevent duplicate Brand names within the same Chain

### Validation
- Mandatory field validation
- Duplicate data validation
- Business rule validation
- User-friendly error messages

---

## Data Hierarchy

```
Group
   │
   ├── Chain
   │      │
   │      ├── Brand
   │      ├── Brand
   │
   ├── Chain
          │
          ├── Brand
```

---

## Technologies Used

| Technology | Version |
|------------|----------|
| Java | 17+ |
| Spring Boot | 3.x |
| Spring MVC | ✓ |
| Spring Data JPA | Hibernate |
| Thymeleaf | ✓ |
| MySQL | 8.x |
| Maven | ✓ |
| HTML5 | ✓ |
| CSS3 | ✓ |
| Bootstrap | 5 |

---

## Project Structure

```
BrandManagementSystem
│
├── controller
├── service
├── repository
├── model
├── config
├── templates
├── static
└── resources
```

---

## Database Schema

### customer_groups

- group_id
- group_name
- is_active
- created_at
- updated_at

### chain

- chain_id
- company_name
- gst_number
- group_id
- is_active

### brand

- brand_id
- brand_name
- chain_id
- is_active

---

## Screenshots

Add screenshots here.

Example:

```
screenshots/
│
├── dashboard.png
├── add-group.png
├── add-chain.png
├── add-brand.png
```

---

## Installation

### Clone the Repository

```bash
git clone https://github.com/shubhamvish8108-del/Brand_Management_System.git
```

### Open the Project

Open the project in IntelliJ IDEA or Eclipse.

### Configure Database

Create a MySQL database.

```sql
CREATE DATABASE brand_management;
```

Update your `application.properties`.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/brand_management
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Run the Application

Run:

```
BrandManagementApplication.java
```

Open your browser:

```
http://localhost:8080/
```

---

## Application Workflow

1. Create a Group.
2. Add Chains under the Group.
3. Add Brands under a Chain.
4. View all records on the Dashboard.
5. Search Brands using filters.
6. Edit, Activate, Deactivate, or Delete records.

---

## Future Enhancements

- User Authentication & Authorization
- Spring Security
- REST APIs
- Pagination
- Search using AJAX
- File Upload
- Export to Excel/PDF
- Docker Support
- Unit Testing
- CI/CD Integration

---

## Learning Outcomes

This project helped in understanding:

- Spring Boot Architecture
- MVC Design Pattern
- Spring Data JPA
- Hibernate ORM
- CRUD Operations
- Entity Relationships
- Form Validation
- MySQL Integration
- Thymeleaf Templates
- Exception Handling

---

## Author

**Shubham Vishwakarma**

- GitHub: https://github.com/shubhamvish8108-del
- LinkedIn: *(Add your LinkedIn profile link here)*

---

## License

This project is developed for learning and internship purposes.
