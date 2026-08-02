package PagesObjects;

import AbstractElements.AbstractElements;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    By loginErrorMsg = By.cssSelector(".login-form p");
    By signupErrorMsg = By.cssSelector(".signup-form p");

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

    @FindBy(css = "h2[data-qa='account-created'] b")
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

    public String getErrorLoginMessage() {
        WebElement msgError = waitForWebElementToAppear(loginErrorMsg);

        return msgError.getText();
    }

    public void signUp(String name, String email){

        waitForWebElementToAppear(signupForm);

        signupForm.findElement(nameField).sendKeys(name);
        signupForm.findElement(emailField).sendKeys(email);
        signupForm.findElement(submitButton).click();
    }

    public String getErrorSignUpMessage(){
        WebElement msgError = waitForWebElementToAppear(signupErrorMsg);


        return msgError.getText();
    }

    public void informationAccount(Map<String, String> info){
        waitToUrlContain("/signup");
        waitForWebElementToAppear(loginForm);

        if (info.get("title").equalsIgnoreCase("mrs."))
            femaleButton.click();
        else
            maleButton.click();

        loginForm.findElement(passwordField).sendKeys(info.get("password"));

        String [] birthday = info.get("birthday").split("/");

        Select days = new Select(daysField);
        Select month = new Select(monthsField);
        Select years = new Select(yearsField);

        birthday[0] = birthday[0].replaceFirst("^0+", "");
        birthday[1] = birthday[1].replaceFirst("^0+", "");

        days.selectByValue(birthday[0]);
        month.selectByValue(birthday[1]);
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
        String msg = accountCreatedMsg.getText();
        continueButton.click();

        return msg;

    }

    public String accountDeleted(){

        waitForWebElementToAppear(deleteAccountMsg);
        String msg = deleteAccountMsg.findElement(By.tagName("b")).getText();
        continueButton.click();
        return msg;

    }



    public Map<String,String> fakeInfoSignup (){
        Faker fakerInfo = new Faker();

        Date fecha = fakerInfo.date().birthday(18,85);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");

        String fechaFormateada = fecha.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .format(formatter);

        String gender = ((int) (Math.random()*2) == 1)? "mrs.":"mr.";

        List<String> countries = Arrays.asList(
                "India", "United States", "Canada",
                "Australia", "Israel", "New Zealand", "Singapore"
        );


        Map<String, String> info = new HashMap<>();
        info.put("userName", fakerInfo.name().username());
        info.put("email", fakerInfo.internet().emailAddress());
        info.put("title", gender);
        info.put("password", fakerInfo.internet().password());
        info.put("birthday", fechaFormateada);
        info.put("firstName", fakerInfo.name().firstName());
        info.put("lastName", fakerInfo.name().lastName());
        info.put("company", fakerInfo.company().name());
        info.put("address1", fakerInfo.address().streetAddress());
        info.put("address2", fakerInfo.address().secondaryAddress());
        info.put("country",countries.get((int)(Math.random()*7)));
        info.put("state", fakerInfo.address().state());
        info.put("city", fakerInfo.address().city());
        info.put("zipCode", fakerInfo.address().zipCode());
        info.put("mobileNumber", fakerInfo.phoneNumber().cellPhone());

        return info;
    }


}
