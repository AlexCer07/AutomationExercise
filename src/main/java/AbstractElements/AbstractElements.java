package AbstractElements;

import PagesObjects.*;
import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.time.Year;
import java.util.*;

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

    @FindBy(css = "ul[class='nav navbar-nav'] a[href='/view_cart")
    WebElement cartButton;

    @FindBy(xpath = "//a[contains(text(), ' Logged in as ')]")
    WebElement userLoggedElement;

    @FindBy(xpath = "//a[@href='/delete_account']")
    WebElement deleteAccount;

    @FindBy(tagName = "footer")
    WebElement footer;

    @FindBy(tagName = "header")
    WebElement header;

    @FindBy(id = "susbscribe_email")
    WebElement subscriptionEmailField;

    @FindBy(id = "subscribe")
    WebElement subscribeButton;

    @FindBy(css = "[class='alert-success alert']")
    WebElement successfulSubscriptionMsg;

    @FindBy(css = "div[class='features_items'] h2[class='title text-center']")
    WebElement sectionName;

    public WebElement getHeader() {
        return header;
    }

    public WebElement getFooter() {
        return footer;
    }

    //*********** Go To views *************

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

    public CartPage goToCartPage(){
        cartButton.click();
        return new CartPage(driver);
    }




    //************ Waits *********

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

    public void waitForVisibilityOfElementLocated(By ele){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
    }

    public void waitForVisibilityOfElementLocated(WebElement ele){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElements(ele));
    }

    public WebElement waitForWebElementToAppear(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForWebElementToClickable(WebElement ele){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(ele));
        return ele;
    }

    public WebElement waitForWebElementToClickable(By ele){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(ele));

        return element;
    }

    public void waitCloseAd(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".ad-overlay"), 0));

    }

    public void waitToLocatedElement(By element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void waitStalenessOf(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.stalenessOf(element));
    }

    public void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d ->
                        ((JavascriptExecutor) d)
                                .executeScript("return document.readyState")
                                .equals("complete")
                );
    }

    public List<WebElement> waitAllElementsVisible(By element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> items = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
        return items;
    }

    public File waitForFile(String folderPath, String fileName, int timeoutSeconds) {

        File file = new File(folderPath + "/" + fileName);

        int waited = 0;

        while (waited < timeoutSeconds) {

            if (file.exists() && !file.getName().endsWith(".crdownload")) {
                return file;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            waited++;
        }

        return null;
    }

    public File waitForLatestFile(String folderPath, int timeoutSeconds, long startTime) {

        File latestFile = null;

        int waited = 0;

        while (waited < timeoutSeconds) {

            latestFile = getLatestDownloadedFile(folderPath, startTime);

            if (latestFile != null
                    && !latestFile.getName().endsWith(".crdownload")) {

                return latestFile;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            waited++;
        }

        return null;
    }

    public File getLatestDownloadedFile(String folderPath, long startTime) {

        File dir = new File(folderPath);

        File[] files = dir.listFiles();

        if (files == null || files.length == 0) {
            return null;
        }


        return Arrays.stream(files)
                .filter(file -> !file.getName().endsWith(".crdownload") && !file.getName().endsWith(".tmp"))
                .filter(file -> file.lastModified() > startTime)
                .max(Comparator.comparingLong(File::lastModified))
                .orElse(null);
    }


    //******* Fake Info **************
    public Map<String,String> fakeInfo(){
        Faker faker = new Faker();

        Map<String,String> info = new HashMap<>();
        info.put("name", faker.name().firstName());
        info.put(("fullName"), faker.name().fullName());
        info.put("email",faker.internet().emailAddress());
        info.put("username",faker.name().username());
        info.put("password",faker.internet().password());
        info.put("sentence", faker.lorem().sentence());
        info.put("paragraph", faker.lorem().paragraph());
        return info;
    }

    public Map<String,String> fakePaymentInfo(){
        Faker faker = new Faker();
        String[] expiryDate = faker.business().creditCardExpiry().split("-");

        int randomYear = Integer.parseInt(expiryDate[0]);
        int currentYear = Integer.parseInt(Year.now().toString());
        String expiryYear = randomYear < currentYear ? String.valueOf(currentYear + 5) : String.valueOf(randomYear);
        String expiryMonth = String.format("%02d", Integer.parseInt(expiryDate[1]));

        Map<String,String> info = new HashMap<>();
        info.put("name", faker.name().fullName());
        info.put("cardNumber",faker.business().creditCardNumber());
        info.put("cvv", String.valueOf((int) (Math.random() * 1000)));
        info.put("expiryYear", expiryYear);
        info.put("expiryMonth", expiryMonth);
        info.put("paragraph", faker.lorem().paragraph());
        return info;
    }


    //*********** Actions ****************

    public void scrollToFooter (){

        new Actions(driver)
                .scrollToElement(footer)
                .perform();

    }

    public void scrollToElement (WebElement element){

        new Actions(driver)
                .scrollToElement(element)
                .perform();

    }

    public boolean scrollDown(WebElement element){

        int maxScrolls = 500;
        int i = 0;
        Actions actions = new Actions(driver);

        while (true) {
            i +=1;
            actions.sendKeys(Keys.ARROW_DOWN).perform();

            // valida si ya es visible en viewport
            Boolean visible = (Boolean) ((JavascriptExecutor) driver)
                    .executeScript(
                            "const rect = arguments[0].getBoundingClientRect();" +
                                    "return rect.top < window.innerHeight && rect.bottom >= 0;",
                            element
                    );

            if (visible) {
                return true;
            }
            if (i == maxScrolls){
                return false;
            }
        }
    }

    public boolean scrollUp(WebElement element){

        int maxScrolls = 500;
        int i = 0;
        Actions actions = new Actions(driver);
        while (true) {
            i +=1;
            actions.sendKeys(Keys.ARROW_UP).perform();

            Boolean visible = (Boolean) ((JavascriptExecutor) driver)
                    .executeScript(
                            "const rect = arguments[0].getBoundingClientRect();" +
                                    "return rect.top < window.innerHeight && rect.bottom >= 0;",
                            element
                    );

            if (visible) {
                return true;
            }
            if (i == maxScrolls){
                return false;
            }
        }

    }


    public void acceptAlert(){
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void moveToElement(WebElement element){
        new Actions(driver)
                .moveToElement(element)
                .perform();
    }



    //******** Page's Functions **********

    public String userLogged(){
        return userLoggedElement.getText();
    }

    public String userLogout(){
        logoutButton.click();
        waitToUrlContain("/login");

        return driver.getCurrentUrl();
    }

    public void deleteAccount(){
        deleteAccount.click();
        waitToUrlContain("/delete_account");
    }

    public String filePathImage(String fileName){
        return System.getProperty("user.dir")+"\\src\\test\\java\\data\\images\\".concat(fileName);
    }

    public void subscribe(String email){

        subscriptionEmailField.sendKeys(email);
        subscribeButton.click();

    }

    public String successSubscription(){

        waitForWebElementToAppear(successfulSubscriptionMsg);
        return successfulSubscriptionMsg.getText();

    }

    public String getTotalPrice(String unitPrice, int quantity){

        String []value = unitPrice.split(" ");
        int total = Integer.parseInt(value[1])*quantity;

        return value[0].concat(" "+total);

    }

    public String getSectionName(){
        return sectionName.getText();
    }








}
