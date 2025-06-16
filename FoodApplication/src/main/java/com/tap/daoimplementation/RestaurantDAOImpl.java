package com.tap.daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


import com.tap.dao.RestaurantDAO;
import com.tap.model.Restaurant;
import com.tap.util.DBConnection;

public class RestaurantDAOImpl implements RestaurantDAO{

	private static final String INSERT_QUERY = "INSERT INTO `restaurant` (`name`, `address`, `phoneNumber`, `cuisineType`, `deliveryTime`, `adminUserId`, `rating`) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String SELECT_QUERY = "SELECT * FROM `restaurant` WHERE `restaurantId` = ?";  
	private static final String UPDATE_QUERY = "UPDATE `restaurant` SET `name` = ? `address` = ? `phoneNumber` = ? `cuisineType` = ? `deliveryTime` = ? `rating` = ?";
	private static final String DELETE_QUERY = "DELETE FROM `restaurant` WHERE `restaurantId` = ?";
	private static final String GET_ALL_RESTAURANT_QUERY = "SELECT * FROM `restaurant`";

	
	private static Restaurant restaurant = null;
	
     private final Restaurant extractRestaurant(ResultSet resultSet) throws SQLException{
    	
	    int restaurantId = resultSet.getInt("restaurantId");
		String name = resultSet.getString("name");
		String address = resultSet.getString("address");
		int phoneNumber = resultSet.getInt("phoneNumber");	
		String cuisineType = resultSet.getString("cuisineType");
		Time deliveryTime = resultSet.getTime("deliveryTime");
		int adminUserId = resultSet.getInt("adminUserId");
		int rating = resultSet.getInt("rating");
		String isActive = resultSet.getString("isActive");
		String imagePath = resultSet.getString("imagePath");
			
		restaurant = new Restaurant(restaurantId, name, address, phoneNumber, cuisineType, deliveryTime, adminUserId, rating, isActive, imagePath);

		return restaurant;
	}
	
	
	

	@Override
	public void addRestaurant(Restaurant restaurant) {
		
		try(Connection connection = DBConnection.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(INSERT_QUERY);) {
			
			int res = prepareStatement.executeUpdate();
			
		}
		
		
	    catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public Restaurant getRestaurant(int restaurantId) {
		
		try(Connection connection = DBConnection.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(SELECT_QUERY);) {
				
		    prepareStatement.setInt(1, restaurantId);
			ResultSet resultSet = prepareStatement.executeQuery();
			
			restaurant = extractRestaurant(resultSet);
		}
        catch (SQLException e) {
			e.printStackTrace();
		}
		
		return restaurant;
	}

	@Override
	public void updateRestaurant(Restaurant restaurant) {
		try(Connection connection = DBConnection.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_QUERY);){
				
			prepareStatement.setString(1, restaurant.getName());
			prepareStatement.setString(2, restaurant.getAddress());
			prepareStatement.setInt(3, restaurant.getPhoneNumber());
			prepareStatement.setString(4, restaurant.getCuisineType());
			prepareStatement.setTime(5, restaurant.getDeliveryTime());
			prepareStatement.setInt(4, restaurant.getRating());
			
			int res = prepareStatement.executeUpdate();

				
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void deleteRestaurant(int restaurantId) {
		try(Connection connection = DBConnection.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(DELETE_QUERY);){
				
			prepareStatement.setInt(1, restaurantId);
			int res = prepareStatement.executeUpdate();				
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public List<Restaurant> getAllRestaurant() {  
           
		ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
		Restaurant restaurant = null;
		
		try(Connection connection = DBConnection.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(GET_ALL_RESTAURANT_QUERY);){
			ResultSet resultSet = prepareStatement.executeQuery();
			
			while(resultSet.next()) {

				restaurant = extractRestaurant(resultSet);
				restaurantList.add(restaurant);
		    }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return restaurantList;
	}
}
