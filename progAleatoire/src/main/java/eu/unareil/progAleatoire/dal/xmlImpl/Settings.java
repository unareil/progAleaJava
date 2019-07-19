/**
 * 
 */
package eu.unareil.progAleatoire.dal.xmlImpl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Settings {
	private static Properties properties;
	
	static {
			properties = new Properties();
			//properties.load(Settings.class.getResourceAsStream("settings.properties"));
			try {
				properties.loadFromXML(Settings.class.getResourceAsStream("config.xml"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	
	public static String getProperty(String key){
		String parametre = properties.getProperty(key,null);
		return parametre;
	}
	public static void setProperty(String value) throws IOException{
		FileOutputStream fos = new FileOutputStream(Settings.class.getResourceAsStream("config.xml").toString());
		properties.setProperty("urlxml", value);
		properties.storeToXML(fos, "nouvel endroit");
	}

}
