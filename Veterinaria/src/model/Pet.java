package model;

import java.util.Date;

public class Pet {
	
	private String name;
	private String race;
	private int id;
	private String state;
	private Date birthdate;
	
	public Pet(String name, String race, int id, String state, Date birthdate) {
		super();
		this.name = name;
		this.race = race;
		this.id = id;
		this.state = state;
		this.birthdate = birthdate;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public String getRace() {
		return race;
	}
	public int getId() {
		return id;
	}
	public String getState() {
		return state;
	}
	public Date getBirthdate() {
		return birthdate;
	}

	@Override
	public String toString() {
		return "Mascota: nombre: " + name + ", raza: " + race + ", id: " + id + ", estado: " + state + ", fecha de nacimiento: " + birthdate
				;
	}
	
	
}
