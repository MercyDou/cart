package Dao;

import bean.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println(new ProductDao().products().size());
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?characterEncoding=UTF-8&useSSL=true&serverTimezone=GMT%2B8",
                                   "root", "admin");
        return conn;
    }

    public Product getProduct(int id) throws SQLException, ClassNotFoundException {
        Product result = null;
        Connection conn = getConnection();
        String sql = "select * from product where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            result = new Product();
            result.setId(id);

            String name = rs.getString(2);
            float price = rs.getFloat(3);

            result.setName(name);
            result.setPrice(price);
        }
        pstmt.close();
        conn.close();
        return result;
    }

    public List<Product> products() throws SQLException, ClassNotFoundException {
        List<Product> list = new ArrayList<>();
        Connection conn = getConnection();
        String sql = "select * from product order by id asc ";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            int id = rs.getInt(1);
            String name = rs.getString(2);
            float price = rs.getFloat(3);

            product.setId(id);
            product.setName(name);
            product.setPrice(price);
            list.add(product);
        }
        pstmt.close();
        conn.close();
        return list;
    }
}
