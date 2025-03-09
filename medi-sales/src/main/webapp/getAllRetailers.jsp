<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="s" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Retailers Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .container {
            width: 90%;
            margin: 20px auto;
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            color: #4B0082;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #8A2BE2;
            color: white;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        footer {
            text-align: center;
            padding: 10px;
            background-color: #8A2BE2;
            color: white;
            margin-top: 20px;
        }
    </style>
</head>
<body>

    <div class="container">
        <h2>Retailers Details</h2>
        <p>${message}</p>
        <table>
            <thead>
                <tr>
                    <th>Retailer Id</th>
                    <th>Retailer Name</th>
                    <th>Retailer Phone</th>
                    <th>Retailer Email</th>
                    <th>GSTIN</th>
                    <th>Retailer Address</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <s:forEach var="retailers" items="${retailers}">
                    <tr>
                        <td>${retailers.id}</td>
                        <td>${retailers.retailerName}</td>
                        <td>${retailers.retailerPhone}</td>
                        <td>${retailers.retailerEmail}</td>
                        <td>${retailers.retailerGstNumber}</td>
                        <td>${retailers.retailerAddress}</td>

                        <td><button><a href="deleteretailer?id=${retailers.id}">Delete</a></button>
                            &nbsp <button><a href="fetchretailer?id=${retailers.id}">Update</a></button>
                        </td>

                    </tr>
                </s:forEach>
            </tbody>
        </table>
    </div>

    <footer>
        &copy; 2025 Medi-Sales - Trusted for Your Health Needs
    </footer>

</body>
</html>
