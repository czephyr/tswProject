package DataAccess;

import Beans.AccountSession;
import Beans.Product;
import Beans.User;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDao extends DAO{

	public ArrayList<Product> getAllProducts() throws SQLException {
		startConnection();
		try(Connection conn = getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * from product")) {
				//ps.setInt(1, 1);
				ResultSet set = ps.executeQuery();
				ArrayList<Product> products = new ArrayList<>();
				while (set.next()) {
					Product product = new Product();
					product.setProductID(set.getInt("productID"));
					product.setProductName(set.getString("productName"));
					product.setProductPrice(set.getDouble("productPrice"));
					product.setProductQuantity(set.getInt("productQuantity"));
					product.setProductText(set.getString("productText"));
					product.setProductCategory(set.getString("productCategory"));
					product.setProductImg(set.getString("productImg"));
					products.add(product);
				}
				return products;
			}
		}
	}

	public boolean createProduct(String productName, Double productPrice, String productText, int productQuantity, String productCategory) throws SQLException, NoSuchAlgorithmException {
		int affected = 0;
		startConnection();
		try (Connection conn = getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("INSERT INTO product (productName,productPrice,productText,productQuantity, productImg, productCategory) values (?, ?, ?, ?, ?, ?);")) {
				ps.setString(1, productName);
				ps.setDouble(2, productPrice);
				ps.setString(3, productText);
				ps.setInt(4, productQuantity);
				ps.setString(5, "/images/550ngrey.png");
				ps.setString(6, productCategory);
				affected = ps.executeUpdate();
			}
		}
		return affected > 0 ;
	}

	public Product findByID(int productID) throws SQLException {
		Product product = new Product();
		startConnection();
		try (Connection conn = getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * from product WHERE productID=?")) {
				ps.setInt(1, productID);
				ResultSet set = ps.executeQuery();
				while (set.next()) {
					product.setProductID(set.getInt("productID"));
					product.setProductName(set.getString("productName"));
					product.setProductPrice(set.getDouble("productPrice"));
					product.setProductQuantity(set.getInt("productQuantity"));
					product.setProductText(set.getString("productText"));
					product.setProductCategory(set.getString("productCategory"));
					product.setProductImg(set.getString("productImg"));
				}
				return product;
			}
		}
	}

	public Boolean deleteProductByID(int productID) throws SQLException {
		int affectedRows = 0;
		startConnection();
		try (Connection conn = getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("DELETE FROM product WHERE productID=?")) {
				ps.setInt(1, productID);
				affectedRows = ps.executeUpdate();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
			return affectedRows > 0;
		}
	}

	public ArrayList<Product> find3mostQuantity() throws SQLException {
		ArrayList<Product> threeProducts = new ArrayList<>();
		startConnection();
		try (Connection conn = getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM product ORDER BY RAND() LIMIT 3")) {
				ResultSet set = ps.executeQuery();
				while (set.next()) {
					Product usableProduct = new Product();
					usableProduct.setProductID(set.getInt("productID"));
					usableProduct.setProductName(set.getString("productName"));
					usableProduct.setProductPrice(set.getDouble("productPrice"));
					usableProduct.setProductText(set.getString("productText"));
					usableProduct.setProductQuantity(set.getInt("productQuantity"));
					usableProduct.setProductImg(set.getString("productImg"));
					usableProduct.setProductCategory(set.getString("productCategory"));
					threeProducts.add(usableProduct);
				}
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		return threeProducts;
	}

	public ArrayList<Product> getAllSearch(String query, String orderBy) throws SQLException {
		PreparedStatement ps = null;
		startConnection();
		try(Connection conn = getConnection()) {
			if(query.equals("")) {
				switch (orderBy) {
					case "lowPrice": {
						 ps = conn.prepareStatement("SELECT * from product ORDER BY productPrice ASC");
						break;
					}
					case "highPrice": {
						 ps = conn.prepareStatement("SELECT * from product ORDER BY productPrice DESC");
						break;
					}
					case "alphabetical": {
						 ps = conn.prepareStatement("SELECT * from product");
						break;
					}
				}
			}else{
				switch(orderBy) {
					case "lowPrice": {
						 ps = conn.prepareStatement("SELECT * from product WHERE productName LIKE '%"+query+"%' ORDER BY productPrice ASC");
						break;
					}
					case "highPrice": {
						 ps = conn.prepareStatement("SELECT * from product WHERE productName LIKE '%"+query+"%' ORDER BY productPrice DESC");
						break;
					}
					case "alphabetical": {
						 ps = conn.prepareStatement("SELECT * from product WHERE productName LIKE '%"+query+"%'");
						break;
					}
				}
			}

			assert ps != null;
			ResultSet set = ps.executeQuery();
				ArrayList<Product> products = new ArrayList<>();

					while (set.next()) {
					Product product = new Product();
					product.setProductID(set.getInt("productID"));
					product.setProductName(set.getString("productName"));
					product.setProductPrice(set.getDouble("productPrice"));
					product.setProductQuantity(set.getInt("productQuantity"));
					product.setProductText(set.getString("productText"));
					product.setProductCategory(set.getString("productCategory"));
					product.setProductImg(set.getString("productImg"));
					products.add(product);
				}
					closeConnection();
				return products;
		}
	}
}
