package com.tap.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
	private static Map<Integer, CartItem> cart;
	
	public Cart() {
	  this.cart = new HashMap<>();  //this way of initialization prevents NullPointerException
	}
	
	public static void addCartItem(CartItem cartItem){		
		int newMenuId = cartItem.getMenuId();
				
		if(cart.containsKey(newMenuId)) {
			
			int newQuantity = cartItem.getQuantity();
			CartItem existingCartItem = cart.get(newMenuId);     //where did we set the key as menuId (doubt)
			int existingQuantity = existingCartItem.getQuantity();   
			
			existingCartItem.setQuantity(newQuantity + existingQuantity);				
		}
		else {
		  cart.put(newMenuId, cartItem);		
		}		
	}
	
    public static void upateCartItem(int menuId, int quantity){
		
    	if(cart.containsKey(menuId)){
    		
    		if(quantity <= 0) {
    			cart.remove(menuId);    			
    		}
    		else {   			
    			CartItem cartItem = cart.get(menuId);
    			cartItem.setQuantity(quantity);   			
    		}
    	}   	 
	}
    
    public static void removeCartItem(int menuId){    	
    	cart.remove(menuId);    	
    }
    
    public static void clearAllCart() {   	
    	cart.clear();   	
    }
    
    public static Map<Integer, CartItem> getItems(){  	
    	return cart;   	
    }
    
    public static double getTotalPrice(Cart cart){   	
    	 double total = 0.0;
    	 
    	 if (cart == null || cart.getItems() == null) {   //not required but just in case (actually this is what total intialization to 0.0 does (above line))
    	        return 0.0;
    	 }
    	
    	 for(CartItem items : cart.getItems().values()) {
    		    		 
    		 total += items.getPrice()*items.getQuantity();
    	 }
    	
		return total;    	
    }   
}
