A simple tool that helps a library keep track of its books, who borrowed them, and when they’re due. It makes checkouts easy, returns smooth, and helps staff find information fast.
# Library Management System

This project is a Java Swing desktop Library Management System designed to satisfy the provided rubric:
- OOP (inheritance, interfaces, polymorphism, exceptions)
- Collections & Generics
- Multithreading & Synchronization
- DAO classes for DB operations
- JDBC (SQLite) integration

## How to build and run

1. Install Java 8+.
2. Download sqlite-jdbc driver (for example: https://github.com/xerial/sqlite-jdbc/releases) and place the jar next to compiled classes or add it to your classpath.
3. Compile:
   javac -cp .:sqlite-jdbc-<version>.jar src/*.java
4. Run:
   java -cp .:sqlite-jdbc-<version>.jar Main

On Windows replace `:` with `;` in classpath.

## Files included
- src/*.java : source code
- README.md

## Notes
- The project uses a simple `library.db` SQLite file created automatically.
- Background backup thread runs every 60 seconds to `backup_library.db`.
- DAO methods are synchronized to satisfy thread-safety.


Library Management System
A simple, robust Java Swing desktop application that streamlines library operations—tracking books, managing borrowers, handling checkouts/returns, and providing quick search functionality for staff.​

✨ Features
Book Management: Add, edit, delete, and search books with full inventory tracking.

Borrower Management: Register members, view profiles, and monitor borrowing history.

Checkout/Return: Easy issue/return processes with due date calculations and overdue alerts.

Search & Reports: Fast lookups by title, author, borrower, or status; generates activity summaries.

Automated Backup: Background thread creates backup_library.db every 60 seconds.

Thread-Safe: Synchronized DAO methods ensure data integrity in multi-user scenarios.​

🛠️ Tech Stack
Frontend: Java Swing (GUI components, JFrame-based interface)

Backend: JDBC with SQLite (library.db auto-created)

Core Concepts: OOP (inheritance, polymorphism, interfaces), Generics & Collections (ArrayList, HashMap), Multithreading/Synchronization, Custom Exceptions, DAO pattern​

🚀 Quick Start
Prerequisites
Java 8 or higher (JDK recommended)

SQLite JDBC driver ( from sqlite-jdbc)​

Build & Run
# Download sqlite-jdbc-<version>.jar and place beside src/

# Compile (Linux/macOS)
javac -cp ".:sqlite-jdbc-<version>.jar" src/*.java

# Run
java -cp ".:sqlite-jdbc-<version>.jar" Main

# Windows: Replace ':' with ';'
javac -cp ".;sqlite-jdbc-<version>.jar" src/*.java
java -cp ".;sqlite-jdbc-<version>.jar" Main
The database library.db generates automatically on first run

📁 Project Structure

├── src/          # All Java source files
├── README.md     # This file
└── library.db    # SQLite DB (created on run)
└── backup_library.db  # Auto-backup file
🔒 Notes
Data Persistence: All operations use a local SQLite file—no server setup required.

Threading: Backup runs asynchronously every 60s; DAO ops are synchronized for safety.

Extensibility: Modular DAO design supports easy DB migration (e.g., to MySQL).​​

🤝 Contributing
Fork the repo, create a feature branch, and submit a PR. Focus on clean code, tests, and documentation.

📄 License
MIT License—feel free to use, modify, and distribute.
