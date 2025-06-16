package com.tap.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import com.tap.daoimplementation.RestaurantDAOImpl;
import com.tap.daoimplementation.UserDAOImpl;
import com.tap.model.Restaurant;
import com.tap.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/HomePage")
public class HomeServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		
		if(user != null) {
		
		        String loginEmail = user.getEmail();
		        String loginPasssword = user.getPassword();
		        req.setAttribute("login_email", loginEmail);
				req.setAttribute("login_password", loginPasssword);		
				
			    RestaurantDAOImpl restaurant = new RestaurantDAOImpl();
			    List<Restaurant> allRestaurants = restaurant.getAllRestaurant();
			
			    req.setAttribute("restaurants", allRestaurants);
				
			    RequestDispatcher requestDispatch = req.getRequestDispatcher("Home1.jsp");
			    requestDispatch.forward(req, res);

		}
		else {
			   res.sendRedirect("LoginPage.jsp?error=Account+not+found");
		}
				
	}
	
	// above line when clicked from the nav-bar
	
	
      
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		    HttpSession session = req.getSession();
    	
	        String loginEmail = (String)req.getParameter("login_email");
		    String loginPassword = (String)req.getParameter("login_password");
		
		
            if(loginEmail == null || loginPassword == null ) {
	   
	            res.sendRedirect("LoginPage.jsp?error=Account+not+found");
			
		    }		
		
            UserDAOImpl userDAOImpl = new UserDAOImpl();
	
		    List<User> users = userDAOImpl.getAllUsers(); 
		
		      for(User userDetails : users) {
			
			     if((userDetails.getEmail()).equals(loginEmail) && (userDetails.getPassword()).equals(loginPassword)) {
				
			    	    RestaurantDAOImpl restaurant = new RestaurantDAOImpl();
					    List<Restaurant> allRestaurants = restaurant.getAllRestaurant();
					
					    req.setAttribute("restaurants", allRestaurants);
					    
                        session.setAttribute("user", userDetails);
					
					    RequestDispatcher requestDispatch = req.getRequestDispatcher("Home1.jsp");
					    requestDispatch.forward(req, res);
					    
					    return;			
			      }			
		       }	
		      
		       res.sendRedirect("LoginPage.jsp?error=Account+not+found");		
	  }
	
   }
  

