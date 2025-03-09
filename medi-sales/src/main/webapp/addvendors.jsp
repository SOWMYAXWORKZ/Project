<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Add Vendor Page</title>
        <!--Icon-->
        <link rel="icon" href="https://media.istockphoto.com/id/1312665318/vector/medical-logo-design-vector.jpg?s=612x612&w=0&k=20&c=dp5fFItTDGnZy8j1gB0GVjqVyJPG_Xznp_JTRZFXCXs=" type="icon">
        <!-- Google fonts  -->
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
        <!-- Bootstrap Icons -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
        <!-- Custom CSS -->
        <link rel="stylesheet" href="/medi-sales/URLToReachStaticFolder/css/addvendors.css">
        <!--Script-->
        <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
        <script src="https://cdn.tailwindcss.com"></script>

</head>
<body>
    <form action = "addvendors" method = "post" class="form-container" >
        <h2>Add Vendor</h2>
        <div class="form-group">
        <input type="hidden" name="id" th:value="${id}" />

            <label for="vendorName">Vendor Name:</label>
            <input type="text" id="vendorName" name="vendorName">

        </div>

        <div class="form-group">
            <label for="vendorPhone">Phone Number:</label>
            <input type="text" id="vendorPhone" name="vendorPhone">

        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="text" id="vendorEmail" name="vendorEmail">

        </div>
        <div class="form-group">
            <label for="vendorGstNumber">GSTIN:</label>
            <input type="text" id="vendorGstNumber" name="vendorGstNumber">
        </div>


        <div class="form-group">
            <label for="vendorAddress">Address:</label>
            <input type="text" id="vendorAddress" name="vendorAddress">
        </div>
        <button type="submit" class="submit-btn">Add Vendor</button>
    </form>

     <script src="/medi-sales/URLToReachStaticFolder/js/addvendors.js"></script>
</body>
</html>
