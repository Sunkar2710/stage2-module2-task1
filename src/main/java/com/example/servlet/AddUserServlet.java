package com.example.servlet;

import com.example.User;
import com.example.Warehouse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        // Retrieve the parameters from the request
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        // Create a new user and save it in the Warehouse
        User user = new User(firstName, lastName);
        Warehouse warehouse = Warehouse.getInstance();
        warehouse.addUser(user);

        // Set the "user" attribute in the request
        request.setAttribute("user", warehouse.getUsers());

        // Forward the request to the "/add" page
        try {
            request.getRequestDispatcher("/jsp/add.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
