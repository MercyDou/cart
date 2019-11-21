package Servlet;

import bean.OrderItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderItemDeleteServlet")
public class OrderItemDeleteServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id =Integer.valueOf(req.getParameter("id"));
        List<OrderItem> ois =(List<OrderItem>) req.getSession().getAttribute("ois");
        for (int i = 0; i < ois.size(); i++) {
            if (id == ois.get(i).getProduct().getId()) {
                ois.remove(i);
            }
        }
       // resp.sendRedirect("/listOrderItem.jsp");
        req.getRequestDispatcher("/listOrderItem.jsp").forward(req,resp);
    }
}
