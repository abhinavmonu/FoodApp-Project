<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "com.tap.model.Cart, com.tap.model.CartItem, com.tap.model.User" %>
   
    
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Cart | FoodApp</title>
    
    <link rel="stylesheet" href="Cart.css">
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>

    <!-- Header -->   
     <%User user = (User)session.getAttribute("user");%>
    <header id="header">
        <div class="container">
            <div class="header-container">
                <a href="" class="logo">Food<span>App</span></a>
                
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

    <!-- Cart Hero -->
    <section class="cart-hero">
        <div class="container">
            <div class="cart-hero-content">
                <h1>Your Cart</h1>
                <p>Review your selected items before checkout</p>
            </div>
        </div>
    </section>

    <!-- Cart Section -->
    <section class="cart-section">
        <div class="container">
            <div class="cart-container">
                <div class="cart-items">              
                  <% Cart cart = (Cart)session.getAttribute("cart");
                   
                   if(cart != null && !cart.getItems().isEmpty()){                       
                	   for(CartItem cartItems : cart.getItems().values()){             
                  %>               
                   <!-- Example with cart items (you can remove this to show empty cart) -->
                   <div class="cart-item" style="display: block">
                       <div class="cart-item-img">
                           <img src="<%=cartItems.getImagePath()%>" alt="<%=cartItems.getName()%>">
                       </div>
                       
                       <div class="cart-item-details">
                       
                           <div class="cart-item-title">
                               <h4 class="cart-item-name"><%=cartItems.getName()%></h4>      
                               <span class="cart-item-price">$<%=cartItems.getPrice()%></span>
                           </div>
                           
                           <p class="cart-item-desc"><%=cartItems.getMenuDescription()%></p>
                           <div class="cart-item-actions">
                           
                               <div class="quantity-control">
                               <form action="CartServlet">
                                   <input type="hidden" name="menu_id" value="<%=cartItems.getMenuId()%>">
                                   <input type="hidden" name="action" value="update"> 
                                   <input type="hidden" name="restaurant_id" value="<%=session.getAttribute("restaurantId")%>"> 
                                   <input type="hidden" name="quantity" value="<%=cartItems.getQuantity() - 1%>">                                                           
                                   <button type="submit" class="quantity-btn minus" <%if(cartItems.getQuantity() <= 1) { %> disabled <% } %>>-</button>
                                </form>
                                
                               <input type="number" style="width: 40px; height: 25px; text-align: center; border: red;  background: transparent; font-weight: bold" value="<%=cartItems.getQuantity()%>" readonly>
                                   
                               <form action="CartServlet">
                                  <input type="hidden" name="menu_id" value="<%=cartItems.getMenuId()%>">
                                   <input type="hidden" name="action" value="update">
                                    <input type="hidden" name="restaurant_id" value="<%=session.getAttribute("restaurantId")%>"> 
                                    <input type="hidden" name="quantity" value="<%=cartItems.getQuantity() + 1%>">
                                   <button  type="submit" class="quantity-btn plus">+</button>
                                </form>
                               </div>
                               
                               <form action="CartServlet" method="post" class="remove-form">
                                   <input type="hidden" name="menu_id" value="<%=cartItems.getMenuId()%>">
                                   <input type="hidden" name="restaurant_id" value="<%=session.getAttribute("restaurantId")%>"> 
                                   <input type="hidden" name="action" value="remove">
                                   <button type="submit" class="remove-btn">
                                       <i class="fas fa-trash"></i> Remove
                                   </button>
                               </form>
                           </div>
                       </div><br><br>
                     
                     <%  }
                       }
                     %>             
                    </div>
                </div>
                
                <!-- Order Summary (shown when cart has items) -->
                <div class="cart-summary">
                    <h3 class="cart-summary-title">Order Summary</h3>
   
                    
                    <div class="summary-row summary-total">
                        <span>Total</span>
                        <span>$<%=cart.getTotalPrice(cart)%></span>
                    </div>
                    <a href="MenuPage?restaurantId=<%=session.getAttribute("restaurantId")%>" class="btn add-more-btn" style="display:flex; justify-content:center">Add More Items</a>
                    
                    <a href="Checkout.jsp" class="btn checkout-btn">Proceed to Checkout</a>
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
                <p>&copy; 2025 FoodApp. All Rights Reserved.</sp>
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