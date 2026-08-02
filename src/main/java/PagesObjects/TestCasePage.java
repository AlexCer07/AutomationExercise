package PagesObjects;

import AbstractElements.AbstractElements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class TestCasePage extends AbstractElements {

    WebDriver driver;

    public TestCasePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String pageSuccessfully(){
        waitToUrlContain("/test_case");
        waitForPageLoad();
        return driver.getCurrentUrl();
    }
}
