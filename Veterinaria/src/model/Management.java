package model;

import java.util.ArrayList;

public class Management {

	private ArrayList<History> histories;
	
	public Management() {
		histories = new ArrayList<History>();
	}
	
	public void addHistory(History history) {
		histories.add(history);
	}
	
	public ArrayList<History> getHistories() {
		return histories;
	}
	
	public void clearList() {
		histories.clear();
	}
}
