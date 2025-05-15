<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Bike</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h2>Edit Bike Details</h2>

    <form action="<%= request.getContextPath() %>/BikeServlet" method="post">
        <input type="hidden" name="action" value="update" />
        <input type="hidden" name="id" value="<%= request.getParameter("id") %>" />

        <div class="mb-3">
            <label for="name" class="form-label">Bike Name</label>
            <input type="text" class="form-control" id="name" name="name" value="<%= request.getParameter("name") %>" required>
        </div>

        <div class="mb-3">
            <label for="type" class="form-label">Type</label>
            <select class="form-control" id="type" name="type">
                <option value="Electric" <%= "Electric".equals(request.getParameter("type")) ? "selected" : "" %>>Electric</option>
                <option value="Regular" <%= "Regular".equals(request.getParameter("type")) ? "selected" : "" %>>Regular</option>
            </select>
        </div>

        <div class="mb-3">
            <label for="price" class="form-label">Price Per Hour (LKR)</label>
            <input type="number" step="0.01" class="form-control" id="price" name="price" value="<%= request.getParameter("price") %>" required>
        </div>

        <div class="mb-3">
            <label for="available" class="form-label">Available</label>
            <select class="form-control" id="available" name="available">
                <option value="true" <%= "true".equals(request.getParameter("available")) ? "selected" : "" %>>Yes</option>
                <option value="false" <%= "false".equals(request.getParameter("available")) ? "selected" : "" %>>No</option>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Update Bike</button>
    </form>
</div>

</body>
</html>