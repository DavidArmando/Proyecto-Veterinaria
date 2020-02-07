package model;

import java.util.Date;

public class Consult {
	private Date date;
	private Pet pet;
	private Administrator admin;
	private String process;
	private String diagnosis;
	private String prescription;

	public Consult(Date date, Pet pet, String process, String diagnosis, String prescription) {
		this.date = date;
		this.pet = pet;
		this.process = process;
		this.diagnosis = diagnosis;
		this.prescription = prescription;
	}

	public Date getDate() {
		return date;
	}

	public Pet getPet() {
		return pet;
	}

	public Administrator getAdministrator() {
		return admin;
	}

	public String getProcess() {
		return process;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public String getPrescription() {
		return prescription;
	}

	@Override
	public String toString() {
		return "\nConsulta: Fecha: " + date  + ", Veterinario: " + admin;
	}

	
}
