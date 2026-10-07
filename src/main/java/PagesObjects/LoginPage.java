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

    //*************Elements**************

    @FindBy( className = "login-form")
    WebElement loginForm;

    @FindBy(className = "signup-form")
    WebElement signupForm;

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
    WebElement receiveSpecialOffersButton;

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

    @FindBy(css = "h2[data-qa='account-created'] b")
    WebElement accountCreatedMsg;

    @FindBy(css = "a[data-qa='continue-button']")
    WebElement continueButton;

    @FindBy(css = "h2[data-qa='account-deleted']")
    WebElement deleteAccountMsg;

    private final By emailField = By.cssSelector("input[type='email']");
    private final By passwordField = By.cssSelector("input[type='password']");
    private final By nameField = By.cssSelector("input[name='name']");
    private final By submitButton = By.cssSelector("button[type='submit']");
    private final By loginErrorMsg = By.cssSelector(".login-form p");
    private final By signupErrorMsg = By.cssSelector(".signup-form p");

    //***********Methods**********

    //Ingresa los datos del usuario para el inicio de sesión
    public void logIn(String email, String password){

        waitForWebElementToAppear(loginForm);

        log.debug("Se ingresa email del usuario: {}", email);
        loginForm.findElement(emailField).sendKeys(email);
        log.debug("Se ingresa password del usuario: {}", password);
        loginForm.findElement(passwordField).sendKeys(password);
        loginForm.findElement(submitButton).click();
        log.info("Se intenta inicio de sesión");
    }

    //Obtiene el mensaje de inicio de sesión fallido
    public String getErrorLoginMessage() {
        WebElement msgError = waitForWebElementToAppear(loginErrorMsg);
        log.info("Inicio de sesión fallido: {}", msgError.getText());
        return msgError.getText();
    }

    //Realiza el ingreso de los datos primarios (name y email) para el registro del usuario
    public void signUp(String name, String email){

        waitForWebElementToAppear(signupForm);

        signupForm.findElement(nameField).sendKeys(name);
        signupForm.findElement(emailField).sendKeys(email);
        log.debug("Nombre del nuevo usuario: {}", name);
        log.debug("Email del nuevo usuario: {}", email);

        signupForm.findElement(submitButton).click();
        log.info("Se ingresa a la vista del registro de usuario");
    }

    //Obtiene mensaje de inicio de sesión fallido
    public String getErrorSignUpMessage(){
        WebElement msgError = waitForWebElementToAppear(signupErrorMsg);
        log.info("Inicio de sesión invalido: {}", msgError.getText());
        return msgError.getText();
    }

    //Se realiza el ingrso de la información personal del usaurio
    public void informationAccount(Map<String, String> info){
        waitToUrlContain("/signup");
        waitForWebElementToAppear(loginForm);

        if (info.get("title").equalsIgnoreCase("mrs.")) {
            femaleButton.click();
        }else {
            maleButton.click();
        }
        log.debug("title : {}", info.get("title"));

        loginForm.findElement(passwordField).sendKeys(info.get("password"));

        log.debug("Birthday: {}", info.get("birthday"));
        String [] birthday = info.get("birthday").split("/");

        Select days = new Select(daysField);
        Select month = new Select(monthsField);
        Select years = new Select(yearsField);

        birthday[0] = birthday[0].replaceFirst("^0+", "");
        birthday[1] = birthday[1].replaceFirst("^0+", "");

        days.selectByValue(birthday[0]);
        month.selectByValue(birthday[1]);
        years.selectByValue(birthday[2]);

        safeClick(newsLetterButton);
        safeClick(receiveSpecialOffersButton);

        firstNameField.sendKeys(info.get("firstName"));
        log.debug("firstName: {}", info.get("firstName"));
        lastNameFields.sendKeys(info.get("lastName"));
        log.debug("lastName: {}", info.get("lastName"));
        companyField.sendKeys(info.get("company"));
        log.debug("company: {}", info.get("company"));

        address1Field.sendKeys(info.get("address1"));
        log.debug("address1: {}", info.get("address1"));
        address2Field.sendKeys(info.get("address2"));
        log.debug("address2: {}", info.get("address2"));

        Select country = new Select(countryField);
        country.selectByValue(info.get("country"));
        log.debug("Country: {}", info.get("country"));

        stateField.sendKeys(info.get("state"));
        log.debug("state: {}", info.get("state"));
        cityField.sendKeys(info.get("city"));
        log.debug("city: {}", info.get("city"));
        zipcodeField.sendKeys(info.get("zipCode"));
        log.debug("zipCode: {}", info.get("zipCode"));
        mobileNumberField.sendKeys(info.get("mobileNumber"));
        log.debug("mobileNumber: {}", info.get("mobileNumber"));

        safeClickAndWaitForUrl(submitButton,"/account_created");
        log.info("Se crea el usuario");
    }

    //Obtiene el mensaje de que la cuenta fue creada
    //Además de redirigir a la vista home
    public String accountCreated(){
        waitForWebElementToAppear(accountCreatedMsg);
        String msg = accountCreatedMsg.getText();

        safeClickAndWaitForExactUrl(continueButton,"https://automationexercise.com/");
        log.info("Se redirigue a la vista home");

        return msg;
    }

    //Obtiene el mensaje de que la cuenta ha sido eliminada
    //Además redirige a la vista home
    public String accountDeleted(){

        waitForWebElementToAppear(deleteAccountMsg);
        String msg = deleteAccountMsg.findElement(By.tagName("b")).getText();
        continueButton.click();
        log.info("Se redirige a la vista home");
        return msg;
    }

    //Genera información aleatoria para la creación de una cuenta
    public Map<String,String> fakeInfoSignup (){

        log.info("Generando Datos aleatorios para registro de usuario");
        //Clase para la obtención de los datos aleatorios
        Faker fakerInfo = new Faker();

        Date fecha = fakerInfo.date().birthday(18,85);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");

        String fechaFormateada = fecha.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .format(formatter);

        String gender = ((int) (Math.random()*2) == 1)? "mrs.":"mr.";

        //PENDING TASK
        //*** ¿Se podran obtener los datos aleatorios directos desde el website? ***
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