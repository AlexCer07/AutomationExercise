package AbstractElements;

import PagesObjects.ContactUsPage;
import PagesObjects.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractElements {

    WebDriver driver;
    public AbstractElements(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='shop-menu pull-right']/ul/li/a[@href='/products']")
    WebElement productButton;

    @FindBy(xpath = "//a[@href='/login']")
    WebElement loginButton;

    @FindBy(xpath = "//a[@href='/logout']")
    WebElement logoutButton;

    @FindBy (css = "a[href='/contact_us']")
        WebElement contactUsButton;

    @FindBy(xpath = "//a[contains(text(), ' Logged in as ')]")
    WebElement userLoggedElement;

    @FindBy(xpath = "//a[@href='/delete_account']")
    WebElement deleteAccount;


    public void goToProductPage(){
        productButton.click();
    }

    public LoginPage goToLoginPage(){

        loginButton.click();
        return new LoginPage(driver);
    }

    public ContactUsPage goToContactUs(){
        contactUsButton.click();
        return new ContactUsPage(driver);
    }

    public String userLogged(){
        return userLoggedElement.getText();
    }

    public void userLogut(){
        logoutButton.click();
        waitToUrlContain("/login");
    }

    public void deleteAccount(){
        deleteAccount.click();
        waitToUrlContain("/delete_account");
    }




    public void waitForWebElementToAppear(WebElement ele){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public void waitForUrlBe(String url){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(url));
    }

    public void waitToUrlContain(String path){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains(path));
    }
}
