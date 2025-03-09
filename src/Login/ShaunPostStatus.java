package Login;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ShaunPostStatus{
    private WebDriver driver;
    
    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", Util.CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://stage.moosocial.com/php81/nhan/shaun1n31/");
    }
    
	 @Test(description = "T1: Verify login succcess") 
	 public void LoginSuccess() throws InterruptedException {
	 
	 //input email and password
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Email']")));
		 emailField.sendKeys(Login.Util.WRONGPASSWORD); 
		//input[@placeholder='Email']
	     //usernameField.sendKeys(Util.USERNAME);
		 WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"right\"]/div/div/div[2]/div/input")));
		 passField.sendKeys(Util.PASSWORD);
	 
	 //click login button
		 driver.findElement(By.xpath("//*[@id=\"right\"]/div/div/button")).click();
	 //waiting 2 seconds
		 //Thread.sleep(4000); 
		 
	 //expected title
		 String expectedTitle = "Home Page | Shaun platform demo Dec 03, 2024 Zoldyck 1004 Angel Demon";
		 new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.titleIs(expectedTitle));
	 //get actualtitle and compare it
		 String actualTitle = driver.getTitle();
		 Assert.assertEquals(actualTitle, expectedTitle);
	 }
	
	 @Test(description = "T2: Login wrong pass") 
	 public void LoginWrongpass() throws InterruptedException {
	 
		 //input email and password
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='right']/div/div/div[1]/div/input")));
		 emailField.sendKeys(Login.Util.WRONGPASSWORD); 
		 WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"right\"]/div/div/div[2]/div/input")));
		 passField.sendKeys(Login.Util.WRONGPASSWORD);
		 driver.findElement(By.xpath("//*[@id=\"right\"]/div/div/button")).click();
		 
		 //waiting 2 seconds
		 Thread.sleep(2000); 
		 //get error
		 String expectedError = "Your email or password was incorrect.";
		 
		 WebElement errorMessage = driver.findElement(By.cssSelector("div.p-toast-detail"));
		 String actualError = errorMessage.getText();
		 Assert.assertEquals(actualError,expectedError); 
	}
	 @Test(description = "T3: login with blank fields")
	 public void loginWithBlankfields() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='right']/div/div/div[1]/div/input")));
        emailField.clear();
      
        WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"right\"]/div/div/div[2]/div/input")));
        passField.clear();
        //click login button
        driver.findElement(By.xpath("//*[@id=\"right\"]/div/div/button")).click();
        //waiting 2 seconds
        Thread.sleep(2000);
        
        //get error
    	String expectedError1 = "The email is required.";
    	String expectedError2 = "The password is required.";
    	//get actualtitle and compare it
    	String actualError1 = driver.findElement(By.xpath("//*[@id=\"right\"]/div/div/div[1]/small")).getText();
	    Assert.assertEquals(actualError1, expectedError1);
	    
    	String actualError2 = driver.findElement(By.xpath("//*[@id='right']/div/div/div[2]/small")).getText();
	    Assert.assertEquals(actualError2, expectedError2);
	}
	
    
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}