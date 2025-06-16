package com.tap.daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.UserDAO;
import com.tap.model.User;
import com.tap.util.DBConnection;

public class UserDAOImpl implements UserDAO{
	private static final String INSERT_QUERY = "INSERT INTO `user` (`name`, `userName`, `password`, `email`, `phoneNumber`, `address`, `role`, `createDate`, `lastLoginDate`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_QUERY = "SELECT * FROM `user` WHERE `userId = ?`";   
	private static final String UPDATE_QUERY = "UPDATE `user` SET `name` = ? `password` = ? `phoneNumber` = ? `address` = ? `role` = ?";
	private static final String DELETE_QUERY = "DELETE FROM `user` WHERE `userId` = ?";
	private static final String GET_ALL_USER_QUERY = "SELECT * FROM `user`";

private static User user = null;
	
	
	User extractUser(ResultSet resultSet) throws SQLException{
		
		int userId = resultSet.getInt("userId");
		String name = resultSet.getString("name");
		String username = resultSet.getString("username");
		String password = resultSet.getString("password");
		String email = resultSet.getString("email");
		String phoneNumber = resultSet.getString("phoneNumber");
		String address = resultSet.getString("address");
		String role = resultSet.getString("role");
		LocalDateTime createDate = resultSet.getTimestamp("createDate").toLocalDateTime();  //toLocalDateTime() is a method that converts a java.sql.Timestamp object to a java.time.LocalDateTime object.
		LocalDateTime lastLoginDate = resultSet.getTimestamp("lastLoginDate").toLocalDateTime();
		
		user = new User(userId, name, username, password, email, phoneNumber, address, role, createDate, lastLoginDate);
		
		return user;		
	}

	@Override
	public int addUser(User user) {
		
		
		int userId = 0;
	
		try(Connection connection = DBConnection.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);){
			
			prepareStatement.setString(1, user.getName());
			prepareStatement.setString(2, user.getUsername());
			prepareStatement.setString(3, user.getPassword());
			prepareStatement.setString(4, user.getEmail());
			prepareStatement.setString(5, user.getPhoneNumber());
			prepareStatement.setString(6, user.getAddress());
			prepareStatement.setString(7, user.getRole());
			prepareStatement.setObject(8, user.getCreateDate());
			prepareStatement.setObject(9, user.getLastLoginDate());
			
			prepareStatement.executeUpdate();
			
			ResultSet userIdKeys = prepareStatement.getGeneratedKeys();
			
			while(userIdKeys.next()) {
				
				userId = userIdKeys.getInt(1);				
			}			
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userId;	
	}

	@Override
	public User getUser(int userId) {
		
		try(Connection connection = DBConnection.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(SELECT_QUERY);)
			{
			
			prepareStatement.setInt(1, userId);
			ResultSet resultSet = prepareStatement.executeQuery();
			
			user = extractUser(resultSet);
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return user;	
	}

	@Override
	public void updateUser(User user) {
		
		
		try(Connection connection = DBConnection.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_QUERY);){
			
			prepareStatement.setString(1, user.getName());
			prepareStatement.setString(2, user.getPassword());
			prepareStatement.setString(3, user.getPhoneNumber());
			prepareStatement.setString(4, user.getAddress());
			prepareStatement.setString(5, user.getRole());
			
			prepareStatement.executeUpdate();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteUser(int userId) {
			
			try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(DELETE_QUERY);){
				
				prepareStatement.setInt(1, userId);
				prepareStatement.executeUpdate();
				
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
	}

	@Override
	public List<User> getAllUsers() {
		
		
		ArrayList<User> userList = new ArrayList<User>();
		
		try(Connection connection = DBConnection.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(GET_ALL_USER_QUERY);){
			ResultSet resultSet = prepareStatement.executeQuery();
			
			while(resultSet.next()) {
				user = extractUser(resultSet);
                userList.add(user);
			}						
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	    return userList;
	}	
	
}
