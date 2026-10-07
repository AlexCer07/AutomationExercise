package AbstractElements;

import PagesObjects.CartPage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductActions extends AbstractElements{

    WebDriver driver;

    public ProductActions(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //*****************Elements*****************

    @FindBy(css = "[class='modal-content'] button")
    WebElement modalContinueShopping;

    @FindBy(css = "[class='modal-content'] a")
    WebElement modalViewCart;

    @FindBy(css = "#cartModal .modal-content button")
    WebElement continueShoppingBtn;

    private final By productCardLocator = By.cssSelector(".features_items div.col-sm-4");
    private final By productNameInsideCard = By.cssSelector(".productinfo > p");
    private final By productInfoName = By.cssSelector(".productinfo > p");
    private final By productOverlayName = By.cssSelector(".product-overlay .overlay-content > p");
    private final By addProductButtonOverlay = By.cssSelector(".product-overlay .overlay-content a.add-to-cart");
    private final By price = By.cssSelector(".productinfo > h2");
    private final By cartModal = By.id("cartModal");


    //**********************Methods************************

    //Método auxiliar para obtener el card del un producto
    public WebElement getProductElement(String nameProduct) {

        String expectedName = normalizeText(nameProduct);
        List<WebElement> products = waitForWebElementListToAppearBy(productCardLocator);

        log.debug("Buscando producto: [{}]", expectedName);
        for (WebElement product : products) {
            WebElement nameElement = product.findElement(productNameInsideCard);
            String cleanName = getCleanText(nameElement);
            String fullName = normalizeText(nameElement.getText());
            log.debug("Producto limpio: [{}]", cleanName);
            log.debug("Producto completo: [{}]", fullName);
            if (cleanName.equalsIgnoreCase(expectedName)) {
                return product;
            }
            if (fullName.equalsIgnoreCase(expectedName)) {
                return product;
            }
            if (fullName.toLowerCase()
                    .startsWith(expectedName.toLowerCase())) {
                return product;
            }
        }
        throw new RuntimeException("Product Not Found: " + nameProduct);
    }

    //Agrega un producto al carrito de compras
    public void addProduct(String productName) {
        WebElement addButton = getOverlayButtonWithHover(productName);
        addButton.click();
        waitForVisibilityOfElementLocated(cartModal);
    }

    //Obtiene el nombre de un producto desde la card de este
    public String getProductNameFromCard(WebElement productCard) {

        WebElement normalName = productCard.findElement(productInfoName);
        WebElement overlayName = productCard.findElement(productOverlayName);
        String normalText = getTextIfNotModifiedByGoogle(normalName);

        if (normalText != null) {
            return normalText;
        }

        String overlayText = getTextIfNotModifiedByGoogle(overlayName);

        if (overlayText != null) {
            return overlayText;
        }

        String normalClean = getCleanText(normalName);
        String overlayClean = getCleanText(overlayName);
        return normalClean.length() >= overlayClean.length() ? normalClean : overlayClean;
    }

    //Obtiene el nombre y el precio de un producto desde la card de este
    public Map<String, String> getAProductNameAndPrice() {
        List<WebElement> productCards = waitForWebElementListToAppearBy(productCardLocator);
        int randomIndex = (int) (Math.random() * productCards.size());
        WebElement productCard = productCards.get(randomIndex);
        WebElement priceElement = productCard.findElement(price);
        String name = getProductNameFromCard(productCard);
        String price = getNormalizedText(priceElement);
        Map<String, String> productInfo = new HashMap<>();
        productInfo.put("name", name);
        productInfo.put("price", price);
        return productInfo;
    }

    //Método auxiliar que ayuda a obtener el nombre de un producto de manera limpia
    //Obtiene el nombre del producto incluso si no está visible
    public String getProductNameText(WebElement element) {
        String text = element.getDomProperty("textContent");
        return normalizeText(text);
    }

    //hace clic en el botón continue shopping de la modal que se muestra al agregar al carrito
    public void continueShopping() {
        log.info("Se depliega modal para ir al carrito de compras para continuar comprando");
        waitForWebElementToAppear(modalContinueShopping);
        continueShoppingBtn.click();
        log.info("Se continua con proceso de continuar comprando");
        waitForInvisibilityOfElementLocated(cartModal);
    }

    //hace clic en el botón go to cart de la modal que se muestra al agregar al carrito
    public CartPage viewCart(){
        log.info("Se depliega modal para ir al carrito de compras, para ir al carrito de compras");
        waitForWebElementToAppear(modalContinueShopping);
        modalViewCart.click();
        log.info("se redirige al carrito de compras");
        return new CartPage(driver);
    }

    //Obtiene un nombre aleatorio de un producto
    public String getRandomProductName() {
        List<WebElement> productCards = waitForWebElementListToAppearBy(productCardLocator);
        int randomIndex = (int) (Math.random() * productCards.size());
        WebElement productCard = productCards.get(randomIndex);
        String name = getProductNameFromCard(productCard);
        log.info("Nombre del producto aleatorio: [{}]", name);
        return name;
    }

    //Obtiene el botón de agregar producto de overlay de la card del producto
    private WebElement getOverlayButtonWithHover(String productName) {

        int maxAttempts = 3;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            log.debug("Intento {} de {} - Hover sobre [{}]", attempt, maxAttempts, productName);
            try {
                WebElement productCard = getProductElement(productName);
                scrollToElement(productCard);
                WebElement hoverArea = productCard.findElement(By.cssSelector(".single-products"));
                moveToElement(hoverArea);
                WebElement overlayButton = productCard.findElement(addProductButtonOverlay);
                WebElement button = waitForElementDisplayed(overlayButton, 2);
                log.info("Hover exitoso sobre [{}] en intento {}", productName, attempt);

                return button;

            } catch (TimeoutException e) {
                log.warn("Intento {} - El botón overlay de [{}] no apareció", attempt, productName);
            } catch (StaleElementReferenceException e) {
                log.warn("Intento {} - El producto [{}] quedó stale. Reintentando...", attempt, productName);
            } catch (MoveTargetOutOfBoundsException e) {
                log.warn("Intento {} - No fue posible realizar hover sobre [{}]", attempt, productName);
            }
        }

        throw new TimeoutException("El botón Add to cart del overlay de [" +
                        productName +
                        "] no estuvo disponible después de " +
                        maxAttempts +
                        " intentos");
    }
}