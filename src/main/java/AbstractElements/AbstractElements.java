package AbstractElements;

import PagesObjects.LoginPage;
import org.openqa.selenium.JavascriptExecutor;
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
    WebElement productPage;

    @FindBy(xpath = "//a[@href='/login']")
    WebElement loginPage;

    public void goToProductPage(){
        productPage.click();
    }

    public LoginPage goToLoginPage(){

        loginPage.click();
        return new LoginPage(driver);
    }




    public void waitForWebElementToAppear(WebElement ele){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(ele));
    }
}
