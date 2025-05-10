package com.RealEstate.servlet;

import com.RealEstate.model.Buyer;
import com.RealEstate.service.BuyerService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class BuyerLoginServlet extends HttpServlet {
    private BuyerService buyerService;

    @Override
    public void init() throws ServletException {
        buyerService = new BuyerService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println("Login attempt - Email: " + email);

        try {
            Buyer buyer = buyerService.authenticateBuyer(email, password);
            System.out.println("Authentication result - Buyer: " + (buyer != null ? "Found" : "Not found"));
            
            if (buyer != null) {
                System.out.println("Login successful for: " + email);
                HttpSession session = request.getSession();
                session.setAttribute("buyer", buyer);
                session.setAttribute("userType", "buyer");
                session.setAttribute("successMessage", "Login successful! Welcome back " + buyer.getFullName());
                response.sendRedirect("index.jsp");
            } else {
                System.out.println("Login failed for: " + email + " - Invalid credentials");
                request.setAttribute("error", "Invalid email or password");
                request.getRequestDispatcher("buyer_login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.err.println("Error during login: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("error", "An error occurred during login: " + e.getMessage());
            request.getRequestDispatcher("buyer_login.jsp").forward(request, response);
        }
    }
} 