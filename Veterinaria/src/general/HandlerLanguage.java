package general;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class HandlerLanguage {
	private static HandlerLanguage handlerLanguage;
	public String fileName;
	public static String language = "undefined";
	public static HandlerProperties languageProperties;
	
	private HandlerLanguage(){
	}
	
	public void loadLanguage(String fileName) throws IOException{	
		this.fileName = "language/language"+fileName+".properties";
		languageProperties = new HandlerProperties(this.fileName);
		languageProperties.load();
	}
	
	public void saveLanguage() throws IOException{
		HandlerProperties handlerproperties = new HandlerProperties(fileName);
		handlerproperties.load();
		handlerproperties.setProperty("Language", language);
		handlerproperties.save();
	}	
	
	public String getValue(String key) {
		return languageProperties.getProperty(key);
	}
	
	public static HandlerLanguage getInstance() {
		if(handlerLanguage==null)
			handlerLanguage=new HandlerLanguage();
		return handlerLanguage;
	}
}