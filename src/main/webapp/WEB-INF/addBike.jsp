
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Bike</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../css/style.css"> <!-- Link to your custom CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h2 class="mb-4">Add New Bike</h2>
    <form action="<%= request.getContextPath() %>/BikeServlet" method="post">
        <input type="hidden" name="action" value="add" />

        <div class="mb-3">
            <label for="name" class="form-label">Bike Model / Name</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="e.g., Yamaha FZ" required>
        </div>

        <div class="mb-3">
            <label for="type" class="form-label">Type</label>
            <select class="form-control" id="type" name="type">
                <option value="Electric">Electric</option>
                <option value="Regular">Regular</option>
            </select>
        </div>

        <div class="mb-3">
            <label for="price" class="form-label">Price Per Hour (LKR)</label>
            <input type="number" step="0.01" class="form-control" id="price" name="price" placeholder="e.g., 150.00" required>
        </div>

        <button type="submit" class="btn btn-primary">Add Bike</button>
    </form>
</div>

</body>
</html>

