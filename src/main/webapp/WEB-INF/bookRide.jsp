
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ page import="model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<%
    String priceParam = request.getParameter("price");
    double price = 0.0;
    if (priceParam != null && !priceParam.isEmpty()) {
        try {
            price = Double.parseDouble(priceParam);
        } catch (Exception e) {
            price = 0.0;
        }
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Book a Ride</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h2 class="mb-4">Book a Ride</h2>

    <form action="BookingServlet" method="post">
        <input type="hidden" name="action" value="book" />
        <input type="hidden" name="username" value="<%= user.getUsername() %>" />
        <input type="hidden" name="rideId" value="<%= request.getParameter("rideId") %>" />
        <input type="hidden" name="price" value="<%= request.getParameter("price") %>" />

        <div class="mb-3">
            <label class="form-label">Seats to Book</label>
            <input type="number" name="seats" class="form-control" value="1" min="1" required />
        </div>

        <div class="mb-3">
            <label class="form-label">Payment Method</label>
            <select name="paymentMethod" class="form-control" required>
                <option value="Cash">Cash</option>
                <option value="Card">Card</option>
                <option value="Online">Online</option>
            </select>
        </div>

        <!-- total input inside the form -->
        <div class="mb-3">
            <label class="form-label">Total Amount (LKR)</label>
            <input type="text" class="form-control" name="total" readonly
                   value="<%= price %>" />
        </div>


        <button type="submit" class="btn btn-primary">Confirm Booking</button>
    </form>
</div>

</body>
</html>
