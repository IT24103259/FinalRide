<%@ page import="java.util.List" %>
<%@ page import="model.Ride" %>
<%@ page import="model.User" %>
<%@ page session="true" %>
<%@ page import="model.User" %>
<%
    User currentUser = (User) session.getAttribute("user");
    if (currentUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Available Rides</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h2 class="mb-4">Available Rides</h2>

    <a href="offerRide.jsp" class="btn btn-success mb-3">+ Offer New Ride</a>

    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Driver</th>
            <th>From</th>
            <th>To</th>
            <th>Date</th>
            <th>Seats</th>
            <th>Price (LKR)</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Ride> rides = (List<Ride>) request.getAttribute("rideList");
            if (rides != null && !rides.isEmpty()) {
                for (Ride ride : rides) {
        %>
        <tr>
            <td><%= ride.getDriverUsername() %></td>
            <td><%= ride.getOrigin() %></td>
            <td><%= ride.getDestination() %></td>
            <td><%= ride.getDate() %></td>
            <td><%= ride.getSeats() %></td>
            <td><%= ride.getPrice() %></td>
            <td><%= ride.getStatus() %></td>
            <td>
            <td>
                <%-- If logged-in user is the driver, show Edit/Delete --%>
                <%
                    if (currentUser.getUsername().equals(ride.getDriverUsername())) {
                %>
                <!-- Edit -->
                <form action="editRide.jsp" method="get" style="display:inline;">
                    <input type="hidden" name="id" value="<%= ride.getId() %>" />
                    <input type="hidden" name="driver" value="<%= ride.getDriverUsername() %>" />
                    <input type="hidden" name="origin" value="<%= ride.getOrigin() %>" />
                    <input type="hidden" name="destination" value="<%= ride.getDestination() %>" />
                    <input type="hidden" name="date" value="<%= ride.getDate() %>" />
                    <input type="hidden" name="seats" value="<%= ride.getSeats() %>" />
                    <input type="hidden" name="price" value="<%= ride.getPrice() %>" />
                    <input type="hidden" name="status" value="<%= ride.getStatus() %>" />
                    <button type="submit" class="btn btn-warning btn-sm me-1">Edit</button>
                </form>

                <!-- Delete -->
                <form action="RideServlet" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete" />
                    <input type="hidden" name="id" value="<%= ride.getId() %>" />
                    <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Delete this ride?');">Delete</button>
                </form>

                <%
                } else {
                %>
                <form action="bookRide.jsp" method="get">
                    <input type="hidden" name="rideId" value="<%= ride.getId() %>" />
                    <input type="hidden" name="price" value="<%= ride.getPrice() %>" />
                    <button type="submit" class="btn btn-success btn-sm">Book</button>
                </form>

                <%
                    }
                %>
            </td>

        </tr>
        <%
            }
        } else {
        %>
        <tr><td colspan="8">No rides available.</td></tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

</body>
</html>