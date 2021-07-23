package Driver;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;
//import org.openqa.selenium.WebDriver;

//import bsh.Capabilities;
//import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;


public class LaunchApplication {

	public static String Sheetname;
	public static WebDriver driver;
	
	public static String screenshot = null;

	public static void main(String[] args) throws IOException {
		
		//System.out.println(args[0]);
		
		//System.out.println(args[1]);
		
		//System.out.println(args[2]);
		String ReportGenPath="C:\\Users\\PramodJumde\\Desktop\\Frameword21\\HybridSel\\Report\\";
		String TestSuitePath="C:\\Users\\PramodJumde\\Desktop\\Frameword21\\HybridSel\\DataSheet.xls";
		
		screenshot = "C:\\Users\\PramodJumde\\Desktop\\Frameword21\\HybridSel\\Screenshots\\";

		commonclasses.Create_Report_Excel createExl = new commonclasses.Create_Report_Excel();
		createExl.Excel_Report_Generation(ReportGenPath);
		ReadExcel read=new ReadExcel();
		read.Readxls("TestSuite", TestSuitePath);
	}
	
	

	public void initialise(String browsername,String browserexepath) {
		/*	if(browsername.equalsIgnoreCase("Phantom"))
		{
			System.setProperty("phantomjs.binary.path", 
					browserexepath);
			driver=new PhantomJSDriver();
			driver.manage().window().setSize(new Dimension(1920, 1080));


			/*Map<String, Object> chromeOptions = new HashMap<String, Object>();
			chromeOptions.put("binary", "/usr/lib/chromium-browser/chromium-browser");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);*/
			//WebDriver driver = new ChromeDriver(capabilities);
			//driver = new HtmlUnitDriver();
			//HtmlUnitDriver driver = new HtmlUnitDriver();
		
		 
			if(browsername.equalsIgnoreCase("InternetExplorer"))
			{
				System.setProperty("webdriver.ie.driver", 
						"C://IEDriverServer.exe");
		//		driver=new InternetExplorerDriver();
			}
			else
				if(browsername.equalsIgnoreCase("Chrome"))
				{
					System.setProperty("webdriver.chrome.driver",browserexepath);
				driver=new ChromeDriver();
				}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void callclass(String classname,String Methodname, HashMap<String, String> hm) throws NoSuchMethodException, SecurityException {
		// TODO Auto-generated method stub

		//String parameter
		Class[] paramHashMap = new Class[1];
		//Class[] paramString = new Class[1];
		paramHashMap[0] = HashMap.class;
	//	paramHashMap[1] = String.class;


		try {
			System.out.println("In callclass");
			Class cls = java.lang.Class.forName("scripts."+classname);
			System.out.println("Methodname: "+Methodname);
			//Class Class=Class.forName("scripts."+classname);
			try {
				Object obj=cls.newInstance();
				//			Class[] Types=new Class[] {HashMap.class, String.class, String.class };
				Method method=cls.getDeclaredMethod(Methodname, paramHashMap);

				//call the printItInt method, pass a int param
				try {
					System.out.println("About to invoke method");
					method.invoke(obj, hm);

					//	Object status=method.invoke(o,hm);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	

}
