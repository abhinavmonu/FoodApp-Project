<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FoodApp | Login</title>
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="LoginPage.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <div class="login-container">
        <!-- Left Side - Image -->
        <div class="login-image">
            <h2>Welcome Back!</h2>
            <p>Sign in to access your favorite restaurants, track your orders, and discover new culinary experiences.</p>
            <p>Feeling hungry? <a style="color: #c19a6b; font-weight: 600;">Grab your favourites.</a></p>
        </div>
        
        <!-- Right Side - Form -->
        <div class="login-form">
            <div class="login-header">
                <h1>Sign In</h1>
               <%if(request.getParameter("error") == null) { %>
               
                <p>Enter your credentials to access your account</p>     
                        
               <% } else { %> 
               
               <p style="color: #d32f2f"><%=request.getParameter("error")%></p>                
               <p style="color: #d32f2f">Please sign up first or check your credentials</p>
               
               <% } %>
            </div>
            
            <form action="HomePage" method="post">
                <div class="form-group">
                    <label for="email">Email Address</label>
                    <div class="input-with-icon">
                        <i class="fas fa-envelope"></i>
                        <input type="email" id="email" class="form-control" placeholder="Enter your email" name="login_email" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="password">Password</label>
                    <div class="input-with-icon">
                        <i class="fas fa-lock"></i>
                        <input type="password" id="password" class="form-control" placeholder="Enter your password" name="login_password" required>
                    </div>
                </div>
                
                <div class="remember-forgot">
                    <div class="remember-me">
                        <input type="checkbox" id="remember">
                        <label for="remember">Remember me</label>
                    </div>
                    <div class="forgot-password">
                        <a href="#">Forgot password?</a>
                    </div>
                </div>
                
                <button type="submit" class="btn">Sign In</button>
                
                <div class="social-login">
                    <p>or sign in with</p>
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
                
                <div class="login-footer">
                    <p>Don't have an account? <a href="SignUpPage.jsp">Sign up</a></p>
                </div>
            </form>
        </div>
    </div>
</body>
</html>