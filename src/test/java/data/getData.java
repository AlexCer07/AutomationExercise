package data;

import TestComponents.BaseTest;
import org.testng.annotations.DataProvider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class getData extends DataReader {

    @DataProvider
    public Object[][] getDataLogin() throws IOException {
        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\data\\fieldsJson\\loginData.json");
        Object[][] testData = new Object[data.size()][1];

        for (int i = 0; i < data.size(); i++) {
            testData[i][0] = data.get(i);
        }

        return testData;
    }

    @DataProvider
    public Object[][] getDataProductBlueTop() throws IOException {
        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\data\\fieldsJson\\productBlueTop.json");
        Object[][] testData = new Object[data.size()][1];

        for (int i = 0; i < data.size(); i++) {
            testData[i][0] = data.get(i);
        }

        return testData;
    }

    @DataProvider(name = "twoProducts")
    public Object[][] getTwoProducts() throws IOException {

        List<HashMap<String, String>> product1 = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\data\\fieldsJson\\productBlueTop.json");

        List<HashMap<String, String>> product2 = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\data\\fieldsJson\\SleevesPrintedTop.json");

        List<Object[]> combinedData = new ArrayList<>();

        for (int i = 0; i < product1.size(); i++) {
            combinedData.add(new Object[]{product1.get(i), product2.get(i)});
        }

        return combinedData.toArray(new Object[0][]);
    }

}
