package PagesObjects;

import AbstractElements.AbstractElements;
import AbstractElements.ProductActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartPage extends AbstractElements {

    WebDriver driver;
    ProductActions productActions;

    public CartPage(WebDriver driver){
        super (driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        productActions = new ProductActions(driver);
    }

    //********Elements*************

    @FindBy(className = "cart_quantity_delete")
            List<WebElement> allRemoveButtons;

    @FindBy(css = ".btn.btn-default.check_out")
            WebElement proceedToCheckoutButton;

    @FindBy(css = ".modal-content a")
            WebElement modalRegisterLoginButton;

    @FindBy(css = "#cart_info_table tbody tr")
            List<WebElement> productItems;

    @FindBy(css = "#empty_cart p")
            WebElement cartEmptyMsg;

    private final By productRowLocator = By.cssSelector("#cart_info_table tbody tr");
    private final By productNameLocator = By.cssSelector(".cart_description h4 a");

    //*********Methods***********

    //Obtiene la cantidad de elementos en el carrito de compras
    public int getSizeProductsSection(){
        waitForWebElementToAppear(cartEmptyMsg);
        log.debug("Elementos en el carrito de compras: {}",productItems.size());
        return productItems.size();
    }

    //Obtiene el mensaje de que el carrito de compras está vacío
    public String getCartEmptyMsg(){
        waitForWebElementToAppear(cartEmptyMsg);
        return cartEmptyMsg.getText();
    }

    //Obtiene los detalles de un producto que se encuentra en el carrito de compras
    public Map<String, String> getProductInCart(String name) {
        WebElement productRow = findProductRow(name);
        List<WebElement> columns = productRow.findElements(By.tagName("td"));
        Map<String, String> productDetails = new HashMap<>();

        productDetails.put("productName", getDirectText(columns.get(1).findElement(By.cssSelector("h4 a"))));
        productDetails.put("category", getCleanText(columns.get(1).findElement(By.cssSelector("p"))));
        productDetails.put("unitPrice", getCleanText(columns.get(2).findElement(By.cssSelector("p"))));
        productDetails.put("quantity", getCleanText(columns.get(3).findElement(By.cssSelector("button"))));
        productDetails.put("totalPrice", getCleanText(columns.get(4).findElement(By.cssSelector("p"))));

        log.debug("Product Name in cart: {}", productDetails.get("productName"));
        log.debug("Product Category in cart: {}", productDetails.get("category"));
        log.debug("Unit Price in cart: {}", productDetails.get("unitPrice"));
        log.debug("Quantity products in cart: {}", productDetails.get("quantity"));
        log.debug("Total Price in cart: {}", productDetails.get("totalPrice"));

        return productDetails;
    }

    //Elimina todos los productos del carrito de compras
    public void removeAllProducts(){
        for(WebElement removeButton : allRemoveButtons){
            removeButton.click();
        }
        log.info("Se remueven los productos del carrito de compras");
    }

    //hace clic en el botón para proceder con el pago
    public CheckOutPage proceedToCheckout(){
        proceedToCheckoutButton.click();
        log.info("Se procede a empezar el proceso de checkout");
        return new CheckOutPage(driver);
    }

    //hace clic en el enlace register or login cuando no se ha iniciado sesión
    public LoginPage registerOrLogin(){
        waitForWebElementToAppear(modalRegisterLoginButton);
        modalRegisterLoginButton.click();
        log.info("Se procede a autenticar el usuario");
        return new LoginPage(driver);
    }

    //localiza la fila correspondiente a un producto en el carrito de compras
    public  WebElement findProductRow(String name) {

        String expectedName = normalizeText(name);
        List<WebElement> productItems = waitForWebElementListToAppearBy(productRowLocator);

        for (WebElement item : productItems) {

            WebElement nameElement = item.findElement(productNameLocator);
            String cleanName = getCleanText(nameElement);
            String fullName = productActions.getProductNameText(nameElement);

            log.info("Buscando en carrito: [{}]", expectedName);
            log.debug("Nombre limpio carrito: [{}]", cleanName);
            log.debug("Nombre completo carrito: [{}]", fullName);

            if (cleanName.equalsIgnoreCase(expectedName) || fullName.equalsIgnoreCase(expectedName)
                    || fullName.toLowerCase().startsWith(expectedName.toLowerCase())) {
                return item;
            }
        }

        throw new RuntimeException("Product not found in cart, product expected: " + name);
    }
}
