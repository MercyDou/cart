package Dao;

import bean.OrderItem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderItemDao {
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?characterEncoding=UTF-8&useSSL=true&serverTimezone=GMT%2B8",
                "root", "admin");
        return conn;
    }

    public void insert(OrderItem oi) throws SQLException, ClassNotFoundException {
        String sql = "insert into orderitem values(null,?,?,?)";
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, oi.getProduct().getId());
        pstmt.setInt(2, oi.getNum());
        pstmt.setInt(3, oi.getOrder().getId());

        pstmt.execute();
        pstmt.close();
        conn.close();
    }
}
