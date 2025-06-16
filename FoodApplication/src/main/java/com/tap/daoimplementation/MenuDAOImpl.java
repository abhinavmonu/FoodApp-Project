package com.tap.daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.MenuDAO;
import com.tap.model.Menu;
import com.tap.util.DBConnection;

public class MenuDAOImpl implements MenuDAO{
	
	private static final String INSERT_QUERY = "INSERT INTO `menu` (`menuId`, `restaurantId`, `itemName`, `description`, `price`, `isAvailable`, `ratings`) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_QUERY = "SELECT * FROM `menu` WHERE `menuId` = ?";  
	private static final String UPDATE_QUERY = "UPDATE `menu` SET `menuId` = ? `restaurantId` = ? `itemName` = ? `description` = ? `price` = ? `ratings` = ?";
	private static final String DELETE_QUERY = "DELETE FROM `menu` WHERE `menuId` = ?";
	private static final String GET_ALL_MENU_QUERY = "SELECT * FROM `menu`";
	private static final String GET_MENU_BY_RESTAURANT_ID_QUERY = "SELECT * FROM `menu` WHERE `restaurantId` = ?";
	
	
	private static Menu menu = null;
	
    private final Menu extractMenu(ResultSet resultSet) throws SQLException{
   		
	    int menuId = resultSet.getInt("menuId");
	    int restaurantId = resultSet.getInt("restaurantId");
		String itemName = resultSet.getString("itemName");
		String description = resultSet.getString("description");
		int price = resultSet.getInt("price");	
		String isAvailable = resultSet.getString("isAvailable");
		int ratings = resultSet.getInt("ratings");
		String imagePath = resultSet.getString("imagePath");
		menu = new Menu(menuId, restaurantId, itemName, description, price, isAvailable, ratings, imagePath);
		
		return menu;
	}
	

	@Override
	public void addMenu(Menu menu) {
	
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(INSERT_QUERY);) {
				
				int res = prepareStatement.executeUpdate();
		}
        catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Menu getMenu(int menuId) {
		
		try(Connection connection = DBConnection.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(SELECT_QUERY);) {
					
				prepareStatement.setInt(1, menuId);
			    ResultSet resultSet = prepareStatement.executeQuery();
					
					
				if(resultSet.next()) {     //resultSet.next() is must not to read from the ResultSet (table heading i think)
					menu = extractMenu(resultSet);					
				}
		}
        catch (SQLException e) {
			e.printStackTrace();
		}
        
		 return menu;
	}

	@Override
	public void updateMenu(Menu menu) {
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_QUERY);){
			
				
				prepareStatement.setInt(1, menu.getMenuId());
				prepareStatement.setInt(2, menu.getRestaurantId());
				prepareStatement.setString(3, menu.getItemName());
				prepareStatement.setString(4, menu.getDescription());
				prepareStatement.setInt(5, menu.getPrice());
				prepareStatement.setInt(4, menu.getRatings());
				
				int res = prepareStatement.executeUpdate();

				
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteMenu(int menuId) {
		
		try(Connection connection = DBConnection.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(DELETE_QUERY);){
			
			prepareStatement.setInt(1, menuId);
			int res = prepareStatement.executeUpdate();
				
        }
		catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public List<Menu> getAllMenu() {
		
		ArrayList<Menu> menuList = new ArrayList<Menu>();
		
		try(Connection connection = DBConnection.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(GET_ALL_MENU_QUERY);){
			ResultSet resultSet = prepareStatement.executeQuery();
			
			while(resultSet.next()) {

				menu = extractMenu(resultSet);
				menuList.add(menu);
			}
						
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return menuList;
	}

	@Override
	public List<Menu> getAllMenuByRestaurantId(int restaurantId) {
       
		ArrayList<Menu> menuList = new ArrayList<Menu>();
		
		try(Connection connection = DBConnection.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(GET_MENU_BY_RESTAURANT_ID_QUERY);){
			prepareStatement.setInt(1, restaurantId);
			
			ResultSet resultSet = prepareStatement.executeQuery();
			
			while(resultSet.next()) {

				menu = extractMenu(resultSet);
				menuList.add(menu);
			}
						
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return menuList;
	}

}
