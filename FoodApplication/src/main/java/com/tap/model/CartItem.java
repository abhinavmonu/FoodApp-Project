package com.tap.model;

public class CartItem {	
	private int menuId;
	private int restaurantId;
	private String name;
	private int price;
	private int quantity;
	private String menuDescription;
	private String imagePath;
	
	public CartItem() {
	}
	
	public CartItem(int menuId, int restaurantId, String name, int price, int quantity, String menuDescription, String imagePath) {
	  this.menuId = menuId;
	  this.restaurantId = restaurantId;
	  this.name = name;
	  this.price = price;
	  this.quantity = quantity;
	  this.menuDescription = menuDescription;
	  this.imagePath = imagePath;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getMenuDescription() {
		return menuDescription;
	}

	public void setMenuDescription(String menuDescription) {
		this.menuDescription = menuDescription;
	}
	
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Override
	public String toString() {
		return "CartItem [menuId=" + menuId + ", restaurantId=" + restaurantId + ", name=" + name + ", price=" + price
				+ ", quantity=" + quantity + ", menuDescription=" + menuDescription + ", imagePath=" + imagePath + "]";
	}
	
	
}
