package Cucumber;

import java.io.File;

import org.apache.commons.lang.SystemUtils;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import controllers.Application;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CucumberSteps {

	private static FirefoxDriver driver;
	
	public static void setUp() {
		FirefoxBinary ffBinary;
//        if (SystemUtils.IS_OS_WINDOWS) {
//            ffBinary = new FirefoxBinary(new File("FirefoxWindows\\FirefoxPortable.exe"));
//        } else {
            ffBinary = new FirefoxBinary();
//        }
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        driver = new FirefoxDriver(ffBinary, firefoxProfile);
		
	}

	public static void tearDown() {
		driver.quit();
	}
	
	@After
    public void cleanCookies() {
        driver.manage().deleteAllCookies();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	
	@Given("^the admin navigates to \"([^\"]*)\"$")
	public void theUserNavigatesTo(String url) throws Throwable {
		driver.get(url);
	}

	@When("^the user introduces username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void theUserIntroducesUsernameAndPassword(String username, String password) throws Throwable {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys(username);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("login")).click();
	}

	@Then("^the login fails$")
	public void theLoginFails() throws Throwable {
		assertTrue(driver.getCurrentUrl().contains("/error"));
	}

	@Then("^the user successfully logs in$")
	public void theUserSuccessfullyLogsIn() throws Throwable {
		assertTrue(driver.getCurrentUrl().contains("/Admin/home"));
	}
	
	

}
