package Dao;

import bean.User;

import java.sql.*;

public class UserDao {
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?characterEncoding=UTF-8&useSSL=true&serverTimezone=GMT%2B8",
                 "root", "admin");
        return conn;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println(new UserDao().getUser("tom", "123").getId());
    }

    public User getUser(String name, String password) throws SQLException, ClassNotFoundException {
        User result = null;
        String sql = "select * from user where name = ? and password = ?";

        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            result = new User();
            result.setId(rs.getInt(1));
            result.setName(name);
            result.setPassword(password);
        }
        pstmt.close();
        conn.close();

        return result;
    }
}
