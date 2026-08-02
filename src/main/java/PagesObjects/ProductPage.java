package PagesObjects;

import AbstractElements.AbstractElements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductPage extends AbstractElements {

    WebDriver driver;

    public ProductPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "title")
    WebElement allProductsTitle;

    @FindBy(css = ".features_items div.col-sm-4")
    List<WebElement> productList;

    @FindBy(id = "search_product")
    WebElement searchField;

    @FindBy(id = "submit_search")
    WebElement searchButton;

    @FindBy(className = "modal-content")
    WebElement modalProductAdded;

    @FindBy(css = "[class='modal-content'] button")
    WebElement modalContinueShopping;

    @FindBy(css = "[class='modal-content'] a")
    WebElement modalViewCart;

    @FindBy(css = ".category-tab.shop-details-tab")
    WebElement opinionProductSection;



    By addProductButtonOverlay = By.xpath(".//div[@class='overlay-content']/a[@class='btn btn-default add-to-cart']");
    By nameField = By.cssSelector("[type='text']");
    By emailField = By.cssSelector("[type='email']");
    By commentField = By.cssSelector("[name='review']");
    By submit = By.id("button-review");
    By msg = By.cssSelector(".alert-success.alert");

    public boolean allProductsSuccessfully(){
        waitForPageLoad();
        waitForWebElementListToAppear(productList);
        String title = allProductsTitle.getText();
        String url = driver.getCurrentUrl();

        return (title.equalsIgnoreCase("all products") && url.endsWith("/products"));

    }

    public ProductDetailPage viewProduct(String nameProduct){

        List<WebElement> product = productList.stream()
                .filter( s -> s.findElement(By.tagName("p")).getText().equalsIgnoreCase(nameProduct))
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


    public void searchProduct(String name){
        searchField.sendKeys(name);
        searchButton.click();
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

    public String getProductsName(){
        List<String> products = productList.stream()
                .map(s -> s.findElement(By.tagName("p")).getText()).toList();

        String prod = products.get((int) (Math.random()*(products.size())));

        return prod;
    }

    public void addProduct(String productName) {

        WebElement productOnList = getProductElement(productName);
        moveToElement(productOnList);

        waitForWebElementToClickable(productOnList.findElement(addProductButtonOverlay));
        productOnList.findElement(addProductButtonOverlay).click();

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

    public String publishOpinion(Map<String,String> info){
        opinionProductSection.findElement(nameField).sendKeys(info.get("name"));
        opinionProductSection.findElement(emailField).sendKeys(info.get("email"));
        opinionProductSection.findElement(commentField).sendKeys(info.get("paragraph"));
        opinionProductSection.findElement(submit).click();

        return opinionProductSection.findElement(msg).getText();

    }



}
