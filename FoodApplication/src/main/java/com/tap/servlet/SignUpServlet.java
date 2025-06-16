package com.tap.servlet;

import java.io.IOException;
import java.time.LocalDateTime;

import com.tap.daoimplementation.UserDAOImpl;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
	
	HttpSession session = req.getSession();

	
	String name = req.getParameter("name");
	String userName = req.getParameter("userName");
	String email = req.getParameter("email");
	String address = req.getParameter("address");
	String phoneNumber = req.getParameter("phoneNumber");
	String password = req.getParameter("password");
	String confirmPassword = req.getParameter("confirmPassword");
	LocalDateTime createDate = LocalDateTime.now();
	LocalDateTime lastLoginDate = LocalDateTime.now();
	
	if(password != null && confirmPassword != null) {
		
    	if(!(password.equals(confirmPassword))) {
		  res.sendRedirect("SignUpPage.jsp?error=Password+entered+do+not+match.");
	    }
	    else {
	
	       User user = new User();
	
	       user.setName(name);
	       user.setUsername(userName);
	       user.setPassword(password);
	       user.setEmail(email);
      	   user.setPhoneNumber(phoneNumber);
	       user.setAddress(address);
	       user.setCreateDate(createDate);
	       user.setLastLoginDate(lastLoginDate);

	     UserDAOImpl userDAOImpl = new UserDAOImpl();
	     int userId = userDAOImpl.addUser(user);
	     
	     session.setAttribute("userId", userId);	    
	     
	     res.sendRedirect("LoginPage.jsp");
	     
	
	    }
    	
    	
	}
    	
	
	
	
	
	
	
	
	
	
	
	
	}
	
	
	
	
	
	
	
	
	

}
