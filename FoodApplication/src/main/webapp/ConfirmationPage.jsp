<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="com.tap.model.Order, com.tap.model.Cart, com.tap.model.CartItem, java.time.Month, com.tap.model.User" %>

    
    
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Confirmation | FoodApp</title>
    
    <link rel="stylesheet" href="ConfirmationPage.css">
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <!-- Header -->
      
    <%User user = (User)session.getAttribute("user");%>
    
    <header id="header">
        <div class="container">
            <div class="header-container">
                <a href="index.html" class="logo">Food<span>App</span></a>
                
                <nav id="nav">
                    <ul>
                        <li><a href="HomePage">Home</a></li>
                        <li><a href="MenuPage?restaurantId=<%=session.getAttribute("restaurantId")%>">Menu</a></li>
                        <li><a href="#contact">Contact us</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </header>

    <!-- Confirmation Hero -->
    <section class="confirmation-hero">
        <div class="container">
            <div class="confirmation-hero-content">
                <h1>Order Confirmed!</h1>
                <p>Thank you for your order. We're reaching out to you soon.</p>
            </div>
        </div>
    </section>
    
    <% Order order = (Order)session.getAttribute("order");%>
  	
  	<% int day = order.getOrderDate().getDayOfMonth();
       Month month = order.getOrderDate().getMonth();
       int year = order.getOrderDate().getYear();  	
  	%>

    <!-- Confirmation Section -->
    <section class="confirmation-section">
        <div class="container">
            <div class="confirmation-card">
                <div class="confirmation-icon">
                    <i class="fas fa-check-circle"></i>
                </div>
                <h2 class="confirmation-title">Your Order Is Confirmed</h2>
                <p class="confirmation-text">
                    We've received your order and it's now being processed. 
                    You'll receive a confirmation email shortly with all the details.
                </p>
                
                <div class="order-details">
                    <h3 class="order-details-title">Order Details</h3>
                    
                    <div class="detail-row">
                        <span class="detail-label">Order Number:</span>
                        <span class="detail-value"><%=session.getAttribute("orderId")%></span>
                    </div>
                    <div class="detail-row">
                        <span class="detail-label">Date:</span>
                        <span class="detail-value"><%=day%> <%=month%> <%=year%></span>
                    </div>
                    <div class="detail-row">
                        <span class="detail-label">Payment Method:</span>
                        <span class="detail-value"><%=order.getPaymentMode()%></span>
                    </div>
                    <div class="detail-row">
                        <span class="detail-label">Delivery Address:</span>
                        <span class="detail-value"><%=order.getAddress()%></span>
                    </div>
                    <div class="detail-row">
                        <span class="detail-label">Estimated Delivery:</span>
                        <span class="detail-value">45-60 minutes</span>
                    </div>
                    
                    <div class="order-items">
                        <h4 class="order-details-title" style="font-size: 18px; margin-bottom: 15px;">Your Items</h4>
                        
                   <% Cart cart = (Cart)session.getAttribute("cart"); 
                   
                        for(CartItem cartItems : cart.getItems().values()) {
                   
                   %>                            
                        <div class="order-item">
                            <div class="order-item-name"><%=cartItems.getName()%></div>
                            <div class="order-item-qty">x<%=cartItems.getQuantity()%></div>
                            <div class="order-item-price">$<%=cartItems.getPrice()%></div>
                        </div>
                     
                     <% } %>
                        
                        <div class="order-total">
                            <span>Total</span>
                            <span>$<%=cart.getTotalPrice(cart)%></span>
                        </div>
                    </div>
                </div>
                
                <div class="action-buttons">
                    <a href="MenuPage?restaurantId=<%=session.getAttribute("restaurantId")%>" class="btn">Order Again</a>
                    <a href="HomePage" class="btn" style="background-color: #333;">Back to Home</a>
                </div>
            </div>
        </div>
    </section>

    <!-- Footer -->
    <footer>
        <div class="container">
            <div class="footer-container">               
                <div class="footer-col" id="contact">
                    <h3>Contact Us</h3>
                    <p>123 Restaurant Row</p>
                    <p>Foodie City, FC 10001</p>
                    <p>Phone: (123) 456-7890</p>
                    <p>Email: info@foodapp.com</p>
                </div>
                
                <div class="footer-col">
                    <h3>Opening Hours</h3>
                    <p>Monday - Friday: 11am - 10pm</p>
                    <p>Saturday: 10am - 11pm</p>
                    <p>Sunday: 10am - 9pm</p>
                </div>
            </div>
            
            <div class="copyright">
                <p>&copy; 2025 FoodApp. All Rights Reserved.</p>
            </div>
        </div>
    </footer>
    
    <script>       
        
        // Smooth scrolling for anchor links
        document.querySelectorAll('a[href^="#"]').forEach(anchor => {
            anchor.addEventListener('click', function(e) {
                e.preventDefault();
                
                const targetId = this.getAttribute('href');
                const targetElement = document.querySelector(targetId);
                
                window.scrollTo({
                    top: targetElement.offsetTop - 80,
                    behavior: 'smooth'
                });
            });
        });
    </script>
    
    <%Cart.clearAllCart();
    System.out.println("CART REMOVED");%>
    
    
</body>
</html>