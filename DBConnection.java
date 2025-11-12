package app;
import java.sql.*;

public class DBConnection {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/online_forum",
                "root",
                "Kavi@2004"
            );
        } catch (Exception e) {
            System.out.println("DB Error: " + e);
            return null;
        }
    }
}
