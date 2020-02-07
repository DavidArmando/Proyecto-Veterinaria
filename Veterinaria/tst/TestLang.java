import java.io.IOException;

import general.HandlerLanguage;

public class TestLang {
	public static void main(String[] args) {
		try {
			HandlerLanguage.getInstance().loadLanguage("US");
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		System.out.println(HandlerLanguage.getInstance().getValue("Admin"));
		try {
			HandlerLanguage.getInstance().loadLanguage("ES");
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		System.out.println(HandlerLanguage.getInstance().getValue("Admin"));
	}
}