package scripts;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import Driver.LaunchApplication;
import Driver.ReadExcel;
import commonclasses.PropertyRead;
import commonclasses.TakeScreenShot;
import objectRepository.Reg;

public class Registration extends LaunchApplication {
	
	public static String registration(HashMap<String, String> hm)
	
	{

		try{	
						
			System.out.println("values in HashMap: "+hm);
			String ActualResult="";
			String ExpectedResult="";
			String TestCaseId=hm.get("TC_ID").toString();
			String TestCaseDesc=hm.get("TC_Desc").toString();
			String classname=hm.get("ClassName").toString();
			String return_result=null;	
			String screenshot="Registration";
			screenshot=screenshot+TestCaseId;
			
			driver.get(PropertyRead.TestURL);
			driver.manage().timeouts().implicitlyWait(05, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			
			driver.findElement(By.xpath(Reg.Fname)).sendKeys(hm.get("First_Name"));
			//driver.findElement(By.xpath(OR.getProperty("firstname"))).sendKeys("Amit");
			driver.findElement(By.xpath("//div/input[@placeholder='Last Name']")).sendKeys(hm.get("Last_Name"));
			driver.findElement(By.xpath("//div/textarea[@ng-model='Adress']")).sendKeys(hm.get("Address"));
			driver.findElement(By.xpath("//div/input[@ng-model='EmailAdress']")).sendKeys(hm.get("Email"));
			driver.findElement(By.xpath("//input[@ng-model='Phone']")).sendKeys(hm.get("Phone"));
			
		    
			ExpectedResult="Submit";
			ActualResult=driver.findElement(By.xpath("//div/button[@id='submitbtn']")).getText();
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
