package PagesObjects;

import AbstractElements.AbstractElements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractElements {

    WebDriver driver;

    public HomePage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }


    public void goTo (){
        driver.get("https://automationexercise.com/");
    }
}
