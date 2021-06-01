package Beans;

import java.util.ArrayList;

public class Order {

	private int orderID;
	private int user_id;
	private ArrayList<Product> orderProducts;

	public Order(int user_id) {
		this.user_id = user_id;
	}

	public Order() {
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public ArrayList<Product> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(ArrayList<Product> products) {
		this.orderProducts = products;
	}

	public Double getOrderTotal(){
		//to be implemented with sum of products list prices
		return 0.00;
	}
}
