package Test;

import PagesObjects.*;
import Test.softvalidation.SoftValidation;
import TestComponents.BaseTest;
import data.getData;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.HashMap;
import java.util.Map;

public class SecondaryFunctionalities extends BaseTest {

    @Test(groups = {"functionalities"})
    public void case7ContactUsForm() {
        //Step 1 - Hacer clic en el botón 'Contact Us'
        ContactUsPage contactUsPage = homePage.goToContactUs();

        //Get Random Values to fill form
        Map<String,String> contactInfo = contactUsPage.fakeInfo();

        //Step 2 - Ingresar el nombre, email, asunto y mensaje en los campos correspondiente de la sección
        //Step 3 - Hacer clic en el botón 'seleccionar archivo'
        //Step 4 - Hacer clic en un archivo que se desee subir y hacer clic en aceptar
        //Step 5 - Hacer clic en el botón 'submit'
        contactUsPage.fillOutForm(contactInfo.get("name")
                , contactInfo.get("email")
                , contactInfo.get("sentence")
                , contactInfo.get("paragraph")
                , contactUsPage.filePathImage("imgTest.png"));


        //Step 6 - Hacer clic en el botón aceptar
        contactUsPage.acceptAlert();

        //Validate message successfully
        String successMessage = contactUsPage.successfullyMessage();
        Assert.assertEquals(successMessage, "Success! Your details have been submitted successfully.");

        //Step 7 - Hacer clic en el botón Home
        String urlHomePage = contactUsPage.goHome();
        Assert.assertEquals(urlHomePage, "https://automationexercise.com/");

    }

    @Test(groups = {"functionalities"})
    public void case8TestCasePage(){

        //Step 1 - Hacer clic en el botón 'Test Cases'
        TestCasePage testCasePage = homePage.goToTestCase();

        //Validate URL page
        String url = testCasePage.pageSuccessfully();
        Assert.assertEquals(url, "https://automationexercise.com/test_cases");
    }

    @Test(groups = {"functionalities"}, dataProvider = "getDataProductBlueTop", dataProviderClass = getData.class)
    public void case9productDetails(HashMap<String,String> input){

        SoftAssert softAssert = new SoftAssert();

        //Step 1 - Hacer clic en el botón 'Products'
        ProductPage productPage = homePage.goToProductPage();

        //validation to product page
        boolean allProductsView = productPage.allProductsSuccessfully();
        softAssert.assertTrue(allProductsView,"Title not found");

        //Step 2 - Hacer clic en el botón 'View Product' del primer producto
        ProductDetailPage productDetailPage = productPage.viewProduct(input.get("name"));

        //Validations to product info
        Map<String,String> details = productDetailPage.detailProduct();
        SoftValidation.validateProduct(input, details, softAssert);
        softAssert.assertAll();
    }

    @Test(groups = {"functionalities"})
    public void case11SubscriptionHome (){

        //Step 1 - 	Hacer scroll hasta el pie de pagina
        homePage.scrollToElement(homePage.getFooter());

        //Obtiene un email aleatorio
        String email = homePage.fakeInfo().get("email");

        //Step 2 - Ingresar email en el campo 'Your mail address'
        //Step 3 - Hacer clic en el botón arrow que se encuentra a lado del campo 'Your mail address'
        String subscriptionMsg = homePage.subscribe(email);

        Assert.assertEquals(subscriptionMsg,"You have been successfully subscribed!");
    }

    @Test(groups = {"functionalities"})
    public void case12SubscriptionCart (){

        //Step 1 - Hacer clic en el botón 'Cart'
        CartPage cartPage = homePage.goToCartPage();

        //Step 2 - Hacer scroll hasta el pie de página
        cartPage.scrollToElement(homePage.getFooter());

        String email = cartPage.fakeInfo().get("email");

        //Step 3 - Ingresar email en el campo 'Your mail address'
        //Step 4 - Hacer clic en el botón arrow que se encuentra a lado del campo 'Your mail address'
        String subscriptionMsg = cartPage.subscribe(email);
        Assert.assertEquals(subscriptionMsg,"You have been successfully subscribed!");
    }


    @Test(groups = {"functionalities"})
    public void test24ScrollArrow(){

        SoftAssert softAssert = new SoftAssert();

        //Step 1 - Mantener presionada la tecla flecha abajo hasta el pie de página
        softAssert.assertTrue(homePage.scrollDown(homePage.getFooter()), "Element not found");
        //Step 2 - Mantener presionada la tecla flecha arriba hasta el header de la página
        softAssert.assertTrue(homePage.scrollUp(homePage.getHeader()), "Element not found");

        softAssert.assertAll();
    }
}
