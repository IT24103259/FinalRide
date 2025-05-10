
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.Bike" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bike List</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h2 class="mb-4">Available Bikes</h2>

    <a href="addBike.jsp" class="btn btn-success mb-3">+ Add New Bike</a>

    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Bike ID</th>
            <th>Model Name</th>
            <th>Type</th>
            <th>Price/Hour (LKR)</th>
            <th>Availability</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Bike> bikes = (List<Bike>) request.getAttribute("bikeList");
            if (bikes != null && !bikes.isEmpty()) {
                for (Bike bike : bikes) {
        %>
        <tr>
            <td><%= bike.getId() %></td>
            <td><%= bike.getName() %></td>
            <td><%= bike.getType() %></td>
            <td><%= bike.getPricePerHour() %></td>
            <td><%= bike.isAvailable() ? "Yes" : "No" %></td>
            <td>
                <!-- Edit form -->
                <form action="editBike.jsp" method="get" style="display:inline;">
                    <input type="hidden" name="id" value="<%= bike.getId() %>" />
                    <input type="hidden" name="name" value="<%= bike.getName() %>" />
                    <input type="hidden" name="type" value="<%= bike.getType() %>" />
                    <input type="hidden" name="price" value="<%= bike.getPricePerHour() %>" />
                    <input type="hidden" name="available" value="<%= bike.isAvailable() %>" />
                    <button type="submit" class="btn btn-warning btn-sm">Edit</button>
                </form>

                <!-- Delete form -->
                <form action="<%= request.getContextPath() %>/BikeServlet" method="post">
                    <input type="hidden" name="action" value="delete" />
                    <input type="hidden" name="id" value="<%= bike.getId() %>" />
                    <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Delete this bike?');">Delete</button>
                </form>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr><td colspan="6">No bikes available.</td></tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

</body>
</html>
