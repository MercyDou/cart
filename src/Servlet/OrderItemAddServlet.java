package Servlet;

import Dao.ProductDao;
import bean.OrderItem;
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

@WebServlet(name = "OrderItemAddServlet")
public class OrderItemAddServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int num =Integer.valueOf(req.getParameter("num")) ;
        int id = Integer.valueOf(req.getParameter("pid"));

        try {
            Product product = new ProductDao().getProduct(id);
            OrderItem oi = new OrderItem();
            oi.setNum(num);
            oi.setProduct(product);

            List<OrderItem> ois = (List<OrderItem>) req.getSession().getAttribute("ois");
            if (null == ois) {
                ois = new ArrayList<>();
                req.getSession().setAttribute("ois", ois);
            }
            //如果添加了重复商品
            boolean found = false;
            for (OrderItem orderItem : ois) {
                if (orderItem.getProduct().getId() == oi.getProduct().getId()) {
                    orderItem.setNum(orderItem.getNum() + oi.getNum());
                    found = true;
                    break;
                }
            }
            if(!found)
                ois.add(oi);

            resp.sendRedirect("/listOrderItem");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
