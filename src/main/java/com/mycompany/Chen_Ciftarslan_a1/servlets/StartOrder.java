package com.mycompany.Chen_Ciftarslan_a1.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nancy Chen
 */
public class StartOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            writeHeader(out);

            String customerName = request.getParameter("name");
            String customerPhone = request.getParameter("phone");

            //try to get rid of space or "-" character, only catch digital for formatting
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < customerPhone.length(); i++) {
                char ch = customerPhone.charAt(i);
                if (Character.isDigit(ch)) {
                    builder.append(ch);
                }
            }

            //check name and phone is correct or not, phone number should be 10 digital
            //and should be enter name, dectect only type space as well
            if (customerName == null || customerName.trim().length() == 0
                    || customerPhone == null || customerPhone.trim().length() == 0 || builder.length() != 10) {
                out.println("<h1>Please enter correctly name and phone(Phone should be 10 digital number) </h1>");
            } else {

                //fomating output number is XXX XXX-XXX
                //first I get all digital already
                //then insert space and hyphen to offset 3 and 7 spot
                builder.insert(3, " ");
                builder.insert(7, "-");

                //HttpSession for pass to displayOrder.jsp purpose
                HttpSession session = request.getSession();
                session.setAttribute("customerName", customerName);
                session.setAttribute("customerPhone", builder.toString());

                String format = "<h1>Hi %s </h1> <h2> %s </h2>";
                out.printf(String.format(format, customerName, builder.toString()));

                out.println("<form action=\"PlaceOrder.do\" method=\"POST\">");
                out.println("Pick up or delivery ");
                out.println("<select name=\"pickupOrDelievery\">");
                out.println("<option value=\"pickup\">Pick Up</option>");
                out.println("<option value=\"delivery\">Delivery</option>");
                out.println("</select>");
                out.println("<br><br><br>");

                //create pizza size option(small, medium,and large)
                String[] size = new String[]{"Small", "Medium", "Large"};
                double price = 5;
                for (int i = 0; i < size.length; i++) {
                    out.println("<input type=\"radio\" name=\"pizzaSize\" value=\"" + size[i] + "\">" + size[i] + "($" + String.valueOf(price).substring(0, 1) + ")");
                    price += 2;
                }
                out.println("<br><br><br>");

                //create pizza topping option("Pepperoni", "Sausage", "Baby Spinach", "Pepper");
                String[] topping = new String[]{"Pepperoni", "Sausage", "Baby Spinach", "Pepper"};
                for (int i = 0; i < topping.length; i++) {
                    out.println("<input type=\"checkbox\" name=\"topping\" value=\"" + topping[i] + "\">" + topping[i] + "<br>");
                }
                out.println("<br><br><br>");

                out.println("<input type=\"submit\" value=\"Place My Order\">");

                out.println("</form>");

                writeFooter(out);
            }
        }
    }

    private void writeHeader(final PrintWriter out) {
        /* TODO output your page here. You may use following sample code. */
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Place your order</title>");
        out.println("</head>");
        out.println("<body>");
    }

    private void writeFooter(final PrintWriter out) {
        out.println("</body>");
        out.println("</html>");
    }

}
