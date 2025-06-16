package com.tap.model;

public class Menu {
  private int menuId;
  private int restaurantId;
  private String itemName;
  private String description;
  private int price;
  private String isAvailable;
  private int ratings;
  private String imagePath;
  
  public Menu() {
  }

public Menu(int menuId, int restaurantId, String itemName, String description, int price, String isAvailable,
		int ratings, String imagePath) {
	this.menuId = menuId;
	this.restaurantId = restaurantId;
	this.itemName = itemName;
	this.description = description;
	this.price = price;
	this.isAvailable = isAvailable;
	this.ratings = ratings;
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

public String getItemName() {
	return itemName;
}

public void setItemName(String itemName) {
	this.itemName = itemName;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public int getPrice() {
	return price;
}

public void setPrice(int price) {
	this.price = price;
}

public String getIsAvailable() {
	return isAvailable;
}

public void setIsAvailable(String isAvailable) {
	this.isAvailable = isAvailable;
}

public int getRatings() {
	return ratings;
}

public void setRatings(int ratings) {
	this.ratings = ratings;
}

public String getImagePath() {
	return imagePath;
}

public void setImagePath(String imagePath) {
	this.imagePath = imagePath;
}

@Override
public String toString() {
	return "Menu [menuId=" + menuId + ", restaurantId=" + restaurantId + ", itemName=" + itemName + ", description="
			+ description + ", price=" + price + ", isAvailable=" + isAvailable + ", ratings=" + ratings
			+ ", imagePath=" + imagePath + "]";
}
 
}
