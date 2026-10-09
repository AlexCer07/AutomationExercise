package TestComponents;

import AbstractElements.AdHandler;
import PagesObjects.HomePage;
import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import Listeners.AdListener;
import org.openqa.selenium.support.events.EventFiringDecorator;
import java.util.Locale;

public class BaseTest {

    @Getter
    public WebDriver driver;
    public HomePage homePage;
    public AdHandler adHandler;
    private static final Logger log = LogManager.getLogger(BaseTest.class);
    public String downloadPath = System.getProperty("user.home") + File.separator + "Downloads";

    public WebDriver initializeDriver() throws IOException {

        Properties prop = new Properties();

        String propertiesPath = System.getProperty("user.dir")
                + "/src/main/java/Resources/GlobalData.properties";

        try (FileInputStream fis = new FileInputStream(propertiesPath)) {
            prop.load(fis);
        }

        // Permite seleccionar el navegador desde Maven/IntelliJ
        // o desde GlobalData.properties
        String browserName = System.getProperty(
                "browser",
                prop.getProperty("browser", "chrome")
        ).trim().toLowerCase(Locale.ROOT);

        log.info("Navegador configurado: {}", browserName);

        WebDriver originalDriver = switch (browserName) {
            case "chrome" -> initializeChrome();
            case "firefox" -> initializeFirefox();
            default -> throw new IllegalArgumentException("Navegador no soportado: " + browserName);
        };

        // Configuración del listener para anuncios
        AdHandler listenerAdHandler = new AdHandler(originalDriver);

        AdListener adListener = new AdListener(listenerAdHandler);

        driver = new EventFiringDecorator<>(adListener).decorate(originalDriver);

        driver.manage().window().maximize();

        log.info("WebDriver inicializado correctamente: {}", browserName);

        return driver;
    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        String screenshotName = testCaseName + ".png";

        File destination = new File(
                System.getProperty("user.dir") + "/reports/" + screenshotName);

        FileUtils.copyFile(source, destination);

        return screenshotName;
    }

    private WebDriver initializeChrome() {

        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();

        // Configuración de descargas
        prefs.put("download.default_directory", downloadPath);
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);

        options.setExperimentalOption("prefs", prefs);
        log.info("Inicializando Google Chrome");

        return new ChromeDriver(options);
    }

    private WebDriver initializeFirefox() {

        FirefoxOptions options = new FirefoxOptions();

        // Carpeta de descargas
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

        // Descargar archivos sin mostrar confirmación
        options.addPreference(
                "browser.helperApps.neverAsk.saveToDisk",
                "text/plain,application/octet-stream"
        );

        log.info("Inicializando Mozilla Firefox");

        return new FirefoxDriver(options);
    }

    @BeforeMethod (alwaysRun = true)
    public HomePage launchApplication() throws IOException {

        log.info("Iniciando navegador");
        driver = initializeDriver();
        homePage = new HomePage(driver);

        //Cerrar anuncios al iniciar cada test
        adHandler = new AdHandler(driver);

        homePage.goTo();

        return homePage;
    }


    @AfterMethod (alwaysRun = true)
    public void tearDown(){
        log.info("Se cierra el navegador");
        driver.quit();
    }
}
