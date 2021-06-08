package Beans;

public class AccountSession {

	private int userID;
	private String userEmail;
	private String userName;
	private String userSurname;
	private boolean userIsAdmin;

	public AccountSession(String userEmail, String userName, String userSurname, boolean userIsAdmin) {
		this.userEmail = userEmail;
		this.userName = userName;
		this.userSurname = userSurname;
		this.userIsAdmin = userIsAdmin;
	}

	public AccountSession() {
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


	public boolean isUserIsAdmin() {
		return userIsAdmin;
	}

	public void setUserIsAdmin(boolean userIsAdmin) {
		this.userIsAdmin = userIsAdmin;
	}
}
