package Test.softvalidation;

import org.testng.asserts.SoftAssert;

import java.util.Map;

public class SoftValidation {

    public static void validateProduct(Map<String, String> expectedProduct, Map<String, String> actualProduct, SoftAssert softAssert){

        softAssert.assertTrue(expectedProduct.get("name").equalsIgnoreCase(actualProduct.get("productName")));
        softAssert.assertTrue(expectedProduct.get("category").equalsIgnoreCase(actualProduct.get("productCategory")));
        softAssert.assertTrue(expectedProduct.get("price").equalsIgnoreCase(actualProduct.get("productPrice")));
        softAssert.assertTrue(expectedProduct.get("availability").equalsIgnoreCase(actualProduct.get("productAvailable")));
        softAssert.assertTrue(expectedProduct.get("condition").equalsIgnoreCase(actualProduct.get("productCondition")));
        softAssert.assertTrue(expectedProduct.get("brand").equalsIgnoreCase(actualProduct.get("productBrand")));
    }

    public static void validateProductOnCart(Map<String, String> expectedProduct, Map<String, String> actualProduct, SoftAssert softAssert){

        softAssert.assertTrue(expectedProduct.get("name").equalsIgnoreCase(actualProduct.get("productName")),"name wrong");
        softAssert.assertTrue(expectedProduct.get("category").equalsIgnoreCase(actualProduct.get("category")),"category wrong");
        softAssert.assertTrue(expectedProduct.get("price").equalsIgnoreCase(actualProduct.get("unitPrice")), "unit price wrong");
        softAssert.assertTrue(expectedProduct.get("quantity").equalsIgnoreCase(actualProduct.get("quantity")), "quantity wrong");
        softAssert.assertTrue(expectedProduct.get("totalPrice").equalsIgnoreCase(actualProduct.get("totalPrice")), "total price wrong");
    }

    public static void shortValidateProductOnCart(Map<String, String> expectedProduct, Map<String, String> actualProduct, SoftAssert softAssert){

        softAssert.assertTrue(expectedProduct.get("name").equalsIgnoreCase(actualProduct.get("productName")),"name wrong");
        softAssert.assertTrue(expectedProduct.get("price").equalsIgnoreCase(actualProduct.get("unitPrice")), "unit price wrong");
        softAssert.assertTrue(expectedProduct.get("quantity").equalsIgnoreCase(actualProduct.get("quantity")), "quantity wrong");
        softAssert.assertTrue(expectedProduct.get("totalPrice").equalsIgnoreCase(actualProduct.get("totalPrice")), "total price wrong");
    }

    public static void validateAddressOrBillingDetail(Map<String,String>expectedProduct, Map<String,String> actualProduct,SoftAssert softAssert){

        String userFullName = expectedProduct.get("title").concat(" " + expectedProduct.get("firstName")).concat(" "+expectedProduct.get("lastName"));
        String userLocation = expectedProduct.get("city").concat(" "+expectedProduct.get("state").concat(" "+expectedProduct.get("zipCode")));

        softAssert.assertTrue(userFullName.equalsIgnoreCase(actualProduct.get("fullName")),"name wrong");
        softAssert.assertTrue(expectedProduct.get("company").equalsIgnoreCase(actualProduct.get("company")),"company wrong");
        softAssert.assertTrue(expectedProduct.get("address1").equalsIgnoreCase(actualProduct.get("address")),"address wrong");
        softAssert.assertTrue(expectedProduct.get("address2").equalsIgnoreCase(actualProduct.get("address2")),"address2 wrong");
        softAssert.assertTrue(userLocation.equalsIgnoreCase(actualProduct.get("location")),"location wrong");
        softAssert.assertTrue(expectedProduct.get("country").equalsIgnoreCase(actualProduct.get("country")),"country wrong");
        softAssert.assertTrue(expectedProduct.get("mobileNumber").equalsIgnoreCase(actualProduct.get("phone")),"phone wrong");


    }
}
