<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="https://media.istockphoto.com/id/1312665318/vector/medical-logo-design-vector.jpg?s=612x612&w=0&k=20&c=dp5fFItTDGnZy8j1gB0GVjqVyJPG_Xznp_JTRZFXCXs=" type="icon">
    <title>SignIn Page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="/medi-sales/URLToReachStaticFolder/css/signin.css">
</head>
<body class="flex items-center justify-center min-h-screen bg-purple-100">
    <div class="bg-purple-500 text-white rounded-lg shadow-lg flex max-w-4xl mx-auto">
        <div class="bg-purple-700 text-white p-8 rounded-l-lg flex flex-col justify-center items-center w-1/2">
            <h2 class="text-3xl font-bold mb-4">Hello, Welcome!</h2>
            <p class="mb-4">Don't have an account?</p>
           <a href = "signup"> <button class="bg-white text-purple-700 px-4 py-2 rounded-full font-semibold">Register</button></a>
        </div>
        <div class="bg-white p-8 w-1/2 rounded-r-lg">
            <h2 class="text-2xl font-bold mb-4 text-purple-700">Login</h2>
            <form action="signin" method="post">
                <span style="color: red;">${error}</span>
                <div class="mb-4">
                    <label class="block text-gray-700">Email</label>
                    <div class="flex items-center border rounded-lg px-3 py-2">
                        <input type="email" id="email" name="email" value="${found}" class="w-full outline-none text-black" placeholder="Enter your email" required>
                        <i class="fas fa-envelope text-gray-400"></i>
                    </div>
                </div>
                <div class="mb-4">
                    <label class="block text-gray-700">Business Type</label>
                    <div class="flex items-center border rounded-lg px-3 py-2">
                        <select id="businesstype" name="businesstype" class="w-full outline-none text-black" required>
                            <option value="">Select</option>
                            <option value="distributor">Distributor</option>
                            <option value="retailer">Retailer</option>
                        </select>
                        <i class="fas fa-briefcase text-gray-400"></i>
                    </div>
                </div>
                <div class="mb-4">
                    <label class="block text-gray-700">Password</label>
                    <div class="flex items-center border rounded-lg px-3 py-2">
                        <input type="password" id="password" name="password" class="w-full outline-none text-black" required>
                        <i class="fas fa-lock text-gray-400"></i>
                    </div>
                </div>
                <div class="flex justify-between items-center mb-4">
                    <div class="flex items-center">
                        <input type="checkbox" id="toggle-password" class="mr-2"> Show
                    </div>
                    <a href="forgotpassword" class="text-purple-700">Forgot Password?</a>
                </div>
                <button type="submit" class="bg-purple-700 text-white w-full py-2 rounded-lg font-semibold">Sign In</button>
            </form>

            <div class="links text-center mt-4">
                <p>Don't have an account? <a href="signup" class="text-purple-700">Sign Up</a></p>
            </div>
            <div class="text-center mt-4">
                <a href="index" class="bg-purple-700 text-white px-4 py-2 rounded-full font-semibold">Home</a>
            </div>
        </div>
    </div>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="/medi-sales/URLToReachStaticFolder/js/signin.js"></script>
</body>
</html>