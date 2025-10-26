package TestComponents;

import AbstractElements.AbstractElements;
import PagesObjects.HomePage;
import org.openqa.selenium.JavascriptExecutor;
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

    public boolean checkPageLoad(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean pageLoaded = false;

        for (int i = 0; i < 10; i++) { // Reintenta por unos segundos
            String readyState = js.executeScript("return document.readyState").toString();
            if (readyState.equals("complete")) {
                pageLoaded = true;
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return pageLoaded;
    }


    @BeforeMethod (alwaysRun = true)
    public HomePage launchApplication(){
        driver = initioalizeDriver();
        homePage = new HomePage(driver);
        homePage.goTo();

        int count = 1;
        while (!checkPageLoad()){
            if (count == 3){
                System.exit(1);
            }
            count++;
        }

        return homePage;
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown(){
        driver.close();
    }
}
