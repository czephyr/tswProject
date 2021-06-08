package Beans;

import java.util.ArrayList;

public class Cart {

	private int cartID;
	private int user_id;
	private ArrayList<Product> cartProducts;

	public Cart(int user_id) {
		this.user_id = user_id;
		cartProducts = new ArrayList<>();
	}

	public Cart() {
		cartProducts = new ArrayList<>();
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

	public void addProduct(Product newProduct){
		cartProducts.add(newProduct);
	}

	public void setCartProducts(ArrayList<Product> products) {
		this.cartProducts = products;
	}

	public Double getCartTotal(){
		double total = 0.00;
		for (Product product: cartProducts) {
			total = total + product.getProductPrice()*product.getProductQuantity();
		}
		return total;
	}
}
