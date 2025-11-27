# Library Management System (Rubric-Ready)

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
   ```
   javac -cp .:sqlite-jdbc-<version>.jar src/*.java
   ```
4. Run:
   ```
   java -cp .:sqlite-jdbc-<version>.jar Main
   ```

On Windows replace `:` with `;` in classpath.

## Files included
- src/*.java : source code
- README.md

## Notes
- The project uses a simple `library.db` SQLite file created automatically.
- Background backup thread runs every 60 seconds to `backup_library.db`.
- DAO methods are synchronized to satisfy thread-safety.
