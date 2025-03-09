<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Medi-Sales</title>
    <link rel="icon" href="https://media.istockphoto.com/id/1312665318/vector/medical-logo-design-vector.jpg?s=612x612&w=0&k=20&c=dp5fFItTDGnZy8j1gB0GVjqVyJPG_Xznp_JTRZFXCXs=" type="icon">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">

    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f7f9fc;
            color: #333;
            display: flex;
            flex-direction: column;
            height: 100vh;
            scroll-behavior: smooth;
        }

        /* Header */
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 30px;
            background-color: #8A2BE2;
            color: white;
            position: sticky;
            top: 0;
            z-index: 10;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .header .logo img {
            width: 50px;
            height: 50px;
            border-radius: 50%;
        }

        .header h1 {
            margin-left: -15%;
            font-size: 1.8rem;
            text-align: left;
            left : 2px;
        }

        .navbar {
            list-style: none;
            display: flex;
        }

        .navbar a {
            text-decoration: none;
            color: white;
            font-size: 16px;
            margin-left: 30px;
            padding: 8px;
            transition: background-color 0.3s ease;
        }

        .navbar a:hover {
            background-color: #3498db;
            border-radius: 5px;
        }

        /* Main Content */
        .content {
            padding: 50px;
            display: flex;
            align-items: center;
            flex-direction: column;
            text-align: center;
            background-image: url('https://media.istockphoto.com/id/1370358685/photo/multicolored-pills-scattered-from-white-plastic-medicine-container.jpg?s=612x612&w=0&k=20&c=zknrVfCELovlvvXKrAlWKLnFLfkMQF8nh9k2d97pJkE=');
            background-size: cover;
            background-position: center;
            border-radius: 10px;
            color: black;
            margin: 20px;
            padding: 40px;
        }

        .content h1 {
            font-size: 2.5rem;
            margin-bottom: 15px;
        }

        .content p {
            font-size: 1.2rem;
            margin-bottom: 20px;
        }

        .content ul {
            margin: 10px 0;
            padding-left: 20px;
            list-style-type: disc;
            color: #fff;
        }

        .content .button {
            display: inline-block;
            background-color: #8A2BE2;
            color: white;
            padding: 12px 25px;
            font-size: 16px;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
            transition: background-color 0.3s ease, transform 0.3s ease;
        }

        .content .button:hover {
            background-color: #2980b9;
            transform: translateY(-3px);
        }

        /* Feature Section */
        .features {
            background-color: #FFF8DC;
            padding: 40px 30px;
            text-align: center;
        }

        .features h2 {
            font-size: 28px;
            margin-bottom: 30px;
        }

        .features .feature-card {
            display: inline-block;
            width: 30%;
            margin: 15px;
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease;
        }

        .features .feature-card:hover {
            transform: translateY(-5px);
        }

        .features .feature-card h3 {
            font-size: 20px;
            margin-bottom: 10px;
        }

        .features .feature-card p {
            font-size: 16px;
            color: #555;
        }

        /* Card Group Styles */
        .card-group {
            display: flex;
            gap: 1rem;
            background-color: #F8F8FF;
            padding: 2rem;
            border-radius: 8px;
            justify-content: center;
            flex-wrap: wrap;
        }

        .benefits-title {
            text-align: center;
            font-size: 2rem;
            font-weight: bold;
            margin: 20px 0;
            color: #333;
        }

        .card {
            width: 300px;
            border: 1px solid #ddd;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            background-color: #F8F8FF;
            margin: 10px;
        }

        .card-img-top {
            width: 100%;
            height: 200px;
            object-fit: cover;
            border-top-left-radius: 8px;
            border-top-right-radius: 8px;
        }

        .card-body {
            padding: 1rem;
        }

        .card-title {
            font-size: 1.25rem;
            font-weight: bold;
        }

        .card-text {
            font-size: 1rem;
            color: #555;
        }

        .text-body-secondary {
            color: #6c757d;
        }

        /* Footer */
        footer {
            background-color: #8A2BE2;
            color: white;
            text-align: center;
            padding: 20px;
        }

        .social-media a {
            display: inline-block;
            margin: 0 15px;
            text-decoration: none;
            color: white;
            font-size: 1.5rem;
            transition: color 0.3s ease, transform 0.3s ease;
        }

        .social-media a:hover {
            transform: scale(1.2);
        }

        footer p {
            font-size: 14px;
            margin-top: 15px;
        }

        /* Responsive */
        @media (max-width: 768px) {
            .header {
                flex-direction: column;
                text-align: center;
            }

            .navbar {
                margin-top: 15px;
                flex-direction: column;
            }

            .navbar a {
                margin-left: 0;
                margin-bottom: 10px;
            }

            .content {
                flex-direction: column;
                text-align: center;
            }

            .features .feature-card {
                width: 80%;
                margin: 10px auto;
            }

            .card-group {
                flex-direction: column;
            }
        }
    </style>
</head>

<body>
    <!-- Header Section -->
    <header class="header">
        <a href="#" class="logo">
            <img src="https://media.istockphoto.com/id/1312665318/vector/medical-logo-design-vector.jpg?s=612x612&w=0&k=20&c=dp5fFItTDGnZy8j1gB0GVjqVyJPG_Xznp_JTRZFXCXs=" alt="Medical Logo">
        </a>
        <h1>Medi-Sales</h1>

        <!-- Navigation Bar -->
        <nav class="navbar">
            <a href="index">Home</a>
            <a href="#about">About Us</a>
            <a href="#medicines">Medicines</a>
            <a href="#contact">Contact Us</a>
            <a href="signin">Login</a>
            <a href="signup">Register</a>
        </nav>
    </header>

    <!-- Content Section -->
    <div class="content" id="home">
        <div class="text">
            <h1>Medi-Sales</h1>
            <p>Your one-stop platform for medical supplies. We offer high-quality products delivered directly to your doorstep. Stay healthy, stay safe!</p>

                Access Your Business Anytime,<br>
                Anywhere 100% Safe Automatic Data Backup<br>
               	Access Real-Time Report<br>

            <a href="#" class="button">Order Now</a>
        </div>
    </div>

    <hr>

    <!-- Feature Section -->
    <section class="features">
        <h2>Our Key Features</h2>
        <div class="feature-card">
            <h3>Quality Products</h3>
            <p>We ensure all our medical supplies are of the highest quality, sourced from trusted suppliers.</p>
        </div>
        <div class="feature-card">
            <h3>Fast Delivery</h3>
            <p>Get your products delivered at your doorstep quickly and safely.</p>
        </div>
        <div class="feature-card">
            <h3>24/7 Support</h3>
            <p>Our customer support team is available around the clock to assist you with any queries.</p>
        </div>
    </section>

    <hr>

    <!-- Card Group -->
    <h1 class="benefits-title">Top Benefits of Medi Sales</h1>
    <div class="card-group">
        <div class="card">
            <img src="https://media.istockphoto.com/id/1248228302/vector/senior-man-in-consultation-with-doctor-nurce-health-monitoring-in-old-age-verification-and.jpg?s=612x612&w=0&k=20&c=Vw1hnmzBdkeoMaqADqCcGlkCFtarvkAFGNHnh77YgUI=" class="card-img-top" alt="Improved Patient Outcomes">
            <div class="card-body">
                <h5 class="card-title">Improved Patient Outcomes</h5>
                <p class="card-text">Medi sales offer innovative solutions that enhance the effectiveness of patient treatments, ensuring better outcomes and quicker recovery times.</p>
                <p class="card-text"><small class="text-body-secondary">Last updated 3 mins ago</small></p>
            </div>
        </div>
        <div class="card">
            <img src="https://resourcecenter.infinit-o.com/wp-content/uploads/2023/12/stethoscope-min.jpg" class="card-img-top" alt="Streamlined Healthcare Processes">
            <div class="card-body">
                <h5 class="card-title">Streamlined Healthcare Processes</h5>
                <p class="card-text">Medi sales contribute to more efficient management of medical products, helping healthcare providers improve service delivery and reduce costs.</p>
                <p class="card-text"><small class="text-body-secondary">Last updated 3 mins ago</small></p>
            </div>
        </div>
        <div class="card">
            <img src="https://codemantra.com/wp-content/uploads/2023/05/digital-tools-2-1204x650.png" class="card-img-top" alt="Increased Access to Healthcare">
            <div class="card-body">
                <h5 class="card-title">Increased Access to Healthcare</h5>
                <p class="card-text">Medi sales ensure that medical products are easily accessible to healthcare providers, thus enhancing the reach of healthcare services to patients in need.</p>
                <p class="card-text"><small class="text-body-secondary">Last updated 3 mins ago</small></p>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer>
        <div class="social-media">
            <a href="#" target="_blank" class="text-white mx-2">
                <i class="bi bi-instagram"></i>
            </a>
            <a href="#" target="_blank" class="text-white mx-2">
                <i class="bi bi-whatsapp"></i>
            </a>
            <a href="#" target="_blank" class="text-white mx-2">
                <i class="bi bi-facebook"></i>
            </a>
        </div>
        <p>&copy; 2025 Medi-Sales - Trusted for Your Health Needs</p>
    </footer>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>