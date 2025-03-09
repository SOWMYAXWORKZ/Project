<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Retailer Details</title>

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">

    <style>
        body {
            font-family: 'Roboto', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .header {
            background: #4B0082;
            color: white;
            padding: 15px;
            text-align: center;
            font-size: 24px;
            font-weight: bold;
            position: relative;
        }

        .dashboard-btn {
            position: absolute;
            left: 20px;
            top: 50%;
            transform: translateY(-50%);
            background: white;
            color: #4B0082;
            padding: 10px 15px;
            border-radius: 5px;
            text-decoration: none;
            font-weight: bold;
        }

        .dashboard-btn:hover {
            background: #6A0DAD;
            color: white;
        }

        .container {
            width: 40%;
            margin: 50px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            color: #4B0082;
        }

        label {
            font-weight: bold;
            display: block;
            margin-top: 10px;
        }

        input, select {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .btn {
            width: 100%;
            background: #4B0082;
            color: white;
            padding: 10px;
            border: none;
            cursor: pointer;
            margin-top: 15px;
            font-size: 16px;
            border-radius: 5px;
        }

        .btn:hover {
            background: #6A0DAD;
        }

        .footer {
            text-align: center;
            padding: 15px;
            background-color: #4B0082;
            color: white;
            margin-top: 20px;
        }
    </style>
</head>
<body>

    <div class="header">
        <a href="distributorDashboard" class="dashboard-btn">Dashboard</a>
        Edit Stock Details
    </div>

    <div class="container">
        <h2>Update Retailer Details</h2>
        <form action="updateRetailer" method="post">
            <input type="hidden" name="id" value="${retailer.id}">
            <label>Retailer Name:</label>
            <input type="text" name="retailerName" value="${retailer.retailerName}"><br><br>

            <label>Retailer Company Name:</label>
            <input type="text" name="retailerCompanyName" value="${retailer.retailerCompanyName}"><br><br>

            <label>Retailer Phone:</label>
            <input type="text" name="retailerPhone" value="${retailer.retailerPhone}"><br><br>

            <label>Retailer Email:</label>
            <input type="text" name="retailerEmail" value="${retailer.retailerEmail}"><br><br>

            <label>GSTIN:</label>
            <input type="text" name="retailerGstNumber" value="${retailer.retailerGstNumber}"><br><br>

            <label>Retailer Address:</label>
            <input type="text" name="retailerAddress" value="${retailer.retailerAddress}"><br><br>

            <button type="submit">Update</button>
        </form>
    </div>

    <div class="footer">
        &copy; 2025 Medi-Sales - Trusted for Your Health Needs
    </div>

</body>
</html>
