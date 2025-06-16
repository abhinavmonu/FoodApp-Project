<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.tap.model.Cart, com.tap.model.CartItem, com.tap.model.User" %>
    
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout | FoodApp</title>
    
    <link rel="stylesheet" href="Checkout.css">
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
                        <li><a href="Cart.jsp">Cart</a></li>
                        <li><a href="#contact">Contact us</a></li>                    
                    </ul>
                </nav>
            </div>
        </div>
    </header>

    <!-- Checkout Hero -->
    <section class="checkout-hero">
        <div class="container">
            <div class="checkout-hero-content">
                <h1>Checkout</h1>
                <p>Complete your order with secure payment</p>
            </div>
        </div>
    </section>

    <!-- Checkout Section -->
    <section class="checkout-section">
        <div class="container">
            <div class="checkout-container">
                <div class="checkout-form">
                    <h2 class="section-title">Delivery Information</h2>
                                        
                     <% Cart cart = (Cart)session.getAttribute("cart"); %>
                    
                    <form action="CheckOutServlet">
                        <div class="form-row">
                            <div class="form-group">
                                <label for="firstName">First Name</label>
                                <input type="text" id="firstName" name="firstName" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label for="lastName">Last Name</label>
                                <input type="text" id="lastName" name="lastName" class="form-control" required>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label for="email">Email Address</label>
                            <input type="email" id="email" name="email" class="form-control" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="phone">Phone Number</label>
                            <input type="tel" id="phone" name="phone" class="form-control" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="address">Delivery Address</label>
                            <input type="text" id="address" name="address" class="form-control" required>
                        </div>
                        
                        <div class="form-row">
                            <div class="form-group">
                                <label for="city">City</label>
                                <input type="text" id="city" name="city" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label for="zip">ZIP Code</label>
                                <input type="text" id="zip" name="zip" class="form-control" required>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label for="instructions">Delivery Instructions (Optional)</label>
                            <textarea id="instructions" name="instructions" class="form-control" rows="3"></textarea>
                        </div>
                        
                        <h2 class="section-title">Payment Method</h2>
                        
                        <div class="payment-methods">
                            <label class="payment-method">
                                <input type="radio" name="paymentMode" value="creditCard" checked>
                                <i class="fas fa-credit-card payment-method-icon"></i>
                                <div class="payment-method-details">
                                    <div class="payment-method-title">Credit/Debit Card</div>
                                    <div class="payment-method-desc">Pay with Visa, Mastercard, or other cards</div>
                                </div>
                            </label>
                            
                            <label class="payment-method">
                                <input type="radio" name="paymentMode" value="paypal">
                                <i class="fab fa-cc-paypal payment-method-icon"></i>
                                <div class="payment-method-details">
                                    <div class="payment-method-title">PayPal</div>
                                    <div class="payment-method-desc">Pay with your PayPal account</div>
                                </div>
                            </label>
                            
                            <label class="payment-method">
                                <input type="radio" name="paymentMode" value="cash">
                                <i class="fas fa-money-bill-wave payment-method-icon"></i>
                                <div class="payment-method-details">
                                    <div class="payment-method-title">Cash on Delivery</div>
                                    <div class="payment-method-desc">Pay in cash when your order arrives</div>
                                </div>
                            </label>
                        </div>
                        
                        <div id="creditCardDetails">
                            <div class="form-group">
                                <label for="cardNumber">Card Number</label>
                                <input type="text" id="cardNumber" name="cardNumber" class="form-control" placeholder="1234 5678 9012 3456">
                            </div>
                            
                            <div class="form-row">
                                <div class="form-group">
                                    <label for="expiryDate">Expiry Date</label>
                                    <input type="text" id="expiryDate" name="expiryDate" class="form-control" placeholder="MM/YY">
                                </div>
                                <div class="form-group">
                                    <label for="cvv">CVV</label>
                                    <input type="text" id="cvv" name="cvv" class="form-control" placeholder="123">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label for="cardName">Name on Card</label>
                                <input type="text" id="cardName" name="cardName" class="form-control">
                            </div>
                        </div>
                        
                        <button type="submit" class="btn place-order-btn">Place Order</button>
                    </form>
                </div>
                
                <!-- Order Summary -->
                <div class="order-summary">
                    <h3 class="order-summary-title">Your Order</h3>
                    
                    <div class="order-items">
                        <!-- Sample order items - replace with dynamic content in your actual implementation -->
                            
                       <% for(CartItem items : cart.getItems().values()) { %>                   
                        
                        <div class="order-item">
                            <div class="order-item-name"><%=items.getName()%></div>
                            <div class="order-item-qty">x<%=items.getQuantity()%></div>
                            <div class="order-item-price">$<%=items.getPrice()*items.getQuantity()%></div>
                        </div>
                        
                       <% } %>
                 
                    </div>
                    
                    <div class="summary-row summary-total">
                        <span>Total</span>
                        <span>$<%=cart.getTotalPrice(cart)%></span>
                    </div>
                    
                    <a href="Cart.jsp" class="btn" style="width: 100%; margin-top: 20px; display: block; text-align: center;">Back to Cart</a>
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
</body>
</html>