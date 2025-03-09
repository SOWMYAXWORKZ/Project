<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

    <title>Update Password</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            background-color: #f8f9fa;
            margin: 0;
        }
        .header, .footer {
            background-color: #6a0dad; /* Purple */
            color: white;
            text-align: center;
            padding: 15px;
            width: 100%;
            font-size: 18px;
            font-weight: bold;
        }
        .reset-container {
            background: white;
            border-radius: 8px;
            padding: 25px;
            width: 100%;
            max-width: 380px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            margin-top: 20px;
            margin-bottom: 20px;
        }
        .reset-container h2 {
            text-align: center;
            margin-bottom: 20px;
            font-size: 22px;
            color: #333;
        }
        .btn-custom {
            background-color: #6a0dad; /* Purple */
            color: white;
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
        }
        .btn-custom:hover {
            background-color: #500a8d;
        }
        .links {
            text-align: center;
            margin-top: 10px;
        }
        .links a {
            color: #6a0dad;
            text-decoration: none;
        }
        .links a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

    <div class="header">Update Password</div>

    <div class="reset-container">
        <h2>Reset Your Password</h2>

        <form action="resetpassword" method="post">
            <div class="mb-3">
               
                <input type="hidden" id="email" name="email" value="${FoundEmail}">
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">New Password</label>
                <input type="password" id="password" name="password" class="form-control" placeholder="Enter new password" required>
            </div>
            <div class="mb-3">
                <label for="confirmPassword" class="form-label">Conform New Password</label>
                <input type="password" id="confirmPassword" name="confirmPassword" class="form-control" placeholder="Confirm new password" required>
            </div>
            <button type="submit" class="btn btn-custom">Update</button>
        </form>

        <div class="links">
            <p><a href="signIn">Back to Sign In</a></p>
        </div>
    </div>

    <div class="footer">© 2025 Medi-Sales</div>

</body>
</html>
