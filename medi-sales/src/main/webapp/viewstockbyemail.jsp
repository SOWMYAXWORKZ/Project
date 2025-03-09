<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="s" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Stock</title>
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
        <h2>Stock Details</h2>
        <p>${message}</p>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Product Company</th>
                    <th>Product Name</th>
                    <th>Description</th>
                    <th>Pack</th>
                    <th>Qty</th>
                    <th>HSN</th>
                    <th>Batch Number</th>
                    <th>Mfg Date</th>
                    <th>Exp Date</th>
                    <th>MRP</th> <!--Original price-->
                    <th>Rate</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <s:forEach var="stock" items="${stocks}">
                    <tr>
                        <td>${stock.id}</td>
                        <td>${stock.productCompany}</td>
                        <td>${stock.productName}</td>
                        <td>${stock.description}</td>
                        <td>${stock.pack}</td>
                        <td>${stock.quantity}</td>
                        <td>${stock.hsn}</td>
                        <td>${stock.batchNumber}</td>
                        <td>${stock.mfgDate}</td>
                        <td>${stock.expDate}</td>
                        <td>${stock.mrp}</td>
                        <td>${stock.rate}</td>
                        <td><button type= "delete"><a href = "delete?id={stock.getId()}">Delete</a></button>
                            &nbsp <button type = "update"><a href = "fetch?id={stock.getId()}">Update</a></button>
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
