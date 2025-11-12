package app;
import java.sql.*;

public class UserDAO {

    public boolean register(String username, String password, String displayName) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO users(username,password,display_name) VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, displayName);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Integer login(String username, String password) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT id FROM users WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return rs.getInt("id");
        } catch (Exception e) {}
        return null;
    }
}
