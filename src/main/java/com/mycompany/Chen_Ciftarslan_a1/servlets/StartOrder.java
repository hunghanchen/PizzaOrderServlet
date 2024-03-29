package com.mycompany.Chen_Ciftarslan_a1.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * In this program we are creating a basic Java Servlet Application to order a
 * pizza.
 *
 * @authors Hung-Han,Chen AliCemilcan Date:24.09.2019
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
            //Define a regex for the name, it should be all character with a max limit of 20 and min 2
            String RegexforName = "([a-zA-Z]{2,20})";
            Pattern patternName = Pattern.compile(RegexforName);
            Matcher matcherName = patternName.matcher(customerName.replaceAll(" ", ""));

            // Define a regex for the Ontorio Phone numbers(647-XXX-XXXX)
            String RegexforPhone = "([6]{1}[4]{1}[7]{1}[0-9]{7})";
            Pattern patternPhone = Pattern.compile(RegexforPhone);
            //Create a matcher for phone number
            Matcher matcherPhone = patternPhone.matcher(builder.toString());

            
            if (!matcherName.matches()) {
                out.println("<h1>Please enter a valid name </h1>");
            } else if (!matcherPhone.matches()) {                 
                out.println("<h1>Please enter a valid phone number </h1>");
            } else {

                //fomating output number is XXX XXX-XXX
                //builder got all digital already
                String Phonenumber = builder.toString().replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");

                //HttpSession for pass to displayOrder.jsp purpose
                HttpSession session = request.getSession();
                session.setAttribute("customerName", customerName);
                session.setAttribute("customerPhone", Phonenumber);

                String format = "<h1>Hi %s </h1> <h2> %s </h2>";
                out.printf(String.format(format, customerName, Phonenumber));

                out.println("<form action='PlaceOrder.do' method='POST'>");
                out.println("Pick up or delivery ");
                out.println("<select name='pickupOrDelievery'>");
                out.println("<option value='pickup'>Pick Up</option>");
                out.println("<option value='delivery'>Delivery</option>");
                out.println("</select>");
                out.println("<br><br><br>");

                //create pizza size option(small, medium,and large)
                //We are using loop, we dont want redundant
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
