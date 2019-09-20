<%-- 
    Document   : displayOrder
    Created on : 17-Sep-2019, 3:04:01 PM
    Author     : Nancy Chen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "com.mycompany.Chen_Ciftarslan_a1.model.PizzaOrder" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Details</title>
    </head>
    <body>
        <% PizzaOrder pizzaOrder = (PizzaOrder) request.getAttribute("pizzaOrder"); %>
        <% String pickOrDelievery = (String) request.getAttribute("pickOrDelievery"); %>
        <% String[] toppingName = (String[]) request.getAttribute("toppingName"); %>


        <% String customerNameTest = (String) session.getAttribute("customerName"); %>
        <% String customerPhoneTest = (String) session.getAttribute("customerPhone"); %>

        <h1>Pizza Order for  <% out.print(customerNameTest);%>,</h1>
        <h1><% out.print(customerPhoneTest);%> &nbsp; total $<% out.print(String.format("%.2f", pizzaOrder.getPrice())); %></h1>



        <%-- display pick up for 20 mins or delievery for 40 mins--%>
        <% if (pizzaOrder.isDelivery()) { %>
        <h2> <% out.print(pickOrDelievery);%> </h2>
        <% } else { %>
        <h2> <% out.print(pickOrDelievery);%> </h2>
        <% }%>

        <h3><% out.print(pizzaOrder.getSize());%> pizza with</h3>


        <% if (toppingName.length >= 1) { %>
        <ul>
            <% for (int i = 0; i < toppingName.length; i++) { %>
            <li> <% out.print(toppingName[i].toString());
                }%></li>
        </ul>
        <% } else { %>
        <% out.print("No Toppings");%>
        <% }%>
    </body>
</html>
