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

### Backend (Spring Boot)

```bash
git clone https://github.com/your-username/pg-management-system.git
cd backend

1. Configure application.properties with your MySQL credentials
