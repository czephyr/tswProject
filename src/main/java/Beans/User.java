package Beans;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {

	private int userID;
	private String userEmail;
	private String userName;
	private String userSurname;
	private String userPassword;
	private boolean userIsAdmin;

	public User(String userEmail, String userName, String userSurname, boolean userIsAdmin) {
		this.userEmail = userEmail;
		this.userName = userName;
		this.userSurname = userSurname;
		this.userIsAdmin = userIsAdmin;
	}

	public User(String userEmail) {
		this.userEmail = userEmail;
	}

	public User() {
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSurname() {
		return userSurname;
	}

	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-512");
		byte[] hashedPwd = digest.digest(userPassword.getBytes(StandardCharsets.UTF_8));
		StringBuilder builder = new StringBuilder();
		for(byte bit : hashedPwd){
			builder.append(String.format("%02x", bit));
		}
		this.userPassword = builder.toString();
	}

	public boolean isUserIsAdmin() {
		return userIsAdmin;
	}

	public void setUserIsAdmin(boolean userIsAdmin) {
		this.userIsAdmin = userIsAdmin;
	}
}
