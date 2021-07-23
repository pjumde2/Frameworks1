package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;

public class Generic {
	
	//builds a new report using the html template
	public static ExtentHtmlReporter htmlReporter;
	public   static ExtentReports extent;
	public   static ExtentTest test;//helps to generate the logs in test report.
	    public String ScreenShotPath;
	    
	    private WebDriverWait wait;
	    
	    public static void startTest()
	    {
	    	//initialize ExtentReports and attach the HtmlReporter
	    	 htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/testReport.html");
	    	 extent = new ExtentReports();
	    	 extent.attachReporter(htmlReporter);
	    	
	    	
	    	
	    	
	         //configuration items to change the look and feel
	         //add content, manage tests etc
	         htmlReporter.config().setDocumentTitle("Extent Report Demo");
	         htmlReporter.config().setReportName("Test Report");
	         htmlReporter.config().setTheme(Theme.STANDARD);
	         htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	    	
	    	
	    	
	    }


	public Generic(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
		
		startTest();
		
				
	}
	
	private FileInputStream instream;
	private static Properties prop;
	public String getproperty(String key)
	{
		String value=null;
		try {
			instream = new FileInputStream(Constant.propertyfilepath);
			prop= new Properties();
			prop.load(instream);
			value=prop.getProperty(key);
			
			
			
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return value;
	}
	
	public void LaunchPage(WebDriver driver)
	{
		driver.get(getproperty("URL"));
		
		waitForPageToLoad(driver);
		
		takeScreenShot(driver);
	}
	
	public static void waitForPageToLoad(WebDriver driver)
	{
		wait(1);
		  String state = (String) ((JavascriptExecutor) driver).executeScript("return document.readyState");
		  int totalTime = 0;
		  while (!state.equals("complete")) {
		   wait(1);
		   state = (String) ((JavascriptExecutor) driver).executeScript("return document.readyState");
		   totalTime++;
		   if (totalTime > Constant.shortwait)
					
		   test.log(Status.FAIL, "Page is not loaded successfully after waiting for long time.");
		  }
	}
	
	public static void wait(int timeToWaitInSec) {
		  try {
		   Thread.sleep(timeToWaitInSec * 1000);
		  } catch (InterruptedException e) {
		   e.printStackTrace();
		  }
		 }
	
	public void takeScreenShot(WebDriver driver) {
		if (driver != null) {
			Date date = new Date();
			String todaysFolder = createFolder(System.getProperty("user.dir") + "//target", date);
			createFolder(todaysFolder);
			String screenshotFile = date.toString().replace(":", "_").replace(" ", "_") + ".png";
			String filePath = todaysFolder + File.separator + screenshotFile;
			String screenPath = "." + File.separator + "Screenshots" + File.separator + screenshotFile;
			try {
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(filePath));
				// test.addScreenCaptureFromPath(screenPath, screenPath);
				ScreenShotPath = screenPath;
			} catch (Exception e) {
				test.log(Status.FAIL, "Got an exception while capturing the screenshot");
			}
		} else
			throw new Error("Either driver or report object is 'Null'");
	}
	public static String createFolder(String folderPath, Date date1) {
		String folderName;
		SimpleDateFormat sdtf = new SimpleDateFormat("dd-MMM-YY");
		folderName = folderPath + File.separator + sdtf.format(date1);
		// create a directory in the path
		createFolder(folderName);
		return folderName;
	}
	public static void createFolder(String folderPath) {
		try {
			File file = new File(folderPath);
			if (!file.exists())
				file.mkdir();
		} catch (Exception e) {
		}
	}
	  
	
	
	public void enterText(WebDriver driver, WebElement element, String value)
	{
		try
		{
		  scrollToElement(driver, element);
		  element.sendKeys(Keys.chord(Keys.CONTROL,"a"));
		  element.sendKeys(Keys.DELETE);
		  element.sendKeys(value);
		  takeScreenShot(driver);
		}catch(Exception e)
		{
			test.log(Status.FAIL,"Exception occured while entering Text in Field"+element.toString() +"Exception Description: "+e.getMessage());
			
		}
		
		
	}
	
	public void scrollToElement(WebDriver driver, WebElement element)
	{
		try
		{
			if(isElementPresent(driver, element))
			{
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			}
			else {
				
				test.log(Status.FAIL, "Unable To ScrollElement"+element.toString());
			}
		}catch(Exception e)
		{
			
			test.log(Status.FAIL, "Unable To ScrollElement"+element.toString());
		}
	}
	
    public boolean isElementPresent(WebDriver driver, By by)
    {
    	WebElement element=null;
    	
    	try
    	{
    		element=driver.findElement(by);
    	}catch(NoSuchElementException elementNotFound)
    	{
    		return false;
    	}
    	
    	return isElementPresent(driver,element);
    }
    
    public boolean isElementPresent(WebDriver driver, WebElement expectedElement)
    {
    	boolean isPresent=false;
    	try
    	{
    		if (expectedElement.isDisplayed() || expectedElement.isEnabled())
    		{
    			isPresent=true;
    		}
    		else {
    			takeScreenShot(driver);
    		   test.warning("WarningMessage: Element is not Present. Trying to find element."+expectedElement.toString());
    		}
    	}catch(Exception exception)
    	{
    		takeScreenShot(driver);
    		test.warning("Got an Exception "+exception.getMessage()+"Element is not Present. Trying to find element. " +expectedElement.toString());
    		return isPresent;
    	}
    	
    	return isPresent;
    }
    
    public void clickOnElement(WebDriver driver, WebElement elementToClick)
    {
        
    	
    	if(isElementPresent(driver, elementToClick))
    	{
    		
    		if(waitUntilElementIsClickable(driver, elementToClick, Constant.shortwait))
    		{
    			test.info("Element found and Clickable"+elementToClick.toString());
    			clickWithJavaScript(driver, elementToClick);
    		
    			
    		}else
    		{
    			test.log(Status.FAIL, "Element was found and Displayed but not Clickable "+elementToClick.toString());
    			takeScreenShot(driver);
    		}
    		
    	}else
    	{
    		test.log(Status.FAIL, "Element was not found.Hence Unable to click "+elementToClick.toString());
    		takeScreenShot(driver);
    	}
    }
    
    public boolean waitUntilElementIsClickable(WebDriver driver, WebElement element, int waitTime)
    {
    	try
    	{
    		wait=new WebDriverWait(driver, waitTime);
    		wait.until(ExpectedConditions.elementToBeClickable(element));
    		return true;
    	}catch(TimeoutException e)
    	{
    		return false;
    	}
    }
    
    
    public void clickWithJavaScript(WebDriver driver, WebElement element)
    {
    	scrollToElement(driver, element);
    	waitUntilElementIsVisible(driver, element, Constant.shortwait);
    	JavascriptExecutor executer=(JavascriptExecutor)driver;
    	executer.executeScript("arguments[0].click();", element);
    	executer.executeScript("arguments[0].click();");
    	takeScreenShot(driver);
    }
    
    public boolean waitUntilElementIsVisible(WebDriver driver, WebElement element, int waitTime)
    {
    	try
    	{
    		wait=new WebDriverWait(driver, waitTime);
    		wait.until(ExpectedConditions.visibilityOf(element));
    		return true;
    	}catch(TimeoutException e)
    	{
    		return false;
    	}
    }
    
    
    

}
