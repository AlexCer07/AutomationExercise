package Test;

import PagesObjects.*;
import Test.softvalidation.SoftValidation;
import TestComponents.BaseTest;
import data.getData;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class ProductsModule extends BaseTest {

    @Test(groups = {"product"}, dataProvider = "getDataProductBlueTop", dataProviderClass = getData.class)
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



    @Test(groups = {"product"})
    public void case10SearchProduct(){

        //Step 1 - Hacer clic en el botón 'Products'
        ProductPage productPage = homePage.goToProductPage();

        //Step 2 - Ingresar el nombre de un producto en el campo 'Search Product'
        String productName = productPage.getRandomProductName();

        //Step 3 -Hacer clic en el botón de búsqueda
        productPage.searchProduct(productName);

        Assert.assertTrue(productPage.isSearchedProductDisplayed(productName),
                "Searched product was not displayed: " + productName);
    }

    @Test(groups = {"product"}, dataProvider = "twoProducts", dataProviderClass = getData.class)
    public void case13AddProductsToCart(HashMap<String,String> product1,
                                        HashMap<String,String> product2) {

        //Step 1 - Hacer clic en el botón products
        ProductPage productPage = homePage.goToProductPage();

        //Step 2 - Poner el cursor sobre el primer producto
        //Step 3 - Hacer clic en el botón 'Add to cart'
        productPage.addProduct(product1.get("name"));

        //Se agregan a la map para validación de información en el cart
        product1.put("quantity", "1");
        product1.put("totalPrice", product1.get("price"));

        //Step 4 - Hacer clic en el botón 'Continue shopping'
        productPage.continueShopping();

        //Step 5 - Poner el cursor sobre el segundo producto
        //Step 6 - Hacer clic en el botón 'Add to cart'
        productPage.addProduct(product2.get("name"));

        //Se agregan a la map para validación de información en el cart
        product2.put("quantity", "1");
        product2.put("totalPrice", product2.get("price"));

        //Step 7 - Hacer clic en el botón 'view Cart'
        CartPage cartPage = productPage.viewCart();

        Map<String,String> prodOnCart1 = cartPage.getProductInCart(product1.get("name"));
        Map<String,String> prodOnCart2 = cartPage.getProductInCart(product2.get("name"));


        //Validations
        SoftAssert softAssert = new SoftAssert();
        SoftValidation.validateProductOnCart(product1, prodOnCart1, softAssert);
        SoftValidation.validateProductOnCart(product2, prodOnCart2, softAssert);
        softAssert.assertAll();

    }

    @Test(groups = {"product"}, dataProvider = "getDataProductBlueTop", dataProviderClass = getData.class)
    public void case14AddMoreOneTypeProduct(HashMap<String,String> input){
        int quantity = 4;

        //step 1 - Hacer clic en el botón 'view product' de cualquier producto
        ProductDetailPage productDetailPage = homePage.viewProduct(input.get("name"));

        //step 2 - Ingresar 4 en el campo de quantity
        //step 3 - Hacer clic en el botón 'Add to cart'
        productDetailPage.addToCart(quantity);

        Map<String,String> details = productDetailPage.detailProduct();

        //Validations
        SoftAssert softAssert = new SoftAssert();
        SoftValidation.validateProduct(input, details, softAssert);

        input.put("quantity", String.valueOf(quantity));
        input.put("totalPrice", productDetailPage.getTotalPrice(input.get("price"), quantity));

        //step 4 - Hacer clic en el botón 'view Cart'
        CartPage cartPage = productDetailPage.viewCart();
        Map<String,String> prodOnCart = cartPage.getProductInCart(input.get("name"));

        //Validations
        SoftValidation.validateProductOnCart(input, prodOnCart, softAssert);
        softAssert.assertAll();
    }

    @Test(groups = {"product"})
    public void case15MakeAnOrderAndRegisterUser() {

        Map<String,String> product = homePage.getAProductNameAndPrice();

        //Step 1 - Hacer clic en el botón 'Add to cart' de cualquier producto
        homePage.addProduct(product.get("name"));

        //step 2 - Hacer clic en el botón 'Continue shopping'
        homePage.continueShopping();

        //step 3 - Hacer clic en el botón 'Cart'
        CartPage cartPage = homePage.goToCartPage();
        Map<String,String> prodOnCart = cartPage.getProductInCart(product.get("name"));

        product.put("quantity", "1");
        product.put("totalPrice", product.get("price"));

        //Validations
        SoftAssert softAssert = new SoftAssert();
        SoftValidation.shortValidateProductOnCart(product, prodOnCart, softAssert);

        //Step 4 - Hacer clic en el botón 'Proceed To Checkout'
        cartPage.proceedToCheckout();

        //Step 5 - Hacer clic en el botón 'Register / Login'
        LoginPage loginPage = cartPage.registerOrLogin();

        //Random values
        Map<String,String> userData = loginPage.fakeInfoSignup();

        //Step 6 - Ingresar nombre y email válidos en los campos correspondiente de la sección
        //Step 7 - Hacer clic en el botón Signup
        loginPage.signUp( userData.get("userName"), userData.get("email"));

        //Step 8 - Completar la información obligatoria de la cuenta
        //Step 9 - Completar la información de dirección obligatoria
        //Step 10 - Presionar el botón Create Account
        loginPage.informationAccount(userData);

        //Step 11 - Hacer clic en el botón continuar
        String accountCreatedMsg = loginPage.accountCreated();
        softAssert.assertEquals(accountCreatedMsg, "ACCOUNT CREATED!");
        String userLoggedMsg = loginPage.userLogged();
        softAssert.assertEquals(userLoggedMsg, "Logged in as " + userData.get("userName"));


        //step 12 - Hacer clic en el botón 'Cart'
        loginPage.goToCartPage();
        prodOnCart = cartPage.getProductInCart(product.get("name"));


        //Validations
        SoftValidation.shortValidateProductOnCart(product, prodOnCart, softAssert);

        //step 13 - Hacer clic en el botón 'Proceed To Checkout'
        CheckOutPage checkOutPage = cartPage.proceedToCheckout();

        SoftValidation.validateAddressOrBillingDetail(userData,checkOutPage.deliveryAddressInfo(),softAssert);
        SoftValidation.validateAddressOrBillingDetail(userData,checkOutPage.billingAddressInfo(),softAssert);


        //Step 14 - Ingresar una descripción en el área de texto, para agregar un comentario sobre la orden
        Map<String,String> paymentInfo = checkOutPage.fakePaymentInfo();
        checkOutPage.fillMsgAboutOrder(paymentInfo.get("paragraph"));

        //Step 15 - Hacer clic en el botón 'Place order'
        PaymentPage paymentPage = checkOutPage.placeOrder();

        //Step 16 - Ingresar la información de la forma de pago(name on card, card number, cvc, expiration date)
        //Step 17 - Hacer clic en el botón 'pay an confirm'
        String orderPlacedMsg = paymentPage.fillFormPayment(paymentInfo);
        softAssert.assertEquals(orderPlacedMsg, "Congratulations! Your order has been confirmed!");

        softAssert.assertAll();

        //Elimina la cuenta para no llenar la base de cuentas aleatorias
        loginPage.deleteAccount();

    }

    @Test(groups = {"product"}, dataProvider="getDataLogin", dataProviderClass = getData.class)
    public void case16MakeAnOrderWithUserLogged (HashMap<String,String> input){

        Map<String,String> product = homePage.getAProductNameAndPrice();

        //Step 1
        homePage.addProduct(product.get("name"));

        //step 2
        homePage.continueShopping();

        //step 3
        CartPage cartPage = homePage.goToCartPage();

        Map<String,String> prodOnCart = cartPage.getProductInCart(product.get("name"));

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
        prodOnCart = cartPage.getProductInCart(product.get("name"));


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

    }

    @Test(groups = {"product"})
    public void case17RemoveProducts(){

        //Preconditions - productos agregados al carrito de compras
        homePage.addProduct(homePage.getRandomProductName());
        homePage.continueShopping();
        homePage.addProduct(homePage.getRandomProductName());

        //Step 1 - Hacer clic en le botón cart
        CartPage cartPage = homePage.viewCart();

        //Step 2 - Hacer clic en el botón X de los productos agregados
        cartPage.removeAllProducts();

        //Validations
        Assert.assertEquals(cartPage.getSizeProductsSection(), 0);
        Assert.assertEquals(cartPage.getCartEmptyMsg(), "Cart is empty! Click here to buy products.");
    }

    @Test(groups = {"product"})
    public void case18ViewCategories() {

        SoftAssert softAssert = new SoftAssert();

        // WOMEN
        String womenCategory = homePage.getRandomCategory("women");
        String womenCategoryBuild = homePage.buildCategory("women", womenCategory);

        //Step 1 - Hacer clic en la categoria Women
        //Step 2 - Hacer clic en uno de los nombres de las categorías
        homePage.selectCategory("women", womenCategory);

        //Step 3 - Hacer clic en el botón view product de uno de los productos que se muestran
        ProductDetailPage productDetailPage = homePage.viewProduct(homePage.getRandomProductName());
        homePage.waitForPageLoad();

        softAssert.assertEquals(productDetailPage.detailProduct().get("productCategory"), womenCategoryBuild);

        // MEN
        String menCategory = homePage.getRandomCategory("men");
        String menCategoryBuild = homePage.buildCategory("men", menCategory);

        //Step 4 - Hacer clic en la categoria Men
        //Step 5 - Hacer clic en uno de los nombres de las categorías
        homePage.selectCategory("men", menCategory);

        //Step 6 - Hacer clic en uno de los productos que se muestran
        productDetailPage = homePage.viewProduct(homePage.getRandomProductName());
        homePage.waitForPageLoad();

        softAssert.assertEquals(productDetailPage.detailProduct().get("productCategory"), menCategoryBuild);

        softAssert.assertAll();
    }

    @Test(groups = {"product"})
    public void case19ViewBrands(){

        SoftAssert softAssert = new SoftAssert();

        String brandName = homePage.getRandomBrand();

        //Step 1 - Hacer clic en una marca
        homePage.selectBrand(brandName);

        //Step 2 - Hacer clic en el botón view product de uno de los productos que se muestran
        ProductDetailPage productDetailPage = homePage.viewProduct(homePage.getRandomProductName());
        homePage.waitForPageLoad();

        softAssert.assertEquals(productDetailPage.detailProduct().get("productBrand"),brandName);

        String brandNameTwo = homePage.getRandomBrand();

        //Step 3 - Hacer clic en una marca diferente
        homePage.selectBrand(brandNameTwo);

        //Step 4 - Hacer clic en el botón view product de uno de los productos que se muestran
        homePage.viewProduct(homePage.getRandomProductName());

        homePage.waitForPageLoad();

        softAssert.assertEquals(productDetailPage.detailProduct().get("productBrand"),brandNameTwo);
        softAssert.assertAll();
    }

    @Test(groups = {"product"})
    public void case20SearchProductAndAddProduct() {

        SoftAssert softAssert = new SoftAssert();

        // Step 1
        ProductPage productPage =
                homePage.goToProductPage();

        Map<String, String> product =
                productPage.getAProductNameAndPrice();

        product.put("quantity", "1");
        product.put(
                "totalPrice",
                product.get("price")
        );

        // Step 2 and 3
        productPage.searchProduct(
                product.get("name")
        );

        softAssert.assertEquals(
                productPage.getSectionName(),
                "SEARCHED PRODUCTS",
                "Incorrect section name"
        );

        softAssert.assertTrue(
                productPage.isSearchedProductDisplayed(
                        product.get("name")
                ),
                "Searched product was not displayed: "
                        + product.get("name")
        );

        // Step 4 and 5
        productPage.addProduct(
                product.get("name")
        );

        // Step 6
        CartPage cartPage =
                productPage.viewCart();

        Map<String, String> cartProduct =
                cartPage.getProductInCart(
                        product.get("name")
                );

        SoftValidation.shortValidateProductOnCart(
                product,
                cartProduct,
                softAssert
        );

        softAssert.assertAll();
    }

    @Test(groups = {"product"})
    public void test21ProductOpinion(){

        //step 1
        ProductPage productPage = homePage.goToProductPage();

        String product = productPage.getRandomProductName();

        //Step 2
        productPage.viewProduct(product);
        Map<String,String> info = productPage.fakeInfo();

        //steps 3 through 6
        String msg =productPage.publishOpinion(info);

        Assert.assertEquals(msg,"Thank you for your review.");
    }

    @Test(groups = {"product"})
    public void test22AddRecommendedProduct(){

        homePage.goToRecommendedItems();

        Map<String,String> product = homePage.randomRecommendProduct();

        homePage.viewRecommendProduct(product.get("name"));
        CartPage cartPage = homePage.viewCart();

        product.put("quantity", "1");
        product.put("totalPrice", product.get("price"));

        Map<String,String> prodOnCart = cartPage.getProductInCart(product.get("name"));

        //Validations
        SoftAssert softAssert = new SoftAssert();
        SoftValidation.shortValidateProductOnCart(product, prodOnCart, softAssert);

        softAssert.assertAll();
    }

    @Test(groups = {"product"}, dataProvider="getDataLogin", dataProviderClass = getData.class)
    public void test23DownloadPurchaseInvoice(HashMap<String,String> input) throws IOException {

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

        Map<String,String> prodOnCart = cartPage.getProductInCart(product.get("name"));
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
        long startTime = System.currentTimeMillis();
        paymentPage.downloadInvoice();
        File file = paymentPage.waitForLatestFile(downloadPath, 30,startTime);

        softAssert.assertNotNull(file);
        String contentFile = Files.readString(file.toPath());
        System.out.println("contentFile = " + contentFile);

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


}
