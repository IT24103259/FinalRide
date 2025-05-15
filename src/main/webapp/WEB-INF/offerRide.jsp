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


<!DOCTYPE html>
<html>
<head>
    <title>Offer Ride</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h2 class="mb-4">Offer a New Ride</h2>

    <form action="<%= request.getContextPath() %>/RideServlet" method="post">
        <input type="hidden" name="action" value="offer" />
        <input type="hidden" name="driver" value="<%= user.getUsername() %>" />

        <div class="mb-3">
            <label class="form-label">From (Origin)</label>
            <input type="text" name="origin" class="form-control" required />
        </div>

        <div class="mb-3">
            <label class="form-label">To (Destination)</label>
            <input type="text" name="destination" class="form-control" required />
        </div>

        <div class="mb-3">
            <label class="form-label">Date</label>
            <input type="date" name="date" class="form-control" required />
        </div>

        <div class="mb-3">
            <label class="form-label">Seats Available</label>
            <input type="number" name="seats" class="form-control" required />
        </div>

        <div class="mb-3">
            <label class="form-label">Price (LKR)</label>
            <input type="number" step="0.01" name="price" class="form-control" required />
        </div>

        <button type="submit" class="btn btn-primary">Offer Ride</button>
    </form>
</div>

</body>
</html>