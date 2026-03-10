package AbstractElements;

import PagesObjects.ContactUsPage;
import PagesObjects.LoginPage;
import PagesObjects.ProductPage;
import PagesObjects.TestCasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

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

    @FindBy (css = "a[href='/test_cases']")
    WebElement testCaseButton;

    @FindBy(xpath = "//a[contains(text(), ' Logged in as ')]")
    WebElement userLoggedElement;

    @FindBy(xpath = "//a[@href='/delete_account']")
    WebElement deleteAccount;


    public ProductPage goToProductPage(){
        productButton.click();
        return new ProductPage(driver);
    }

    public LoginPage goToLoginPage(){

        loginButton.click();
        return new LoginPage(driver);
    }

    public ContactUsPage goToContactUs(){
        contactUsButton.click();
        return new ContactUsPage(driver);
    }

    public TestCasePage goToTestCase(){
        testCaseButton.click();
        return new TestCasePage(driver);
    }

    public String userLogged(){
        return userLoggedElement.getText();
    }

    public String userLogut(){
        logoutButton.click();
        waitToUrlContain("/login");

        return driver.getCurrentUrl();
    }

    public void deleteAccount(){
        deleteAccount.click();
        waitToUrlContain("/delete_account");
    }




    public void waitForWebElementToAppear(WebElement ele){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public void waitForWebElementListToAppear(List<WebElement> listElements){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElements(listElements));
    }

    public void waitForUrlBe(String url){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(url));
    }

    public void waitToUrlContain(String path){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains(path));
    }

    public void closeAdsIfPresent(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try{

            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("iframe")));

            for (WebElement iframe : driver.findElements(By.tagName("iframe"))){

                try {
                    driver.switchTo().frame(iframe);

                    WebElement closeAd = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[aria-label='Close ad']")));

                    closeAd.click();
                    driver.switchTo().defaultContent();
                    return;
                }catch (TimeoutException | NoSuchElementException e){
                    driver.switchTo().defaultContent();
                }
            }
        }catch (TimeoutException e){

        }



        /*try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(
                            By.cssSelector("[aria-label='Close ad']")
                    )).click();
        }catch (TimeoutException ignored){}*/
    }


    public void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d ->
                        ((JavascriptExecutor) d)
                                .executeScript("return document.readyState")
                                .equals("complete")
                );
    }

}
