package DataAccess;

import Beans.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDao extends DAO{

	//to be implemented
	public ArrayList<User> getAllUsers() throws SQLException {
		startConnection();
		try(Connection conn = getConnection()){
			try(PreparedStatement ps = conn.prepareStatement("SELECT * from user")){
				//ps.setInt(1, 1);
				ResultSet set = ps.executeQuery();
				ArrayList<User> users = new ArrayList<>();
				while(set.next()){
					User account = new User();
					account.setUserName(set.getString("userName"));
					users.add(account);
				}
				closeConnection();
				return users;
			}
		}

	}
}



