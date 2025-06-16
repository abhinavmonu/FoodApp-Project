package com.tap.servlet;

import java.io.IOException;

import com.tap.daoimplementation.MenuDAOImpl;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		  
		  HttpSession session = req.getSession();
		  
		  Cart cart = (Cart)session.getAttribute("cart"); //Lazy Initialization: Won't create a cart until actually needed
		  Integer currentRestaurantId = (Integer)session.getAttribute("restaurantId");
		  
		  // the above two lines are actually for request from Cart.jsp to CartServlet otherwise they return null
		  		  
		  int restaurantIdPara = Integer.parseInt(req.getParameter("restaurant_id"));
		  
		  if(cart == null || restaurantIdPara != currentRestaurantId) {                //doubt (solved)
			  
			  cart = new Cart();			  
			  session.setAttribute("cart", cart);
			  session.setAttribute("restaurantId", restaurantIdPara);	//key name doubt   (solved)	  
			  
		  }
		  
		  String action = req.getParameter("action");
		  
		  if(action.equals("add")) {	
			  
			  addItemToCart(req, cart);
			  
		  }
		  else if(action.equals("update")) {
			  
			  updateToCartItem(req, cart);
			  
		  }
          else if(action.equals("remove")) {
        	  
        	  removeItemFromCart(req, cart);
        	  
		  }
		  
		  res.sendRedirect("Cart.jsp");
		  
	
	}	

	private void addItemToCart(HttpServletRequest request, Cart cart){
		
		int menuId = Integer.parseInt(request.getParameter("menu_id"));
		int restaurantId = Integer.parseInt(request.getParameter("restaurant_id"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		MenuDAOImpl menusDAO = new MenuDAOImpl(); //not working any of the two lines (the one made me crazy)
		Menu menus = menusDAO.getMenu(menuId);
		
		CartItem cartItem = new CartItem(menuId, restaurantId, menus.getItemName(), menus.getPrice(), quantity, menus.getDescription(), menus.getImagePath());
		
		cart.addCartItem(cartItem);
	}
	
	private void updateToCartItem(HttpServletRequest req, Cart cart) {
		
	       int quantity = Integer.parseInt(req.getParameter("quantity"));
	       int menuId = Integer.parseInt(req.getParameter("menu_id"));
	       cart.upateCartItem(menuId, quantity);
		
	}
	
	private void removeItemFromCart(HttpServletRequest req, Cart cart) {
		
		int menuId = Integer.parseInt(req.getParameter("menu_id"));
		cart.removeCartItem(menuId);
		
	}	
	
	
	
	
}
