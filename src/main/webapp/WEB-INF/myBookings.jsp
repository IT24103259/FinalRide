
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ page import="java.util.*, model.User, model.Booking" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Booking> bookings = (List<Booking>) request.getAttribute("bookingList");
%>

<!DOCTYPE html>
<html>
<head>
    <title>My Bookings</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h2 class="mb-4">My Bookings</h2>

    <%
        if (bookings == null || bookings.isEmpty()) {
    %>
    <p>No bookings found.</p>
    <%
    } else {
    %>

    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Booking ID</th>
            <th>Ride ID</th>
            <th>Seats</th>
            <th>Total (LKR)</th>
            <th>Status</th>
            <th>Payment</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Booking b : bookings) {
        %>
        <tr>
            <td><%= b.getBookingId() %></td>
            <td><%= b.getRideId() %></td>
            <td><%= b.getSeatsBooked() %></td>
            <td><%= b.getTotalAmount() %></td>
            <td><%= b.getStatus() %></td>
            <td><%= b.getPaymentMethod() %></td>
            <td>
                <% if ("confirmed".equals(b.getStatus())) { %>
                <form action="BookingServlet" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="cancel" />
                    <input type="hidden" name="bookingId" value="<%= b.getBookingId() %>" />
                    <input type="hidden" name="username" value="<%= user.getUsername() %>" />
                    <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Cancel this booking?');">Cancel</button>
                </form>
                <% } else { %>
                <span class="text-muted">N/A</span>
                <% } %>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <% } %>
</div>

</body>
</html>
