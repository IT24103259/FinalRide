
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%@ page session="true" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Settings</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            background-color: #f5f5f5;
        }

        .main-content {
            flex: 1;
            padding: 20px;
            margin-left: 250px;
        }

        .dashboard-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
        }

        .dashboard-title {
            font-size: 24px;
            font-weight: bold;
            color: #2c3e50;
        }

        .user-profile {
            display: flex;
            align-items: center;
        }

        .user-avatar {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            background-color: #3498db;
            margin-right: 10px;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            font-weight: bold;
        }

        .settings-container {
            background-color: white;
            border-radius: 8px;
            padding: 30px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            margin: 0 auto;
        }

        .settings-title {
            font-size: 22px;
            font-weight: bold;
            color: #2c3e50;
            margin-bottom: 30px;
            padding-bottom: 15px;
            border-bottom: 1px solid #eee;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-label {
            display: block;
            margin-bottom: 8px;
            font-weight: 600;
            color: #2c3e50;
        }

        .form-control {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 16px;
        }

        .form-control:focus {
            border-color: #3498db;
            outline: none;
        }

        .btn-submit {
            background-color: #3498db;
            color: white;
            border: none;
            padding: 12px 20px;
            border-radius: 4px;
            cursor: pointer;
            font-weight: bold;
            font-size: 16px;
            transition: background-color 0.3s;
            width: 100%;
        }

        .btn-submit:hover {
            background-color: #2980b9;
        }

        .footer {
            text-align: center;
            color: #7f8c8d;
            font-size: 14px;
            margin-top: 40px;
        }

        .tab-container {
            display: flex;
            margin-bottom: 20px;
            border-bottom: 1px solid #eee;
        }

        .tab {
            padding: 10px 20px;
            cursor: pointer;
            border-bottom: 3px solid transparent;
        }

        .tab.active {
            border-bottom-color: #3498db;
            font-weight: bold;
            color: #3498db;
        }
    </style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
<jsp:include page="sidebar.jsp">
    <jsp:param name="activePage" value="settings"/>
</jsp:include>

<div class="main-content">
    <div class="dashboard-header">
        <div class="dashboard-title">Settings</div>
        <div class="user-profile">
            <div class="user-avatar">JD</div>
            <span>John Doe</span>
        </div>
    </div>

    <div class="settings-container">
        <div class="settings-title">Account Settings</div>

        <div class="tab-container">
            <div class="tab active">Account</div>
            <div class="tab">Security</div>
            <div class="tab">Notifications</div>
        </div>

        <form action="UserServlet" method="post">
            <input type="hidden" name="action" value="update" />
            <input type="hidden" name="username" value="<%= user.getUsername() %>" />

            <div class="form-group">
                <label for="username" class="form-label">Username</label>
                <input type="text" id="username" class="form-control" value="<%= user.getUsername() %>" readonly>
            </div>

            <div class="form-group">
                <label for="email" class="form-label">Email</label>
                <input type="email" id="email" name="email" class="form-control" value="<%= user.getEmail() %>">
            </div>

            <div class="form-group">
                <label for="newPassword" class="form-label">New Password</label>
                <input type="password" id="newPassword" name="password" class="form-control">
            </div>

            <button type="submit" class="btn-submit">Update Settings</button>
        </form>

        <form action="UserServlet" method="post" onsubmit="return confirm('Are you sure you want to delete your account?');">
            <input type="hidden" name="action" value="delete" />
            <input type="hidden" name="username" value="<%= user.getUsername() %>" />
            <button type="submit" class="btn-submit" style="background-color: crimson; margin-top: 10px;">Delete Account</button>
        </form>

    </div>

    <div class="footer">
        <p><strong>Foster Bike Rental System.</strong> All rights reserved.</p>
    </div>
</div>

<script>
    // Simple tab switching functionality
    document.querySelectorAll('.tab').forEach(tab => {
        tab.addEventListener('click', () => {
            document.querySelectorAll('.tab').forEach(t => t.classList.remove('active'));
            tab.classList.add('active');
        });
    });
</script>
</body>
</html>
