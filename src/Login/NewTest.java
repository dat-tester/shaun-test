package Login;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class NewTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\selenium webdriver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://stage.moosocial.com/php81/nhan/shaun1n31/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(description = "T1: Verify login success") 
    public void loginSuccess() {
        login("admin@socialloft.com", "123456");
        String expectedTitle = "Landing Page | Shaun platform demo Dec 03, 2024 Zoldyck 1004 Angel Demon";
        Assert.assertEquals(driver.getTitle(), expectedTitle);
    }

    @Test(description = "T2: Login with wrong password") 
    public void loginWrongPass() {
        login("admin@socialloft.com", "4321243");
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.p-toast-detail")));
        Assert.assertEquals(errorMessage.getText(), "Your email or password was incorrect.");
    }

    @Test(description = "T3: Login with blank fields")
    public void loginWithBlankFields() {
        clearFields();
        driver.findElement(By.xpath("//*[@id=\"right\"]/div/div/button")).click();

        WebElement emailError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"right\"]/div/div/div[1]/small")));
        WebElement passError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='right']/div/div/div[2]/small")));

        Assert.assertEquals(emailError.getText(), "The email is required.");
        Assert.assertEquals(passError.getText(), "The password is required.");
    }

    private void login(String email, String password) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='right']/div/div/div[1]/div/input")));
        WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='right']/div/div/div[2]/div/input")));

        emailField.clear();
        emailField.sendKeys(email);

        passField.clear();
        passField.sendKeys(password);

        driver.findElement(By.xpath("//*[@id=\"right\"]/div/div/button")).click();
    }

    private void clearFields() {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='right']/div/div/div[1]/div/input")));
        WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='right']/div/div/div[2]/div/input")));

        emailField.clear();
        passField.clear();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
