<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Distributor Dashboard</title>
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
     <!-- Custom CSS -->
        <link rel="stylesheet" href="/medi-sales/URLToReachStaticFolder/css/distributorDashboard.css">

</head>
<body>
    <!-- Header -->
    <header class="header">
        <button class="toggle-btn" onclick="toggleSidebar()">☰</button>
        <h2>Distributor Dashboard</h2>
        <ul class="navbar">
            <li><a href="index">Home</a></li>
            <li><a href="profile">Profile</a></li>
            <li><a href="index">Logout</a></li>
        </ul>
    </header>

    <!-- Sidebar -->
    <nav class="sidebar" id="sidebar">
        <h3>Dashboard</h3>
        <ul>
            <li><a href="addvendors">Add Vendors</a></li>
            <li><a href="getAllVendors">Get All Vendors</a></li>
            <li><a href="createstock">Add Product</a></li>
            <li><a href="viewstockbyemail">View Products</a></li>
            <li><a href="retailersalesbill">Retailers Sales Bills</a></li>
            <li><a href="addretailers">Add Retailers</a></li>
            <li><a href="getAllRetailers">Get All Retailers</a></li>
        </ul>
    </nav>

    <!-- Main Content -->
    <div class="content" id="content">
        <h1>Welcome to the Distributor Dashboard</h1>
        <p>Manage your vendors, products, and invoices easily from this dashboard.</p>
    </div>

    <!-- Footer -->
    <footer>
        <p>&copy; 2025 Medi-Sales. All rights reserved.</p>
    </footer>

     <script src="/medi-sales/URLToReachStaticFolder/js/distributorDashboard.js"></script>
</body>
</html>