package DataAccess;

import Beans.AccountSession;
import Beans.User;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

public class UserDao extends DAO{

	public ArrayList<User> getAllUsers() throws SQLException {
		ArrayList<User> users = new ArrayList<>();
		startConnection();
		try (Connection conn = getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * from user")) {
				//ps.setInt(1, 1);
				ResultSet set = ps.executeQuery();
				while (set.next()) {
					User account = new User();
					account.setUserID(set.getInt("userID"));
					account.setUserEmail(set.getString("userEmail"));
					account.setUserName(set.getString("userName"));
					account.setUserSurname(set.getString("userSurname"));
					account.setUserIsAdmin(set.getBoolean("userIsAdmin"));
					users.add(account);
				}
			}
			return users;
		}
	}

	public AccountSession findUserForLogin(String email, String password) throws NoSuchAlgorithmException, SQLException {
		User account = new User(email);
		AccountSession currentAccount = new AccountSession();
		account.setUserPassword(password);
		startConnection();
		try (Connection conn = getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM user WHERE (userEmail=? AND userPassword=?)")) {
				ps.setString(1, account.getUserEmail());
				ps.setString(2, account.getUserPassword());
				ResultSet set = ps.executeQuery();
				while (set.next()) {

					currentAccount.setUserID(set.getInt("userID"));
					currentAccount.setUserEmail(set.getString("userEmail"));
					currentAccount.setUserName(set.getString("userName"));
					currentAccount.setUserSurname(set.getString("userSurname"));
					currentAccount.setUserIsAdmin(set.getBoolean("userIsAdmin"));
				}
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		return currentAccount;
	}

	public int createUser(String email, String name, String surname, String password) throws SQLException, NoSuchAlgorithmException {
		User account = new User(email, name, surname, false);
		account.setUserPassword(password);
		int lastInsertId = -1;
		startConnection();
		try (Connection conn = getConnection()) {

			try (PreparedStatement ps = conn.prepareStatement("INSERT INTO user (userEmail,userName,userSurname,userPassword, userIsAdmin) values (?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS)) {
				ps.setString(1, account.getUserEmail());
				ps.setString(2, account.getUserName());
				ps.setString(3, account.getUserSurname());
				ps.setString(4, account.getUserPassword());
				ps.setBoolean(5, account.isUserIsAdmin());
				ps.executeUpdate();

				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					lastInsertId = rs.getInt(1);
				}
			}
		}
		return lastInsertId ;
	}

	public Boolean deleteUserByID(int userID) throws SQLException {
		int affectedRows = 0;
		startConnection();
		try (Connection conn = getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("DELETE FROM user WHERE userID=?")) {
				ps.setInt(1, userID);
				affectedRows = ps.executeUpdate();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
			return affectedRows > 0;
		}
	}

}



