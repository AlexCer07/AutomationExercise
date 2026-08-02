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

public class CartPage extends AbstractElements {

    WebDriver driver;

    public CartPage(WebDriver driver){
        super (driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "cart_info_table")
    WebElement itemTable;


    @FindBy(className = "cart_quantity_delete")
            List<WebElement> allRemoveButtons;

    @FindBy(css = ".btn.btn-default.check_out")
            WebElement proceedToCheckoutButton;

    @FindBy(css = ".modal-content a")
            WebElement modalRegisterLoginButton;

    @FindBy(css = ".modal-content button")
    WebElement modalContinueOnCartButton;

    By description = By.cssSelector(".cart_description h4 a");

    @FindBy(css = "#cart_info_table tbody tr")
    List<WebElement> productItems;

    @FindBy(css = "#empty_cart p")
    WebElement cartEmptyMsg;

    public int getSizeProductsSection(){
        waitForWebElementToAppear(cartEmptyMsg);
        return productItems.size();
    }

    public String getCartEmptyMsg(){
        waitForWebElementToAppear(cartEmptyMsg);
        return cartEmptyMsg.getText();
    }


    public Map<String, String> getProduct(String name){

        List<WebElement> product = productItems.stream().filter(s -> s.findElement(description).getText().equalsIgnoreCase(name)).collect(Collectors.toList());
        WebElement productRow = product.get(0);

        Map<String, String> productDetailOnCart = new HashMap<>();
        List<WebElement> cols = productRow.findElements(By.tagName("td"));

        productDetailOnCart.put("productName", cols.get(1).findElement(By.cssSelector("h4 a")).getText());
        productDetailOnCart.put("category", cols.get(1).findElement(By.cssSelector("p")).getText());
        productDetailOnCart.put("unitPrice", cols.get(2).getText());
        productDetailOnCart.put("quantity", cols.get(3).getText());
        productDetailOnCart.put("totalPrice", cols.get(4).getText());

        return productDetailOnCart;
    }

    public void removeAllProducts(){

        for(WebElement removeButton : allRemoveButtons){
            removeButton.click();
        }

    }

    public CheckOutPage proceedToCheckout(){
        proceedToCheckoutButton.click();
        return new CheckOutPage(driver);
    }


    public void continueOnCart(){

        waitForWebElementToAppear(modalContinueOnCartButton);
        modalContinueOnCartButton.click();
    }

    public LoginPage registerOrLogin(){

        waitForWebElementToAppear(modalRegisterLoginButton);
        modalRegisterLoginButton.click();
        return new LoginPage(driver);
    }
}
