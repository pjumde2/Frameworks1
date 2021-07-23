package runnerPackage;

import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterTest;

import io.cucumber.testng.AbstractTestNGCucumberTests;


@CucumberOptions(features = {"src\\test\\resources\\featurePackage"},
glue="src\\test\\java\\stepDefinition",
 dryRun=false)
 public class RunTest extends AbstractTestNGCucumberTests{
	
	@AfterTest
	public void tearDown()
	{
		utility.Generic.extent.flush();
	}
}

