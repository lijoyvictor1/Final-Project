package com.mycompany.finalproject.servlets;

import com.mycompany.finalproject.dao.UserDAO;
import com.mycompany.finalproject.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");

        // Basic input validation (you can expand this)
        if (name == null || email == null || password == null || userType == null) {
            // Handle validation errors (e.g., show an error message)
            response.sendRedirect("register.html"); // Redirect back to registration form
            return;
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setUserType(userType);

        UserDAO userDAO = new UserDAO();

        try {
            userDAO.addUser(user);
            response.sendRedirect("register-success.html"); // Redirect on success
        } catch (SQLException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, "Error adding user", ex);
            // Optionally redirect to an error page or display an error message
            response.sendRedirect("error.html");
        }
    }
}
