<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <title>ResetOTP</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
     <!-- Bootstrap Icons -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
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
        #emailError {
            color: red;
            font-size: 14px;
        }
    </style>
</head>
<body>

    <div class="header">Reset Password</div>

    <div class="reset-container">
        <h2>ResetPassword</h2>

        <form>
            <div class="mb-3">
                <label for="inputEmail" class="form-label">Email Address</label>
                <input type="email" id="inputEmail" name="email" value="${email}" class="form-control" placeholder="Enter your email" autocomplete="off" required>
            </div>
            <span id="emailError"></span>
            <button type="button" onclick="sendOTP()" class="btn btn-custom">Send OTP</button>
        </form>

        <form action="resetpassword" method="post">
            <div class="mb-3">
                <label for="otp" class="form-label">Enter the OTP</label>
                <input type="text" id="otp" name="otp" class="form-control" placeholder="Enter your OTP">
            </div>
            <button type="submit" class="btn btn-custom">Submit</button>
        </form>

        <div class="links">
            <p><a href="signin">Back to Sign In</a></p>
        </div>
    </div>

    <div class="footer">© 2025 Medi-Sales</div>

    <script>
        const sendOTP = async () => {
            const email = document.getElementById("inputEmail").value;
            console.log("User email:", email);

            try {
                const response = await axios.get("http://localhost:2992/medi-sales/api/sentOtp/" + email);
                console.log("Response:", response.data);

                if (response.data === "Otp Sent Successfully") {
                    document.getElementById("emailError").innerText = "OTP Sent Successfully! Please check your email.";
                    document.getElementById("emailError").style.color = "green";
                } else {
                    document.getElementById("emailError").innerText = response.data;
                    document.getElementById("emailError").style.color = "red";
                }
            } catch (error) {
                document.getElementById("emailError").innerText = "Error: " + error.message;
                console.error("Error:", error);
            }
        };

         // Disable send OTP button if email is invalid
         document.getElementById("inputEmail").addEventListener("input", function() {
             const email = this.value;
             const isValid = /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email); // Basic email regex validation
             document.querySelector(".btn-custom").disabled = !isValid;
         });

            // Disable OTP submit button if OTP field is empty
            document.getElementById("otp").addEventListener("input", function() {
                const otp = this.value;
                const otpButton = document.querySelector("form[action='resetpassword'] button[type='submit']");
                if (otpButton) {
                    otpButton.disabled = otp.trim() === "";
                } else {
                    console.error("OTP submit button not found!");
                }
            });
    </script>
</body>
</html>
