package model;

import java.util.ArrayList;

public class History {

	private ArrayList<Consult> consults;
	private Pet pet;
	
	
	public History(Pet pet) {
		this.consults = new ArrayList<Consult>();
		this.pet = pet;
	}
	
	public void addConsult(Consult consult) {
		consults.add(consult);
	}
	
	public ArrayList<Consult> getConsults() {
		return consults;
	}
	
	public Pet getPet() {
		return pet;
	}

	@Override
	public String toString() {
		return "\nHistoria: \nConsultas: " + consults + ", \n" + pet + "\n";
	}
}