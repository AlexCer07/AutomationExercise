package PagesObjects;

import AbstractElements.AbstractElements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

public class PaymentPage extends AbstractElements {

    WebDriver driver;

    public PaymentPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //*********Elements*************

    @FindBy(css = "[name = 'name_on_card']")
    WebElement nameOnCardField;

    @FindBy(css = "[name='card_number']")
    WebElement cardNumberField;

    @FindBy(css = "[name = 'cvc']")
    WebElement cvcField;

    @FindBy(css = "[name = 'expiry_month']")
    WebElement expiryMonthField;

    @FindBy(css = "[name = 'expiry_year']")
    WebElement expiryYear;

    @FindBy(id = "submit")
    WebElement payAndConfirmOrderButton;

    @FindBy(xpath = "//h2[@data-qa='order-placed']/following-sibling::p")
    WebElement orderPlacedMsg;

    @FindBy(xpath = "//a[contains(@href,'/download_invoice/')]")
    WebElement downloadInvoiceBtn;

    @FindBy(css = "a[data-qa='continue-button']")
    WebElement continueBtn;

    private final By formPayment = By.id("payment-form");

    //*********Methods***********

    //Ingresa información de pago
    public String fillFormPayment(Map<String,String> payment) {

        log.info("Se procede a llenar los datos de pago");

        waitForWebElementToAppear(formPayment);

        nameOnCardField.sendKeys(payment.get("name"));
        cardNumberField.sendKeys(payment.get("cardNumber"));
        cvcField.sendKeys(payment.get("cvv"));
        expiryMonthField.sendKeys(payment.get("expiryMonth"));
        expiryYear.sendKeys(payment.get("expiryYear"));

        payAndConfirmOrderButton.click();

        return orderPlacedMsg.getText();
    }

    //Se descar el archivo txt con los detalles de la compra
    public void downloadInvoice(){
        downloadInvoiceBtn.click();
        log.info("Se descarga el archivo con los detalles de la compra");
    }

    //Después de pagar se hace clic en el botón para continuar comprando, redirigiendo a la vista home
    public String continueShop(){
        safeClickAndWaitForExactUrl(continueBtn,"https://automationexercise.com/");
        waitForUrlBe("https://automationexercise.com/");

        log.info("Se redirige a la vista home");
        return driver.getCurrentUrl();
    }

    //Espera hasta que aparezca un archivo nuevo en la carpeta de descargas, con un límite de tiempo.
    public File waitForLatestFile(String folderPath, int timeoutSeconds, long startTime) {

        File latestFile;
        int waited = 0;

        while (waited < timeoutSeconds) {
            latestFile = getLatestDownloadedFile(folderPath, startTime);

            if (latestFile != null && !latestFile.getName().endsWith(".crdownload")) {
                return latestFile;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("La espera de descarga fue interrumpida", e);
                return null;
            }

            waited++;
        }

        return null;
    }

    //Devuelve el último archivo de la carpeta de descargas
    public File getLatestDownloadedFile(String folderPath, long startTime) {

        File dir = new File(folderPath);
        File[] files = dir.listFiles();

        if (files == null || files.length == 0) {
            return null;
        }

        return Arrays.stream(files)
                //Descarta archivos incompletos:
                .filter(file -> !file.getName().endsWith(".crdownload") && !file.getName().endsWith(".tmp"))
                .filter(file -> file.lastModified() > startTime)
                .max(Comparator.comparingLong(File::lastModified))
                .orElse(null);
    }
}
