package com.tap.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

import com.tap.daoimplementation.OrderDAOImpl;
import com.tap.daoimplementation.OrderItemDAOImpl;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.Order;
import com.tap.model.OrderItem;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/CheckOutServlet")
public class CheckOutServlet extends HttpServlet{
		
	private OrderDAOImpl orderDAOImpl;
	private OrderItem orderItem;


	@Override
	public void init(){
		try {
		  super.init();			
		  orderDAOImpl = new OrderDAOImpl();	
		  orderItem = new OrderItem();
			
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		HttpSession session = req.getSession();
		
		Cart cart = (Cart)session.getAttribute("cart");		
		User user = (User)session.getAttribute("user");	
		
		if( cart != null && user != null && !(cart.getItems().isEmpty())) {
		
		
	    	int userId = user.getUserId();	
		    String address = req.getParameter("address");
		    String paymentMode = req.getParameter("paymentMode");			
		    String status = "pending";
		    LocalDateTime orderDate = LocalDateTime.now();
		    int restaurantId = (int)session.getAttribute("restaurantId");
		    double totalPrice = cart.getTotalPrice(cart);
		
		    Order order = new Order(restaurantId, userId, orderDate, totalPrice, status, paymentMode, address);		
		
		    int orderId = orderDAOImpl.addOrder(order);
		
		    session.setAttribute("order", order);		
		    session.setAttribute("orderId", orderId);
		
		for(CartItem cartItem: cart.getItems().values()) {
			  
			orderItem.setMenuId(cartItem.getMenuId());  
			orderItem.setOrderId(orderId);
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setTotalAmount((cartItem.getPrice() * cartItem.getQuantity()));
			
			OrderItemDAOImpl orderItemDAOImpl = new OrderItemDAOImpl();			
			orderItemDAOImpl.addOrderItem(orderItem);
		}
		
		res.sendRedirect("ConfirmationPage.jsp");
		
		}		
		else {
			
		  res.sendRedirect("LoginPage.jsp");
			
		}
		
	}

}
