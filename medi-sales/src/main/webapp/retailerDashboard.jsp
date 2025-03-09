<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page isELIgnored="false"%><!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Retailer Page</title>
    <style>
        /* Header */
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 1rem;
            background-color: #8A2BE2;
            color: white;
            position: sticky;
            top: 0;
            z-index: 10;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .header .logo img {
            width: 30px;
            height: 30px;
            border-radius: 80%;
        }

        .header h2 {
            width: 20%;
        }

        .navbar {
            list-style: none;
            display: flex;
        }

        .navbar a {
            text-decoration: none;
            color: white;
            font-size: 20px;
            margin-right: 30px;
            padding: 5px;
            transition: background-color 0.3s ease;
        }

        .navbar a:hover {
            background-color: #3498db;
            border-radius: 5px;
        }

        /* Footer */
        footer {
            background-color: #8A2BE2;
            color: black;
            text-align: center;
            padding: 1rem;
            margin-top: 2rem;
        }

        footer p {
            margin: 0;
        }

        /* Content */
        .container {
            padding: 2rem;
        }
    </style>
</head>
<body>

  <!-- Header Section -->
  <header class="header">
      <a href="index" class="logo">
          <img src="https://media.istockphoto.com/id/1312665318/vector/medical-logo-design-vector.jpg?s=612x612&w=0&k=20&c=dp5fFItTDGnZy8j1gB0GVjqVyJPG_Xznp_JTRZFXCXs=" alt="Medical Logo">
      </a>
      <h2>Medi-Sales</h2>

      <!-- Navigation Bar -->
      <nav class="navbar">
          <a href="index">Home</a>
      </nav>
  </header>

  <!-- Retailer Page Content -->
  <div class="container">
      <h1>Retailer Dashboard</h1>
      <p>Welcome to your retailer dashboard. Manage your products, orders, and sales here.</p>
      <!-- Add your retailer-related content here -->
  </div>

  <!-- Footer -->
  <footer>
      &copy; 2025 Medi-Sales - Trusted for Your Health Needs
  </footer>

</body>
</html>
