package Beans;

public class User {

	private int userID;
	private String userEmail;
	private String userName;
	private String userSurname;
	private String userPassword;

	public User(String userEmail, String userName, String userSurname, String userPassword) {
		this.userEmail = userEmail;
		this.userName = userName;
		this.userSurname = userSurname;
		this.userPassword = userPassword;
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

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}
