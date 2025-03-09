<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Add Retailer Page</title>
        <!--Icon-->
        <link rel="icon" href="https://media.istockphoto.com/id/1312665318/vector/medical-logo-design-vector.jpg?s=612x612&w=0&k=20&c=dp5fFItTDGnZy8j1gB0GVjqVyJPG_Xznp_JTRZFXCXs=" type="icon">
        <!-- Google fonts  -->
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
        <!-- Bootstrap Icons -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
        <!-- Custom CSS -->
        <link rel="stylesheet" href="/medi-sales/URLToReachStaticFolder/css/addretailers.css">
        <!--Script-->
        <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
        <script src="https://cdn.tailwindcss.com"></script>

</head>
<body>
    <form action = "addretailers" method = "post" class="form-container" >
        <h2>Add Retailer</h2>
        <div class="form-group">
            <label for="retailerName">Retailer Name:</label>
            <input type="text" id="retailerName" name="retailerName">

        </div>

        <div class="form-group">
            <label for="retailerPhone">Phone Number:</label>
            <input type="text" id="retailerPhone" name="retailerPhone">

        </div>
        <div class="form-group">
            <label for="retailerEmail">Email:</label>
            <input type="text" id="retailerEmail" name="retailerEmail">

        </div>
        <div class="form-group">
            <label for="retailerGstNumber">GSTIN:</label>
            <input type="text" id="retailerGstNumber" name="retailerGstNumber">
        </div>


        <div class="form-group">
            <label for="retailerAddress">Address:</label>
            <input type="text" id="retailerAddress" name="retailerAddress">
        </div>
        <button type="submit" class="submit-btn">Add Retailer</button>
    </form>

     <script src="/medi-sales/URLToReachStaticFolder/js/addretailers.js"></script>
</body>
</html>
