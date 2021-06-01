package Beans;

import java.util.ArrayList;

public class Cart {

	private int cartID;
	private int user_id;
	private ArrayList<Product> cartProducts;

	public Cart(int user_id) {
		this.user_id = user_id;
	}

	public Cart() {
	}

	public int getCartID() {
		return cartID;
	}

	public void setCartID(int cartID) {
		this.cartID = cartID;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public ArrayList<Product> getCartProducts() {
		return cartProducts;
	}

	public void setCartProducts(ArrayList<Product> products) {
		this.cartProducts = products;
	}

	public Double getCartTotal(){
		//to be implemented with sum of products list prices
		return 0.00;
	}
}
