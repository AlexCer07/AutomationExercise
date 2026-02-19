package Test;

import PagesObjects.ContactUsPage;
import PagesObjects.HomePage;
import PagesObjects.LoginPage;
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
        info.put("password","PasswordTest123.");
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

    }

    @Test
    public void case2SuccessLogin(){
        LoginPage loginPage = homePage.goToLoginPage();

        loginPage.logIn("test454@test.com", "test");
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

        loginPage.userLogut();

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
}
