package TestComponents;

import AbstractElements.AdHandler;
import PagesObjects.HomePage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseTest {

    public WebDriver driver;
    public HomePage homePage;
    public AdHandler adHandler;

    //public String downloadPath = System.getProperty("user.dir") + "\\src\\test\\java\\downloads";
    public String downloadPath = "C:\\Users\\alexc\\Downloads";
    public WebDriver initializeDriver(){

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadPath);
        prefs.put("download.prompt_for_download", false);

        ChromeOptions options = new ChromeOptions();

        options.addArguments("user-data-dir=D:/Test-Automation/chromeProfle");
        options.addArguments("profile-directory=Automation");

        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        return driver;
    }





    @BeforeMethod (alwaysRun = true)
    public HomePage launchApplication(){
        driver = initializeDriver();
        homePage = new HomePage(driver);

        homePage.goTo();

        // ✅ Cerrar anuncios al iniciar cada test
        adHandler = new AdHandler(driver);
        adHandler.closeAdsIfPresent();

        return homePage;
    }


    //@AfterMethod (alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
