package PagesObjects;

import AbstractElements.AbstractElements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;
import java.util.stream.Collectors;

public class HomePage extends AbstractElements {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(css = ".features_items div.col-sm-4")
    List<WebElement> productList;

    @FindBy(css = "[class='modal-content'] button")
    WebElement modalContinueShopping;

    @FindBy(css = "[class='modal-content'] a")
    WebElement modalViewCart;

    @FindBy(css = ".brands-name li a")
            List<WebElement> brandsItems;

    @FindBy(id = "accordian")
            WebElement sidebarCategory;

    @FindBy(css = "#recommended-item-carousel")
    WebElement recommendedProduct;

    By addProductButtonOverlay = By.xpath(".//div[@class='overlay-content']/a[@class='btn btn-default add-to-cart']");

    By visibleProducts = By.cssSelector(".item.active .productinfo");
    By nextProducts = By.cssSelector("a.left");

    public void goTo() {
        driver.get("https://automationexercise.com/");
    }


    public ProductDetailPage viewProduct(String nameProduct) {

        List<WebElement> product = productList.stream()
                .filter(s -> s.findElement(By.tagName("p")).getText().equalsIgnoreCase(nameProduct))
                .collect(Collectors.toList());

        product.get(0).findElement(By.xpath("//a[contains(@href,'product_details/')]")).click();

        return new ProductDetailPage(driver);
    }

    public WebElement getProductElement(String nameProduct){

        WebElement product = productList.stream()
                .filter( s -> s.findElement(By.tagName("p")).getText().equalsIgnoreCase(nameProduct))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product Not Find"));

        return product;
    }

    public Map<String, String> getAProductNameAndPrice(){
        List<String> products = productList.stream()
                .map(s -> s.findElement(By.tagName("p")).getText()).toList();

        String prod = products.get((int) (Math.random()*(products.size())));
        List<String> price = productList.stream()
                .filter(s -> s.findElement(By.tagName("p")).getText().equalsIgnoreCase(prod))
                .map(s -> s.findElement(By.tagName("h2")).getText()).toList();

        Map<String,String> productInfo = new HashMap<>();
        productInfo.put("name" , prod);
        productInfo.put("price", price.get(0));

        return productInfo;
    }

    public String getAProductName(){
        List<String> products = productList.stream()
                .map(s -> s.findElement(By.tagName("p")).getText()).toList();

        String prod = products.get((int) (Math.random()*(products.size())));


        return prod;
    }

    public void addProduct(String productName) {

        WebElement productOnList = getProductElement(productName);
        moveToElement(productOnList);

        waitForWebElementToClickable(productOnList.findElement(addProductButtonOverlay));
        WebElement addBtn = productOnList.findElement(addProductButtonOverlay);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);

    }

    public void continueShopping(){
        waitForWebElementToAppear(modalContinueShopping);
        modalContinueShopping.click();
    }

    public CartPage viewCart(){
        waitForWebElementToAppear(modalContinueShopping);
        modalViewCart.click();
        return new CartPage(driver);
    }

    private By getSectionToggle(String sectionName) {

        String capitalName = sectionName.substring(0,1).toUpperCase().concat(sectionName.substring(1).toLowerCase());
        return By.xpath("//a[@href='#" + capitalName + "']");
    }

    private By getSectionPanel(String sectionName) {

        String capitalName = sectionName.substring(0,1).toUpperCase().concat(sectionName.substring(1).toLowerCase());
        return By.id(capitalName);
    }

    private By getCategoryItems(String sectionName) {
        String capitalName = sectionName.substring(0,1).toUpperCase().concat(sectionName.substring(1).toLowerCase());

        return By.cssSelector("#" + capitalName + " .panel-body ul li a");
    }



    public void expandSection(String sectionName){
        WebElement toggle = waitForWebElementToClickable(getSectionToggle(sectionName));
        toggle.click();

        waitForVisibilityOfElementLocated(getSectionPanel(sectionName));
    }

    public void selectCategory(String sectionName, String categoryName){

        expandSection(sectionName);

        List<WebElement> items = waitAllElementsVisible(getCategoryItems(sectionName));

        items.stream().filter( s -> s.getText().equalsIgnoreCase(categoryName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontró la categoría '" + categoryName + "' en '" + sectionName + "'"))
                .click();
    }

    public String getRandomCategory(String sectionName){

        List<WebElement> items = sidebarCategory.findElements(getCategoryItems(sectionName));

        Random random = new Random();
        return items.get(random.nextInt(items.size())).getDomProperty("textContent").trim();
    }

    public void selectBrand(String name){
        brandsItems.stream().filter(s -> s.getText().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Brand "+ name + " not found"))
                .click();
    }

    public String getRandomBrand(){

        return brandsItems.get((int) Math.random()*brandsItems.size()).getText().trim();
    }


    public void goToRecommendedItems(){

        scrollToElement(recommendedProduct);
    }

    public void viewRecommendProduct(String productName){

        int maxAttempts = 10;

        for(int i = 0; i < maxAttempts; i++){
            List<WebElement> products = waitAllElementsVisible(visibleProducts);

            for (WebElement product : products){
                String currentName = product.findElement(By.tagName("p")).getText().trim();

                if(currentName.equalsIgnoreCase(productName)){

                    WebElement addToCart = product.findElement(By.cssSelector(".add-to-cart"));
                    waitForWebElementToClickable(addToCart);
                    addToCart.click();
                    return;
                }
            }


            /*products.stream()
                    .filter(s -> s.findElement(By.tagName("p")).getText().trim().equalsIgnoreCase(productName))
                    .findFirst()
                    .ifPresent(
                            s -> {
                                WebElement addToCart = s.findElement(By.cssSelector(".add-to-cart"));
                                waitForWebElementToClickable(addToCart);
                                //addToCart.click();
                                ((JavascriptExecutor) driver)
                                        .executeScript("arguments[0].click();", addToCart);
                                waitToLocatedElement(By.cssSelector("[class='modal-content'] a"));
                                return;
                            }
                    );*/


        }

        //throw new NoSuchElementException("no se encontro el producto: "+ productName);

    }

}
