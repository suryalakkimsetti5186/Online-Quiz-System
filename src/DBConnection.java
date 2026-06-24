import java.sql.*;
public class DBConnection {
    public static Connection connect() {
        Connection conn=null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/quiz_system", "root", "9885640717");
            System.out.println("Database Connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
