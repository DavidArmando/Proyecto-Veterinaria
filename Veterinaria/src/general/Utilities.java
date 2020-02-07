package general;

import java.util.Date;

public class Utilities {
	public static String convertDate(Date date) {
		return date.getDate()+"/"+date.getMonth()+"/"+date.getYear();
	}
	
	public static Date convertString(String string) {
		return new Date();
	}
}