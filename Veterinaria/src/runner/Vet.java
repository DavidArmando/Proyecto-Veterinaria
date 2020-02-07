package runner;

import java.io.IOException;

import controller.Controller;
import general.HandlerLanguage;
import view.MyFrame;

public class Vet {
	public static void main(String[] args) {
		new Controller();
		try {
			HandlerLanguage.getInstance().loadLanguage("ES");
		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
		new MyFrame().startFrame();
	}
}