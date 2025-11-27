import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO implements LibraryActions<Transaction> {
    public synchronized void add(Transaction t) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection()){
            String sql = "INSERT INTO transactions(book_id, member_id, issue_date, due_date, return_date) VALUES(?,?,?,?,?)";
            try (PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                ps.setInt(1, t.getBookId());
                ps.setInt(2, t.getMemberId());
                ps.setString(3, t.getIssueDate().toString());
                ps.setString(4, t.getDueDate().toString());
                ps.setString(5, t.getReturnDate()==null?null:t.getReturnDate().toString());
                ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys()){
                    if (keys.next()){
                        // set id via reflection would be complex; skipping
                    }
                }
            }
            // mark book unavailable
            try (PreparedStatement ps2 = c.prepareStatement("UPDATE books SET available=0 WHERE id=?")){
                ps2.setInt(1, t.getBookId());
                ps2.executeUpdate();
            }
        }
    }

    public synchronized void update(Transaction t) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection()){
            String sql = "UPDATE transactions SET return_date=? WHERE id=?";
            try (PreparedStatement ps = c.prepareStatement(sql)){
                ps.setString(1, t.getReturnDate()==null?null:t.getReturnDate().toString());
                ps.setInt(2, t.getId());
                ps.executeUpdate();
            }
            if (t.getReturnDate()!=null){
                try (PreparedStatement ps2 = c.prepareStatement("UPDATE books SET available=1 WHERE id=?")){
                    ps2.setInt(1, t.getBookId());
                    ps2.executeUpdate();
                }
            }
        }
    }

    public synchronized void delete(int id) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection()){
            String sql = "DELETE FROM transactions WHERE id=?";
            try (PreparedStatement ps = c.prepareStatement(sql)){
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        }
    }

    public Transaction find(int id) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection()){
            String sql = "SELECT id, book_id, member_id, issue_date, due_date, return_date FROM transactions WHERE id=?";
            try (PreparedStatement ps = c.prepareStatement(sql)){
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()){
                    if (rs.next()){
                        Transaction t = new Transaction(rs.getInt(1), rs.getInt(2), rs.getInt(3), LocalDate.parse(rs.getString(4)), LocalDate.parse(rs.getString(5)));
                        String rd = rs.getString(6);
                        if (rd!=null) t.setReturnDate(LocalDate.parse(rd));
                        return t;
                    }
                }
            }
        }
        return null;
    }

    public List<Transaction> all() throws SQLException {
        List<Transaction> list = new ArrayList<>();
        try (Connection c = DatabaseConnection.getConnection();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery("SELECT id, book_id, member_id, issue_date, due_date, return_date FROM transactions")){
            while (rs.next()){
                Transaction t = new Transaction(rs.getInt(1), rs.getInt(2), rs.getInt(3), LocalDate.parse(rs.getString(4)), LocalDate.parse(rs.getString(5)));
                String rd = rs.getString(6);
                if (rd!=null) t.setReturnDate(LocalDate.parse(rd));
                list.add(t);
            }
        }
        return list;
    }
}
