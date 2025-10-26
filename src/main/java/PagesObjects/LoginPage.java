package PagesObjects;

import AbstractElements.AbstractElements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractElements {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy( className = "login-form")
    WebElement loginForm;

    By emailField = By.cssSelector("input[type='email']");
    By passwordField = By.cssSelector("input[type='password']");
    By submitButton = By.cssSelector("button[type='submit']");

    By text = By.tagName("p");


    public String logIn(String email, String password){

        waitForWebElementToAppear(loginForm);

        loginForm.findElement(emailField).sendKeys(email);
        loginForm.findElement(passwordField).sendKeys(password);
        loginForm.findElement(submitButton).click();

        waitForWebElementToAppear(loginForm.findElement(text));

        return loginForm.findElement(text).getText();

    }
}
