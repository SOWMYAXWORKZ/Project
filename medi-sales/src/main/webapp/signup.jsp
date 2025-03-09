<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>SignUp Page</title>
    <!--Icon-->
    <link rel="icon" href="https://media.istockphoto.com/id/1312665318/vector/medical-logo-design-vector.jpg?s=612x612&w=0&k=20&c=dp5fFItTDGnZy8j1gB0GVjqVyJPG_Xznp_JTRZFXCXs=" type="icon">
    <!-- Google fonts  -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="/medi-sales/URLToReachStaticFolder/css/signup.css">
    <!--Script-->
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="flex items-center justify-center min-h-screen bg-gray-100">
    <div class="bg-white rounded-lg shadow-lg flex flex-col md:flex-row w-full max-w-3xl h-auto">
        <div class="bg-purple-500 text-white p-6 rounded-t-lg md:rounded-l-lg md:rounded-t-none flex flex-col justify-center items-center md:w-1/2">
            <h2 class="text-2xl font-bold mb-4">Hello, Welcome!</h2>
            <p class="mb-4">Already have an account?</p>
           <a href = "signin"><button class="bg-white text-purple-500 px-4 py-2 rounded-full font-semibold">Login</button></a>
        </div>
        <div class="p-6 md:w-1/2">
            <h2 class="text-xl font-bold mb-4">Register</h2>
            <form action="signup" method="post" onclick="form()">
                <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
                    <div class="mb-3">
                        <label class="block text-gray-700">Company Name</label>
                        <div class="flex items-center border rounded-lg px-3 py-2">
                            <input type="text" id="companyName" name="companyName" class="w-full outline-none" placeholder="Enter your company name" onblur="forcompanyName()">
                        </div>
                        <span id="validcompanyname" class="text-red-500"></span>
                    </div>
                    <div class="mb-3">
                        <label class="block text-gray-700">Contact Person</label>
                        <div class="flex items-center border rounded-lg px-3 py-2">
                            <input type="text" id="contactPerson" name="contactPerson" class="w-full outline-none" placeholder="Enter the contact person's name" onblur="forcontactPerson()">
                        </div>
                        <span id="validcontactperson" class="text-red-500"></span>
                    </div>
                    <div class="mb-3">
                        <label class="block text-gray-700">Registered Email *</label>
                        <div class="flex items-center border rounded-lg px-3 py-2">
                            <input type="email" id="email" name="email" class="w-full outline-none" placeholder="Enter the email" onblur="checkEmail()" required>
                        </div>
                        <span id="validemail" class="text-red-500"></span>
                    </div>
                    <div class="mb-3">
                        <label class="block text-gray-700">Gender</label>
                        <div class="flex items-center border rounded-lg px-3 py-2">
                            <select id="gender" name="gender" class="w-full outline-none" required>
                                <option value="">Select Gender</option>
                                <option value="MALE">Male</option>
                                <option value="FEMALE">Female</option>
                            </select>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label class="block text-gray-700">Registered Mobile *</label>
                        <div class="flex items-center border rounded-lg px-3 py-2">
                            <input type="text" id="mobile" name="mobile" class="w-full outline-none" placeholder="Enter registered mobile number" onblur="checkPhone()" required>
                        </div>
                        <span id="validmobile" class="text-red-500"></span>
                    </div>
                    <div class="mb-3">
                        <label class="block text-gray-700">Business Type *</label>
                        <div class="flex items-center border rounded-lg px-3 py-2">
                            <select id="businesstype" name="businesstype" class="w-full outline-none" required>
                                <option value="">Select Business Type</option>
                                <option value="distributor">Distributor</option>
                                <option value="retailer">Retailer</option>
                            </select>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label class="block text-gray-700">Address *</label>
                        <div class="flex items-center border rounded-lg px-3 py-2">
                            <input type="text" id="address" name="address" class="w-full outline-none" placeholder="Enter address" onblur="foraddress()">
                        </div>
                        <span id="validaddress" class="text-red-500"></span>
                    </div>
                    <div class="mb-3">
                        <label class="block text-gray-700">Pincode *</label>
                        <div class="flex items-center border rounded-lg px-3 py-2">
                            <input type="text" id="pincode" name="pincode" class="w-full outline-none" placeholder="Enter pincode" onblur="forpincode()">
                        </div>
                        <span id="validpincode" class="text-red-500"></span>
                    </div>
                    <div class="mb-3">
                        <label class="block text-gray-700">Password *</label>
                        <div class="flex items-center border rounded-lg px-3 py-2">
                            <input type="password" id="password" name="password" class="w-full outline-none" placeholder="Enter password" onblur="forpassword()">
                        </div>
                        <span id="validpassword" class="text-red-500"></span>
                    </div>
                    <div class="mb-3">
                        <label class="block text-gray-700">Confirm Password *</label>
                        <div class="flex items-center border rounded-lg px-3 py-2">
                            <input type="password" id="confirmPassword" name="confirmPassword" class="w-full outline-none" placeholder="Confirm your password" onblur="forconformpwd()">
                        </div>
                        <span id="validcpwd" class="text-red-500"></span>
                    </div>
                </div>
                <div class="mb-3">
                     <button type="submit" id="button" class="btn btn-primary">SignUp</button>

                </div>
            </form>
            <div class="text-center mb-3">
                <p>Already have an account? <a href="signin" class="text-purple-500">Sign In</a></p>
            </div>
            <div class="text-center">
                <a href="index" class="bg-purple-500 text-white w-full py-2 rounded-lg text-center font-semibold inline-block">Home</a>
            </div>
        </div>
    </div>

    <script src="/medi-sales/URLToReachStaticFolder/js/signup.js"></script>
</body>
</html>