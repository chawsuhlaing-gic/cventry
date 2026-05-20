import java.sql.*;
public class TestConnection {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/db_cventry?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Yangon&useSSL=false",
            "root",
            "Chaw562!" // replace with password if any
        );
        System.out.println("Connected!");
        conn.close();
    }
}