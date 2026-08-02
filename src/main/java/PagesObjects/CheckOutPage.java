package PagesObjects;

import AbstractElements.AbstractElements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

public class CheckOutPage extends AbstractElements {

    WebDriver driver;

    public CheckOutPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(css = "[name= 'message']")
    WebElement msgAboutOrder;

    @FindBy(css = "a[href='/payment']")
    WebElement placeOrderBtn;

    @FindBy(id = "address_delivery")
    WebElement addressDelivery;

    @FindBy(id = "address_invoice")
    WebElement addressBilling;


    By name = By.cssSelector(".address_firstname.address_lastname");
    By company = By.xpath("//li[@class = 'address_address1 address_address2'][1]");
    By address = By.xpath("//li[@class = 'address_address1 address_address2'][2]");
    By address2 = By.xpath("//li[@class = 'address_address1 address_address2'][3]");
    By location = By.cssSelector(".address_city.address_state_name.address_postcode");
    By country = By.cssSelector(".address_country_name");
    By phone = By.cssSelector(".address_phone");




    public void fillMsgAboutOrder(String message){

        msgAboutOrder.sendKeys(message);
    }

    public PaymentPage placeOrder(){

        waitForWebElementToAppear(By.cssSelector("a[href='/payment']"));
        placeOrderBtn.click();
        return new PaymentPage(driver);
    }

    public Map<String, String> deliveryAddressInfo(){

        Map<String,String> deliveryAddress = new HashMap<>();
        deliveryAddress.put("fullName", addressDelivery.findElement(name).getText());
        deliveryAddress.put("company", addressDelivery.findElement(company).getText());
        deliveryAddress.put("address", addressDelivery.findElement(address).getText());
        deliveryAddress.put("address2", addressDelivery.findElement(address2).getText());
        deliveryAddress.put("location", addressDelivery.findElement(location).getText());
        deliveryAddress.put("country", addressDelivery.findElement(country).getText());
        deliveryAddress.put("phone", addressDelivery.findElement(phone).getText());

        return deliveryAddress;
    }

    public Map<String, String> billingAddressInfo(){

        Map<String,String> addressBilling = new HashMap<>();
        addressBilling.put("fullName", addressDelivery.findElement(name).getText());
        addressBilling.put("company", addressDelivery.findElement(company).getText());
        addressBilling.put("address", addressDelivery.findElement(address).getText());
        addressBilling.put("address2", addressDelivery.findElement(address2).getText());
        addressBilling.put("location", addressDelivery.findElement(location).getText());
        addressBilling.put("country", addressDelivery.findElement(country).getText());
        addressBilling.put("phone", addressDelivery.findElement(phone).getText());

        return addressBilling;

    }



}
