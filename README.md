# Course Registration System (CRS)

## 📌 Project Overview
The **Course Registration System (CRS)** is designed to automate and streamline the course registration process at educational institutions. It provides a user-friendly platform for students, faculty, and administrators to manage academic schedules, track enrollments, and maintain course data.

## 🛠 Features
### 🔹 Course Management
- Create, read, update, and delete course information.
- Store details such as Course ID, title, credit hours, department, prerequisites, and capacity.

### 🔹 Student Management
- Manage student profiles including Student ID, name, date of birth, program of study, and contact information.
- Store academic records, including enrolled courses and grades.

### 🔹 Enrollment Management
- Allow students to register for courses based on eligibility and capacity.
- Enable students to add or drop courses within the allowed time frame.
- Enforce prerequisite checks automatically before course enrollment.

### 🔹 User Authentication & Security
- Secure login system with **Role-Based Access Control (RBAC)** for students, faculty, and administrators.
- Authentication and authorization mechanisms to ensure data protection.

### 🔹 Reporting & Analytics
- Generate reports on course enrollments, vacancies, and student schedules.
- Provide faculty and administrators with insights into student academic progress.

## 🔧 Implementation Strategy
### 🏗 Architecture
The system follows a **Layered Architecture**:
- **Presentation Layer**: JavaFX or Swing for Graphical User Interface (GUI).
- **Business Logic Layer**: Handles validations, business rules, and process management.
- **Persistence Layer**: Hibernate ORM or JDBC for seamless database operations.
- **Database Layer**: MySQL for managing all data related to courses, students, and enrollments.

### 📌 Tech Stack
- **Frontend**: JavaFX / Swing
- **Backend**: Java (Spring Boot for APIs - optional)
- **Database**: MySQL
- **ORM**: Hibernate / JDBC
- **Version Control**: Git & GitHub

## 📂 Project Structure
```bash
📦 CRS (Course Registration System)
├── 📂 src
│   ├── 📂 presentation  # GUI Components (JavaFX / Swing)
│   ├── 📂 business      # Business logic
│   ├── 📂 persistence   # Data Access Layer (Hibernate / JDBC)
│   ├── 📂 database      # MySQL scripts & configurations
│   └── 📂 reports       # Report generation utilities
├── 📄 README.md         # Project Documentation
├── 📄 CRS.sql           # MySQL database schema & sample data
└── 📄 .gitignore        # Git ignore file
```

## 🚀 Getting Started
### Prerequisites
- Java 8+
- MySQL Server
- Git (for version control)

### 🔥 Setup Instructions
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-repository-url.git
   cd CRS
   ```
2. **Set Up the Database**:
   - Create a MySQL database using the provided **CRS.sql** file.
   ```sql
   CREATE DATABASE CRS;
   USE CRS;
   SOURCE CRS.sql;
   ```
3. **Compile & Run the Application**:
   ```bash
   javac -d bin src/**/*.java
   java -cp bin Main
   ```

## 📜 Submission Requirements
- ✅ **Complete Source Code** (Version-controlled on GitHub)
- ✅ **Database File** (MySQL dump with sample data)
- ✅ **Documentation** (README with setup instructions, system requirements, and user guide)

## 👥 Contributors
- **Your Name** - Developer

📌 **Note:** This project is part of the CMJD - Comprehensive Master Java Developer coursework.

---
✅ **License:** MIT License | 📧 Contact: senevias@gmail.com

