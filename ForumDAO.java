package app;
import java.sql.*;
import java.util.*;

public class ForumDAO {

    public void createTopic(String title, int userId) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO topics(title,user_id) VALUES (?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, title);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (Exception e) { }
    }

    public java.util.List<String> getTopics() {
        java.util.List<String> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT topics.id, title, display_name FROM topics JOIN users ON topics.user_id = users.id";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) list.add(rs.getInt(1)+" - "+rs.getString(2)+" (by "+rs.getString(3)+")");
        } catch (Exception e) {}
        return list;
    }

    public void addPost(int topicId, int userId, String content) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO posts(topic_id,user_id,content) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, topicId);
            ps.setInt(2, userId);
            ps.setString(3, content);
            ps.executeUpdate();
        } catch (Exception e) { }
    }

    public java.util.List<String> getPosts(int topicId) {
        java.util.List<String> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT display_name, content FROM posts JOIN users ON posts.user_id = users.id WHERE topic_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, topicId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) list.add(rs.getString(1)+": "+rs.getString(2));
        } catch (Exception e) {}
        return list;
    }
}
