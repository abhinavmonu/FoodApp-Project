<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FoodApp | Sign Up</title>
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="SignUpPage.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
   
</head>
<body>
    <div class="signup-container">
        <!-- Left Side - Image -->
        <div class="signup-image">
            <h2>Join Our Food Community</h2>
            <p>Create an account to save your favorite restaurants, track orders, and get personalized recommendations.</p>
            <p>Why wait? <a style="color: #c19a6b; font-weight: 600;">Join now!</a></p>
        </div>
        
        <!-- Right Side - Form -->
        <div class="signup-form">
            <div class="signup-header">
                <h1>Create Account</h1>
                <p>Fill in your details to get started</p>
            </div>
            
            <form action="SignUpServlet" method="post">
                <div class="name-fields">
                    <div class="form-group">
                        <label for="first-name">Full Name</label>
                        <div class="input-with-icon">
                            <i class="fas fa-user"></i>
                            <input type="text" id="first-name" class="form-control" placeholder="Full name" name="name" required>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="last-name">User Name</label>
                        <div class="input-with-icon">
                            <i class="fas fa-user"></i>
                            <input type="text" id="last-name" class="form-control" placeholder="User name" name="userName" required>
                        </div>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="email">Email Address</label>
                    <div class="input-with-icon">
                        <i class="fas fa-envelope"></i>
                        <input type="email" id="email" class="form-control" placeholder="Enter your email" name="email" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="phone">Phone Number</label>
                    <div class="input-with-icon">
                        <i class="fas fa-phone"></i>
                        <input type="tel" id="phone" class="form-control" placeholder="Enter your phone number" name="phoneNumber">
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="password">Password</label>
                    <div class="input-with-icon">
                        <i class="fas fa-lock"></i>
                        <input type="password" id="password" class="form-control" placeholder="Create a password" name="password" required>
                    </div>
                </div>
                
                <div class="form-group">         
                    <label for="confirm-password">Confirm Password</label>
                    <span <%if(request.getParameter("error") == null) { %>style="display: none" <% } %> style="color: #d32f2f; background-color: #fde0e0; padding: 0px 12px 4px; border-radius: 4px; border: 1px solid #ef9a9a; margin-top: 5px" class="warning-message"><%=request.getParameter("error")%></span>
                    <div class="input-with-icon">
                        <i class="fas fa-lock"></i>
                        <input type="password" id="confirm-password" class="form-control" placeholder="Confirm your password" name="confirmPassword" required>
                    </div>
                </div>
                
                <div class="terms">
                    <input type="checkbox" id="terms" required>
                    <label for="terms">I agree to the <a href="#">Terms of Service</a> and <a href="#">Privacy Policy</a></label>
                </div>
                
                <button type="submit" class="btn">Create Account</button>
                
                <div class="social-signup">
                    <p>or sign up with</p>
                    <div class="social-icons">
                        <a href="#" class="social-icon facebook">
                            <i class="fab fa-facebook-f"></i>
                        </a>
                        <a href="#" class="social-icon google">
                            <i class="fab fa-google"></i>
                        </a>
                        <a href="#" class="social-icon apple">
                            <i class="fab fa-apple"></i>
                        </a>
                    </div>
                </div>
                
                <div class="login-link">
                    <p>Already have an account? <a href="LoginPage.jsp">Log in</a></p>
                </div>
            </form>
        </div>
    </div>
</body>
</html>