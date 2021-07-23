package commonclasses;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyRead {
	public static String Browsername="";
	public static String TestURL ="";
	public static String Browserexepath="";
	public static void readProperty (){
	Properties prop = new Properties();
	InputStream input = null;

	try {

		input = new FileInputStream("config.properties");

		// load a properties file
		prop.load(input);

		// get the property value and print it out
		Browsername =prop.getProperty("browser");
		System.out.println("Browsername : "+Browsername);
		TestURL=prop.getProperty("url");
		Browserexepath=prop.getProperty("browserpath");

	} catch (IOException ex) {
		ex.printStackTrace();
	} finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
}
