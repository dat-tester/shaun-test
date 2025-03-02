package Login;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeClass;
import org.openqa.selenium.firefox.FirefoxDriver;

//import java.awt.AWTException;
//import java.awt.Robot;
//import java.awt.Toolkit;
//import java.awt.datatransfer.StringSelection;
//import java.awt.event.KeyEvent;
//import java.time.Duration;

//import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import java.util.Random; 
public class LoginPage {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set the path to the Firefox executable
        System.setProperty("webdriver.gecko.driver", "C:\\selenium webdriver\\geckodriver-v0.35.0-win64\\geckodriver.exe");
        // Initialize the FirefoxDriver
        driver = new FirefoxDriver();
        // Maximize the browser window
        driver.manage().window().maximize();
        driver.get("https://stage.moosocial.com/php81/dat/moo321v7/home");
    }
	
    @Test(description = "T1: Verify user login succcess")
    public void verifyLoginAtLangdingPage() throws InterruptedException {
    	driver.findElement(By.xpath("//*[@id=\"right\"]/div/div/div[1]/div/input")).sendKeys("admin@socialloft.com");
    	
    	driver.findElement(By.xpath("//*[@id=\"right\"]/div/div/div[2]/div/input")).sendKeys("123456");
    	Thread.sleep(2000);
    	driver.findElement(By.xpath("//*[@id=\"right\"]/div/div/button")).click();
    	Thread.sleep(2000);
    	
    	String expectedTitle = "Home Page | moo321v7";
	    String actualTitle = driver.getTitle();
	    Assert.assertEquals(actualTitle, expectedTitle);
	}
	@Test(description = "T2: Login with all blank field")
    public void verifyLoginWithAllBlankField() throws InterruptedException {
    	// click username field
    	driver.findElement(By.xpath("//*[@id=\"login_email\"]")).sendKeys("acc1@aa.com");
    	driver.findElement(By.xpath("//*[@id=\"login_password\"]")).sendKeys("123456");
    	Thread.sleep(2000);
    	driver.findElement(By.xpath("//*[@id=\"login_email\"]")).clear();
    	driver.findElement(By.xpath("//*[@id=\"login_password\"]")).clear();
    	Thread.sleep(2000);
    	driver.findElement(By.xpath("//*[@id=\"UserLoginForm\"]/div[2]/div[2]/input")).click();
    	Thread.sleep(5000);
    	String message = driver.findElement(By.xpath("//*[@id=\"login_email\"]")).getAttribute("validationMessage");
    	System.out.println(message);
    	String expectedMessage = "Vui lòng điền vào trường này.";
    	Assert.assertEquals(message, expectedMessage);
        
        String expectedTitle = "The social network for people with Shared Interests.";
	    String actualTitle = driver.findElement(By.xpath("/html/body/div[3]/div/div/div/div/div/div/div/div[1]/div/div[1]")).getText();
	    Assert.assertEquals(actualTitle, expectedTitle);
	}
	@Test(description = "T3: Login with valid username, blank password")
    public void verifyLoginWithValidUsernameBlankPassword() throws InterruptedException {
    	// click username field
    	driver.findElement(By.xpath("//*[@id=\"login_email\"]")).sendKeys("acc1@aa.com");
//    	driver.findElement(By.xpath("//*[@id=\"login_password\"]")).sendKeys("123456");
    	Thread.sleep(2000);
//    	driver.findElement(By.xpath("//*[@id=\"login_email\"]")).clear();
    	driver.findElement(By.xpath("//*[@id=\"login_password\"]")).clear();
    	Thread.sleep(2000);
    	driver.findElement(By.xpath("//*[@id=\"UserLoginForm\"]/div[2]/div[2]/input")).click();
    	Thread.sleep(2000);
    	String errorMessage = driver.findElement(By.xpath("//*[@id=\"flashMessage\"]")).getText();
    	System.out.println(errorMessage);
    	String expectedMessage = "Your email or password was incorrect.";
    	Assert.assertEquals(errorMessage, expectedMessage);
	}
	@Test(description = "T4: Login with valid username, incorrect password")
    public void verifyLoginWithValidUsernameIncorrectPassword() throws InterruptedException {
    	// click username field
    	driver.findElement(By.xpath("//*[@id=\"login_email\"]")).sendKeys("acc1@aa.com");
    	driver.findElement(By.xpath("//*[@id=\"login_password\"]")).sendKeys("hjbkj6799sad");
    	Thread.sleep(2000);
//    	driver.findElement(By.xpath("//*[@id=\"login_email\"]")).clear();
//    	driver.findElement(By.xpath("//*[@id=\"login_password\"]")).clear();
    	driver.findElement(By.xpath("//*[@id=\"UserLoginForm\"]/div[2]/div[2]/input")).click();
    	Thread.sleep(2000);
    	String errorMessage = driver.findElement(By.xpath("//*[@id=\"flashMessage\"]")).getText();
    	System.out.println(errorMessage);
    	String expectedMessage = "Your email or password was incorrect.";
    	Assert.assertEquals(errorMessage, expectedMessage);
	}
	@Test(description = "T5: verify hide password")
    public void verifyHidePassword() throws InterruptedException {
    	// click username field
//    	driver.findElement(By.xpath("//*[@id=\"login_email\"]")).sendKeys("acc1@aa.com");
    	driver.findElement(By.xpath("//*[@id=\"login_password\"]")).sendKeys("123456");
    	Thread.sleep(2000);
//    	driver.findElement(By.xpath("//*[@id=\"login_email\"]")).clear();
//    	driver.findElement(By.xpath("//*[@id=\"login_password\"]")).clear();
    	driver.findElement(By.xpath("//span[@class='material-symbols-outlined notranslate moo-icon moo-icon-visibility_off icon-hide-password js-eye-show-pass']")).click();
    	Thread.sleep(2000);
    	String style = driver.findElement(By.xpath("//span[@class='material-symbols-outlined notranslate moo-icon moo-icon-visibility_off icon-hide-password js-eye-show-pass']")).getText();
    	System.out.println("T5: show password = " + style);
    	String expectedStyle = "visibility";
    	Assert.assertEquals(style, expectedStyle);
    	Thread.sleep(2000);
    	driver.findElement(By.xpath("//span[@class='material-symbols-outlined notranslate moo-icon moo-icon-visibility_off icon-hide-password js-eye-show-pass']")).click();
    	Thread.sleep(2000);
    	String style1 = driver.findElement(By.xpath("//span[@class='material-symbols-outlined notranslate moo-icon moo-icon-visibility_off icon-hide-password js-eye-show-pass']")).getText();
    	System.out.println("T5: hide password = " + style1);
    	String expectedStyle1 = "visibility_off";
    	Assert.assertEquals(style1, expectedStyle1);
    	Thread.sleep(2000);

	}
	@AfterMethod
    public void afterMethod() { 
        // Clean up after each test method (e.g., close any pop-ups, reset state, etc.)
    	driver.quit();

    }
}