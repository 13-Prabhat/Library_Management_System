import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.nio.file.*;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:sqlite:library.db";

    static {
        // initialize database file and tables if not exist
        try (Connection conn = getConnection()){
            try (Statement s = conn.createStatement()){
                s.execute("""CREATE TABLE IF NOT EXISTS books(
                    id INTEGER PRIMARY KEY,
                    title TEXT NOT NULL,
                    author TEXT,
                    available INTEGER DEFAULT 1
                )""");
                s.execute("""CREATE TABLE IF NOT EXISTS members(
                    id INTEGER PRIMARY KEY,
                    name TEXT NOT NULL
                )""");
                s.execute("""CREATE TABLE IF NOT EXISTS transactions(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    book_id INTEGER,
                    member_id INTEGER,
                    issue_date TEXT,
                    due_date TEXT,
                    return_date TEXT
                )""");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    // simple backup utility
    public static void backupDatabase(){
        Path src = Paths.get("library.db");
        Path dest = Paths.get("backup_library.db");
        try {
            if (Files.exists(src)) {
                Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Database backup completed: " + dest.toAbsolutePath());
            } else {
                System.out.println("No database file to backup yet.");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
