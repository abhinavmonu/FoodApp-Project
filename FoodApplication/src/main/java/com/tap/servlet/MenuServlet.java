package com.tap.servlet;

import java.io.IOException;
import java.util.List;

import com.tap.daoimplementation.MenuDAOImpl;
import com.tap.model.Menu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/MenuPage")
public class MenuServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
			
		int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));
		
		MenuDAOImpl menus = new MenuDAOImpl();
		
		List<Menu> allMenus = menus.getAllMenuByRestaurantId(restaurantId);
		
		req.setAttribute("menusById", allMenus);
		
		RequestDispatcher requestDispatch = req.getRequestDispatcher("Menu1.jsp");
		requestDispatch.forward(req, res);
		
	}
}
