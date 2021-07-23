package scripts;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import Driver.LaunchApplication;
import Driver.ReadExcel;
import commonclasses.PropertyRead;
import commonclasses.TakeScreenShot;

public class Login1 extends LaunchApplication  {
	
	public static String login1(HashMap<String, String> hm)
	{
		try{	
			
			System.out.println("values in HashMap: "+hm);
			String ActualResult="";
			String ExpectedResult="";
			String TestCaseId=hm.get("TC_ID").toString();
			String TestCaseDesc=hm.get("TC_Desc").toString();
			String classname=hm.get("ClassName").toString();
			String return_result=null;	
			String screenshot="Login1";
			screenshot=screenshot+TestCaseId;
			
			driver.get(PropertyRead.TestURL);
			driver.manage().timeouts().implicitlyWait(05, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			
			driver.findElement(By.xpath("/html/body/header/nav/div/div[2]/ul/li[1]/a")).click();			
			driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(hm.get("EmaiId"));
			driver.findElement(By.xpath("//*[@id=\"enterimg\"]")).click();
			
		    
			ExpectedResult="Register";
			ActualResult=driver.findElement(By.xpath("/html/body/section/div/h2")).getText();
             System.out.println("Message is:"+ActualResult);
			
			if(ActualResult.contains(ExpectedResult))
			{
				return_result="Pass"+","+TestCaseId+","+TestCaseDesc;
				
				System.out.println("Return Result is: "+return_result);
				TakeScreenShot.TakecreenShotMethod(screenshot);
				ReadExcel.Excel_Report_Generation(classname, return_result);
			}
			
			else
			{
				return_result="Fail"+","+TestCaseId+","+TestCaseDesc;
				System.out.println("Return Result is: "+return_result);
				TakeScreenShot.TakecreenShotMethod(screenshot);
				ReadExcel.Excel_Report_Generation(classname, return_result);
			}
		    
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

		
		
		return null;
	}

}
