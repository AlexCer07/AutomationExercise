package PagesObjects;

import AbstractElements.AbstractElements;
import org.openqa.selenium.Alert;
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

    @FindBy (xpath = "//div[@class='contact-form']")
    WebElement contactFrom;

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

    public String fillOutForm(String name, String mail, String subject, String message, String pathFile){

        fieldName.sendKeys(name);
        fieldEmail.sendKeys(mail);
        fieldSubject.sendKeys(subject);
        fieldMessage.sendKeys(message);
        uploadFileButton.sendKeys(pathFile);


        submitButton.click();

        Alert alerta = driver.switchTo().alert();
        alerta.accept();

        waitForWebElementToAppear(successMessage);

        return successMessage.getText();
    }

    public String goHome(){
        homeButton.click();
        waitForUrlBe("https://automationexercise.com/");

        String url = driver.getCurrentUrl();
        return url;
    }

}
