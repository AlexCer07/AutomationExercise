package Test.softvalidation;

import org.testng.asserts.SoftAssert;

import java.util.Map;

public class SoftValidation {

    public static void validateProduct(Map<String, String> expectedProduct,
                                       Map<String, String> actualProduct, SoftAssert softAssert){

        softAssert.assertTrue(expectedProduct.get("name").equalsIgnoreCase(actualProduct.get("productName"))
                ,"ProductName wrong, don't match: " + expectedProduct.get("name") + " - " +
                        actualProduct.get("productName"));

        softAssert.assertTrue(expectedProduct.get("category")
                .equalsIgnoreCase(actualProduct.get("productCategory"))
                ,"Category wrong, don't wrong: " + expectedProduct.get("category") + " - " +
                        actualProduct.get("productCategory"));

        softAssert.assertTrue(expectedProduct.get("price").equalsIgnoreCase(actualProduct.get("productPrice"))
                ,"price wrong, don't match: " + expectedProduct.get("price") + " - " +
                        actualProduct.get("productPrice"));

        softAssert.assertTrue(expectedProduct.get("availability")
                .equalsIgnoreCase(actualProduct.get("productAvailable"))
                ,"Available wrong, don't match: " + expectedProduct.get("availability") + " - " +
                        actualProduct.get("productAvailable"));

        softAssert.assertTrue(expectedProduct.get("condition")
                .equalsIgnoreCase(actualProduct.get("productCondition"))
                ,"ProductCondition wrong, don't match: " + expectedProduct.get("condition") + " - " +
                        actualProduct.get("productCondition"));

        softAssert.assertTrue(expectedProduct.get("brand").equalsIgnoreCase(actualProduct.get("productBrand"))
                ,"ProductBrand wrong, don't match: " + expectedProduct.get("brand") + " - " +
                        actualProduct.get("productCondition"));
    }

    public static void validateProductOnCart(Map<String, String> expectedProduct,
                                             Map<String, String> actualProduct, SoftAssert softAssert){

        softAssert.assertTrue(expectedProduct.get("name").equalsIgnoreCase(actualProduct.get("productName"))
                ,"productName wrong, don't match: " + expectedProduct.get("name") + " - " +
                        actualProduct.get("productName"));

        softAssert.assertTrue(expectedProduct.get("category").equalsIgnoreCase(actualProduct.get("category"))
                ,"category wrong, don't match: " + expectedProduct.get("category") +
                        actualProduct.get("category"));

        softAssert.assertTrue(expectedProduct.get("price").equalsIgnoreCase(actualProduct.get("unitPrice")),
                "unit price wrong, don't match: " + expectedProduct.get("price") + " - " +
                        expectedProduct.get("price"));

        softAssert.assertTrue(expectedProduct.get("quantity").equalsIgnoreCase(actualProduct.get("quantity")),
                "quantity wrong, don't match: " + expectedProduct.get("quantity") + " - " +
                        actualProduct.get("quantity"));

        softAssert.assertTrue(expectedProduct.get("totalPrice")
                .equalsIgnoreCase(actualProduct.get("totalPrice")),
                "total price wrong, don't match: " + expectedProduct.get("totalPrice") + " - " +
                        actualProduct.get("totalPrice"));
    }

    public static void shortValidateProductOnCart(Map<String, String> expectedProduct,
                                                  Map<String, String> actualProduct, SoftAssert softAssert){

        softAssert.assertTrue(expectedProduct.get("name").equalsIgnoreCase(actualProduct.get("productName")),
                "name wrong, don't match: " + expectedProduct.get("name") + " - " +
                        actualProduct.get("productName"));

        softAssert.assertTrue(expectedProduct.get("price").equalsIgnoreCase(actualProduct.get("unitPrice")),
                "unit price wrong, don't match: " + expectedProduct.get("price") + " - " +
                        actualProduct.get("unitPrice"));

        softAssert.assertTrue(expectedProduct.get("quantity").equalsIgnoreCase(actualProduct.get("quantity")),
                "quantity wrong, don't match: " + expectedProduct.get("quantity") + " - " +
                        actualProduct.get("quantity"));

        softAssert.assertTrue(expectedProduct.get("totalPrice")
                .equalsIgnoreCase(actualProduct.get("totalPrice")),
                "total price wrong, don't match: " + expectedProduct.get("totalPrice") + " - " +
                        actualProduct.get("totalPrice"));
    }

    public static void validateAddressOrBillingDetail(Map<String,String>expectedProduct,
                                                      Map<String,String> actualProduct,SoftAssert softAssert){

        String userFullName = expectedProduct.get("title")
                .concat(" " + expectedProduct.get("firstName"))
                .concat(" "+expectedProduct.get("lastName"));

        String userLocation = expectedProduct.get("city")
                .concat(" "+expectedProduct.get("state")
                        .concat(" "+expectedProduct.get("zipCode")));

        softAssert.assertTrue(userFullName.equalsIgnoreCase(actualProduct.get("fullName")),
                "name wrong, don't match: " + userFullName + " - " +
                        actualProduct.get("fullName"));

        softAssert.assertTrue(expectedProduct.get("company").equalsIgnoreCase(actualProduct.get("company")),
                "company wrong, don't match: " + expectedProduct.get("company") + " - " +
                        actualProduct.get("company"));

        softAssert.assertTrue(expectedProduct.get("address1").equalsIgnoreCase(actualProduct.get("address")),
                "address wrong, don't match: " + expectedProduct.get("address1") + " - " +
                        actualProduct.get("address"));

        softAssert.assertTrue(expectedProduct.get("address2").equalsIgnoreCase(actualProduct.get("address2")),
                "address2 wrong, don't match: " + expectedProduct.get("address2") + " - " +
                        actualProduct.get("address2"));

        softAssert.assertTrue(userLocation.equalsIgnoreCase(actualProduct.get("location")),
                "location wrong, don't match: " + userLocation + " - " +
                        actualProduct.get("location"));

        softAssert.assertTrue(expectedProduct.get("country").equalsIgnoreCase(actualProduct.get("country")),
                "country wrong, don't match: " + expectedProduct.get("country") + " - " +
                        actualProduct.get("country"));

        softAssert.assertTrue(expectedProduct.get("mobileNumber").equalsIgnoreCase(actualProduct.get("phone")),
                "phone wrong, don't match: " + expectedProduct.get("mobileNumber") + " - " +
                        actualProduct.get("phone"));
    }

}
