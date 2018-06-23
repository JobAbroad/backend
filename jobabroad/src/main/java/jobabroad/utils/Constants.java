package jobabroad.utils;

public class Constants {

	public static final String USERNAME = "jonatha";
	public static final String PASSWORD = "password";
	public static final String DBUSER = "root";
	public static final String DBPASSWORD = "1234";
	public static final String DBURL ="jdbc:mysql://localhost:3306/library?useTimezone=true&serverTimezone=UTC";

	public static final String USER_ALREADY_EXISTS = "User/E-mail already registered.";
	public static final String USER_CREATED = "User created!";
	public static final String USER_DELETED = "User deleted";
	public static final String USER_UPDATED = "User updated";
	public static final String USER_SAVED = "User saved!";
	
	public static final String ERROR_SAVING_USER = "Error saving user.";
	public static final String ERROR_USER_NOT_FOUND = "Sorry, No user was found.";
	
	public static final String ERROR_UNABLE_CONNECT = "Unable to connect to database";
	
	public static final String PATH_LOGIN = "/login";
	public static final String PATH_USER = "/user";
	public static final String PATH_USER_ID = "/user/:id";
}
