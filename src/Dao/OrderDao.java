package Dao;

import bean.Order;

import java.sql.*;

public class OrderDao {
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?characterEncoding=UTF-8&useSSL=true&serverTimezone=GMT%2B8",
                "root", "admin");
        return conn;
    }

    public void inset(Order o) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        String sql = "insert into order_ values(null,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, o.getUser().getId());
        pstmt.execute();

        //获取自增长id
        ResultSet rs = pstmt.getGeneratedKeys();
        if (rs.next()) {
            int id = rs.getInt(1);
            o.setId(id);
        }
        pstmt.close();
        conn.close();
    }
}
