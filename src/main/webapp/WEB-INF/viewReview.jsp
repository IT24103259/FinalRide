<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 10/05/2025
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, java.io.*" %>
<%@ page import="model.Review, model.User" %>
<%@ page session="true" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Review> reviews = new ArrayList<>();
    String path = "C:\\Users\\LOQ\\Desktop\\di bike\\dibike\\data\\reviews.txt";  // <-- Adjust if needed

    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
        String line;
        while ((line = br.readLine()) != null) {
            reviews.add(Review.fromFileString(line));
        }
    } catch (Exception e) {
        System.out.println("<p>Error loading reviews: " + e.getMessage() + "</p>");
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Reviews</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h2 class="text-center mb-4">All Reviews</h2>

    <% if (reviews.isEmpty()) { %>
    <p class="text-center">No reviews available.</p>
    <% } else { %>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>User</th>
            <th>Bike ID</th>
            <th>Rating</th>
            <th>Comment</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <% for (Review r : reviews) { %>
        <tr>
            <td><%= r.getUsername() %></td>
            <td><%= r.getId() %></td>
            <td><%= r.getRating() %></td>
            <td><%= r.getComment() %></td>
            <td>
                <% if (user.getUsername().equals(r.getUsername()) || "admin".equals(user.getRole())) { %>
                <form action="ReviewServlet" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="edit" />
                    <input type="hidden" name="id" value="<%= r.getId() %>" />
                    <button class="btn btn-warning btn-sm">Edit</button>
                </form>
                <form action="ReviewServlet" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete" />
                    <input type="hidden" name="id" value="<%= r.getId() %>" />
                    <button class="btn btn-danger btn-sm">Delete</button>
                </form>
                <% } else { %>
                <span class="text-muted">No access</span>
                <% } %>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <% } %>
</div>

</body>
</html>