package PagesObjects;

import AbstractElements.AbstractElements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.Map;

public class LoginPage extends AbstractElements {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy( className = "login-form")
    WebElement loginForm;

    @FindBy(className = "signup-form")
    WebElement signupForm;

    By emailField = By.cssSelector("input[type='email']");
    By passwordField = By.cssSelector("input[type='password']");
    By nameField = By.cssSelector("input[name='name']");
    By submitButton = By.cssSelector("button[type='submit']");

    By text = By.tagName("p");

    //register page

    @FindBy(id = "id_gender1")
    WebElement maleButton;

    @FindBy(id = "id_gender2")
    WebElement femaleButton;

    @FindBy(id = "days")
    WebElement daysField;

    @FindBy(id = "months")
    WebElement monthsField;

    @FindBy(id = "years")
    WebElement yearsField;

    @FindBy(id = "newsletter")
    WebElement newsLetterButton;

    @FindBy(id = "optin")
    WebElement reciveSpecialOffersButton;

    @FindBy(id = "first_name")
    WebElement firstNameField;

    @FindBy(id = "last_name")
    WebElement lastNameFields;

    @FindBy(id = "company")
    WebElement companyField;

    @FindBy(id = "address1")
    WebElement address1Field;

    @FindBy(id = "address2")
    WebElement address2Field;

    @FindBy(id = "country")
    WebElement countryField;

    @FindBy(id = "state")
    WebElement stateField;

    @FindBy(id = "city")
    WebElement cityField;

    @FindBy(id = "zipcode")
    WebElement zipcodeField;

    @FindBy(id = "mobile_number")
    WebElement mobileNumberField;

    //account Created

    @FindBy(css = "h2[data-qa='account-created']")
    WebElement accountCreatedMsg;

    @FindBy(css = "a[data-qa='continue-button']")
    WebElement continueButton;

    //delete account

    @FindBy(css = "h2[data-qa='account-deleted']")
    WebElement deleteAccountMsg;

    public void logIn(String email, String password){

        waitForWebElementToAppear(loginForm);

        loginForm.findElement(emailField).sendKeys(email);
        loginForm.findElement(passwordField).sendKeys(password);
        loginForm.findElement(submitButton).click();

    }

    public String getErrorLoginMessage(){
        waitForWebElementToAppear(loginForm.findElement(text));

        return loginForm.findElement(text).getText();
    }

    public void signUp(String name, String email){

        waitForWebElementToAppear(signupForm);

        signupForm.findElement(nameField).sendKeys(name);
        signupForm.findElement(emailField).sendKeys(email);
        signupForm.findElement(submitButton).click();
    }

    public String getErrorSignUpMessage(){
        waitForWebElementToAppear(signupForm.findElement(text));

        return signupForm.findElement(text).getText();
    }

    public void informationAccount(Map<String, String> info){
        waitToUrlContain("/signup");
        waitForWebElementToAppear(loginForm);

        if (info.get("title").equalsIgnoreCase("mrs."))
            femaleButton.click();
        else
            maleButton.click();

        loginForm.findElement(passwordField).sendKeys(info.get("password"));

        String [] birthday = info.get("birthday").split("-");
        Select days = new Select(daysField);
        Select month = new Select(monthsField);
        Select years = new Select(yearsField);

        birthday[1] = birthday[1].replaceFirst("^0+", "");
        birthday[0] = birthday[0].replaceFirst("^0+", "");

        days.selectByValue(birthday[1]);
        month.selectByValue(birthday[0]);
        years.selectByValue(birthday[2]);

        newsLetterButton.click();
        reciveSpecialOffersButton.click();

        firstNameField.sendKeys(info.get("firstName"));
        lastNameFields.sendKeys(info.get("lastName"));
        companyField.sendKeys(info.get("company"));

        address1Field.sendKeys(info.get("address1"));
        address2Field.sendKeys(info.get("address2"));

        Select country = new Select(countryField);
        country.selectByValue(info.get("country"));

        stateField.sendKeys(info.get("state"));
        cityField.sendKeys(info.get("city"));
        zipcodeField.sendKeys(info.get("zipCode"));
        mobileNumberField.sendKeys(info.get("mobileNumber"));

        loginForm.findElement(submitButton).click();


    }

    public String accountCreated(){
        waitForWebElementToAppear(accountCreatedMsg);
        continueButton.click();

        return accountCreatedMsg.getText();

    }

    public String accountDeleted(){

        waitForWebElementToAppear(deleteAccountMsg);
        String msg = deleteAccountMsg.findElement(By.tagName("b")).getText();
        continueButton.click();
        return msg;

    }
}
