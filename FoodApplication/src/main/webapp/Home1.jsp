<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.tap.model.Restaurant, java.util.List" %>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FoodApp | Fine Dining Restaurant</title>
    
    <link rel="stylesheet" href="Home1.css">     
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <!-- Header -->
    <header id="header">
        <div class="container">
            <div class="header-container">
                <a href="#" class="logo">Food<span>App</span></a>
                
                <nav id="nav">
                    <ul>
                        <li><a href="#home">Home</a></li>
                        <li><a href="#menu">Restaurants</a></li>
                        <li><a href="#contact">Contact us</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </header>

    <!-- Hero Section -->
    <section class="hero" id="home">
        <div class="container">
            <div class="hero-content">
                <h1>Experience Culinary Excellence</h1>
                <p>Discover our courteous restaurants providing the finest menus with authentic flavors that will transport your senses.</p>
                <div class="hero-btns">
                    <a href="#menu" class="btn">View Restaurants</a>
                </div>
            </div>
        </div>
    </section>

    <!-- About Section -->
    <section class="about" id="about">
        <div class="container">
            <h2 class="section-title">Our Story</h2>
            
            <div class="about-content">
                <div class="about-img">
                    <img src="https://images.unsplash.com/photo-1555396273-367ea4eb4db5?ixlib=rb-1.2.1&auto=format&fit=crop&w=967&q=80" alt="Restaurant Interior">
                </div>
                
                <div class="about-text">
                    <h3>Welcome to FoodApp</h3>
                    <p>Founded in 2025, FoodApp has been serving exceptional cuisine in a warm and inviting atmosphere. Our passion for food and dedication to quality has made us a beloved destination for food enthusiasts.</p>
                    <p>Our premium restaurants, with over 20 years of international experience, creates seasonal menus that highlight the best local ingredients while drawing inspiration from global culinary traditions.</p>
                    <a href="#menu" class="btn">Explore Our Restaurants</a>
                </div>
            </div>
        </div>
    </section>

    <!-- Menu Section -->
    <section class="menu" id="menu">
        <div class="container">
            <h2 class="section-title">Our Restaurants</h2>
          
            <div class="menu-items">
            
                <!-- Menu Item 1 -->
                
                 <% List<Restaurant> allRestaurants = (List<Restaurant>)request.getAttribute("restaurants");           
                    for(Restaurant restaurant : allRestaurants){        
                  %>
                
                <div class="menu-item">
                  <a href="MenuPage?restaurantId=<%=restaurant.getRestaurantId()%>">  
                  <div class="menu-item-img">
                       <img src="<%=restaurant.getImagePath()%>" alt="<%=restaurant.getName()%>">
                    </div>
                    <div class="menu-item-content">
                        <div class="menu-item-title">
                            <h4 class="menu-item-name"><%=restaurant.getName()%></h4>
                            <span class="menu-item-price">★ <%=restaurant.getRating()%></span>
                        </div>
                        <p class="menu-item-desc"><%=restaurant.getCuisineType()%></p>
                    </div> 
                   </a>
                </div> 
              <% } %>  
                
            </div>
        </div>
    </section>


    <!-- Testimonials Section -->
    <section class="testimonials" id="testimonials">
        <div class="container">
            <h2 class="section-title">What Our Guests Say</h2>
            
            <div class="testimonials-container">
                <div class="testimonial">
                    <p class="testimonial-text">"The food was absolutely amazing! Every dish we tried was full of flavor and beautifully presented. The service was impeccable too."</p>
                    <h4 class="testimonial-author">Sarah Johnson</h4>
                    <p class="testimonial-role">Food Critic</p>
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
                
                <div class="footer-col" id="contact">
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
        // Header scroll effect
        window.addEventListener('scroll', () => {
            const header = document.getElementById('header');
            if (window.scrollY > 100) {
                header.classList.add('scrolled');
            } else {
                header.classList.remove('scrolled');
            }
        });
        
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