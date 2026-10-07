package PagesObjects;

import AbstractElements.AbstractElements;
import AbstractElements.ProductActions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.HashMap;
import java.util.Map;

public class ProductDetailPage extends AbstractElements {

    WebDriver driver;
    ProductActions productActions;

    public ProductDetailPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        productActions = new ProductActions(driver);
    }

    //***************Elements*************

    @FindBy(id = "quantity")
    WebElement quantityField;

    @FindBy(css = ".btn.btn-default.cart")
    WebElement addToCartButton;

    private final By productDetailLocator = By.className("product-information");
    private final By productPriceLocator = By.cssSelector("span span");
    private final By productAvailableLocator = By.xpath(".//p[1]/following-sibling::p[1]");
    private final By productConditionLocator = By.xpath(".//p[3]");
    private final By productBrandLocator = By.xpath(".//p[4]");
    private final By categoryLocator = By.xpath(".//p[starts-with(normalize-space(.),'Category:')]");

    //**************Methods*************

    //Obtiene la información del producto desde la vista view product
    public Map<String, String> detailProduct() {

        log.info("Se obtienen detalles del producto de la vista view product");

        WebElement productDetail = waitForWebElementToAppear(productDetailLocator);

        Map<String, String> details = new HashMap<>();

        details.put("productName", getProductName(productDetail));
        details.put("productCategory", getProductCategory(productDetail));
        details.put("productPrice", productDetail.findElement(productPriceLocator).getText().trim());

        details.put("productAvailable", productDetail.findElement(productAvailableLocator)
                .getText()
                .split(":")[1]
                .trim()
        );

        details.put("productCondition", productDetail.findElement(productConditionLocator)
                        .getText()
                        .split(":")[1]
                        .trim()
        );

        details.put("productBrand", productDetail.findElement(productBrandLocator)
                        .getText()
                        .split(":")[1]
                        .trim()
                        .toUpperCase()
        );

        log.debug("productName: {}", details.get("productName"));
        log.debug("productCategory: {}", details.get("productCategory"));
        log.debug("productPrice: {}", details.get("productPrice"));
        log.debug("productAvailable: {}", details.get("productAvailable"));
        log.debug("productCondition: {}", details.get("productCondition"));
        log.debug("productBrand: {}", details.get("productBrand"));

        return details;
    }

    //Obtiene la categoria de un producto, debido a los anuncios es complicado obtenerlo fácilmente
    private String getProductCategory(WebElement productDetail) {

        WebElement categoryElement = productDetail.findElement(categoryLocator);
        String rawText = normalizeText(categoryElement.getText());
        String cleanText = getTextIgnoringAds(categoryElement);
        String category = cleanText.replaceFirst("(?i)^Category:\\s*", "").trim();

        log.debug("Categoría RAW: [{}]", rawText);
        log.debug("Categoría sin anuncios: [{}]", cleanText);
        log.debug("Categoría final: [{}]", category);

        return category;
    }

    //Obtiene el nombre de un producto, debido a los anuncios es complicado obtenerlo fácilmente
    private String getProductName(WebElement productDetail) {
        WebElement nameElement = productDetail.findElement(getH2Locator());
        String productName = normalizeText(nameElement.getText());
        log.debug("Nombre obtenido: [{}]", productName);
        return productName;
    }

    //método sobreescrito para agregar más de un tipo de producto desde la vista view product
    public void addToCart(int quantity){
        for (int i  = 0; i < quantity-1; i++){
            quantityField.sendKeys(Keys.ARROW_UP);
        }
        addToCartButton.click();
    }

    //calcula el total a pagar de un producto y lo devuelve con el formato del sitio web
    public String getTotalPrice(String unitPrice, int quantity){
        String []value = unitPrice.split(" ");
        int total = Integer.parseInt(value[1])*quantity;
        return value[0].concat(" "+total);
    }

    //***************Métodos delegados a ProductActions **************

    public CartPage viewCart(){
        return productActions.viewCart();
    }

}
