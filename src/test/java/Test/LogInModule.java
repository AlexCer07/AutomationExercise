package Test;

import PagesObjects.*;
import TestComponents.BaseTest;
import data.getData;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

public class LogInModule extends BaseTest {

    @Test(groups = {"login"})
    public void case1and6RegisterUserAndDelete() {

        SoftAssert softAssert = new SoftAssert();
        //Step 1 - 	Hacer clic en el botón 'Signup / Login'
        LoginPage loginPage = homePage.goToLoginPage();

        // get Random values to signup
        Map<String, String> userData = loginPage.fakeInfoSignup();

        //Step 2 - Ingresar nombre y email válidos en los campos correspondiente de la sección	signup
        //Step 3 - Hacer clic en el botón Signup
        loginPage.signUp(userData.get("userName"), userData.get("email"));

        //Step 4 - Completar la información obligatoria de la cuenta.
        //Step 5 - seleccionar la opción sign up for our newsletter.
        //Step 6 - seleccionar la opción Receive special offers from our partners.
        //Step 7 - Completar la información de dirección obligatoria.
        //Step 8 - Presionar el botón Create Account.
        loginPage.informationAccount(userData);

        //Step 9 - Hacer clic en el botón continuar
        String accountCreatedMsg = loginPage.accountCreated();
        softAssert.assertEquals(accountCreatedMsg, "ACCOUNT CREATED!",
                "Wrong Message");


        //Validate signup successfully
        String userLoggedMsg = loginPage.userLogged();
        softAssert.assertEquals(userLoggedMsg, "Logged in as " + userData.get("userName"),
                "User Incorrect");

        //*** Case 6 ***

        //Step 1 - Hacer clic en el botón 'Delete Account'
        loginPage.deleteAccount();

        //Validate message user delete
        String accountDeletedMsg = loginPage.accountDeleted();
        softAssert.assertEquals(accountDeletedMsg, "ACCOUNT DELETED!", "Wrong Message");

        //Step 2 - Hacer clic en el botón 'Signup / Login'
        loginPage.goToLoginPage();
        //Step 3 - Ingresar el email y contraseña de la cuenta eliminada,
        // en los campos correspondientes de la sección
        //Step 4 - Hacer clic en el botón 'Login'
        loginPage.logIn(userData.get("email"), userData.get("password"));

        //Validate user deleted
        String msgFail = loginPage.getErrorLoginMessage();
        softAssert.assertEquals(msgFail, "Your email or password is incorrect!",
                "Test Case failed, message not find");
    }


    @Test(groups = {"login"}, dataProvider = "getDataLogin", dataProviderClass = getData.class)
    public void case2and3LoginLogout(HashMap<String, String> input) {

        //Step 1 - Hacer clic en el botón 'Signup / Login'
        LoginPage loginPage = homePage.goToLoginPage();

        //Step 2 - Ingresar el email y contraseña de una cuenta ya registrada
        // en los campos correspondientes de la sección de login
        //Step 3 - Hacer clic en el botón 'Login'
        loginPage.logIn(input.get("email"), input.get("password"));

        // Validate login successfully
        String userLoggedMsg = loginPage.userLogged();
        Assert.assertEquals(userLoggedMsg, "Logged in as " + input.get("username"), "User Incorrect");

        //*** Case 3 ***
        //Step 1 - Hacer clic en el botón 'Logout'
        String url = loginPage.userLogout();

        //si se puede entrar a este link es porque el usuario no está autenticado
        Assert.assertEquals(url, "https://automationexercise.com/login", "Url Incorrect");
    }


    @Test(groups = {"login"})
    public void case4FailLogin() {

        //Step 1 - Hacer clic en el botón 'Signup / Login'
        LoginPage loginPage =  homePage.goToLoginPage();

        //Get random values to login
        Map<String,String> userData = loginPage.fakeInfo();

        //Step 2 - Ingresar el email y contraseña de una cuenta que no se encuentre registrada,
        // en los campos correspondientes de la sección
        //Step 3 - Hacer clic en el botón 'Login'
        loginPage.logIn(userData.get("email"), userData.get("password"));
        String msgFail = loginPage.getErrorLoginMessage();

        Assert.assertEquals(msgFail,"Your email or password is incorrect!",
                "Test Case failed, message not find");

    }


    @Test(groups = {"login"}, dataProvider="getDataLogin", dataProviderClass = getData.class)
    public void case5FailSignup(HashMap<String,String> input){

        //Step 1 - Hacer clic en el botón 'Signup / Login'
        LoginPage loginPage = homePage.goToLoginPage();

        //Step 2 - Ingresar nombre y email válidos de una cuenta registrada previamente
        // en los campos correspondiente de la sección
        //Step 3 - Hacer clic en el botón Signup
        loginPage.signUp(input.get("username"),input.get("email"));
        String msgFail = loginPage.getErrorSignUpMessage();

        Assert.assertEquals(msgFail,"Email Address already exist!",
                "Test Case failed, message not find");
    }

}