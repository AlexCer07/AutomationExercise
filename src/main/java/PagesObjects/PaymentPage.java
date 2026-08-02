package PagesObjects;

import AbstractElements.AbstractElements;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

public class PaymentPage extends AbstractElements {

    WebDriver driver;

    public PaymentPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

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

    public String fillFormPayment(Map<String,String> payment) {

        nameOnCardField.sendKeys(payment.get("name"));
        cardNumberField.sendKeys(payment.get("cardNumber"));
        cvcField.sendKeys(payment.get("cvv"));
        expiryMonthField.sendKeys(payment.get("expiryMonth"));
        expiryYear.sendKeys(payment.get("expiryYear"));

        payAndConfirmOrderButton.click();

        return orderPlacedMsg.getText();
    }

    public void downloadInvoice(){
        downloadInvoiceBtn.click();
    }
    public String continueShop(){
        continueBtn.click();
        waitForUrlBe("https://automationexercise.com/");

        return driver.getCurrentUrl();
    }


}
