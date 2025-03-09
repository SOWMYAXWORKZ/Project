<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thank You</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .body {
            background-image: url('https://wallpapers.com/images/hd/white-color-background-ghw6e685ri75chj4.jpg');
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .content {
            text-align: center;
            color:  #403b3b;
            font-family: 'Arial', sans-serif;
        }
        .content h1 {
            font-size: 3em;
            animation: fadeIn 2s ease-in-out, bounce 1s infinite;
        }
        .content p {
            font-size: 1.5em;
            animation: fadeIn 2s ease-in-out 0.5s forwards;
        }
        .content .button-group {
            margin-top: 20px;
            display: flex;
            justify-content: center;
            gap: 10px;
        }
        .content .button-group .btn {
            min-width: 100px;
        }
        @keyframes fadeIn {
            0% {
                opacity: 0;
            }
            100% {
                opacity: 1;
            }
        }
        @keyframes bounce {
            0%, 100% {
                transform: translateY(0);
            }
            50% {
                transform: translateY(-20px);
            }
        }
    </style>
</head>
<body class="body">

<div class="content">
    <h1>Thank You, ${contactPerson} </h1>
    <p>${contactPerson} Your registration was completed successfully.</p>
    <div class="button-group">
        <a href="index" class="btn btn-primary">Back To The Home Page</a><br>
        <a href="signin" class="btn btn-secondary">Sign In</a><br>
        <a href="forgotpassword" class="btn btn-link">Forgot Password?</a><br>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
