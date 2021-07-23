package driver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
	
	private static WebDriver driver;
	@SuppressWarnings("deprecation")
	public static WebDriver getDriver(String driverName)
	{
		if(driverName.equals(driverNames.CHROME))
		{
			System.setProperty(Drivers.CHROME, Drivers.CHROME_PATH);
			driver=new ChromeDriver();
		} else if(driverName.equals(driverNames.FIREFOX))
		{
			System.setProperty(Drivers.FIREFOX, Drivers.FIREFOX_PATH);
			driver=new  FirefoxDriver();
		} 
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
			
	}

}
