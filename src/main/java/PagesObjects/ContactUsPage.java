package PagesObjects;

import AbstractElements.AbstractElements;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactUsPage extends AbstractElements {

    WebDriver driver;

    public ContactUsPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    //*********Elements***********

    @FindBy(css = "input[name='name']")
    WebElement fieldName;

    @FindBy(css = "input[name='email']")
    WebElement fieldEmail;

    @FindBy(css = "input[name='subject']")
    WebElement fieldSubject;

    @FindBy(css = "textarea[name='message']")
    WebElement fieldMessage;

    @FindBy(css = "input[name='upload_file']")
    WebElement uploadFileButton;

    @FindBy(css = "input[name='submit']")
    WebElement submitButton;

    @FindBy(xpath = "//div[contains(@class, 'alert-success')]")
    WebElement successMessage;

    @FindBy(xpath = "//a[contains(@class, 'btn-success')]")
    WebElement homeButton;

    //***********Methods*************

    //Se ingresan los datos de formulario de contacto
    public void fillOutForm(String name, String mail, String subject, String message, String pathFile) {

        fieldName.sendKeys(name);
        fieldEmail.sendKeys(mail);
        fieldSubject.sendKeys(subject);
        fieldMessage.sendKeys(message);
        uploadFileButton.sendKeys(pathFile);

        log.info("Se llena el formulario de contacto");
        log.debug("name: {}", name);
        log.debug("mail: {}", mail);
        log.debug("subject: {}", subject);
        log.debug("message: {}", message);
        log.debug("pathFile : {}", pathFile);

        submitButton.click();
    }

    //Se obtiene el mensaje de formulario enviado exitosamente
    public String successfullyMessage(){
        waitForWebElementToAppear(successMessage);
        log.info("Se obtiene mensaje de formulario llenado con exito");
        return successMessage.getText();
    }

    //Se hace clic en el botón home
    public String goHome() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", homeButton);
        waitForUrlBe("https://automationexercise.com/");
        return driver.getCurrentUrl();
    }
}
