package TestComponents;

import PagesObjects.HomePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    public WebDriver driver;
    public HomePage homePage;


    public WebDriver initioalizeDriver(){

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--no-first-run");
        options.addArguments("--no-default-browser-check");

        options.addArguments("user-data-dir=D:/Test-Automation/chromeProfle");
        options.addArguments("profile-directory=Automation");

        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        return driver;
    }




    @BeforeMethod (alwaysRun = true)
    public HomePage launchApplication(){
        driver = initioalizeDriver();
        homePage = new HomePage(driver);
        homePage.goTo();

        return homePage;
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
