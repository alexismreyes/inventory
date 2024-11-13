## Inventory Management System - React / Express / Springboot / MySQL

Welcome! This is a basic inventory management project I'm developing as a functional approach to managing inventory and sales orders. This initial version is focused on essential operations and doesn't aim to handle payments, transactional history, or other advanced e-commerce features.

## Key Features

- CRUD operations for Categories, Products, Orders, and Order Items

- User authentication with JWT-based Role management for access control

- A streamlined interface to manage inventory and basic sales orders (React frontend yet to develop)

This project is designed to be a solid foundation for anyone looking to expand with additional features and enhancements. I hope you find it useful and welcome any suggestions for further development!

---

**_Stack used:_**

### Frontend (yet to develop):

**_- React_**

**_- VITE_**

### SideServer proxy (yet to develop):

**_- Express.js_**

**_- cors_**

**_- dotenv_**

**_- Router_**

### Backend (Already Working):

**_- Springboot 3.3.5_**

### Persistence:

**_- MySql_**

---

### Current working features:

**_Backend_**

Currently you need to use postman or any other REST client to make requests to the backend.

Check the controllers for all the available endpoints. Some endpoints to begin trying out could be:

http://localhost:8080/api/auth/register (Register a new user)

- Use a body like this:

{

"username": "user1",

"password": "password123",

"role": "admin"

}

http://localhost:8080/api/auth/login (Authenticate user and retrieve JWT)

- Use a body like this:

{

"username": "user1",

"password": "password123"

}

**AFTER LOGIN USE THE RETRIEVED JWT FOR FURTHER REQUESTS USING AN AUTHORIZATION HEADER**

http://localhost:8080/api/products/ (Get product list)

http://localhost:8080/api/orders/ (Get orders list)

http://localhost:8080/api/categories/ (Get categories list)

Refer to other endpoints in the controllers for additional functionality.

---

### Requirements:

- Create a database in your MySql engine and name it inventory_db (or any name you want)

- Create your own application.properties file inside the src/main/resources folder to set the values for credentials:

example src/main/resources/application.properties:

    spring.application.name=inventory
    spring.datasource.url=jdbc:mysql://localhost:3306/inventory_db #Your database name
    spring.datasource.username=dbuser
    spring.datasource.password=dbpass

    #Hibernate Configuration
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
    spring.jpa.properties.hibernate.hbm2ddl.auto=update

---

### Steps to install and try it out:

1.  clone repository

2.  cd inventory

3.  cd backend

4.  Open the project project folder using your prefered IDE (mine for java is IntelliJ)

5.  Download dependencies using Maven

6.  Run the project using your IDE

---
