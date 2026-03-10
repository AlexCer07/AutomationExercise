package PagesObjects;

import AbstractElements.AbstractElements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductPage extends AbstractElements {

    WebDriver driver;

    public ProductPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "title")
    WebElement allProductsTitle;

    @FindBy(className = "features_items")
    List<WebElement> productList;

    public boolean allProductsSuccessfully(){
        waitForPageLoad();
        waitForWebElementListToAppear(productList);
        String title = allProductsTitle.getText();
        String url = driver.getCurrentUrl();

        return (title.equalsIgnoreCase("all products") && url.endsWith("/products"));

    }

    public void addProduct(){

    }
}
