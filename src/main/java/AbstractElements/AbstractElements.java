package AbstractElements;

import PagesObjects.*;
import com.github.javafaker.Faker;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.time.Year;
import java.util.*;

public class AbstractElements {

    public static final Logger log = LogManager.getLogger(AbstractElements.class);

    WebDriver driver;
    public AbstractElements(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //****************Getters*******************

    @Getter
    @FindBy(tagName = "footer")
    WebElement footer;

    @Getter
    @FindBy(tagName = "header")
    WebElement header;

    @Getter
    By h2Locator = By.tagName("h2");

    //*****************Elements*****************

    @FindBy(xpath = "//a[@href='/login']")
    WebElement loginButton;

    @FindBy(xpath = "//a[@href='/logout']")
    WebElement logoutButton;

    @FindBy (css = "a[href='/contact_us']")
    WebElement contactUsButton;

    @FindBy(xpath = "//a[contains(text(), 'Logged in as ')]")
    WebElement userLoggedElement;

    @FindBy(id = "susbscribe_email")
    WebElement subscriptionEmailField;

    @FindBy(id = "subscribe")
    WebElement subscribeButton;

    @FindBy(css = "[class='alert-success alert']")
    WebElement successfulSubscriptionMsg;

    private final By productButton =
            By.xpath("//div[@class='shop-menu pull-right']/ul/li/a[@href='/products']");
    private final By testCaseButton =  By.cssSelector("a[href='/test_cases']");
    private final By cartButton = By.cssSelector("ul[class='nav navbar-nav'] a[href='/view_cart");
    private final By deleteAccountBtn = By.xpath("//a[@href='/delete_account']");

    //******************Methods******************

    //================== Go To views ====================

    //Navega a la vista Products desde el menú de navegación
    public ProductPage goToProductPage(){
        safeClickAndWaitForUrl(productButton, "/products");
        log.info("Navegando a la vista products desde el menú de navegación");
        return new ProductPage(driver);
    }

    //Navega a la vista Login desde el menú de navegación
    public LoginPage goToLoginPage(){
        log.info("Navegando a la vista Login desde el menú de navegación");
        loginButton.click();
        return new LoginPage(driver);
    }

    //Navega a la vista Contact Us desde el menú de navegación
    public ContactUsPage goToContactUs(){
        contactUsButton.click();
        log.info("Navegando a la vista Contact Us desde le menú de navegación");
        return new ContactUsPage(driver);
    }

    //Navega a la vista Test Cases desde el menú de navegación
    public TestCasePage goToTestCase() {
        safeClickAndWaitForUrl(testCaseButton, "/test_cases");
        log.info("Navegando a la vista de casos de prueba desde le menú de navegación");
        return new TestCasePage(driver);
    }

    //Navega a la vista view cart desde el menu de navegación
    public CartPage goToCartPage(){
        safeClickAndWaitForUrl(cartButton, "/view_cart");
        log.info("Navegando a carrito de compras desde le menú de navegación");
        return new CartPage(driver);
    }

    //====================== Waits =====================

    public Alert waitForAlert(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    public void waitForWebElementToAppear(WebElement ele){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public List<WebElement> waitForWebElementListToAppearBy(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public void waitForUrlBe(String url){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(url));
    }

    public void waitToUrlContain(String path){

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlContains(path));
        } catch (TimeoutException e) {
            log.error("URL don't found");
            log.error("Partially Expected URL: {}", path);
            log.error("Actual URL: {}", driver.getCurrentUrl());

            throw e;
        }
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

    public void waitForWebElementToClickable(WebElement ele){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(ele));
    }

    public WebElement waitForWebElementToClickable(By ele){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.elementToBeClickable(ele));
    }

    public void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(d -> {String readyState = (String) ((JavascriptExecutor) d)
                            .executeScript("return document.readyState");

            return "complete".equals(readyState);
        });
    }

    public List<WebElement> waitAllElementsVisible(By element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
    }

    public List<WebElement> waitPresenceOfAllElementsLocatedBy(By element){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(element));
    }

    public void waitForUrlToChange(String oldUrl) {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(driver -> !Objects.equals(driver.getCurrentUrl(), oldUrl));
    }

    public List<WebElement> waitForWebElementListToBePresentBy(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public void waitForElementToChange(By locator, WebElement oldElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(driver -> {
            WebElement newElement = driver.findElement(locator);
            return !newElement.equals(oldElement);});
    }

    public WebElement waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForInvisibilityOfElementLocated(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public WebElement waitForElementDisplayed(WebElement element, int timeoutSeconds) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

        return wait.until(driver -> {
            try {
                return element.isDisplayed() && element.isEnabled() ? element : null;
            } catch (StaleElementReferenceException e) {
                return null;
            }
        });
    }

    //======================= Fake Info ==========================

    //Genera información básica de un usuario
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

        log.info("Se genera información aleatoria de datos del usuario");
        return info;
    }

    //Genera la información de pago
    public Map<String,String> fakePaymentInfo(){

        log.info("Comienza la generación de datos del pago");
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

        log.debug("Name on card: {}", info.get("name"));
        log.debug("Card Number: {}", info.get("cardNumber"));
        log.debug("CVV: {}", info.get("cvv"));
        log.debug("Expiry date: {} / {}", info.get("expiryMonth"), info.get("expiryYear"));
        log.debug("Message: {}", info.get("paragraph"));

        return info;
    }

    //====================== Actions ===========================

    //Realiza scroll hacia un elemento
    public void scrollToElement(WebElement element) {

        log.info("Haciendo scroll hacia un elemento");

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].scrollIntoView({" +
                        "block: 'center'," +
                        "inline: 'center'" +
                        "});",
                element
        );
    }

    //Realiza scroll usando la flecha direccional hacia abajo
    public boolean scrollDown(WebElement element){

        log.info("Se empieza ha desplazarse a la hacia abajo usando las flechas direccionales");

        int maxScrolls = 500;
        int i = 0;

        while (true) {
            i +=1;
            arrowDown();

            // válida si ya es visible en viewport
            Boolean visible = (Boolean) ((JavascriptExecutor) driver)
                    .executeScript(
                            "const rect = arguments[0].getBoundingClientRect();" +
                                    "return rect.top < window.innerHeight && rect.bottom >= 0;",
                            element
                    );

            if (Boolean.TRUE.equals(visible)) {
                return true;
            }
            if (i == maxScrolls){
                return false;
            }
        }
    }

    //Realiza scroll usando la flecha direccional hacia arriba
    public boolean scrollUp(WebElement element){

        log.info("Se empieza ha desplazarse a la hacia arriba usando las flechas direccionales");

        int maxScrolls = 500;
        int i = 0;

        while (true) {
            i +=1;
            arrowUp();

            Boolean visible = (Boolean) ((JavascriptExecutor) driver)
                    .executeScript(
                            "const rect = arguments[0].getBoundingClientRect();" +
                                    "return rect.top < window.innerHeight && rect.bottom >= 0;",
                            element
                    );

            if (Boolean.TRUE.equals(visible)) {
                return true;
            }
            if (i == maxScrolls){
                return false;
            }
        }
    }

    //Mueve el curso hacia un elemento
    public void moveToElement(WebElement element) {

        log.info("Se mueve el cursor hacia un elemento");

        new Actions(driver)
                .moveToElement(element)
                .perform();
    }

    //Se presiona la fecha hacia arriba
    public void arrowUp(){
        log.info("Se presiona la tecla de flecha hacia arriba");
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_UP).perform();
    }

    //Se presiona la fecha hacia abajo
    public void arrowDown(){
        log.info("Se presiona la tecla de flecha hacia abajo");
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN).perform();
    }

    //Hacer clic usando JavaScript
    protected void jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    //Acepta la alerta del navegador
    public void acceptAlert(){
        log.info("Se intenta aceptar alerta del navegador");
        Alert alert = waitForAlert();
        alert.accept();
        log.info("Se acepta alerta del usuario");
    }

    //====================== Get Text ====================

    //Obtiene el texto de un elemento sin caracteres especiales provocados por anuncios
    public String getNormalizedText(WebElement element) {
        return normalizeText(element.getText());
    }

    // Normaliza el texto reemplazando espacios especiales, espacios múltiples
    // y eliminando espacios al inicio y al final.
    public String normalizeText(String text) {

        if (text == null) {
            return null;
        }

        return text
                .replace('\u00A0', ' ')
                .replaceAll("\\s+", " ")
                .trim();
    }

    // Obtiene únicamente el texto directo del elemento,
    // ignorando el texto contenido en elementos hijos.
    public String getDirectText(WebElement element) {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        String text = (String) js.executeScript(
                """
                let text = '';
    
                arguments[0].childNodes.forEach(node => {
                    if (node.nodeType === Node.TEXT_NODE) {
                        text += node.textContent;
                    }
                });
    
                return text;
                """,
                element
        );

        return normalizeText(text);
    }

    // Obtiene el texto del elemento y de sus elementos hijos,
    // excluyendo elementos identificados como anotaciones o anuncios de Google.
    public String getCleanText(WebElement element) {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        String text = (String) js.executeScript(
                """
                const root = arguments[0];
    
                let result = '';
    
                root.childNodes.forEach(node => {
    
                    if (node.nodeType === Node.TEXT_NODE) {
                        result += node.textContent;
                        return;
                    }
    
                    if (node.nodeType === Node.ELEMENT_NODE) {
    
                        const el = node;
    
                        const isGoogleAnnotation =
                            el.classList.contains('google-anno-skip') ||
                            el.classList.contains('google-anno-sc') ||
                            el.hasAttribute('data-google-vignette') ||
                            el.hasAttribute('data-google-interstitial');
    
                        if (!isGoogleAnnotation) {
                            result += el.textContent;
                        }
                    }
                });
    
                return result;
                """,
                element
        );

        return normalizeText(text);
    }

    // Obtiene el texto completo del elemento solo si no contiene
    // anotaciones o modificaciones agregadas por Google.
    public String getTextIfNotModifiedByGoogle(WebElement element) {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        Boolean hasGoogleAnnotation =
                (Boolean) js.executeScript(
                        """
                        return arguments[0].querySelector(
                            '[class*="google-anno"],' +
                            '[data-google-vignette],' +
                            '[data-google-interstitial]'
                        ) !== null;
                        """,
                        element
                );

        if (Boolean.TRUE.equals(hasGoogleAnnotation)) {
            return null;
        }

        String text =
                (String) js.executeScript(
                        "return arguments[0].textContent;",
                        element
                );

        return normalizeText(text);
    }

    public String getTextIgnoringAds(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String text = (String) js.executeScript(
                """
                const original = arguments[0];
    
                // Clonamos para NO modificar el DOM real
                const clone = original.cloneNode(true);
    
                // Eliminamos elementos claramente inyectados por publicidad
                clone.querySelectorAll(
                    'div[class*="google"], ' +
                    'ins[class*="google"], ' +
                    'iframe'
                ).forEach(el => el.remove());
    
                return clone.textContent;
                """,
                element
        );

        return normalizeText(text);
    }

    //================= Safe Clickers ===================

    //Se realiza clic a un elemento, esperando que la url contenga un texto y haciendo reintentos
    public void safeClickAndWaitForUrl(By locator, String expectedUrl) {
        WebElement element = waitForWebElementToClickable(locator);

        //Coloca el elemento en el centro de la pantalla. Esto reduce problemas con banners, headers y elementos
        //que puedan quedar encima del botón.
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);

        try {
            element = waitForElementToBeClickable(locator);
            element.click();
        } catch (ElementClickInterceptedException e) {
            log.warn("Click interceptado en {}. Intentando cerrar anuncios.", locator);
            AdHandler adHandler = new AdHandler(driver);
            adHandler.closeAdsIfPresent();

            //Se vuelve a localizar el elemento porque el DOM pudo cambiar después de cerrar los anuncios.
            element = waitForElementToBeClickable(locator);

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView({block:'center'});", element);

            element.click();
        }

        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            shortWait.until(ExpectedConditions.urlContains(expectedUrl));
            return;
        } catch (TimeoutException e) {
            log.warn("El click se realizó pero no hubo navegación. Reintentando: {}", locator);
        }

         //Si el clic ocurrió, pero no hubo navegación, se limpian nuevamente posibles anuncios.

        AdHandler adHandler = new AdHandler(driver);
        adHandler.closeAdsIfPresent();

        element = waitForElementToBeClickable(locator);

        try {
            element.click();

        } catch (ElementClickInterceptedException e) {
            log.warn("Segundo click interceptado en {}. Usando JavaScript como último recurso.", locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }

        waitToUrlContain(expectedUrl);
    }

    //Se realiza clic a un elemento, esperando que una url exacta
    public void safeClickAndWaitForExactUrl(WebElement element, String expectedUrl) {
        waitForWebElementToClickable(element);
        element.click();

        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            shortWait.until(ExpectedConditions.urlToBe(expectedUrl));
            return;
        } catch (TimeoutException e) {
            log.warn("El click se realizó pero no hubo navegación.");
        }

        waitForWebElementToClickable(element);
        element.click();
        waitForUrlBe(expectedUrl);
    }

    //Se realiza clic a un elemento
    public void safeClick(WebElement element) {
        waitForVisibilityOfElementLocated(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);
        waitForWebElementToClickable(element);

        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            log.warn("Click interceptado. Ejecutando click con JavaScript");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    //================== Page's Functions ===================

    //Obtiene el usuario que inició sesión
    public String userLogged(){
        waitForVisibilityOfElementLocated(userLoggedElement);
        log.info("usuario que inició sesión: {}",userLoggedElement.getText());
        return userLoggedElement.getText();
    }

    //Cierra sesión del usuario
    public String userLogout(){
        logoutButton.click();
        waitToUrlContain("/login");
        log.info("Se cierra sesión del usuario");
        return driver.getCurrentUrl();
    }

    //Elimina la cuenta que está logueada
    public void deleteAccount(){
        safeClickAndWaitForUrl(deleteAccountBtn,"/delete_account");
        log.info("Cuenta eliminada");
    }

    public String filePathImage(String fileName){
        log.debug("Se obtiene la ruta y nombre del archivo");
        return System.getProperty("user.dir")+"\\src\\test\\java\\data\\images\\".concat(fileName);
    }

    //Realiza la acción de suscribir
    public String subscribe(String email){
        subscriptionEmailField.sendKeys(email);
        subscribeButton.click();
        log.info("Se realiza suscripción a las noticias de la tienda");
        waitForWebElementToAppear(successfulSubscriptionMsg);
        return successfulSubscriptionMsg.getText();
    }
}
