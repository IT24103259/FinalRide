package com.RealEstate.servlet;

import com.RealEstate.model.Buyer;
import com.RealEstate.service.BuyerService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BuyerRegistrationServlet extends HttpServlet {
    private BuyerService buyerService;

    @Override
    public void init() throws ServletException {
        buyerService = new BuyerService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            // Get form data
            String email = request.getParameter("email");
            String fullName = request.getParameter("fullName");
            String password = request.getParameter("password");
            String phoneNumber = request.getParameter("phoneNumber");
            String preferredLocation = request.getParameter("preferredLocation");
            double budget = 0.0;
            
            try {
                budget = Double.parseDouble(request.getParameter("budget"));
            } catch (NumberFormatException e) {
                // If budget is not provided or invalid, default to 0
            }

            // Validate required fields
            if (email == null || email.trim().isEmpty() ||
                fullName == null || fullName.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
                request.setAttribute("error", "Email, full name, and password are required");
                request.getRequestDispatcher("buyer_registration.jsp").forward(request, response);
                return;
            }

            // Create buyer object
            Buyer buyer = new Buyer();
            buyer.setEmail(email.trim());
            buyer.setFullName(fullName.trim());
            buyer.setPassword(password);
            buyer.setPhoneNumber(phoneNumber != null ? phoneNumber.trim() : "");
            buyer.setPreferredLocation(preferredLocation != null ? preferredLocation.trim() : "");
            buyer.setBudget(budget);

            // Register buyer
            boolean registered = buyerService.registerBuyer(buyer);

            if (registered) {
                // Set success message and redirect to login
                request.setAttribute("successMessage", "Registration successful! Please login.");
                response.sendRedirect("buyer_login.jsp");
            } else {
                request.setAttribute("error", "Email already exists. Please use a different email.");
                request.getRequestDispatcher("buyer_registration.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "An error occurred during registration: " + e.getMessage());
            request.getRequestDispatcher("buyer_registration.jsp").forward(request, response);
        }
    }
} 