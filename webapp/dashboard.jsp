<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Session check
    String username = (String) session.getAttribute("username");
    if (username == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard - Library Management System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background: #f5f5f5;
        }
        .header {
            background: #667eea;
            color: white;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 20px;
        }
        .content {
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        .logout {
            float: right;
            background: #f44336;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>📚 Library Management System</h1>
        <p>Welcome, <strong><%= username %></strong>!</p>
        <a href="logout.jsp" class="logout">Logout</a>
    </div>
    <div class="content">
        <h2>✅ Login Successful!</h2>
        <p>You are now authenticated and can access the library system.</p>
        <p>Session ID: <%= session.getId() %></p>
        <p>Login Time: <%= new java.util.Date(session.getCreationTime()) %></p>
    </div>
</body>
</html>
