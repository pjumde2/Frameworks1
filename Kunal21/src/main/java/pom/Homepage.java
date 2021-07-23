package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage {
	
	private WebDriver driver;
    
	public Homepage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div/input[@placeholder='First Name']")
	public WebElement Fname;
	
	@FindBy(xpath = "//div/input[@placeholder='Last Name']")
	public WebElement Lname;
	
	@FindBy(xpath="//div/textarea[@ng-model='Adress']")
	public WebElement Address;
	
	@FindBy(xpath="//div/input[@ng-model='EmailAdress']")
	public WebElement Email;
	
	@FindBy(xpath="//input[@ng-model='Phone']")
	public WebElement Phone;
	
	@FindBy(id="submitbtn")
	public WebElement Submit;
	
	
}
