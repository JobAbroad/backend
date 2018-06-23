package jobabroad.entity;

public class User {

	private int id;
	private String username;
	private String name;
	private String email;
	private String password;
	private String permission;
	
	public User() {
	}
	public User(int id, String username, String name, String email, String password, String permission) {
		this.setId(id);
		this.setUsername(username);
		this.setName(name);
		this.setEmail(email);
		this.setPassword(password);
		this.setPermission(permission);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	
	
}
