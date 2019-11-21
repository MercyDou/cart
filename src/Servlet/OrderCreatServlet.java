package Servlet;

import Dao.OrderDao;
import Dao.OrderItemDao;
import bean.Order;
import bean.OrderItem;
import bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "OrderCreatServlet")
public class OrderCreatServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User u = (User) req.getSession().getAttribute("user");
        if (null == u) {
            resp.sendRedirect("/login.jsp");
            return;
        }

        Order o = new Order();
        o.setUser(u);

        try {
            new OrderDao().inset(o);
            List<OrderItem> ois = (List<OrderItem>) req.getSession().getAttribute("ois");
            for (OrderItem oi : ois) {
                oi.setOrder(o);
                new OrderItemDao().insert(oi);
            }
            ois.clear();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("订单创建成功");
    }
}
