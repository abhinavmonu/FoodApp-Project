<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import = "com.tap.model.Menu, com.tap.model.User, com.tap.model.Cart, java.util.List" %>
    
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Our Menu | FoodApp</title>
    
    <link rel="stylesheet" href="Menu1.css">
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <!-- Header -->
    <header id="header">
        <div class="container">
            <div class="header-container">
                <a href="index.html" class="logo">Food<span>App</span></a>
                
                <%User user = (User)session.getAttribute("user");%>
                
                <nav id="nav">
                    <ul>
                        <li><a href="HomePage">Home</a></li>
                        <li><a href="#menus" class="active">Menu</a></li>
                        <li><a href="Cart.jsp?cart=<%=(Cart)session.getAttribute("cart")%>">Cart</a></li>
                        <li><a href="#contact">Contact us</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </header>

    <!-- Menu Hero -->
    <section class="menu-hero">
        <div class="container">
            <div class="menu-hero-content">
                <h1>Our Menu</h1>
                <p>Discover our carefully crafted dishes made with the finest ingredients and authentic flavors</p>
            </div>
        </div>
    </section>

    <!-- Menu Section -->
    <section class="menu-section" id="menus">
        <div class="container">
            
            <!-- Starters Menu -->
            <div class="menu-items-container active" id="starters">
                <h3 class="menu-category-title">Menus</h3>
                <div class="menu-items">
                
                    <!-- Starter 1 -->
                    
                    <% List<Menu> allMenus = (List<Menu>)request.getAttribute("menusById");
                       for(Menu menus : allMenus){
                    %>  
                                                           
                    <div class="menu-item">
                        <div class="menu-item-img">
                            <img src="<%=menus.getImagePath()%>" alt="<%=menus.getItemName()%>">
                        </div>
                        <div class="menu-item-content">
                            <div class="menu-item-title">
                                <h4 class="menu-item-name"><%=menus.getItemName()%></h4>
                                <span class="menu-item-price">$<%=menus.getPrice()%></span>
                            </div>
                            <p class="menu-item-desc"><%=menus.getDescription()%></p>
                            
                          <form action="CartServlet">                         
                                  <input type="hidden" name="menu_id" value="<%=menus.getMenuId()%>">                           
                                  <input type="hidden" name="restaurant_id" value="<%=menus.getRestaurantId()%>">
                                  <input type="hidden" name="action" value="add" >
                                  <input type="hidden" name="quantity" value="1" >
                                  <input class="cart-btn" type="submit" value="Add to cart" >                          
                          </form>                            
                        </div>
                    </div>
                    <% } %>                                     
                </div>
            </div>
            </div>
                  
         
    </section>
     
    <!-- Footer -->
     <footer>
        <div class="container">
            <div class="footer-container">
                
                <div class="footer-col">
                    <h3>Opening Hours</h3>
                    <ul class="footer-links">
                        <li>Monday - Saturday: 11am - 10pm</li>
                        <li>Sunday: 10am - 9pm</li>
                        <li>Holidays: 12pm - 8pm</li>
                    </ul>
                </div>
                
                <div class="footer-col"  id="contact">
                    <h3>Contact Info</h3>
                    <ul class="footer-links">
                        <li>123 Culinary Avenue, NY 10001</li>
                        <li>+1 (555) 123-4567</li>
                        <li>info@foodapp.com</li>
                    </ul>
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