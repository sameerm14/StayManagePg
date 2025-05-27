# 🏠 PG Management System

The PG Management System is a full-featured web application designed to simplify and automate the management of paying guest accommodations. It provides a platform for PG Owners (Admins) and Tenants to manage room assignments, rent payments, food menus, maintenance requests, and notifications seamlessly.

## 📌 Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [License](#license)
- [Author](#author)

## ✨ Features

### 🔑 Admin Features
- View all rooms with occupancy status  
- Add, edit, and delete rooms and tenant details  
- Track rent payments and overdue status  
- Send notifications to tenants (food, maintenance, rent reminders)  
- Manage food menus with images (breakfast, lunch, dinner)  
- View tenant rent history and generate reports  
- Mark rent as paid and clear overdue status  
- Generate downloadable rent and tenant reports in PDF  

### 👤 Tenant Features
- View assigned room and rent status  
- Access daily or weekly food menus with images  
- Receive important notifications from admin  
- Pay rent via the app (if payment gateway is integrated)  
- Submit maintenance requests and queries  
- View personal rent payment history  

## 🛠 Tech Stack

| Layer         | Technology         |
|---------------|-------------------|
| Frontend      | Angular            |
| Backend       | Spring Boot (Java) |
| Database      | MySQL              |
| PDF Generator | iText              |
| Authentication| (Optional) JWT     |

## 🚀 Getting Started

### Backend Setup (Spring Boot)

1. Clone the repo and navigate to the backend folder:

   ```bash
   git clone https://github.com/your-username/pg-management-system.git
   cd pg-management-system/backend

   
2. Create a MySQL database:

   ```bash
   CREATE DATABASE pg_management;

3. Open src/main/resources/application.properties and update your MySQL credentials:

    ```bash
    
   spring.datasource.url=jdbc:mysql://localhost:3306/pg_management
   spring.datasource.username=your_mysql_username
   spring.datasource.password=your_mysql_password
   spring.jpa.hibernate.ddl-auto=update

4. Run the Spring Boot server:
   
   ```bash
   mvn spring-boot:run

5. Backend will be running at: http://localhost:8080

## Frontend Setup (Angular)

1. Navigate to the frontend folder:

   ```bash
   cd ../frontend
   
2. Install dependencies:

   ```bash
   npm install

3. Run the Angular app:

   ```bash
   ng serve

4. Frontend will be running at: http://localhost:4200

## 📁 Project Structure

         pg-management-system/
         ├── backend/
         │   ├── src/main/java/... (Spring Boot code)
         │   └── src/main/resources/application.properties
         ├── frontend/
         │   ├── src/app/... (Angular components)
         │   └── angular.json
         └── README.md
         
## 📄 License

This project is licensed under the MIT License.

## 👨‍💻 Author

Sameer Nadaf
[LinkedIn](https://www.linkedin.com/in/sameer-nadaf-logged/)


You can copy this **whole thing** as one README.md file for your repo. Let me know if you want me to help create the file for you!

