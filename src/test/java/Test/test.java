package Test;

import PagesObjects.HomePage;
import PagesObjects.LoginPage;
import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class test extends BaseTest {

    @Test
    public void submitOrder(){
        homePage.goToProductPage();

    }

    @Test
    public void case3LoginIncorrect(){
        LoginPage loginPage =  homePage.goToLoginPage();
        String msgFail = loginPage.logIn("test@test.com", "testPa$$word123");

        Assert.assertEquals(msgFail,"Your email or password is incorrect!",
                "Test Case failed, message not find");

    }
}
