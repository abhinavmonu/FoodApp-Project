package com.tap.daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.OrderDAO;
import com.tap.model.Order;
import com.tap.util.DBConnection;

public class OrderDAOImpl implements OrderDAO{
	
	private static final String INSERT_QUERY = "INSERT INTO `order` (`restaurantId`, `userId`, `orderDate`, `totalAmount`, `status`, `paymentMode`, `address`) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_QUERY = "SELECT * FROM `order` WHERE `orderId` = ?";  
	private static final String UPDATE_QUERY = "UPDATE `order` SET `restaurantId` = ? `userId` = ? `orderDate` = ? `totalAmount` = ? `status` = ? `paymentMode` = ? `address` = ?";
	private static final String DELETE_QUERY = "DELETE FROM `order` WHERE `orderId` = ?";
	private static final String GET_ALL_ORDER_QUERY = "SELECT * FROM `order`";
	
	
    private static Order order = null;
	
    private final Order extractOrder(ResultSet resultSet) throws SQLException{
   	  	     
        int restaurantId = resultSet.getInt("restaurantId");
        int userId = resultSet.getInt("userId");
        LocalDateTime orderDate = resultSet.getTimestamp("orderDate").toLocalDateTime();
		int totalAmount = resultSet.getInt("totalAmount");	
		String status = resultSet.getString("status");
		String paymentMode = resultSet.getString("paymentMode");
		String address = resultSet.getString("address");
		
		order = new Order(restaurantId, userId, orderDate, totalAmount, status, paymentMode, address);
		
	    return order;
	}		
	
	@Override
	public int addOrder(Order order){
		
		 int orderId = 0;
	
	   	try(Connection connection = DBConnection.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);) {
	   		 
	   	   	    prepareStatement.setInt(1, order.getRestaurantId());
	   		    prepareStatement.setInt(2, order.getUserId());
	   		    prepareStatement.setObject(3, order.getOrderDate());
	   		    prepareStatement.setDouble(4, order.getTotalAmount());
	   		    prepareStatement.setString(5, order.getStatus());
	   		    prepareStatement.setString(6, order.getPaymentMode());
	   		    prepareStatement.setString(7, order.getAddress());
	   		 
	   		 
	   		    int affectedRows = prepareStatement.executeUpdate();		
				if (affectedRows == 0) {
				    throw new SQLException("Insert failed, no rows affected");
				}
				
								
				ResultSet keys = prepareStatement.getGeneratedKeys();
	
				while(keys.next()){
				
					orderId = keys.getInt(1);    // orderId is the column name in the database
			    }				
		 }			
		 catch (SQLException e) {
			 
			e.printStackTrace();
		 }
	   	 
	   	return orderId;		
	}
	
	@Override
	public Order getOrder(int orderId) {
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(SELECT_QUERY);) {
					
				    prepareStatement.setInt(1, orderId);
					ResultSet resultSet = prepareStatement.executeQuery();
					
					order = extractOrder(resultSet);
					
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
					
		return order;
	}

	@Override
	public void updateOrder(Order order) {
		
		    try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_QUERY);){
			
			
				prepareStatement.setInt(1, order.getRestaurantId());
				prepareStatement.setInt(2, order.getUserId());
				prepareStatement.setObject(3, order.getOrderDate()); //casting to Date happened here
				prepareStatement.setDouble(4, order.getTotalAmount());
				prepareStatement.setString(5, order.getStatus());
				prepareStatement.setString(6, order.getPaymentMode());
				prepareStatement.setString(7, order.getAddress());
				
				int res = prepareStatement.executeUpdate();				
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		
	}

	@Override
	public void deleteOrder(int orderId) {
		
		        try(Connection connection = DBConnection.getConnection();
				    PreparedStatement prepareStatement = connection.prepareStatement(DELETE_QUERY);){
					
					prepareStatement.setInt(1, orderId);
					int res = prepareStatement.executeUpdate();
					
				}
				catch (SQLException e) {
					e.printStackTrace();
				}		
	}

	@Override
	public List<Order> getAllOrder() {		

		ArrayList<Order> orderList = new ArrayList<Order>();
		
		try(Connection connection = DBConnection.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(GET_ALL_ORDER_QUERY);){
			ResultSet resultSet = prepareStatement.executeQuery();
			
			while(resultSet.next()) {

				order = extractOrder(resultSet);
				orderList.add(order);
			}
						
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orderList;
	}

}
