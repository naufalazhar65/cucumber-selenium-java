package StepDefinitions;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Login {

	WebDriver driver = null;

    @Before
    public void setUp() {
        // Set up Chrome driver and wait times
        System.setProperty("webdriver.chrome.driver", "/Users/naufalazhar/Documents/ChromeDriver/chromedriver");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        // Close the driver after each test case
        driver.quit();
    }
	
	@Given("Open web url {string}")
	public void open_web_url(String url) {
		driver.get(url);
		String ActualTitle = driver.getTitle();
		String ExpectedTitle = "Swag Labs";
		Assert.assertEquals(ExpectedTitle, ActualTitle);
		System.out.println("Page title is : " + driver.getTitle());
		System.out.println( driver.getCurrentUrl());
	}

	@And("Input username {string} Input password {string}")
	public void input_username_Input_password(String username, String password) throws InterruptedException {
		driver.findElement(By.id("user-name")).sendKeys(username);
		Thread.sleep(2000);
		driver.findElement(By.id("password")).sendKeys(password);
		Thread.sleep(2000);
	}

	@When("Click Login button")
	public void click_Login_button() {
		driver.findElement(By.id("login-button")).click();	
	}

	@Then("Should success login and redirected to homepage")
	public void should_success_login_and_redirected_to_homepage() {
		Assert.assertTrue(driver.findElement(By.id("inventory_container")).isDisplayed());
	}

	@Then("Failed login and showing message")
	public void Failed_login_and_showing_message() {
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='error-message-container error']")).isDisplayed());
	}
	
}
