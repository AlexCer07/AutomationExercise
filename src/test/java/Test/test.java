package Test;

import PagesObjects.*;
import TestComponents.BaseTest;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class test extends BaseTest {

    @Test
    public void case1RegisterUser(){
        LoginPage loginPage = homePage.goToLoginPage();

        Faker fakerInfo = new Faker();

        loginPage.signUp("test", fakerInfo.internet().emailAddress());

        Map<String, String> info = new HashMap<>();
        info.put("title","mrs.");
        //info.put("password","PasswordTest123.");
        info.put("password", fakerInfo.internet().password());
        info.put("birthday","07-30-1987");
        info.put("firstName", fakerInfo.name().firstName());
        info.put("lastName", fakerInfo.name().lastName());
        info.put("company", fakerInfo.company().name());
        info.put("address1", fakerInfo.address().streetAddress());
        info.put("address2", fakerInfo.address().secondaryAddress());
        info.put("country","Canada");
        info.put("state", fakerInfo.address().state());
        info.put("city", fakerInfo.address().city());
        info.put("zipCode", fakerInfo.address().zipCode());
        info.put("mobileNumber", fakerInfo.phoneNumber().cellPhone());

        loginPage.informationAccount(info);


        String accountCreatedMsg = loginPage.accountCreated();
        Assert.assertEquals(accountCreatedMsg, "ACCOUNT CREATED!");

        loginPage.closeAdsIfPresent();

        String userLoggedMsg = loginPage.userLogged();
        Assert.assertEquals(userLoggedMsg, "Logged in as test");

        loginPage.deleteAccount();
        String accountDeletedMsg = loginPage.accountDeleted();
        Assert.assertEquals(accountDeletedMsg, "ACCOUNT DELETED!");

    }

    @Test
    public void case2SuccessLogin(){
        //Redundant
        LoginPage loginPage = homePage.goToLoginPage();

        loginPage.logIn("test454@test.com", "test");
        String userLoggedMsg = loginPage.userLogged();
        Assert.assertEquals(userLoggedMsg, "Logged in as test");
    }

    @Test
    public void case3LoginIncorrect(){
        LoginPage loginPage =  homePage.goToLoginPage();
        loginPage.logIn("test@test.com", "testPa$$word123");
        String msgFail = loginPage.getErrorLoginMessage();

        Assert.assertEquals(msgFail,"Your email or password is incorrect!",
                "Test Case failed, message not find");

    }

    @Test
    public void case4LogOut(){
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.logIn("test454@test.com", "test");

        String userLogged = loginPage.userLogged();
        Assert.assertEquals(userLogged, "Logged in as test",
                "Test Case failed, usser not find");

        String url = loginPage.userLogut();
        Assert.assertEquals("https://automationexercise.com/login", url);

    }

    @Test
    public void case5(){
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.signUp("test","test454@test.com");

        String msgFail = loginPage.getErrorSignUpMessage();

        Assert.assertEquals(msgFail,"Email Address already exist!",
                "Test Case failed, message not find");
    }

    @Test
    public void case6ContactUsForm(){
        ContactUsPage contactUsPage = homePage.goToContactUs();

        String name="Test",
        mail ="test@test.com",
        subject = "Testing form",
        message = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Nullam bibendum eget lacus vitae tincidunt. Phasellus.",
                filePath="C://Users/alexc/Downloads/ModelTcp.png";

        String successMesage = contactUsPage.fillOutForm(name, mail, subject, message,filePath);

        Assert.assertEquals(successMesage, "Success! Your details have been submitted successfully.");

        String urlHomePage = contactUsPage.goHome();

        Assert.assertEquals(urlHomePage, "https://automationexercise.com/");


    }

    @Test
    public void case7TestCasePage(){
        TestCasePage testCasePage = homePage.goToTestCase();
        String url = testCasePage.pageSuccessfully();
        Assert.assertEquals("https://automationexercise.com/test_cases", url);

    }

    @Test
    public void case8productDetails(){
        ProductPage productPage = homePage.goToProductPage();

        boolean allProductsView = productPage.allProductsSuccessfully();
        Assert.assertTrue(allProductsView);

    }
}
