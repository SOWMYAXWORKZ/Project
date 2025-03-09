<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Stock Page</title>
    <!-- Google fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <!--Script-->
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 30%;
            margin: 50px auto;
            background: white;
            padding: 50px;
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
        }

        .btn:hover {
            background: #6A0DAD;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Create Stock</h2>
        <form action="createstock" method="post" onsubmit="return handleSubmit()">
            <label>Product Company</label>
            <input type="text" name="productCompany" required>

            <label>Product Name</label>
            <input type="text" name="productName" required>

            <label>Description</label>
            <input type="text" name="description" required>

            <label>MRP</label>
            <input type="number" name="mrp" required>

            <label>Pack (Sheets)</label>
            <input type="number" name="pack" required>

            <label>Manufacturing Date</label>
            <input type="date" name="mfgDate" required>

            <label>Expiry Date</label>
            <input type="date" name="expDate" required>

            <label>Quantity</label>
            <input type="number" name="quantity" required>

            <label>Rate</label>
            <input type="number" name="rate" required>


            <button type="submit" class="btn" id="submitBtn">Submit</button>
        </form>
    </div>

    <script>
        // Handle form submission, disable button, and re-enable it after submission
        function handleSubmit() {
            const submitBtn = document.getElementById("submitBtn");
            submitBtn.disabled = true; // Disable the button when form is submitted

            // Simulate form submission (you can perform additional checks or AJAX requests here)
            setTimeout(() => {
                // After submission is complete, re-enable the button
                submitBtn.disabled = false;
            }, 3000);  // Adjust timeout for your use case (simulate waiting for server response)

            return true; // Allow form submission
        }
    </script>
</body>
</html>
