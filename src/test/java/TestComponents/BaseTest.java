package TestComponents;

import PagesObjects.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    public WebDriver driver;
    public HomePage homePage;

    public WebDriver initioalizeDriver(){

        driver = new ChromeDriver();
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
        driver.close();
    }
}
