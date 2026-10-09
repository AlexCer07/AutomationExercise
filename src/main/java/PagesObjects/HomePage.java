package PagesObjects;

import AbstractElements.AbstractElements;
import AbstractElements.ProductActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.*;

public class HomePage extends AbstractElements {

    WebDriver driver;
    ProductActions productActions;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        productActions = new ProductActions(driver);
    }

    //*****************Elements****************

    @FindBy(css = "#recommended-item-carousel")
    WebElement recommendedProduct;

    private final By brandsItems = By.cssSelector(".brands-name li a");
    private final By activeSlide = By.cssSelector("#recommended-item-carousel .item.active");
    private final By viewProductBtn = By.xpath("//a[contains(@href,'product_details/')]");

    private final By rightArrow =
            By.xpath("//div[@id='recommended-item-carousel']//i[@class='fa fa-angle-right']");

    private final By visibleProducts =
            By.cssSelector("#recommended-item-carousel .item.active .productinfo.text-center");

    private final By recommendProductCardLocator =
            By.cssSelector("#recommended-item-carousel .item .productinfo.text-center");

    //****************Methods******************

    //Ingresa al sitio web cuando inician los test
    public void goTo() {
        log.info("Navegando al sitio web: 'https://automationexercise.com/'");
        driver.get("https://automationexercise.com/");
    }

    //Localiza el elemento que funciona como control para abrir/cerrar la sección
    private By getSectionToggle(String sectionName) {
        String capitalName = sectionName.substring(0,1)
                .toUpperCase()
                .concat(sectionName.substring(1)
                        .toLowerCase());
        return By.xpath("//a[@href='#" + capitalName + "']");
    }

    //localiza el panel que contiene las subcategorías
    private By getSectionPanel(String sectionName) {
        String capitalName = sectionName.substring(0,1)
                .toUpperCase()
                .concat(sectionName.substring(1)
                        .toLowerCase());
        return By.id(capitalName);
    }

    //Se obtienen los elementos de las categorias de una sección
    private By getCategoryItems(String sectionName) {
        String capitalName = sectionName.substring(0,1)
                .toUpperCase()
                .concat(sectionName.substring(1)
                        .toLowerCase());
        return By.cssSelector("#" + capitalName + " .panel-body ul li a");
    }

    //Expande la lista de elementos de una categoria general
    public void expandSection(String sectionName) {
        WebElement panel = driver.findElement(getSectionPanel(sectionName));

        if (!panel.isDisplayed()) {
            WebElement toggle = waitForWebElementToClickable(getSectionToggle(sectionName));
            jsClick(toggle);
            waitForVisibilityOfElementLocated(getSectionPanel(sectionName));
        }
    }

    //Se navega hacia una categoria
    public void selectCategory(String sectionName, String categoryName) {

        expandSection(sectionName);
        List<WebElement> items = waitAllElementsVisible(getCategoryItems(sectionName));

        log.info("Se selecciona la categoria: {} > {}", sectionName, categoryName);

        WebElement category = items.stream()
                .filter(item -> item.getText().trim().equalsIgnoreCase(categoryName.trim()))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException(
                                "No se encontró la categoría '" + categoryName + "' en '" + sectionName + "'"));

        String oldUrl = driver.getCurrentUrl();
        jsClick(category);
        waitForUrlToChange(oldUrl);
    }

    //Se obtiene el nombre de una categoria de forma aleatoria para una sección dada
    public String getRandomCategory(String sectionName) {

        List<WebElement> items = waitPresenceOfAllElementsLocatedBy(getCategoryItems(sectionName));

        if (items.isEmpty()) {
            throw new RuntimeException("No se encontraron categorías para " + sectionName);
        }

        Random random = new Random();

        return Objects.requireNonNull(items.get(random.nextInt(items.size()))
                .getDomProperty("textContent"))
                .trim();
    }


    //Se selecciona la marca dada
    public void selectBrand(String brandName){
        List<WebElement> items = waitForWebElementListToAppearBy(brandsItems);

        WebElement category = items.stream()
                .filter(item -> item.getText().trim().contains(brandName.trim()))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("No se encontró la marca " + brandName));

        log.info("Se selecciona la marca: {}",brandName);

        String oldUrl = driver.getCurrentUrl();
        jsClick(category);
        waitForUrlToChange(oldUrl);
    }

    //Se obtiene el nombre de una marca de manera aleatoria
    public String getRandomBrand(){
        List<WebElement> items = waitForWebElementListToAppearBy(brandsItems);

        if (items.isEmpty()) {
            throw new RuntimeException("No se encontraron la marcas");
        }

        List<String> brandNames = items.stream()
                .map(brand -> brand.getText()
                        .replace(brand.findElement(By.tagName("span")).getText(), "")
                        .trim())
                .toList();

        Random random = new Random();

        return brandNames.get(random.nextInt(items.size())).trim();
    }

    //Se anvega hasta la sección de productos recomendados
    public void goToRecommendedItems(){
        scrollToElement(recommendedProduct);
    }

    //Se obtiene el nombre de un producto de la sección recomendados de forma aleatoria
    public Map<String,String> randomRecommendProduct(){
        List<WebElement> productCards = waitForWebElementListToBePresentBy(recommendProductCardLocator);

        int randomIndex = (int) (Math.random() * productCards.size());

        WebElement productCard = productCards.get(randomIndex);
        String price = productCard.findElement(By.cssSelector("h2")).getDomProperty("textContent");
        String name = productCard.findElement(By.cssSelector("p")).getDomProperty("textContent");
        Map<String, String> productInfo = new HashMap<>();

        productInfo.put("name", name);
        productInfo.put("price", price);

        log.info("Producto a buscar de la sección recomendados: {}", productInfo.get("name"));

        return productInfo;
    }

    //Se busca un producto en la sección de recomendados
    public void addRecommendProduct(String productName) {

        int maxAttempts = 10;

        for (int i = 0; i < maxAttempts; i++) {
            List<WebElement> products = waitAllElementsVisible(visibleProducts);

            for (WebElement product : products) {
                String currentName = normalizeText(product.findElement(By.tagName("p")).getText());
                log.debug("Buscando: {}", productName);
                log.debug("Producto visible: {}", currentName);

                if (currentName.equalsIgnoreCase(normalizeText(productName))) {
                    WebElement addToCart = product.findElement(By.cssSelector(".add-to-cart"));
                    waitForWebElementToClickable(addToCart);
                    addToCart.click();
                    return;
                }
            }

            // Guardamos el slide actual
            WebElement oldSlide = driver.findElement(activeSlide);

            // Clic siguiente
            WebElement btn = waitForWebElementToClickable(rightArrow);
            btn.click();

            // Esperamos que el slide anterior deje de ser el activo
            waitForElementToChange(activeSlide, oldSlide);
        }

        throw new RuntimeException("Recommended product not found: " + productName);
    }

    //Se contruye el nombre de la categoria para validaciones en el website
    public String buildCategory(String category, String sectionName) {
        String capitalName = category.substring(0,1).toUpperCase()
                .concat(category.substring(1)
                        .toLowerCase());

        log.debug("Nombre de categoria construida: {}", capitalName.concat(" > " + sectionName));

        return capitalName.concat(" > " + sectionName);
    }

    //Redirige a la vista view Product de un producto
    public ProductDetailPage viewProduct(String nameProduct){
        //Obtiene el card del producto
        WebElement product = productActions.getProductElement(nameProduct);
        scrollToElement(product);
        //Hace clic en el botón view product
        product.findElement(viewProductBtn).click();

        log.info("Se visualizan los detalles del producto: {}", nameProduct);

        return new ProductDetailPage(driver);
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
