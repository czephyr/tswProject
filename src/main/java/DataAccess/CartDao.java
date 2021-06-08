package DataAccess;

import Beans.Cart;
import Beans.Product;
import org.javatuples.Pair;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

public class CartDao extends DAO{

	public int createCartByID(int userid) throws SQLException, NoSuchAlgorithmException {
		int lastInsertId = -1;
		startConnection();
		try (Connection conn = getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("INSERT INTO cart (user_id) values (?)",Statement.RETURN_GENERATED_KEYS)){
				ps.setInt(1, userid);
				ps.executeUpdate();

				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					lastInsertId = rs.getInt(1);
				}
			}
		}
		return lastInsertId;
	}

	public Integer checkCartExistance(int userid) throws SQLException {
		int id = -1;
		startConnection();
		try (Connection conn = getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM cart WHERE user_id=?")) {
				ps.setInt(1, userid);
				ResultSet set = ps.executeQuery();
				set.next();
				id = set.getInt("cartID");
			}
			return id;
		}
	}

	public Cart returnQuantityIDInCart(int cartid){
		Cart cart = new Cart(cartid);
		startConnection();
		try(Connection conn = getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT product_id, quantity from product JOIN product_cart pc on product.productID = pc.product_id WHERE cart_id=?")){
				ps.setInt(1, cartid);
				ResultSet set = ps.executeQuery();
				while(set.next()){
					cart.addProduct(new Product(set.getInt("product_id"),set.getInt("quantity")));
				}
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return cart;
	}

	public Cart returnFullCart(int cartid){
		Cart fullCart = new Cart();
		startConnection();
		try(Connection conn = getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT product_id, productName, productPrice, quantity from product JOIN product_cart pc on product.productID = pc.product_id WHERE cart_id=?")){
				ps.setInt(1, cartid);
				ResultSet set = ps.executeQuery();
				while(set.next()){
					Product tempProduct = new Product();
					tempProduct.setProductID(set.getInt("product_id"));
					tempProduct.setProductName(set.getString("productName"));
					tempProduct.setProductPrice(set.getDouble("productPrice"));
					tempProduct.setProductQuantity(set.getInt("quantity"));
					fullCart.addProduct(tempProduct);
				}
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return fullCart;
	}

	public boolean removeFromCartByID(int userid, int productid){
		int affected = 0;
		startConnection();
		try (Connection conn = getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("DELETE product_cart from product_cart JOIN cart c on product_cart.cart_id = c.cartID JOIN user u on c.user_id = u.userID WHERE userID=? AND product_id=?;")){
				ps.setInt(1, userid);
				ps.setInt(2,productid);
				affected = ps.executeUpdate();
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return affected > 0 ;
	}

	public boolean addToCartByID(int productid, int cartid){
		int affected = 0;
		startConnection();
		try (Connection conn = getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("INSERT into product_cart (cart_id, product_id, quantity) values (?,?,?)")){
				ps.setInt(1,cartid);
				ps.setInt(2, productid);
				ps.setInt(3,1);
				affected = ps.executeUpdate();
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return affected > 0 ;
	}

	public boolean itemIsInCart(int productid, int cartid) {
		startConnection();
		try(Connection conn = getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT product_id, cart_id FROM product_cart WHERE product_id=? AND cart_id=?")){
				ps.setInt(1, productid);
				ps.setInt(2,cartid);
				ResultSet set = ps.executeQuery();
				return set.next();
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return false;
	}

	public boolean updateQuantity(int productid, int cartid) {
		startConnection();
		try(Connection conn = getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("UPDATE product_cart set quantity=quantity+1 WHERE product_id=? AND cart_id=?")){
				ps.setInt(1, productid);
				ps.setInt(2,cartid);
				int set = ps.executeUpdate();
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return true;
	}

	public boolean emptyCurrentCart(int userid){
		int affected = 0;
		startConnection();
		try (Connection conn = getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("DELETE product_cart from product_cart JOIN cart c on product_cart.cart_id = c.cartID JOIN user u on c.user_id = u.userID WHERE userID=?")){
				ps.setInt(1, userid);
				affected = ps.executeUpdate();
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return affected > 0 ;
	}
}
