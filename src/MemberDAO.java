import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO implements LibraryActions<Member> {
    public synchronized void add(Member m) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection()){
            String sql = "INSERT INTO members(id, name) VALUES(?,?)";
            try (PreparedStatement ps = c.prepareStatement(sql)){
                ps.setInt(1, m.getId());
                ps.setString(2, m.getName());
                ps.executeUpdate();
            }
        }
    }

    public synchronized void update(Member m) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection()){
            String sql = "UPDATE members SET name=? WHERE id=?";
            try (PreparedStatement ps = c.prepareStatement(sql)){
                ps.setString(1, m.getName());
                ps.setInt(2, m.getId());
                ps.executeUpdate();
            }
        }
    }

    public synchronized void delete(int id) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection()){
            String sql = "DELETE FROM members WHERE id=?";
            try (PreparedStatement ps = c.prepareStatement(sql)){
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        }
    }

    public Member find(int id) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection()){
            String sql = "SELECT id, name FROM members WHERE id=?";
            try (PreparedStatement ps = c.prepareStatement(sql)){
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()){
                    if (rs.next()){
                        return new Member(rs.getInt(1), rs.getString(2));
                    }
                }
            }
        }
        return null;
    }

    public List<Member> all() throws SQLException {
        List<Member> list = new ArrayList<>();
        try (Connection c = DatabaseConnection.getConnection();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery("SELECT id, name FROM members")){
            while (rs.next()){
                list.add(new Member(rs.getInt(1), rs.getString(2)));
            }
        }
        return list;
    }
}
