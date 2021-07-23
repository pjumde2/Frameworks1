package stepDefinition;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import driver.DriverFactory;
import driver.driverNames;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pom.Homepage;
import utility.Generic;

public class Reg {

	private WebDriver driver;
	private Generic method;
	private Homepage homepage;

	@BeforeClass
	public void setup() {

	}

	@Given("I open chrome browser")
	public void i_open_chrome_browser() {

		driver = DriverFactory.getDriver(driverNames.CHROME);
	}

	@Given("I open website1")
	public void i_open_website1() {

		method = new Generic(driver);
		method.LaunchPage(driver);
		homepage = new Homepage(driver);
	}

	

	@When("I enter First name as {string}")
	public void i_enter_first_name_as(String string) {

		method.enterText(driver, homepage.Fname, string);
	}

	@When("I enter Last name as {string}")
	public void i_enter_last_name_as(String string) {

		method.enterText(driver, homepage.Lname, string);
	}

	@When("I enter Address as {string}")
	public void i_enter_address_as(String string) {

		method.enterText(driver, homepage.Address, string);
	}

	@When("I enter Email as {string}")
	public void i_enter_email_as(String string) {

		method.enterText(driver, homepage.Email, string);
	}

	@When("I enter Phone Number as {string}")
	public void i_enter_phone_number_as(String string) {

		method.enterText(driver, homepage.Phone, string);

	}

	@When("click on LogOn button")
	public void click_on_log_on_button() {
		method.clickOnElement(driver, homepage.Submit);

	}

}
