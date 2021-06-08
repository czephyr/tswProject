package DataAccess;

import Beans.Order;
import Beans.Product;

import java.sql.*;
import java.util.ArrayList;

public class OrderDao extends DAO{


	public ArrayList<Order> returnAllOrders(int user_id){
		ArrayList<Order> orders = new ArrayList<>();
		startConnection();
		try(Connection conn = getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT orderID, orderDate, orderTotal FROM `order` WHERE user_id=?")){
				ps.setInt(1,user_id);
				ResultSet set = ps.executeQuery();
				while(set.next()){
					Order order = new Order();
					order.setOrderID(set.getInt("orderID"));
					order.setUser_id(user_id);
					order.setOrderDate(set.getDate("orderDate").toLocalDate());
					order.setOrderTotal(set.getDouble("orderTotal"));
					orders.add(order);
				}
			}
			for (Order order:orders) {
				try (PreparedStatement ps = conn.prepareStatement("SELECT productName, productPrice, quantity from product_order JOIN product on product_order.product_id = product.productID WHERE order_id=?")){
					ps.setInt(1,order.getOrderID());
					ResultSet set = ps.executeQuery();
					while(set.next()){
						Product product = new Product();
						product.setProductName(set.getString("productName"));
						product.setProductPrice(set.getDouble("productPrice"));
						product.setProductQuantity(set.getInt("quantity"));
						order.addProduct(product);
					}
				}
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return orders;
	}

	public boolean buyFullCart(int cart_id,int user_id, ArrayList<Product> productsToBuy, double orderTotal) throws SQLException {
		int lastInsertId =-1;
		startConnection();
		try (Connection conn = getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("INSERT INTO `order` (user_id,orderDate,orderTotal) values (?,NOW(),?)", Statement.RETURN_GENERATED_KEYS)) {
				ps.setInt(1, user_id);
				ps.setDouble(2, orderTotal);
				ps.executeUpdate();

				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					lastInsertId = rs.getInt(1);
				}
			}
			for (Product product:productsToBuy) {
				try (PreparedStatement ps = conn.prepareStatement("INSERT INTO product_order (order_id,product_id,quantity) values (?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
					ps.setInt(1, lastInsertId);
					ps.setInt(2, product.getProductID());
					ps.setInt(3, product.getProductQuantity());
					ps.executeUpdate();
				}
		}
			return lastInsertId > 0;
	}

}
}

