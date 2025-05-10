<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 10/05/2025
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>Submit Review</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h2 class="mb-4">Leave a Review</h2>

    <form action="ReviewServlet" method="post">
        <input type="hidden" name="action" value="submit" />
        <input type="hidden" name="username" value="<%= user.getUsername() %>" />

        <div class="mb-3">
            <label class="form-label">Target Type</label>
            <select name="targetType" class="form-control" required>
                <option value="bike">Bike</option>
                <option value="ride">Ride</option>
            </select>
        </div>

        <div class="mb-3">
            <label class="form-label">Target ID</label>
            <input type="text" name="targetId" class="form-control" placeholder="Enter Bike or Ride ID" required />
        </div>

        <div class="mb-3">
            <label class="form-label">Rating (1–5)</label>
            <input type="number" name="rating" class="form-control" min="1" max="5" required />
        </div>

        <div class="mb-3">
            <label class="form-label">Comment</label>
            <textarea name="comment" class="form-control" required></textarea>
        </div>

        <button type="submit" class="btn btn-primary">Submit Review</button>
    </form>
</div>

</body>
</html>
