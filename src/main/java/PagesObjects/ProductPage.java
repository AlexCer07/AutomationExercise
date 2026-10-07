package PagesObjects;

import AbstractElements.AbstractElements;
import AbstractElements.ProductActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.Map;

public class ProductPage extends AbstractElements {

    WebDriver driver;
    ProductActions productActions;

    public ProductPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        productActions = new ProductActions(driver);
    }

    //*****************Elements*************

    @FindBy(className = "title")
    WebElement allProductsTitle;

    @FindBy(id = "search_product")
    WebElement searchField;

    @FindBy(id = "submit_search")
    WebElement searchButton;

    @FindBy(css = ".category-tab.shop-details-tab")
    WebElement opinionProductSection;

    @FindBy(css = "div[class='features_items'] h2[class='title text-center']")
    WebElement sectionName;

    private final By nameField = By.cssSelector("[type='text']");
    private final By emailField = By.cssSelector("[type='email']");
    private final By commentField = By.cssSelector("[name='review']");
    private final By submit = By.id("button-review");
    private final By msg = By.cssSelector(".alert-success.alert");
    private final By viewProductBtn = By.xpath("//a[contains(@href,'product_details/')]");
    private final By productListLocator = By.cssSelector(".features_items .product-image-wrapper .productinfo > p");
    private final By productCardLocator = By.cssSelector(".features_items div.col-sm-4");
    private final By productNameInsideCard = By.cssSelector(".productinfo > p");

    //****************Methods*********************

    //Válida que se haya redirigido a la vista de productos, donde se muestra todos los productos
    public boolean allProductsSuccessfully(){
        waitForPageLoad();
        waitForVisibilityOfElementLocated(productListLocator);
        String title = allProductsTitle.getText();
        String url = driver.getCurrentUrl();

        if (!title.equalsIgnoreCase("all products")){
            log.error("Titulo no encontrado");
            return false;
        }

        assert url != null;
        return (url.endsWith("/products"));
    }

    //Redirige a la vista view Product de un producto
    public ProductDetailPage viewProduct(String nameProduct){

        //Obtiene el card del producto
        WebElement product = productActions.getProductElement(nameProduct);
        //Hace clic en el botón view product
        safeClick(product.findElement(viewProductBtn));
        log.info("Se redirige a la vista de detalles del producto: {}",nameProduct);
        waitToUrlContain("/product_details");
        return new ProductDetailPage(driver);
    }

    //Realiza la búsqueda de un producto
    public void searchProduct(String name){
        searchField.sendKeys(name);
        searchButton.click();
        log.info("Se realiza la busqueda del producto");
    }

    //Envía una opinion sobre un producto
    public String publishOpinion(Map<String,String> info){
        opinionProductSection.findElement(nameField).sendKeys(info.get("name"));
        opinionProductSection.findElement(emailField).sendKeys(info.get("email"));
        opinionProductSection.findElement(commentField).sendKeys(info.get("paragraph"));
        opinionProductSection.findElement(submit).click();

        return opinionProductSection.findElement(msg).getText();
    }

    //Obtiene el nombre de una sección
    public String getSectionName(){
        return sectionName.getText();
    }

    //Indica si el producto buscado fue encontrado
    public boolean isSearchedProductDisplayed(String expectedName) {

        String expected = normalizeText(expectedName);
        List<WebElement> products = waitForWebElementListToAppearBy(productCardLocator);

        for (WebElement product : products) {

            WebElement nameElement = product.findElement(productNameInsideCard);
            String cleanName = getCleanText(nameElement);
            String fullName = productActions.getProductNameText(nameElement);

            if (cleanName.equalsIgnoreCase(expected)
                    || fullName.equalsIgnoreCase(expected)
                    || fullName.toLowerCase()
                    .startsWith(expected.toLowerCase())) {

                log.info("Producto encontrado");
                return true;
            }
        }

        log.error("Producto no encontrado");
        return false;
    }

    //***************Métodos delegados a ProductActions **************

    public String getRandomProductName() {
        return productActions.getRandomProductName();
    }

    public void continueShopping() {
        productActions.continueShopping();
    }

    public CartPage viewCart(){
        return productActions.viewCart();
    }

    public Map<String, String> getAProductNameAndPrice() {
        return productActions.getAProductNameAndPrice();
    }

    public void addProduct(String productName) {
        productActions.addProduct(productName);
    }
}