package com.mycompany.Chen_Ciftarslan_a1.servlets;

import com.mycompany.Chen_Ciftarslan_a1.model.PizzaOrder;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nancy Chen
 */
public class PlaceOrder extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            PizzaOrder pizzaOrder = new PizzaOrder();
            String pizzaSize = request.getParameter("pizzaSize");
            // Referer is for back previous page 
            String referer = request.getHeader("Referer");

            //If customers didn't chose pizza size it will create another page
            //to remind customers and back to StartOrder page to refill again
            if (request.getParameter("pizzaSize") == null) {
                writeHeader(out);
                out.println("<h1> Please choose the pizza size</h1>");
                out.println("<h2><a href =\" " + referer + "\" > Back to Order Page </a></h2>");
                writeFooter(out);
            } else {
                String pickupOrDelievery = request.getParameter("pickupOrDelievery");
                Boolean isDelieveryOrNot = pickupOrDelievery.equalsIgnoreCase("delivery");

                // for the displayOrder, We are assign the different infomation 
                //for the pickup and delievery
                if (isDelieveryOrNot) {
                    String pickOrDelievery = "Your pizza will be delivered within 40 minutes";
                    request.setAttribute("pickOrDelievery", pickOrDelievery);
                } else {
                    String pickOrDelievery = "Your pizza will be ready for pickup in 20 minutes";
                    request.setAttribute("pickOrDelievery", pickOrDelievery);

                }

                //if customer didn't chose any topping, topping won't have null exception
                String[] topping = new String[]{};
                if (request.getParameterValues("topping") == null) {
                    topping = new String[0];
                } else {
                    topping = request.getParameterValues("topping");
                }

                pizzaOrder.setTopping(topping);
                request.setAttribute("toppingName", topping);

                pizzaOrder.setSize(pizzaSize);
                
                pizzaOrder.setDelivery(isDelieveryOrNot);

                request.setAttribute("pizzaOrder", pizzaOrder);

                RequestDispatcher rd = request.getRequestDispatcher("displayOrder.jsp");
                rd.forward(request, response);
            }

        }
    }

    private void writeHeader(final PrintWriter out) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Choose your pizze size</title>");
        out.println("</head>");
        out.println("<body>");

    }

    private void writeFooter(final PrintWriter out) {
        out.println("</body>");
        out.println("</html>");
    }
}
