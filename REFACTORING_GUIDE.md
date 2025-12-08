# Project Refactoring Guide - Package Structure Reorganization

## 🎯 Objective
Reorganize the Library Management System into a professional package structure for improved code organization, maintainability, and scoring.

---

## 📦 New Package Structure

```
src/
├── model/              # Data model classes (POJOs)
│   ├── Book.java
│   ├── Member.java
│   ├── Librarian.java
│   ├── User.java
│   ├── Transaction.java
│   └── Repository.java
│
├── dao/                # Data Access Objects
│   ├── BookDAO.java
│   ├── MemberDAO.java
│   └── TransactionDAO.java
│
├── service/            # Business logic layer
│   ├── LibraryService.java
│   └── LibraryActions.java (interface)
│
├── ui/                 # User Interface classes
│   └── MainFrame.java
│
├── util/               # Utility & helper classes
│   └── DatabaseConnection.java
│
├── exceptions/         # ✅ Already created!
│   ├── InvalidInputException.java
│   └── BookNotFoundException.java
│
└── Main.java           # Entry point (stays at root)
```

---

## 📋 Complete File Migration Map

### **MODEL Package** (Data Classes)
```
Move these files to: src/model/

✓ Book.java          → src/model/Book.java
✓ Member.java        → src/model/Member.java  
✓ Librarian.java     → src/model/Librarian.java
✓ User.java          → src/model/User.java
✓ Transaction.java   → src/model/Transaction.java
✓ Repository.java    → src/model/Repository.java
```

### **DAO Package** (Database Access)
```
Move these files to: src/dao/

✓ BookDAO.java       → src/dao/BookDAO.java
✓ MemberDAO.java     → src/dao/MemberDAO.java
✓ TransactionDAO.java → src/dao/TransactionDAO.java
```

### **SERVICE Package** (Business Logic)
```
Move these files to: src/service/

✓ LibraryService.java   → src/service/LibraryService.java
✓ LibraryActions.java   → src/service/LibraryActions.java
```

### **UI Package** (User Interface)
```
Move these files to: src/ui/

✓ MainFrame.java     → src/ui/MainFrame.java
```

### **UTIL Package** (Utilities)
```
Move these files to: src/util/

✓ DatabaseConnection.java → src/util/DatabaseConnection.java
```

### **EXCEPTIONS Package** ✅
```
Already created in: src/exceptions/

✓ InvalidInputException.java
✓ BookNotFoundException.java
```

### **Root Level**
```
Stays at root: src/Main.java
```

---

## 🔧 Step-by-Step Implementation

### **Step 1: Create Package Folders on GitHub**

You can create folders by creating a file with a path. Example:
```
Create file: src/model/.gitkeep
Create file: src/dao/.gitkeep
Create file: src/service/.gitkeep
Create file: src/ui/.gitkeep
Create file: src/util/.gitkeep
```

### **Step 2: Add Package Declarations**

For each moved file, add the package declaration at the top:

#### Model Classes Example:
```java
package model;

import java.io.Serializable;

public class Book implements Serializable {
    // existing code...
}
```

#### DAO Classes Example:
```java
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Book;  // Import from model package
import service.LibraryActions;  // Import from service package
import util.DatabaseConnection;  // Import from util package
import exceptions.BookNotFoundException;  // Import custom exception

public class BookDAO implements LibraryActions<Book> {
    // existing code...
}
```

#### UI Class Example:
```java
package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import service.LibraryService;  // Import service
import model.Book;  // Import model
import exceptions.*;  // Import exceptions
import util.DatabaseConnection;  // Import utility

public class MainFrame extends JFrame {
    // existing code...
}
```

#### Service Classes Example:
```java
package service;

import model.*;
import dao.*;
import exceptions.*;
import java.sql.SQLException;
import java.util.List;

public class LibraryService {
    // existing code...
}
```

#### Util Classes Example:
```java
package util;

import java.sql.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DatabaseConnection {
    // existing code...
}
```

#### Main.java (Root):
```java
// No package declaration for Main.java at root

import ui.MainFrame;
import util.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
        // Initialize database
        try {
            DatabaseConnection.getConnection();
        } catch (SQLException e) {
            System.err.println("Failed to initialize database: " + e.getMessage());
        }
        
        // Launch UI
        MainFrame.showUI();
    }
}
```

---

## 📝 Complete Import Reference Guide

### Typical Imports Per Package:

#### **model/** classes:
```java
package model;
// No imports from other packages usually needed
// May import java.io.Serializable or java.util.Date if needed
```

#### **dao/** classes:
```java
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.*;              // Import all model classes
import service.LibraryActions;  // Import interface
import util.DatabaseConnection; // Import DB utility
import exceptions.*;         // Import custom exceptions
```

#### **service/** classes:
```java
package service;

import model.*;
import dao.*;
import exceptions.*;
import java.sql.SQLException;
import java.util.List;
```

#### **ui/** classes:
```java
package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import service.*;
import model.*;
import exceptions.*;
import util.DatabaseConnection;
```

#### **util/** classes:
```java
package util;

import java.sql.*;
import java.io.*;
import java.nio.file.*;
```

#### **exceptions/** classes:
```java
package exceptions;
// Typically no imports needed beyond java.lang
```

---

## ⚠️ Common Issues & Solutions

### Issue 1: "Cannot find symbol" errors
**Solution**: Add proper import statements for classes from other packages

### Issue 2: Classpath issues when compiling
**Solution**: Compile from the src directory:
```bash
cd src
javac -d ../bin **/*.java
java -cp ../bin Main
```

### Issue 3: GitHub doesn't show empty folders
**Solution**: Add a `.gitkeep` file in each package folder

---

## ✅ Testing Checklist

After refactoring, verify:

- [ ] All files have correct package declarations
- [ ] All imports are updated
- [ ] Project compiles without errors
- [ ] Application runs successfully
- [ ] All features work (add, search, borrow, return)
- [ ] Database operations function correctly
- [ ] Exception handling works properly
- [ ] UI displays correctly

---

## 🚀 GitHub Workflow

### Recommended Approach:

**Option 1: Manual File Movement (GitHub Web UI)**
1. Create new package folders by creating `.gitkeep` files
2. Copy content of each file
3. Create new file in target package folder
4. Add package declaration
5. Update imports
6. Delete old file location
7. Commit with message: "Refactor: Move [ClassName] to [package] package"

**Option 2: Local Git Operations (Faster)**
1. Clone repository locally
2. Create package folders
3. Move files using `git mv`
4. Update package declarations and imports
5. Test compilation
6. Commit all changes: "Refactor: Reorganize project with professional package structure"
7. Push to GitHub

---

## 📊 Benefits of This Structure

✅ **Professional Organization** - Industry-standard package structure  
✅ **Better Maintainability** - Easy to locate and modify code  
✅ **Improved Scoring** - Demonstrates software architecture knowledge  
✅ **Scalability** - Easy to add new features in appropriate packages  
✅ **Team Collaboration** - Clear separation of responsibilities  
✅ **Testing** - Easier to write unit tests for each layer  

---

## 🎓 Scoring Impact

**Before Package Structure:**
- Code Organization: 3/5
- Architecture: 2/5

**After Package Structure:**
- Code Organization: 5/5 ✅
- Architecture: 5/5 ✅
- **Bonus Points**: Shows understanding of MVC/layered architecture

---

## 📚 Package Naming Conventions

- Use lowercase for package names
- Use singular nouns (model, not models)
- Keep names short and descriptive
- Follow standard Java conventions

**Standard Conventions:**
- `model` or `entity` - Data classes
- `dao` or `repository` - Data access
- `service` or `business` - Business logic
- `ui` or `view` or `presentation` - User interface
- `util` or `helper` - Utility classes
- `exception` or `exceptions` - Custom exceptions

---

## 🔄 Migration Priority

**High Priority (Do First):**
1. ✅ exceptions/ - Already done!
2. util/ - DatabaseConnection (used by many classes)
3. model/ - Data classes (no dependencies)

**Medium Priority (Do Next):**
4. service/ - LibraryActions, LibraryService
5. dao/ - All DAO classes

**Low Priority (Do Last):**
6. ui/ - MainFrame (depends on everything)
7. Root - Main.java (entry point, update last)

---

## 💡 Pro Tips

1. **Test After Each Package** - Don't move everything at once
2. **Use IDE Features** - If using IntelliJ/Eclipse, use refactoring tools
3. **Keep Backup** - Create a branch before major refactoring
4. **Document Changes** - Update README with new structure
5. **Consistent Imports** - Use wildcard (*) imports for same package

---

## 📖 Example Commit Messages

```
Refactor: Create professional package structure
Refactor: Move model classes to model package
Refactor: Move DAO classes to dao package
Refactor: Move service classes to service package
Refactor: Move UI classes to ui package
Refactor: Move utility classes to util package
Refactor: Update all imports after package reorganization
Docs: Update README with new project structure
```

---

**Last Updated**: December 2025  
**Status**: Ready for Implementation  
**Estimated Time**: 2-3 hours for complete refactoring  
**Difficulty**: Intermediate  
**Impact**: High - Significantly improves code quality score
