package Test;

import AbstractElements.AdHandler;
import PagesObjects.*;
import Test.softvalidation.SoftValidation;
import TestComponents.BaseTest;
import com.github.javafaker.Faker;
import data.getData;
import net.bytebuddy.asm.MemberSubstitution;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class test extends BaseTest {

    protected String email;
    protected String username;
    protected String password;



    @Test
    public void case1and6RegisterUserAndDelete(){
        adHandler.closeAdsIfPresent();
        //Step 1
        LoginPage loginPage = homePage.goToLoginPage();

        //Random values
        Map<String,String> userData = loginPage.fakeInfoSignup();

        //Step 2 and 3
        loginPage.signUp( userData.get("userName"), userData.get("email"));

        adHandler.closeAdsIfPresent();
        //Steps 4 through 8
        loginPage.informationAccount(userData);

        //Step 9
        adHandler.closeAdsIfPresent();
        String accountCreatedMsg = loginPage.accountCreated();
        Assert.assertEquals(accountCreatedMsg, "ACCOUNT CREATED!");

        adHandler.closeAdsIfPresent();

        String userLoggedMsg = loginPage.userLogged();
        Assert.assertEquals(userLoggedMsg, "Logged in as " + userData.get("userName"));


        //case 6
        adHandler.closeAdsIfPresent();

        loginPage.deleteAccount();
        String accountDeletedMsg = loginPage.accountDeleted();
        Assert.assertEquals(accountDeletedMsg, "ACCOUNT DELETED!");


    }

    @Test(dataProvider="getDataLogin", dataProviderClass = getData.class)
    public void case2and3LoginLogout(HashMap<String,String> input){

        //Step 1
        adHandler.closeAdsIfPresent();
        LoginPage loginPage = homePage.goToLoginPage();

        //Step 2 and 3
        adHandler.closeAdsIfPresent();
        loginPage.logIn(input.get("email"), input.get("password"));

        adHandler.closeAdsIfPresent();
        String userLoggedMsg = loginPage.userLogged();
        Assert.assertEquals(userLoggedMsg, "Logged in as "+input.get("username"));

        //case 3
        String url = loginPage.userLogout();
        Assert.assertEquals("https://automationexercise.com/login", url);
    }



    @Test
    public void case4FailLogin() {

        adHandler.closeAdsIfPresent();
        //Step 1
        LoginPage loginPage =  homePage.goToLoginPage();

        adHandler.closeAdsIfPresent();
        //Random values
        Map<String,String> userData = loginPage.fakeInfo();

        //Step 2 and 3
        loginPage.logIn(userData.get("email"), userData.get("password"));
        String msgFail = loginPage.getErrorLoginMessage();

        Assert.assertEquals(msgFail,"Your email or password is incorrect!",
                "Test Case failed, message not find");

    }



    @Test(dataProvider="getDataLogin", dataProviderClass = getData.class)
    public void case5FailSignup(HashMap<String,String> input){

        adHandler.closeAdsIfPresent();

        //Step 1
        LoginPage loginPage = homePage.goToLoginPage();

        adHandler.closeAdsIfPresent();

        //Step 2 and 3
        loginPage.signUp(input.get("username"),input.get("email"));
        String msgFail = loginPage.getErrorSignUpMessage();

        Assert.assertEquals(msgFail,"Email Address already exist!",
                "Test Case failed, message not find");
    }

    @Test
    public void case7ContactUsForm() throws InterruptedException {

        adHandler.closeAdsIfPresent();

        //Step 1
        ContactUsPage contactUsPage = homePage.goToContactUs();

        adHandler.closeAdsIfPresent();

        //Random Values
        Map<String,String> contactInfo = contactUsPage.fakeInfo();

        //Step 2 through 5
        contactUsPage.fillOutForm(contactInfo.get("name")
                , contactInfo.get("email")
                , contactInfo.get("sentence")
                , contactInfo.get("paragraph")
                , contactUsPage.filePathImage("imgTest.png"));

        //Step 6
        contactUsPage.acceptAlert();

        adHandler.closeAdsIfPresent();

        //Step 7
        String successMessage = contactUsPage.successfullyMessage();

        Assert.assertEquals(successMessage, "Success! Your details have been submitted successfully.");

        //Step 8
        String urlHomePage = contactUsPage.goHome();

        adHandler.closeAdsIfPresent();

        Assert.assertEquals(urlHomePage, "https://automationexercise.com/");


    }

    @Test
    public void case8TestCasePage(){

        //Step 1
        TestCasePage testCasePage = homePage.goToTestCase();

        String url = testCasePage.pageSuccessfully();
        Assert.assertEquals("https://automationexercise.com/test_cases", url);

    }

    @Test(dataProvider = "getDataProductBlueTop", dataProviderClass = getData.class)
    public void case9productDetails(HashMap<String,String> input) throws InterruptedException {

        //Step 1
        ProductPage productPage = homePage.goToProductPage();

        boolean allProductsView = productPage.allProductsSuccessfully();
        Assert.assertTrue(allProductsView);

        //Step 2
        ProductDetailPage productDetailPage = productPage.viewProduct(input.get("name"));

        Map<String,String> details = productDetailPage.detailProduct();

        //Validations
        SoftAssert softAssert = new SoftAssert();
        SoftValidation.validateProduct(input, details, softAssert);
        softAssert.assertAll();

    }





    @Test
    public void case10SearchProduct(){

        //Step 1
        ProductPage productPage = homePage.goToProductPage();

        //Step 2
        String productName = productPage.getProductsName();
        productPage.searchProduct(productName);

        Assert.assertEquals(productPage.getProductsName(), productName);

    }

    @Test
    public void case11SubscriptionHome (){

        //Step 1
        homePage.scrollToFooter();

        String email = homePage.fakeInfo().get("email");

        //Step 2
        homePage.subscribe(email);
        String subscriptionMsg = homePage.successSubscription();;

        Assert.assertEquals(subscriptionMsg,"You have been successfully subscribed!");

    }

    @Test
    public void case12SubscriptionCart (){

        CartPage cartPage = homePage.goToCartPage();

        //Step 1
        cartPage.scrollToFooter();

        String email = cartPage.fakeInfo().get("email");

        //Step 2
        cartPage.subscribe(email);
        String subscriptionMsg = cartPage.successSubscription();;

        Assert.assertEquals(subscriptionMsg,"You have been successfully subscribed!");

    }

    @Test(dataProvider = "twoProducts", dataProviderClass = getData.class)
    public void case13AddProductsToCart(HashMap<String,String> product1,
                                        HashMap<String,String> product2) throws InterruptedException {

        //Step 1
        ProductPage productPage = homePage.goToProductPage();

        //Step 2 and 3
        //String productName = productPage.getProductsName();
        productPage.addProduct(product1.get("name"));

        product1.put("quantity", "1");
        product1.put("totalPrice", product1.get("price"));

        //Step 4
        productPage.continueShopping();

        //Step 6
        //String productNameTwo = productPage.getProductsName();
        productPage.addProduct(product2.get("name"));


        product2.put("quantity", "1");
        product2.put("totalPrice", product2.get("price"));
        //Step 7
        CartPage cartPage = productPage.viewCart();

        Map<String,String> prodOnCart1 = cartPage.getProduct(product1.get("name"));
        Map<String,String> prodOnCart2 = cartPage.getProduct(product2.get("name"));


        //Validations
        SoftAssert softAssert = new SoftAssert();
        SoftValidation.validateProductOnCart(product1, prodOnCart1, softAssert);
        SoftValidation.validateProductOnCart(product2, prodOnCart2, softAssert);
        softAssert.assertAll();

    }

    @Test(dataProvider = "getDataProductBlueTop", dataProviderClass = getData.class)
    public void case14AddMoreOneTypeProduct(HashMap<String,String> input){
        int quantity = 4;

        //step 1
        ProductDetailPage productDetailPage = homePage.viewProduct(input.get("name"));

        //step 2 and 3
        productDetailPage.addToCart(quantity);

        Map<String,String> details = productDetailPage.detailProduct();

        //Validations
        SoftAssert softAssert = new SoftAssert();
        SoftValidation.validateProduct(input, details, softAssert);


        input.put("quantity", String.valueOf(quantity));
        input.put("totalPrice", productDetailPage.getTotalPrice(input.get("price"), quantity));

        //step 4
        CartPage cartPage = productDetailPage.viewCart();

        Map<String,String> prodOnCart = cartPage.getProduct(input.get("name"));

        //Validations

        SoftValidation.validateProductOnCart(input, prodOnCart, softAssert);
        softAssert.assertAll();


    }

    @Test
    public void case15MakeAnOrderAndRegisterUser() throws InterruptedException {

        Map<String,String> product = homePage.getAProductNameAndPrice();

        //Step 1
        homePage.addProduct(product.get("name"));

        //step 2
        homePage.continueShopping();

        //step 3
        CartPage cartPage = homePage.goToCartPage();

        Map<String,String> prodOnCart = cartPage.getProduct(product.get("name"));

        product.put("quantity", "1");
        product.put("totalPrice", product.get("price"));

        //Validations
        SoftAssert softAssert = new SoftAssert();
        SoftValidation.shortValidateProductOnCart(product, prodOnCart, softAssert);

        //Step 4
        cartPage.proceedToCheckout();

        //Step 5
        LoginPage loginPage = cartPage.registerOrLogin();

        //Random values
        Map<String,String> userData = loginPage.fakeInfoSignup();

        //Step 6 and 7
        loginPage.signUp( userData.get("userName"), userData.get("email"));

        //Steps 8 through 10
        loginPage.informationAccount(userData);

        String accountCreatedMsg = loginPage.accountCreated();
        softAssert.assertEquals(accountCreatedMsg, "ACCOUNT CREATED!");

        //Step 11
        String userLoggedMsg = loginPage.userLogged();
        softAssert.assertEquals(userLoggedMsg, "Logged in as " + userData.get("userName"));


        //step 12
        loginPage.goToCartPage();
        prodOnCart = cartPage.getProduct(product.get("name"));


        //Validations
        SoftValidation.shortValidateProductOnCart(product, prodOnCart, softAssert);

        //step 13
        CheckOutPage checkOutPage = cartPage.proceedToCheckout();

        SoftValidation.validateAddressOrBillingDetail(userData,checkOutPage.deliveryAddressInfo(),softAssert);
        SoftValidation.validateAddressOrBillingDetail(userData,checkOutPage.billingAddressInfo(),softAssert);


        //Step 14
        Map<String,String> paymentInfo = checkOutPage.fakePaymentInfo();
        checkOutPage.fillMsgAboutOrder(paymentInfo.get("paragraph"));

        //Step 15
        PaymentPage paymentPage = checkOutPage.placeOrder();

        //Step 16 and 17
        String orderPlacedMsg = paymentPage.fillFormPayment(paymentInfo);
        softAssert.assertEquals(orderPlacedMsg, "Congratulations! Your order has been confirmed!");


        softAssert.assertAll();

        loginPage.deleteAccount();

    }

    @Test(dataProvider="getDataLogin", dataProviderClass = getData.class)
    public void case16MakeAnOrderWithUserLogged (HashMap<String,String> input) throws InterruptedException {

        Map<String,String> product = homePage.getAProductNameAndPrice();

        //Step 1
        homePage.addProduct(product.get("name"));

        //step 2
        homePage.continueShopping();

        //step 3
        CartPage cartPage = homePage.goToCartPage();

        Map<String,String> prodOnCart = cartPage.getProduct(product.get("name"));

        product.put("quantity", "1");
        product.put("totalPrice", product.get("price"));

        //Validations
        SoftAssert softAssert = new SoftAssert();
        SoftValidation.shortValidateProductOnCart(product, prodOnCart, softAssert);

        //Step 4
        cartPage.proceedToCheckout();

        //Step 5
        LoginPage loginPage = cartPage.registerOrLogin();


        //Step 6 and 7
        loginPage.logIn( input.get("email"), input.get("password"));


        //Step 11
        String userLoggedMsg = loginPage.userLogged();
        softAssert.assertEquals(userLoggedMsg, "Logged in as " + input.get("username"));


        //step 12
        loginPage.goToCartPage();
        prodOnCart = cartPage.getProduct(product.get("name"));


        //Validations
        SoftValidation.shortValidateProductOnCart(product, prodOnCart, softAssert);

        //step 13
        CheckOutPage checkOutPage = cartPage.proceedToCheckout();

        SoftValidation.validateAddressOrBillingDetail(input,checkOutPage.deliveryAddressInfo(),softAssert);
        SoftValidation.validateAddressOrBillingDetail(input,checkOutPage.billingAddressInfo(),softAssert);


        //Step 14
        Map<String,String> paymentInfo = checkOutPage.fakePaymentInfo();
        checkOutPage.fillMsgAboutOrder(paymentInfo.get("paragraph"));

        //Step 15
        PaymentPage paymentPage = checkOutPage.placeOrder();

        //Step 16 and 17
        String orderPlacedMsg = paymentPage.fillFormPayment(paymentInfo);
        softAssert.assertEquals(orderPlacedMsg, "Congratulations! Your order has been confirmed!");


        softAssert.assertAll();

        //loginPage.deleteAccount();
    }

    @Test
    public void case17RemoveProducts() throws InterruptedException {

        //Preconditions
        String product1 = homePage.getAProductName();
        homePage.addProduct(product1);
        homePage.continueShopping();

        String product2 = homePage.getAProductName();
        homePage.addProduct(product2);

        //step 1
        CartPage cartPage = homePage.goToCartPage();

        //Step 2
        cartPage.removeAllProducts();

        Assert.assertEquals(cartPage.getSizeProductsSection(), 0);
        Assert.assertEquals(cartPage.getCartEmptyMsg(), "Cart is empty! Click here to buy products.");


    }

    @Test
    public void case18(){
        String womenCategory = homePage.getRandomCategory("women");
        homePage.selectCategory("women", womenCategory);

        String menCategory = homePage.getRandomCategory("men");
        homePage.selectCategory("men", menCategory);

    }

    @Test
    public void case19(){
        String brandName = homePage.getRandomBrand();
        homePage.selectBrand(brandName);

        String brandNameTwo = homePage.getRandomBrand();
        homePage.selectBrand(brandNameTwo);

    }

    @Test
    public void case20SearchProduct(){
        SoftAssert softAssert = new SoftAssert();

        //Step 1
        ProductPage productPage = homePage.goToProductPage();

        Map<String,String> product = productPage.getAProductNameAndPrice();
        product.put("quantity", "1");
        product.put("totalPrice", product.get("price"));

        //Step 2 and 3
        productPage.searchProduct(product.get("name"));
        softAssert.assertEquals(productPage.getSectionName(), "Searched Products".toUpperCase());
        softAssert.assertEquals(productPage.getProductsName(), product.get("name"));

        //Step 4 and 5
        productPage.addProduct(product.get("name"));

        //step 6
        CartPage cartPage = productPage.viewCart();

        SoftValidation.shortValidateProductOnCart(product,cartPage.getProduct(product.get("name")),softAssert);
        softAssert.assertAll();

    }

    @Test
    public void test21ProductOpinion(){

        //step 1
        ProductPage productPage = homePage.goToProductPage();

        String product = productPage.getProductsName();

        //Step 2
        productPage.viewProduct(product);
        Map<String,String> info = productPage.fakeInfo();

        //steps 3 through 6
        String msg =productPage.publishOpinion(info);

        Assert.assertEquals(msg,"Thank you for your review.");
    }

    @Test
    public void test22AddRecommendedProduct(){
        //pendiente carrusel

        homePage.goToRecommendedItems();
        homePage.viewRecommendProduct("Summer White Top");

    }

    @Test(dataProvider="getDataLogin", dataProviderClass = getData.class)
    public void test23(HashMap<String,String> input) throws IOException {

        //precondition
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.logIn(input.get("email"), input.get("password"));

        Map<String,String> product = homePage.getAProductNameAndPrice();
        //Step 1
        homePage.addProduct(product.get("name"));

        //Step 2
        homePage.continueShopping();

        //Step 3
        CartPage cartPage = homePage.goToCartPage();

        Map<String,String> prodOnCart = cartPage.getProduct(product.get("name"));

        product.put("quantity", "1");
        product.put("totalPrice", product.get("price"));

        //Validations
        SoftAssert softAssert = new SoftAssert();
        SoftValidation.shortValidateProductOnCart(product, prodOnCart, softAssert);

        //Step 4
        CheckOutPage checkOutPage = cartPage.proceedToCheckout();

        SoftValidation.validateAddressOrBillingDetail(input,checkOutPage.deliveryAddressInfo(),softAssert);
        SoftValidation.validateAddressOrBillingDetail(input,checkOutPage.billingAddressInfo(),softAssert);


        //Step 5
        Map<String,String> paymentInfo = checkOutPage.fakePaymentInfo();
        checkOutPage.fillMsgAboutOrder(paymentInfo.get("paragraph"));

        //Step 6
        PaymentPage paymentPage = checkOutPage.placeOrder();

        //Step 7 and 8
        String orderPlacedMsg =  paymentPage.fillFormPayment(paymentInfo);
        softAssert.assertEquals(orderPlacedMsg, "Congratulations! Your order has been confirmed!");

        //Step 9
        paymentPage.downloadInvoice();
        long startTime = System.currentTimeMillis();
        File file = paymentPage.waitForLatestFile(downloadPath, 30,startTime);

        softAssert.assertNotNull(file);
        String contentFile = Files.readString(file.toPath());

        String contentExpected = "Hi %s %s, Your total purchase amount is %s. Thank you"
                .formatted(
                        input.get("firstName"),
                        input.get("lastName"),
                        prodOnCart.get("totalPrice").substring(4)
                );
        softAssert.assertEquals(contentFile,contentExpected);

        //step 10

        String urlHomePage = paymentPage.continueShop();

        softAssert.assertEquals(urlHomePage, "https://automationexercise.com/");

        softAssert.assertAll();
    }

    @Test
    public void test24ScrollArrow(){

        SoftAssert softAssert = new SoftAssert();

        //Step 1
        softAssert.assertTrue(homePage.scrollDown(homePage.getFooter()), "Element not found");
        //Step 2
        softAssert.assertTrue(homePage.scrollUp(homePage.getHeader()), "Element not found");

        softAssert.assertAll();
    }


    @Test
    public void fakeTest() {

    }
}
