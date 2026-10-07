package TestComponents;

import AbstractElements.AdHandler;
import PagesObjects.HomePage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


import AbstractElements.AdHandler;
import Listeners.AdListener;
import org.openqa.selenium.support.events.EventFiringDecorator;

public class BaseTest {

    public WebDriver driver;
    public HomePage homePage;
    public AdHandler adHandler;

    private static final Logger log =
            LogManager.getLogger(BaseTest.class);

    public String downloadPath = System.getProperty("user.home") + File.separator + "Downloads";;
    public WebDriver initializeDriver() throws IOException {


        Map<String, Object> prefs = new HashMap<>();



        WebDriver originalDriver;

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\alexc\\Documents\\program projects\\AutomationExercise\\src\\main\\java\\Resources\\GlobalData.properties");
        prop.load(fis);

        String browserName = prop.getProperty("browser");
        log.info("Navegador configurado: " + browserName);
        //****Chrome options***
        //prefs.put("download.default_directory", downloadPath);
        //prefs.put("download.prompt_for_download", false);
        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("user-data-dir=D:/Test-Automation/chromeProfle");
        //options.addArguments("user-data-dir=C:/Users/alexc/Documents/program projects/AutomationExercise/src/test/java/resources/chromeProfle")
        //options.addArguments("profile-directory=Automation");
        //options.setExperimentalOption("prefs", prefs);
        //driver = new ChromeDriver(options);


        FirefoxOptions options = new FirefoxOptions();

        options.addPreference(
                "browser.download.dir",
                downloadPath
        );

        options.addPreference(
                "browser.download.folderList",
                2
        );

        options.addPreference(
                "browser.download.useDownloadDir",
                true
        );

        options.addPreference(
                "browser.helperApps.neverAsk.saveToDisk",
                "text/plain,application/octet-stream"
        );


        originalDriver = new FirefoxDriver();

        AdHandler listenerAdHandler = new AdHandler(originalDriver);
        AdListener adListener = new AdListener(listenerAdHandler);
        driver = new EventFiringDecorator<WebDriver>(adListener).decorate(originalDriver);

        driver.manage().window().maximize();

        return driver;
    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        String screenshotName = testCaseName + ".png";

        File destination = new File(
                System.getProperty("user.dir") + "/reports/" + screenshotName
        );

        FileUtils.copyFile(source, destination);

        return screenshotName;
    }





    @BeforeMethod (alwaysRun = true)
    public HomePage launchApplication() throws IOException {

        log.info("Iniciando navegador");
        driver = initializeDriver();
        homePage = new HomePage(driver);

        // ✅ Cerrar anuncios al iniciar cada test
        adHandler = new AdHandler(driver);
        //adHandler.closeAdsIfPresent();

        homePage.goTo();

        return homePage;
    }


    @AfterMethod (alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
