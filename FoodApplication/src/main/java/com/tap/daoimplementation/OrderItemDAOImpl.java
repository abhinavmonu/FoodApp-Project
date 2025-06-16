package com.tap.daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.OrderItemDAO;
import com.tap.model.OrderItem;
import com.tap.util.DBConnection;

public class OrderItemDAOImpl implements OrderItemDAO{
	
	
	private static final String INSERT_QUERY = "INSERT INTO `orderItem` (`orderId`, `menuId`, `quantity`, `totalAmount`) VALUES (?, ?, ?, ?)";
	private static final String SELECT_QUERY = "SELECT * FROM `orderItem` WHERE `orderItemId` = ?";  
	private static final String UPDATE_QUERY = "UPDATE `orderItem` SET `orderItemId` = ? `orderId` = ? `menuId` = ? `quantity` = ? `totalAmount` = ?";
	private static final String DELETE_QUERY = "DELETE FROM `orderItem` WHERE `orderItemId` = ?";
	private static final String GET_ALL_ORDERITEM_QUERY = "SELECT * FROM `orderItem`";
	
	
    private static OrderItem orderItem = null;
	
    private final OrderItem extractOrderItem(ResultSet resultSet) throws SQLException{
	
	    int orderId = resultSet.getInt("orderId");
        int menuId = resultSet.getInt("menuId");
	    Double totalAmount = resultSet.getDouble("totalAmount");	
	    int quantity = resultSet.getInt("quantity");
	
	    orderItem = new OrderItem(orderId, menuId, quantity, totalAmount);
        return orderItem;
	
    }	
    
	@Override
	public void addOrderItem(OrderItem orderItem) {
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);) {
				
			
		        prepareStatement.setInt(1, orderItem.getOrderId());
			    prepareStatement.setInt(2, orderItem.getMenuId());
			    prepareStatement.setInt(3, orderItem.getQuantity());
			    prepareStatement.setDouble(4, orderItem.getTotalAmount());
			
		
				prepareStatement.executeUpdate();
				ResultSet key = prepareStatement.getGeneratedKeys();
				
				while(key.next()) {             //next() return boolean
					  
					int orderItemId = key.getInt(1);
				}
				
		}			
		catch (SQLException e) {
			e.printStackTrace();
		}
		   
		
	}

	@Override
	public OrderItem getOrderItem(int orderItemId) {
		
		try(Connection connection = DBConnection.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(SELECT_QUERY);) {
					
			    prepareStatement.setInt(1, orderItemId);
				ResultSet resultSet = prepareStatement.executeQuery();
				
				orderItem = extractOrderItem(resultSet);
					
		}
        catch (SQLException e) {
			e.printStackTrace();
		}
					
		return orderItem;
	}

	@Override
	public void updateOrderItem(OrderItem orderItem) {
		
		try(Connection connection = DBConnection.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_QUERY);){
						
			prepareStatement.setInt(1, orderItem.getOrderId());
			prepareStatement.setInt(2, orderItem.getMenuId());
			prepareStatement.setInt(3, orderItem.getQuantity());
			prepareStatement.setDouble(4, orderItem.getTotalAmount());
			
			prepareStatement.executeUpdate();

				
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void deleteOrderItem(int orderItemId) {
		
		try(Connection connection = DBConnection.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(DELETE_QUERY);){
					
			prepareStatement.setInt(1, orderItemId);
			int res = prepareStatement.executeUpdate();					
					
		}
		catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public List<OrderItem> getAllOrderItem() {
		
        ArrayList<OrderItem> orderItemList = new ArrayList<OrderItem>();
		
		try(Connection connection = DBConnection.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(GET_ALL_ORDERITEM_QUERY);){
			ResultSet resultSet = prepareStatement.executeQuery();
			
			while(resultSet.next()) {

				orderItem = extractOrderItem(resultSet);
				orderItemList.add(orderItem);
			}						
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orderItemList;
	}

}
