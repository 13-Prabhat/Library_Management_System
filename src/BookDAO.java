import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO implements LibraryActions<Book> {
    // synchronized to be thread-safe
    public synchronized void add(Book book) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection()){
            String sql = "INSERT INTO books(id, title, author, available) VALUES(?,?,?,?)";
            try (PreparedStatement ps = c.prepareStatement(sql)){
                ps.setInt(1, book.getId());
                ps.setString(2, book.getTitle());
                ps.setString(3, book.getAuthor());
                ps.setInt(4, book.isAvailable()?1:0);
                ps.executeUpdate();
            }
        }
    }

    public synchronized void update(Book book) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection()){
            String sql = "UPDATE books SET title=?, author=?, available=? WHERE id=?";
            try (PreparedStatement ps = c.prepareStatement(sql)){
                ps.setString(1, book.getTitle());
                ps.setString(2, book.getAuthor());
                ps.setInt(3, book.isAvailable()?1:0);
                ps.setInt(4, book.getId());
                ps.executeUpdate();
            }
        }
    }

    public synchronized void delete(int id) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection()){
            String sql = "DELETE FROM books WHERE id=?";
            try (PreparedStatement ps = c.prepareStatement(sql)){
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        }
    }

    public Book find(int id) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection()){
            String sql = "SELECT id, title, author, available FROM books WHERE id=?";
            try (PreparedStatement ps = c.prepareStatement(sql)){
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()){
                    if (rs.next()){
                        Book b = new Book(rs.getInt(1), rs.getString(2), rs.getString(3));
                        b.setAvailable(rs.getInt(4) == 1);
                        return b;
                    }
                }
            }
        }
        return null;
    }

    public List<Book> all() throws SQLException {
        List<Book> list = new ArrayList<>();
        try (Connection c = DatabaseConnection.getConnection();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery("SELECT id, title, author, available FROM books")){
            while (rs.next()){
                Book b = new Book(rs.getInt(1), rs.getString(2), rs.getString(3));
                b.setAvailable(rs.getInt(4) == 1);
                list.add(b);
            }
        }
        return list;
    }
}
