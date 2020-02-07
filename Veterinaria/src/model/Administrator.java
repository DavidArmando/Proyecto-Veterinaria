package model;

public class Administrator{

	private String name;
	private String email;
	private String password;

	public Administrator(long id, String photo, String name, long phone, String city, String email,
			String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public Administrator(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String toString() {
		return name;
	}
}