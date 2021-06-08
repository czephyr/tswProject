package Beans;

import java.util.ArrayList;
import java.time.LocalDate;

public class Order {

	private int orderID;
	private int user_id;
	private ArrayList<Product> orderProducts;
	private LocalDate orderDate;
	private Double orderTotal;

	public Double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(Double total) {
		this.orderTotal = total;
	}

	public void addProduct(Product product){
		this.orderProducts.add(product);
	}

	public Order(int user_id, ArrayList<Product> orderProducts, LocalDate orderDate) {
		this.user_id = user_id;
		this.orderProducts = orderProducts;
		this.orderDate = orderDate;
	}

	public Order() {
		this.orderProducts = new ArrayList<>();
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

	public void setOrderProducts(ArrayList<Product> orderProducts) {
		this.orderProducts = orderProducts;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
}
