package commonclasses;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;



import Driver.LaunchApplication;

public class TakeScreenShot extends LaunchApplication {

	static String filename="";
	
public static void	TakecreenShotMethod(String Method)
{
	try
	{
		//String screenshot="C://Pramod_IBM5//Pramod_Barclay//Report//Screenshot//";
		Calendar currentDate= Calendar.getInstance();
		SimpleDateFormat formatter=new SimpleDateFormat("YYYY_MMM_dd_HH_mm_ss");
		String dateNow=formatter.format(currentDate.getTime());
		filename=screenshot + Method+dateNow+".jpg";
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(filename));
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}

}
