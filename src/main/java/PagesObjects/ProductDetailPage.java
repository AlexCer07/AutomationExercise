package PagesObjects;

import AbstractElements.AbstractElements;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

public class ProductDetailPage extends AbstractElements {

    WebDriver driver;

    public ProductDetailPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "product-information")
    WebElement productDetail;

    @FindBy(id = "quantity")
    WebElement quantityField;

    @FindBy(css = ".btn.btn-default.cart")
    WebElement addToCartButton;

    @FindBy(css = "[class='modal-content'] button")
    WebElement modalContinueShopping;

    @FindBy(css = "[class='modal-content'] a")
    WebElement modalViewCart;

    public Map<String, String> detailProduct(){

        Map<String,String> details = new HashMap<>();
        details.put("productName", productDetail.findElement(By.tagName("h2")).getText());
        details.put("productCategory", productDetail.findElement(By.tagName("p")).getText().split(":")[1].trim());
        details.put("productPrice", productDetail.findElement(By.cssSelector("span span")).getText());
        details.put("productAvailable", productDetail.findElement(By.xpath(".//p[1]/following-sibling::p[1]")).getText().split(":")[1].trim());
        details.put("productCondition", productDetail.findElement(By.xpath(".//p[3]")).getText().split(":")[1].trim());
        details.put("productBrand", productDetail.findElement(By.xpath(".//p[4]")).getText().split(":")[1].trim());

        return details;

    }

    public void addToCart(){
        addToCartButton.click();
    }

    public void addToCart(int quantity){

        for (int i  = 0; i < quantity-1; i++){
            quantityField.sendKeys(Keys.ARROW_UP);
        }

        addToCartButton.click();
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


}
