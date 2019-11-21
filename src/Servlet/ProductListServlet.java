package Servlet;

import Dao.ProductDao;
import bean.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductListServlet")
public class ProductListServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("****************************");
        System.out.println("*      欢迎来到购物车!      *");
        System.out.println("****************************");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        try {
            List<Product> productList = new ProductDao().products();
            req.setAttribute("list", productList);
           /* for (Product p : productList) {
                System.out.println("test"+p.getName());
            }*/
            req.getRequestDispatcher("listProduct.jsp").forward(req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
